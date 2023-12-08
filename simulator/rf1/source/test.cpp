//#include "aes.h"
//#include "aes.c"
//#include "stdio.h"
//
//
//void encrypt_decrypt(uint8_t message[]) {
//    struct AES_ctx ctx;
//    uint8_t key[] = "secret key 123";
//    // iv : initialisation vector
//    uint8_t iv[16] = { 0xf0, 0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfa, 0xfb, 0xfc, 0xfd, 0xfe, 0xff };
//
//    AES_init_ctx_iv(&ctx, key, iv);
//    AES_CTR_xcrypt_buffer(&ctx, message, strlen((char*)message));
//}
//
//int main() {
//    printf("Start");
//
//    uint8_t text[] = "texte a chiffrer et dechiffrer";
//
//    // encrypt
//    encrypt_decrypt(text);
//    printf("\nENC: %s",(char*) text); // don't use this string as an input
//
//    //decrypt
//    encrypt_decrypt(text);
//    printf("\nDEC: %s",(char*) text);
//    return 0;
//}