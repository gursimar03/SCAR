from flask import Blueprint, jsonify, request
from SCAR.app_factory import db
from SCAR.models.user import User
from SCAR.models.session import Session
from flask_bcrypt import Bcrypt
import os

bcrypt = Bcrypt()

user_bp = Blueprint('user_bp', __name__)

@user_bp.route('/api/register_with_google', methods=['POST'])
def google_register_user():
    data = request.json
    access_token = data.get('access_token')
    email = data.get('email')
    name = data.get('name')

    existing_user = User.query.filter_by(email_address=email).first()
    if existing_user:
        return jsonify({'success': False, 'message': 'User already registered'}), 400

    # Add your Google registration logic here, including storing the user in the database
    # ...

    return jsonify({'success': True, 'message': 'Registration successful'}), 200

@user_bp.route('/api/register', methods=['POST'])
def register_user():
    auth_header = request.headers.get('Admin-Key')
    admin_token = os.environ.get('admin_token')
    
    if not auth_header or auth_header != admin_token:
        return jsonify({'success': False, 'message': 'Invalid Authorization header','auth_header': auth_header, 'admin_token': admin_token}), 401

    # Use request.form to get form data
    email = request.form.get('email')
    password = request.form.get('password')
    username = request.form.get('username')

    if not email or not password or not username:
        return jsonify({'success': False, 'message': 'Missing required data'}), 400

    existing_user = User.query.filter_by(email_address=email).first()
    if existing_user:
        return jsonify({'success': False, 'message': 'User already registered'}), 400

    hashed_password = bcrypt.generate_password_hash(password).decode('utf-8')

    new_user = User(email_address=email, password=hashed_password, username=username)
    db.session.add(new_user)
    db.session.commit()

    return jsonify({'success': True, 'message': 'Registration successful'}), 200


@user_bp.route('/api/login', methods=['POST'])
def login_user():
    data = request.json
    email_address = data.get('email')
    password = data.get('password')

    if not email_address or not password:
        return jsonify({'success': False, 'message': 'Missing required data'}), 400

    user = User.query.filter_by(email_address=email_address).first()

    if user is None or not bcrypt.check_password_hash(user.password, password):
        return jsonify({'success': False, 'message': 'Invalid email or password'}), 401

    # Add your user login logic here, including creating and storing a session
    # ...

    return jsonify({
        'success': True,
        'user_id': user.user_id,
        'email': user.email_address,
        'username': user.username
    }), 200

@user_bp.route('/api/logout', methods=['POST'])
def logout_user():
    data = request.json
    user_id = data.get('user_id')

    # Add your logout logic here, including removing the session from the database
    # ...

    return jsonify({'success': True, 'message': 'Logout successful'}), 200

@user_bp.route('/api/update_user', methods=['PUT'])
def update_user():
    data = request.json
    user_id = data.get('user_id')
    new_email = data.get('new_email')
    new_username = data.get('new_username')
    new_age = data.get('new_age')

    if not user_id or not (new_email or new_username or new_age):
        return jsonify({'success': False, 'message': 'Missing required data'}), 400

    user = User.query.get(user_id)
    if user is None:
        return jsonify({'success': False, 'message': 'User not found'}), 404

    if new_email:
        user.email_address = new_email
    if new_username:
        user.username = new_username
    if new_age:
        user.age = new_age

    db.session.commit()

    return jsonify({'success': True, 'message': 'User information updated successfully'}), 200

@user_bp.route('/api/change_password', methods=['PUT'])
def change_password():
    data = request.json
    user_id = data.get('user_id')
    current_password = data.get('current_password')
    new_password = data.get('new_password')

    if not user_id or not current_password or not new_password:
        return jsonify({'success': False, 'message': 'Missing required data'}), 400

    user = User.query.get(user_id)
    if user is None or not bcrypt.check_password_hash(user.password, current_password):
        return jsonify({'success': False, 'message': 'Invalid user or password'}), 401

    hashed_new_password = bcrypt.generate_password_hash(new_password).decode('utf-8')
    user.password = hashed_new_password

    db.session.commit()

    return jsonify({'success': True, 'message': 'Password changed successfully'}), 200

# Additional route for session handling
@user_bp.route('/api/update_session', methods=['PUT'])
def update_session():
    data = request.json
    user_id = data.get('user_id')
    new_session_data = data.get('new_session_data')

    if not user_id or not new_session_data:
        return jsonify({'success': False, 'message': 'Missing required data'}), 400

    session = Session.query.filter_by(user_id=user_id).first()
    if session:
        session.session_data = new_session_data
        db.session.commit()
        return jsonify({'success': True, 'message': 'Session updated successfully'}), 200
    else:
        return jsonify({'success': False, 'message': 'Session not found'}), 404
