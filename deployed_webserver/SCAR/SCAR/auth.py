def require_auth(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        auth_header = request.headers.get('Authorization')
        if not auth_header:
            return jsonify({'success': False, 'error': 'Missing Authorization header'}), 401
        
        token = auth_header.split(' ')[1]
        user = User.query.filter_by(token=token).first()
        if not user or not token:
            return jsonify({'success': False, 'error': 'Invalid Authorization'}), 403
        return f(*args, **kwargs)
    return decorated