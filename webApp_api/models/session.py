# models/session.py

from app_factory import db

class Session(db.Model):
    session_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.user_id'), nullable=False)
    session_data = db.Column(db.String(255), nullable=False)
