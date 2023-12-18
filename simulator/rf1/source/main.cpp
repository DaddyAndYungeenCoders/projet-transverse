#include "MicroBit.h"
#include <stdio.h>
#include <string.h>
#include <cctype>
#include "aes.h"
#include "aes.c"

#define MAX_TEXT_LENGTH 20

ManagedString CODE = "CDJMS:";


MicroBit uBit;
MicroBitI2C i2c(I2C_SDA0, I2C_SCL0);
MicroBitPin P0(MICROBIT_ID_IO_P0, MICROBIT_PIN_P0, PIN_CAPABILITY_DIGITAL_OUT);
MicroBitSerial serial(USBTX, USBRX);


char encryptedText[MAX_TEXT_LENGTH];
char decryptedText[MAX_TEXT_LENGTH];

int key = 18;

void encrypt_decrypt(uint8_t message[]) {
    struct AES_ctx ctx;
    uint8_t key[] = "secret key 123";
    // iv : initialisation vector
    uint8_t iv[16] = {0xf0, 0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfa, 0xfb, 0xfc, 0xfd, 0xfe, 0xff};

    AES_init_ctx_iv(&ctx, key, iv);
    AES_CTR_xcrypt_buffer(&ctx, message, strlen((char *) message));
}

//char* decode_RF(ManagedString s){
//    return decrypt(const_cast<char*>(s.substring(sizeof(CODE), s.length()).toCharArray()), key);
//}

void send_RF(ManagedString s){
    ManagedString tosend = CODE + s;
    uBit.radio.datagram.send(tosend);
    serial.send(" send : " + tosend);

}

//void decrypt_RF(ManagedString s){
//
//
//}

void send_encrypt_RF(ManagedString s){
    serial.send(" send : " + s);
    uint8_t message[MAX_TEXT_LENGTH];
    memcpy(message, (const uint8_t*)s.toCharArray(), MAX_TEXT_LENGTH);

    encrypt_decrypt(message);
    serial.send(" encrypt : " + ManagedString((char*)message));
    //send_RF(ManagedString((char*)s));
    send_RF((char*)message);

}


bool check_cdjms(ManagedString s) {
    return s.substring(0, CODE.length()) == CODE;
}

//void onData(MicroBitEvent) {
//    // received data from microbit radio
//    ManagedString recData = uBit.radio.datagram.recv();
//    if (check_cdjms(recData)) {
//        char* decryptedText = decrypt_RF(recData);
//        serial.send(decryptedText);
//    }
//}

int main() {

    // Initialize the micro:bit runtime.
    uBit.init();
    uBit.serial.baud(115200);
    //uBit.messageBus.listen(MICROBIT_ID_RADIO, MICROBIT_RADIO_EVT_DATAGRAM, onData);

    uBit.radio.enable();
    uBit.radio.setGroup(57);


    while (1) {

        if (uBit.buttonA.isPressed()) {
            // for testing purpose
            //char tosend[128];
            //snprintf(tosend, sizeof(tosend), "bonjour");
            //uBit.display.scroll("A");

            send_encrypt_RF("Hello World !");
        }

        if (uBit.buttonB.isPressed()) {

            send_encrypt_RF("encrypt");
        }
        ManagedString toRead = serial.read(sizeof(char[128]), ASYNC);

        uBit.display.scroll(toRead);

        uBit.sleep(1000);
    }
}