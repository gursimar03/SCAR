from SCAR.app_factory import db

class Weapon(db.Model):
    weapon_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    weapon_name = db.Column(db.String(50), nullable=False)
    type = db.Column(db.String(50), nullable=False)

    # Relationships
    inventory = db.relationship('Inventory', backref='weapon', lazy=True)
    matches = db.relationship('Match', backref='weapon', lazy=True)
