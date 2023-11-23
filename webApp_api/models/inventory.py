from app_factory import db

class Inventory(db.Model):
    user_id = db.Column(db.Integer, db.ForeignKey('user.user_id'), primary_key=True, nullable=False)
    weapon_id = db.Column(db.Integer, db.ForeignKey('weapon.weapon_id'), primary_key=True, nullable=False)
