void exactly(ManagedString message);
void encrypt_decrypt(uint8_t message[]);
bool check_cdjms(ManagedString s);
void send_RF(ManagedString s);
void send_encrypt_RF(ManagedString s);
ManagedString decrypt_RF(ManagedString s);
ManagedString decode_RF(ManagedString s);
void onTimeout();