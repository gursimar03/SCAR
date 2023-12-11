from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from dotenv import load_dotenv
import os

db = SQLAlchemy()

def create_app():
    app = Flask(__name__)

    # Load environment variables from .env
    load_dotenv()

    # Explicitly set SQLALCHEMY_DATABASE_URI
    DATABASE_URI=mysql+mysqlconnector://root:jxd3FzpYmuF4gK2SD62PxcLEVzca26@localhost/scar
    app.config['SQLALCHEMY_DATABASE_URI'] = os.getenv('DATABASE_URI')

    # Create the SQLAlchemy object
    db.init_app(app)
    
    return app, db

