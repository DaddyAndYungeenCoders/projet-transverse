from app.core.database_manager import DatabaseManager

db = DatabaseManager()
db.connect()

def is_fire_real(fire_event):
    return db.check_fire(fire_event["id"])
