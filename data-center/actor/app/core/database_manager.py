import json

import mysql.connector


# Load database configuration from json file
def load_config(config_file):
    with open(config_file, 'r') as file:
        return json.load(file)['database']


class DatabaseManager:
    def __init__(self, config_file='db_config.json'):
        self.config = load_config(config_file)
        self.connection = None
        self.cursor = None

    # Connect to database
    def connect(self):
        try:
            self.connection = mysql.connector.connect(
                host=self.config['host'],
                user=self.config['user'],
                password=self.config['password']
            )
            self.cursor = self.connection.cursor()
            print("DB MANAGER - Connected to:", self.connection.get_server_info())

        except mysql.connector.Error as err:
            print("Error while connecting to database :", err)
            exit(1)

    def check_fire(self, fire_id):
        self.cursor.execute(f"SELECT isReal FROM FireEvent WHERE id={fire_id}")
        fire_reality = self.cursor.fetchone()
        return fire_reality

    # Close database connection
    def close(self):
        self.cursor.close()
        self.connection.close()
