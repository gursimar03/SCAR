from SCAR.app_factory import db

class Leaderboard(db.Model):
    leaderboard_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.user_id'), nullable=False)
    user = db.relationship('User', backref='leaderboard_entry', lazy=True)
    # score = db.Column(db.Integer, nullable=False)