// Coté DataCenter : recoit les données du microbit simulateur

#include "MicroBit.h"
#include <stdio.h>
#include <string.h>
#include <cctype>
#include "aes.h"
#include "aes.c"

#include "project.h"
// #include "project.c"

#define MAX_TEXT_LENGTH 32
#define MAX_RETRY 5
#define TIMEOUT 5000

MicroBit uBit;
MicroBitSerial serial(USBTX, USBRX);

ManagedString CODE = "CDJMS:";
ManagedString received_message;
int etape = 0;
int tries = 0;
Ticker timer;

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
    serial.send("sent\n\r");
}

void send_encrypt_RF(ManagedString s){
    serial.send("send : " + s + "\n\r");
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


void check_qos(ManagedString s) {
    // si on recoit "nok" => on recommence au début du protocole
    if (s == "nok") {
        etape = 0;
        serial.send("--ERROR--" + s + "--ERROR--\n\r");
    }
    
    if (etape == 0) {
        // on enregistre le message reçu, on envoie un ACK et on passe à l'étape suivante
        received_message = s;
        send_encrypt_RF("ACK" + received_message);
        // timer.attach_us(onTimeout, TIMEOUT * 1000);
        etape = 1;
    } else if (etape == 1) {
        // on vérifie que le message reçu est bien le même que celui envoyé, précédé d'un ACKBACK
        if (s == "ACKBACK" + received_message) {
            send_encrypt_RF("ACKDONE");
            serial.send("--DONE--" + received_message + "--DONE--\n\r");
        } else {
            send_encrypt_RF("nok");
        }
        // soit il y a eu un probleme (nok), soit tout s'est bien passé (ACKDONE)
        // on retourne à l'étape 0 dans les 2 cas (attente d'un message ACK)
        etape = 0;
    }
}

  

//Quand on reçoit des données en RF cryptées => on les decrypte
void onData(MicroBitEvent) {
    // PacketBuffer buf(BUF_SIZE);
    ManagedString buf = uBit.radio.datagram.recv();
    serial.send("Received data : ");
    // serial.send(buf);
    if (check_cdjms(buf)) {
        ManagedString test = decode_RF(buf);
        // serial.send("Decoded data");
        ManagedString decrypted = decrypt_RF(decode_RF(buf));
        serial.send(decrypted + "\n\r");
        check_qos(decrypted);
        serial.send("--------------\n\r");
    }
}

void onTimeout(){
    // traitement
    serial.send("Timeout\n\r");
    timer.detach();
}

int main() {
    // Initialise the micro:bit runtime.
    uBit.init();
    uBit.serial.baud(115200);
    
    uBit.radio.setGroup(57);
    uBit.messageBus.listen(MICROBIT_ID_RADIO, MICROBIT_RADIO_EVT_DATAGRAM, onData);
    uBit.radio.enable();
    
    // timer.attach_us(onTimeout, TIMEOUT * 1000);
    // serial.send("Start");


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