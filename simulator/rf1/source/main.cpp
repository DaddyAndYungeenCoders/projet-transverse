#include "MicroBit.h"
#include <stdio.h>
#include <string.h>
#include <cctype>
#include "aes.h"
#include "aes.c"
#include "projet.h"

#define MAX_TEXT_LENGTH 32
#define MAX_RETRY 5
#define TIMEOUT 5000 //en ms

ManagedString CODE = "CDJMS:";
ManagedString saved_message;
int etape = 0;
int tries = 0;
bool retry = false;

MicroBit uBit;
MicroBitSerial serial(USBTX, USBRX);
Ticker timer;
uint8_t key[] = "secret key 123";
// iv : initialisation vector
uint8_t iv[16] = {0xf0, 0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfa, 0xfb, 0xfc, 0xfd, 0xfe, 0xff};


void encrypt_decrypt(uint8_t message[]) {
    struct AES_ctx ctx;

    AES_init_ctx_iv(&ctx, key, iv);
    AES_CTR_xcrypt_buffer(&ctx, message, strlen((char*)message));
}



// Vérification que la trame est bien pour nous
bool check_cdjms(ManagedString s){
   return s.substring(0,CODE.length()) == CODE;
}

void send_RF(ManagedString s){
    ManagedString tosend = CODE + s;
    uBit.radio.datagram.send(tosend);
    //serial.send(" send : " + tosend);
}

void send_encrypt_RF(ManagedString s){
    serial.send(" send : " + s + "\r\n");
    uint8_t message[MAX_TEXT_LENGTH];
    memcpy(message, (const uint8_t*)s.toCharArray(), MAX_TEXT_LENGTH);

    encrypt_decrypt(message);
    //send_RF(ManagedString((char*)s));
    send_RF(ManagedString((char*)message));

}

// Décodage des données reçu
ManagedString decrypt_RF(ManagedString s){
    uint8_t message[MAX_TEXT_LENGTH];
    memcpy(message, (const uint8_t *)s.toCharArray(), MAX_TEXT_LENGTH);
    encrypt_decrypt(message);
    return ManagedString((char*)message);
}

ManagedString decode_RF(ManagedString s){
   return s.substring(CODE.length(), s.length());
}

void onTimeout()
{
    serial.send(" TIMEOUT " + ManagedString(tries)+ "\r\n");
    timer.detach();
    
    if (tries >= MAX_RETRY){
        serial.send("ABANDON \r\n");
        tries =0;
        return ;
    }else {
        etape = 0;
        retry = true;
        //exactly("nok");
        tries ++;
    }

}


void exactly(ManagedString message){
    serial.send("Exactly : " + etape + message + "\r\n" );
    if (message == "nok" || message == ""){
        etape = 0;
        message = saved_message;
    }
    if (etape == 0){
        send_encrypt_RF(message);
        etape = 1;
        saved_message = message;
        timer.attach_us(onTimeout, TIMEOUT * 1000);
    } else if (etape == 1 ){
        if (message == "ACK" + saved_message){
            send_encrypt_RF("ACKBACK" + saved_message);
            etape = 2;
            timer.attach_us(onTimeout, TIMEOUT * 1000);
        }else {
            etape = 0;
            send_encrypt_RF("nok");
            exactly(saved_message);
        }
    } else if (etape == 2){
        if (message == "ACKDONE"){
            etape = 0;
            //serial.send("ACKDONE");
            timer.detach();
            tries = 0;
        } else {
            etape = 0;
            send_encrypt_RF("nok");
            exactly(saved_message);
        }
    }
}

//Quand on reçoit des données en RF cryptées => on les decrypte
void onData(MicroBitEvent) {
    // PacketBuffer buf(BUF_SIZE);
    ManagedString buf = uBit.radio.datagram.recv();
    //serial.send("Received data");
    //serial.send(buf);
    if (check_cdjms(buf))
    {
        ManagedString test = decode_RF(buf);
        //serial.send("Decoded data");
        // serial.send(test);
        ManagedString decrypted = decrypt_RF(decode_RF(buf));
        serial.send("Reçu : " + decrypted + "\r\n");
        exactly(decrypted);
    }
}

int main() {

    // Initialize the micro:bit runtime.
    uBit.init();
    uBit.serial.baud(115200);
    uBit.messageBus.listen(MICROBIT_ID_RADIO, MICROBIT_RADIO_EVT_DATAGRAM, onData);

    uBit.radio.enable();
    uBit.radio.setGroup(57);
   
    //timer.attach_us(onTimeout, TIMEOUT * 1000);


    while (1) {
        if (retry){
            exactly("");
            retry = false;
        }

        if (uBit.buttonA.isPressed()) {
            exactly("Hello World !");
        }

        if (uBit.buttonB.isPressed()) {

            exactly("I love IOT");
        }
        //ManagedString toRead = serial.read(sizeof(char[128]), ASYNC);



        uBit.sleep(1000);
    }
    release_fiber();
}