from SCAR.app_factory import db

class MatchResult(db.Model):
    match_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.user_id'), nullable=False)
    weapon_id = db.Column(db.Integer, db.ForeignKey('weapon.weapon_id'), nullable=False)
    arena_id = db.Column(db.Integer, db.ForeignKey('arena.arena_id'), nullable=False)
    enemies_spotted = db.Column(db.Integer)
    kills = db.Column(db.Integer)
    score = db.Column(db.Integer)
    travelled = db.Column(db.Integer)
    match_time = db.Column(db.Integer)
    match_date = db.Column(db.Date)

    # Relationship to Arena
    arena = db.relationship('Arena', backref='match_results', lazy=True)

    # Virtual column for arena_name
    @property
    def arena_name(self):
        return self.arena.arena_name if self.arena else None
