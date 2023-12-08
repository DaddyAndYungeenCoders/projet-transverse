#include "MicroBit.h"
#include <stdio.h>
#include <string.h>
#include <cctype>
#include "aes.h"
#include "aes.c"

#define MAX_TEXT_LENGTH 20

ManagedString CODE = "DMST:";


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
    uint8_t iv[16] = { 0xf0, 0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfa, 0xfb, 0xfc, 0xfd, 0xfe, 0xff };

    AES_init_ctx_iv(&ctx, key, iv);
    AES_CTR_xcrypt_buffer(&ctx, message, strlen((char*)message));
}

//char* encrypt(char *text, int key) {
//    int textLength = strlen(text);
//
//    for (int i = 0; i < textLength; i++) {
//        text[i] = text[i] + key;
//    }
//    return text;
//}
//
//char* decrypt(char *text, int key) {
//    int textLength = strlen(text);
//    for (int i = 0; i < textLength; i++) {
//        text[i] = text[i] - key;
//    }
//    return text;
//}

//char* decode_RF(ManagedString s){
//    return decrypt(const_cast<char*>(s.substring(sizeof(CODE), s.length()).toCharArray()), key);
//}

void send_RF(ManagedString s){
//    ManagedString tosend = CODE + s;
    uBit.radio.datagram.send(s);
}

void send_encrypt_RF(ManagedString s){
//    char *tosend = const_cast<char*>(s.toCharArray());
//    char* encryptedText = encrypt(tosend, key);

    PacketBuffer buf(sizeof(s));
    buf = s;

    uint8_t *text = buf.getBytes();
    // encrypt data
    encrypt_decrypt(text);

    ManagedString encrypted((StringData * )(void * )text);
    uBit.display.scroll(encrypted);


    send_RF(encrypted);
}


//bool check_dmst(ManagedString s) {
//    return s.substring(0, CODE.length()) == CODE;
//}

//void onData(MicroBitEvent) {
//    // received data from microbit radio
//    ManagedString recData = uBit.radio.datagram.recv();
//    if (check_dmst(recData)) {
//        char* decryptedText = decode_RF(recData);
//        serial.send(decryptedText);
//    }
//}

int main() {

    // Initialize the micro:bit runtime.
    uBit.init();
    uBit.serial.baud(115200);
//    uBit.messageBus.listen(MICROBIT_ID_RADIO, MICROBIT_RADIO_EVT_DATAGRAM, onData);

    uBit.radio.enable();
    uBit.radio.setGroup(57);

    int t = 150;

    while (1) {

        if (uBit.buttonA.isPressed()) {
            // for testing purpose
            char tosend[128];
            snprintf(tosend, sizeof(tosend), "bonjour");
            send_encrypt_RF(tosend);
            t++;
        }
        ManagedString toRead = serial.read(sizeof(char[128]), ASYNC);

        // receive data from serial : LT or TL
        if (toRead == "LT" || toRead == "TL")
        {
            // send it encrypted to distant microbit
            send_encrypt_RF(toRead);
        }
        uBit.sleep(1000);
    }
}