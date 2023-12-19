// Coté DataCenter : recoit les données du microbit simulateur

#include "MicroBit.h"
#include <stdio.h>
#include <string.h>
#include <cctype>
#include "aes.h"
#include "aes.c"

#define MAX_TEXT_LENGTH 32

MicroBit uBit;
MicroBitSerial serial(USBTX, USBRX);

ManagedString CODE = "CDJMS:";
int BUF_SIZE = 16;


uint8_t key[] = "secret key 123";
// iv : initialisation vector
uint8_t iv[16] = {0xf0, 0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfa, 0xfb, 0xfc, 0xfd, 0xfe, 0xff};
   
void encrypt_decrypt(uint8_t message[]) {
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, key, iv);
    AES_CTR_xcrypt_buffer(&ctx, message, strlen((char *) message));
}


// Vérification que la trame est bien pour nous
bool check_cdjms(ManagedString s){
   return s.substring(0,CODE.length()) == CODE;
}

//Envoie des données en RF
void send_RF(ManagedString s){
    ManagedString tosend = CODE + s;
    uBit.radio.datagram.send(tosend);
    serial.send("sent");
}

void send_encrypt_RF(ManagedString s){
    // serial.send(" send : " + s);
    uint8_t message[MAX_TEXT_LENGTH];
    memcpy(message, (const uint8_t*)s.toCharArray(), MAX_TEXT_LENGTH);

    encrypt_decrypt(message);
    // serial.send(" encrypt : " + ManagedString((char*)message));
    send_RF(ManagedString((char*)message));

}

// Décodage des données reçu
ManagedString decrypt_RF(ManagedString s){
    uint8_t message[MAX_TEXT_LENGTH];
    memcpy(message, (const uint8_t *)s.toCharArray(), MAX_TEXT_LENGTH);

    encrypt_decrypt(message);
    return (char*)message;
}

ManagedString decode_RF(ManagedString s){
   return s.substring(CODE.length(), s.length());
}


//Quand on reçoit des données en RF cryptées => on les decrypte
void onData(MicroBitEvent) {
    // PacketBuffer buf(BUF_SIZE);
    ManagedString buf = uBit.radio.datagram.recv();
    serial.send("Received data");
    // serial.send(buf);
    if (check_cdjms(buf))
    {
        ManagedString test = decode_RF(buf);
        // serial.send("Decoded data");
        ManagedString decrypted = decrypt_RF(decode_RF(buf));
        serial.send(decrypted);
    }
}


int main() {
    // Initialise the micro:bit runtime.
    uBit.init();
    uBit.serial.baud(115200);
    
    uBit.radio.setGroup(57);
    uBit.messageBus.listen(MICROBIT_ID_RADIO, MICROBIT_RADIO_EVT_DATAGRAM, onData);
    uBit.radio.enable();
    

    while (1) {
        if (uBit.buttonA.isPressed()) {
            // for testing purpose
            // serial.send("btnA");
            send_encrypt_RF("Hello world!");
        }
        if (uBit.buttonB.isPressed()) {
            // for testing purpose
            // serial.send("btnB");
            send_encrypt_RF("Hello from000000000000000");
        }
        uBit.sleep(1000);
    }

    release_fiber();
}