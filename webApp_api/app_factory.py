from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from dotenv import load_dotenv
import os

db = SQLAlchemy()

def create_app():
    app = Flask(__name__)

    # Load environment variables from .env
    load_dotenv()
    link = os.getenv('DATABASE_URI')
    print(link)
    # Configure your database connection using environment variables
    app.config['SQLALCHEMY_DATABASE_URI'] = os.getenv('DATABASE_URI')
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

    # Create the SQLAlchemy object
    db.init_app(app)

    return app, db
