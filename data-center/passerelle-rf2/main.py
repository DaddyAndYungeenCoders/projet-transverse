# This python program is designed to receive data from uBit through UART.
# It sends these data through MQTT to the Mosquitto Broker
# And it posts through HTTP to the WebServer in the Data Center

from app.service.serial_service import serial, init_uart, process_serial_data


def main():
    # Init UART connection to microbit
    init_uart()

    try:
        # get data from serial
        process_serial_data()

    except (KeyboardInterrupt, SystemExit):
        serial.close()
        exit()


if __name__ == "__main__":
    main()
