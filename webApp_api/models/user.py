# models/user.py

from app_factory import db

class User(db.Model):
    user_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    email_address = db.Column(db.String(255), nullable=False)
    password = db.Column(db.String(255), nullable=False)
    username = db.Column(db.String(50), nullable=False)
    age = db.Column(db.Integer)
    access_level = db.Column(db.Integer, default=0)

    # Relationships
    sessions = db.relationship('models.session.Session', backref='user', lazy=True)
    leaderboard = db.relationship('models.leaderboard.Leaderboard', backref='user', lazy=True)
    inventory = db.relationship('models.inventory.Inventory', backref='user', lazy=True)
    match_results = db.relationship('models.match_result.MatchResult', backref='user', lazy=True)
