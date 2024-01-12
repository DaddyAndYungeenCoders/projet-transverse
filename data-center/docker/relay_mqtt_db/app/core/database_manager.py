import os
import sys

from dotenv import load_dotenv
from influxdb_client import InfluxDBClient, WriteOptions
from influxdb_client.client.write_api import SYNCHRONOUS

sys.path.append(os.path.dirname(os.path.abspath(__file__)))
from core.config_utils import logger

load_dotenv()


class DatabaseManager:
    def __init__(self):
        self.client = InfluxDBClient(url=os.getenv("INFLUXDB_V2_URL"), token=os.getenv("INFLUXDB_V2_TOKEN"))
        self.write_options = WriteOptions(batch_size=1, flush_interval=1000, write_type=SYNCHRONOUS)
        self.write_api = self.client.write_api(write_options=self.write_options)
        self.query_api = self.client.query_api()
        self.bucket = os.getenv("INFLUXDB_V2_BUCKET")
        self.org = os.getenv("INFLUXDB_V2_ORG")

    def insert_data(self, json_data):
        try:
            self.write_api.write(self.bucket, self.org, json_data)
            logger.info(f"Successfully inserted data : {json_data}")
        except Exception as e:
            logger.error("Error writing to InfluxDB : ", e)

    def print_info(self):
        logger.info(self.bucket)
        logger.info(self.org)

    def get_data(self, measurement):
        query = 'from(bucket:"{}") |> range(start: -1h)' \
                '|> filter(fn:(r) => r._measurement == "{}")' \
            .format(self.bucket, measurement)
        return self.query_api.query(org=self.org, query=query)

# for item in json_data:
#     json_body = [
#         {
#             "measurement": "your_measurement",
#             "tags": {
#                 "tag1": "value1",
#                 "tag2": "value2"
#             },
#             "time": item['timestamp'],  # Adjust this based on your JSON structure
#             "fields": {
#                 "field1": item['field1'],
#                 "field2": item['field2'],
#                 # Add more fields as needed
#             }
#         }
#     ]
