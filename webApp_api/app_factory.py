from flask import Flask
from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

def create_app():
    app = Flask(__name__)
    
    # Configure your database connection
    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+mysqlconnector://root:NEWPASSWORD@localhost/scar'
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

    # Create the SQLAlchemy object
    db.init_app(app)

    return app, db
