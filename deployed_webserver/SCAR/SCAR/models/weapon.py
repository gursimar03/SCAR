from SCAR.app_factory import db

class Weapon(db.Model):
    weapon_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.user_id'), nullable=False)
    weapon_name = db.Column(db.String(50), nullable=False)
    type = db.Column(db.String(50), nullable=False)
    image = db.Column(db.String(250), nullable=False)
    

    # Relationships
    inventory = db.relationship('Inventory', backref='weapon', lazy=True)
    match_results = db.relationship('MatchResult', backref='weapon', lazy=True)