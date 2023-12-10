# models/user.py
from app_factory import db
from models.session import Session
from models.leaderboard import Leaderboard
from models.match import Match
from models.inventory import Inventory

class User(db.Model):
    user_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    email_address = db.Column(db.String(255), nullable=False)
    password = db.Column(db.String(255), nullable=False)
    username = db.Column(db.String(50), nullable=False)
    age = db.Column(db.Integer)
    access_level = db.Column(db.Integer, default=0)

    # Relationships
    sessions = db.relationship('Session', backref='user', lazy=True)
    leaderboard = db.relationship('Leaderboard', backref='user', lazy=True)
    inventory = db.relationship('Inventory', backref='user', lazy=True)
    matches = db.relationship('Matcht', backref='user', lazy=True)
