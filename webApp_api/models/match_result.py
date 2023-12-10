from app_factory import db

class Match(db.Model):
    match_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.user_id'), nullable=False)
    weapon_id = db.Column(db.Integer, db.ForeignKey('weapon.weapon_id'), nullable=False)
    arena_id = db.Column(db.Integer, db.ForeignKey('arena.arena_id'), nullable=False)
    enemies_spotted = db.Column(db.Integer)
    kills = db.Column(db.Integer)
    deaths = db.Column(db.Integer)
    score = db.Column(db.Integer),
    status= db.Column(db.String(20))
