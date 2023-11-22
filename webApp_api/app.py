from flask import Flask, request, jsonify
from flask_bcrypt import Bcrypt
import json
import mysql.connector
import os

connector = mysql.connector.connect(
    host=os.environ.get('DB_HOST'),
    user = os.environ.get('DB_USER'),
    password = os.environ.get('DB_PASS'),
    database = os.environ.get('DB_NAME')
)


app = Flask(__name__)
bcrypt = Bcrypt(app)


@app.route('/api/register_with_google', methods=['POST'])
def google_register_user():
    data = request.json
    email = data.get('email')
    name = data.get('name')
    # Add your user registration logic here, including storing the user in the database
    return jsonify({'success': True}), 200

@app.route('/api/register', methods=['POST'])
def register_user():
    data = request.json
    email = data.get('email')
    password = data.get('password')

    # Hash the password before storing it
    hashed_password = bcrypt.generate_password_hash(password).decode('utf-8')

    # Add your user registration logic here, including storing the hashed_password in the database

    return jsonify({'success': True}), 200

@app.route('/api/login', methods=['POST'])
def login_user():
    data = request.json
    email = data.get('email')
    hashed_pass = data.get('password')
    
    # Add your user login logic here, including comparing the hashed_pass with that in the database
    
    return jsonify({'success': True}), 200

@app.route('/api/leaderboard', methods=['GET'])
def get_leaderboard():
    # Add your leaderboard logic here, including retrieving the leaderboard from the database
    return jsonify({'success': True}), 200

@app.route('/api/post/match_result', methods=['POST'])
def post_match_result():
    data = request.json
    winner = data.get('winner')
    loser = data.get('loser')
    # Add your match result logic here, including storing the match result in the database
    return jsonify({'success': True}), 200

@app.route('api/get/weapons/', methods=['GET'])
def get_weapons():
    # Add your get weapons logic here, including retrieving the weapons from the database
    return jsonify({'success': True}), 200

@app.route('api/post/weapon', methods=['POST'])
def post_weapon():
    data = request.json
    weapon = data.get('weapon')
    # Add your post weapon logic here, including storing the weapon in the database
    return jsonify({'success': True}), 200

@app.route('api/get/match_history', methods=['GET'])
def get_match_history():
    data = request.json
    user = data.get('user')
    # Add your get match history logic here, including retrieving the match history from the database
    return jsonify({'success': True}), 200



if __name__ == '__main__':
    app.run(debug=True)
