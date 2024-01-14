from functools import wraps
from flask import jsonify, request
from SCAR.models.user import User

def require_auth(f):
	@wraps(f)
	def decorated(*args, **kwargs):
		token = request.headers.get('User-Key')
		if not token:
			return jsonify({'success': False, 'error': 'Missing Authorization'}), 401
		
		user = User.query.filter_by(token=token).first()
		if not user:
			return jsonify({'success': False, 'error': 'Invalid Authorization'}), 403
		return f(*args, **kwargs)
	return decorated