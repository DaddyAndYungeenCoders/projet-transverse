#include "MicroBit.h"
#include <stdio.h>
#include <string.h>
#include <cctype>
#include "aes.h"
#include "aes.c"

MicroBit uBit;
MicroBitI2C i2c(I2C_SDA0, I2C_SCL0);
MicroBitPin P0(MICROBIT_ID_IO_P0, MICROBIT_PIN_P0, PIN_CAPABILITY_DIGITAL_OUT);

ManagedString CODE = "DMST:";
int key = 18;

char ORDER[] = "LT";
int BUF_SIZE = 16;

#define MAX_TEXT_LENGTH 20

char encryptedText[MAX_TEXT_LENGTH];
char decryptedText[MAX_TEXT_LENGTH];

//ManagedString uint_to_string(uint8_t str[]) {
//    return ManagedString((char*) str);
//}
//
//uint8_t* string_to_uint(const char* str) {
//    size_t length = strlen(str);
//    uint8_t* result = (uint8_t*)malloc(length * sizeof(uint8_t));
//
//    if (result == NULL) {
//        // Gestion de l'échec d'allocation mémoire
//        perror("Erreur d'allocation mémoire");
//        exit(EXIT_FAILURE);
//    }
//
//    for (size_t i = 0; i < length; ++i) {
//        result[i] = (uint8_t)str[i];
//    }
//
//    return result;
//}

void encrypt_decrypt(uint8_t message[]) {
    struct AES_ctx ctx;
    uint8_t key[] = "secret key 123";
    // iv : initialisation vector
    uint8_t iv[16] = {0xf0, 0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfa, 0xfb, 0xfc, 0xfd, 0xfe, 0xff};

    AES_init_ctx_iv(&ctx, key, iv);
    AES_CTR_xcrypt_buffer(&ctx, message, strlen((char *) message));
}


// Vérification que la trame est bien pour nous
//bool check_dsmt(ManagedString s){
//    return s.substring(0,CODE.length()) == CODE;
//}
//
////Envoie des données en RF
//void send_RF(ManagedString s){
//    ManagedString tosend = CODE + s;
//    uBit.radio.datagram.send(tosend);
//}

//Encryption des données
//void send_encrypt_RF(ManagedString s){
//    char* encryptedText = encrypt(const_cast<char*>(s.toCharArray()), key);
//    send_RF(encryptedText);
//}

//Décodage des données reçu
//ManagedString decode_RF(ManagedString s){
//    return decrypt(const_cast<char*>(s.substring(CODE.length(), s.length()-1).toCharArray()), key);
//}

//Quand on reçoit des données en RF cryptées => on les decrypte
void onData(MicroBitEvent) {

    PacketBuffer buf(BUF_SIZE);
    buf = uBit.radio.datagram.recv();

    uint8_t *text = buf.getBytes();
    // decrypt
    encrypt_decrypt(text);

    ManagedString decrypted((StringData * )(void * )text);
    uBit.display.scroll(decrypted);

//    if (check_dsmt(s)){
//        strncpy(ORDER, decode_RF(s).toCharArray(), sizeof(ORDER)-1); //On modifie l'ordre
//    }

}

// Lecture du serial pour transférer les données reçues du simulateur
//void data(bme280 *bme,tsl256x *tsl, ManagedString order ){
//
//    uint32_t pressure = 0;
//    int32_t temp = 0;
//    uint16_t humidite = 0;
//
//    uint16_t comb =0;
//    uint16_t ir = 0;
//    uint32_t lux = 0;
//
//    bme->sensor_read(&pressure, &temp, &humidite);
//    short tmp = bme->compensate_temperature(temp);
//    short pres = bme->compensate_pressure(pressure)/100;
//    short hum = bme->compensate_humidity(humidite);
//
//    tsl->sensor_read(&comb, &ir, &lux);
//
//    //Création de la trame RF
//    ManagedString d = "T:"+ ManagedString(tmp/100) + "." + (tmp > 0 ? ManagedString(tmp%100): ManagedString((-tmp)%100))+";L:"+ ManagedString((int)lux) +"|";
//
//    short datalignestart = 0;
//
//    //Affichage des données celon l'ordre
//    for (int i=0; i<order.length(); i++){
//        screen.display_line(i + datalignestart,0, "              ");
//        switch(order.toCharArray()[i]) {
//        case 'T':
//            screen.display_line(i + datalignestart,0, tempdys.toCharArray());
//            break;
//        case 'H':
//            screen.display_line(i + datalignestart,0, humdys.toCharArray());
//            break;
//        case 'P':
//            screen.display_line(i + datalignestart,0, presdys.toCharArray());
//            break;
//        case 'L':
//            screen.display_line(i + datalignestart,0, luxdys.toCharArray());
//            break;
//        default:
//            break;
//        }
//    }
//
//    //On envoit les données
//    send_encrypt_RF(d);
//    screen.update_screen();
//}



int main() {
    // Initialise the micro:bit runtime.
    uBit.init();

    uBit.radio.setGroup(57);
    uBit.messageBus.listen(MICROBIT_ID_RADIO, MICROBIT_RADIO_EVT_DATAGRAM, onData);
    uBit.radio.enable();

//    screen.display_line(0,0,"Hello");
//    screen.update_screen();



    while (1) {
//        data(&bme, &tsl, ORDER);
        uBit.sleep(1000);
    }

    release_fiber();

}

