from flask import Blueprint, jsonify, request
from app_factory import db
from flask_bcrypt import Bcrypt
from models.user import User 

user_bp = Blueprint('user_bp', __name__)

bcrypt = Bcrypt()

    
@user_bp.route('/api/register_with_google', methods=['POST'])
def google_register_user():
    data = request.json
    access_token = data.get('access_token')
    email = data.get('email')
    name = data.get('name')
    
    sql = "SELECT * FROM users WHERE email = %s"
    cursor = db.cursor()
    
    
    # Use the 'connector' here for database operations
    # Add your user registration logic here, including storing the user in the database
    return jsonify({'success': True}), 200

@user_bp.route('/api/register', methods=['POST'])
def register_user():
    data = request.json
    email = data.get('email')
    password = data.get('password')
    
    # Use the 'connector' here for database operations
    # Hash the password before storing it
    hashed_password = bcrypt.generate_password_hash(password).decode('utf-8')
    
    # Add your user registration logic here, including storing the hashed_password in the database
    return jsonify({'success': True}), 200

@user_bp.route('/api/login', methods=['POST'])
def login_user():
    data = request.json
    email = data.get('email')
    password = data.get('password')

    # Query the user by email
    user = User.query.filter_by(email=email).first()

    if user is None:
        return jsonify({'success': False, 'message': 'User not found'}), 404

    # Use the 'db' instance for database operations
    # Add your user login logic here, including comparing the hashed_pass with that in the database
    # Note: Typically, you would hash the provided password and compare it with the hashed password in the database

    # For demonstration, returning user details (you may want to exclude sensitive information)
    return jsonify({
        'success': True,
        'user_id': user.user_id,
        'email': user.email,
        'username': user.username
    }), 200

@user_bp.route('/api/test')
def test():
    # Use the 'connector' here for database operations
    return jsonify({'success': True}), 200

# Note: The verification function needs to be defined in this file or imported from where it is defined.
