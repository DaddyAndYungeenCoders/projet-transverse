import os
from http import client

from dotenv import load_dotenv
from influxdb_client import InfluxDBClient, Point, WritePrecision
from influxdb_client.client.write_api import SYNCHRONOUS

load_dotenv()


class DatabaseManager:
    def __init__(self):
        self.client = InfluxDBClient.from_env_properties()
        self.write_api = self.client.write_api(write_options=SYNCHRONOUS)
        self.query_api = self.client.query_api()
        self.bucket = os.getenv("INFLUXDB_V2_BUCKET")
        self.org = os.getenv("INFLUXDB_V2_ORG")

    def insert_data(self, data, tag):
        self.client.write_points(data)
        p = Point("test").tag(tag["label"], tag["value"]).field(data["field"], data["value"])
        self.write_api.write(bucket=self.bucket, record=p)

