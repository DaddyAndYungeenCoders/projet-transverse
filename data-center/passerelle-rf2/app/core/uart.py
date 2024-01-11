import os
import sys

import serial

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from core.config_utils import logger
from core.config_vars import SERIALPORT, BAUDRATE

ser = serial.Serial()


def init_uart():
    # ser = serial.Serial(SERIALPORT, BAUDRATE)
    ser.port = SERIALPORT
    ser.baudrate = BAUDRATE
    ser.bytesize = serial.EIGHTBITS  # number of bits per bytes
    ser.parity = serial.PARITY_NONE  # set parity check: no parity
    ser.stopbits = serial.STOPBITS_ONE  # number of stop bits
    ser.timeout = None  # block read
    # ser.timeout = 0             #non-block read
    # ser.timeout = 2              #timeout block read
    ser.xonxoff = False  # disable software flow control
    ser.rtscts = False  # disable hardware (RTS/CTS) flow control
    ser.dsrdtr = False  # disable hardware (DSR/DTR) flow control
    # ser.writeTimeout = 0     # timeout for write
    logger.info(f"Starting Up Serial Monitor on {SERIALPORT}...")
    try:
        ser.open()
    except serial.SerialException:
        logger.critical("Serial {} port not available".format(SERIALPORT))
        exit()
