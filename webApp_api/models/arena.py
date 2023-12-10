from app_factory import db

class Arena(db.Model):
    arena_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    arena_name = db.Column(db.String(50), nullable=False)

    # Relationships
    matches = db.relationship('Match', backref='arena', lazy=True)
