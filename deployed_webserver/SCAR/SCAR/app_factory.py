from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from dotenv import load_dotenv
from SCAR.pn_functions import setup_pubnub
import os

db = SQLAlchemy()

def create_app():
    app = Flask(__name__,static_folder='static', template_folder='templates')
    setup_pubnub()
    # Load environment variables from .env
    load_dotenv()

    # Explicitly set SQLALCHEMY_DATABASE_URI
    print(os.getenv('DATABASE_URI'))
    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+mysqlconnector://root:jxd3FzpYmuF4gK2SD62PxcLEVzca26@localhost/scar'
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

    # Create the SQLAlchemy object
    db.init_app(app)

    return app, db


create_app()
