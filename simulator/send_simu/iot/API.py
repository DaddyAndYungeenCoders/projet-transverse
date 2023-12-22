from typing import Union

from fastapi import FastAPI
import serial
import multiprocessing as mp
from multiprocessing import Process 

from pydantic import BaseModel

SERIALPORT = "/dev/ttyACM0"
BAUDRATE = 115200
ser = serial.Serial()



class Sensor(BaseModel):
    id: int
    intensity: float


def read_UART():
    while ser.isOpen():
        # time.sleep(100)
        if (ser.inWaiting() > 0):  # if incoming bytes are waiting
            data_str = ser.read(ser.inWaiting())
            data_new = str(data_str, 'UTF-8')
            print("receive : " + data_new)

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
    msg = msg.replace(" ", "")
    ser.write(msg.encode())
    print("Message <" + msg + "> sent to micro-controller.")




initUART()
reader = Process(target=read_UART, args=())
reader.start()

app = FastAPI()



@app.post("/")
def read_root():
    sendUARTMessage("test")
    return {"Hello": "World"}

@app.post("/new")
def new(sensor: Sensor):
    sendUARTMessage("id:" + str(sensor.id) +",in:" + str(sensor.intensity))
    return {"id" : sensor.id, "intensity": sensor.intensity}

""" @app.post("/new")
def new(body: dict):
    print(str(body))
    sendUARTMessage(str(body) + "|")
    return body """

""" reader.join() """