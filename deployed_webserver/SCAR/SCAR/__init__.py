from flask import render_template , jsonify, request
from functools import wraps
from SCAR.app_factory import create_app
from SCAR.auth.user import user_bp
from SCAR.routes.arena import arena_bp
from SCAR.routes.inventory import inventory_bp
from SCAR.routes.match_result import match_result_bp
from SCAR.routes.leaderboard import leaderboard_bp
from SCAR.routes.weapon import weapon_bp
from SCAR.routes.pn_routes import pb_route
from SCAR.models.user import User

import os

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

app, db = create_app()

# Set the connector for each blueprint
app.register_blueprint(user_bp)
app.register_blueprint(leaderboard_bp)
app.register_blueprint(arena_bp)
app.register_blueprint(inventory_bp)
app.register_blueprint(match_result_bp)
app.register_blueprint(weapon_bp)
app.register_blueprint(pb_route)


def access_token_verify(access_token):
    verifier = os.environ.get('access_token')
    if access_token != verifier:
        return jsonify({'success': False}), 403

@app.route('/')
def index():
    return render_template('unAuth.html')

if __name__ == '__main__':
    # Register the blueprints after setting the connector
    app.run()
