# models/user.py
from SCAR.app_factory import db
from SCAR.models.session import Session
from SCAR.models.leaderboard import Leaderboard
from SCAR.models.match_result import MatchResult
from SCAR.models.inventory import Inventory

class User(db.Model):
    user_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    email_address = db.Column(db.String(255), nullable=False)
    password = db.Column(db.String(255), nullable=False)
    username = db.Column(db.String(50), nullable=False)
    age = db.Column(db.Integer)
    access_level = db.Column(db.Integer, default=0)
    score = db.Column(db.Integer, default=0)
    token = db.Column(db.String(255), nullable=False)

    # Relationships
    sessions = db.relationship('Session', backref='user', lazy=True)
    #leaderboard = db.relationship('Leaderboard', backref='user', lazy=True)
    inventory = db.relationship('Inventory', backref='user', lazy=True)
    match_results = db.relationship('MatchResult', backref='user', lazy=True)
