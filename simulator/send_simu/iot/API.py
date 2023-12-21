from typing import Union

from fastapi import FastAPI
import serial

from pydantic import BaseModel

SERIALPORT = "/dev/ttyACM0"
BAUDRATE = 115200
ser = serial.Serial()



class Sensor(BaseModel):
    id: str
    intensity: float

def initUART():
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
    print('Starting Up Serial Monitor')
    try:
        ser.open()
    except serial.SerialException:
        print("Serial {} port not available".format(SERIALPORT))
        exit()

def sendUARTMessage(msg):
    ser.write(bytes(msg, 'UTF-8'))
    print("Message <" + msg + "> sent to micro-controller.")




initUART()

app = FastAPI()



@app.post("/")
def read_root():
    sendUARTMessage("test")
    return {"Hello": "World"}

@app.post("/new")
def new(sensor: Sensor):
    sendUARTMessage("(" + sensor.id +"," + str(sensor.intensity)+")")
    return{"code": "200"}