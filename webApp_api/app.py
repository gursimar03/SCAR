from flask import render_template , jsonify
from app_factory import create_app
from auth.user import user_bp
from routes import leaderboard_bp, arena_bp, inventory_bp, match_result_bp, weapon_bp
import os 

app, db = create_app()

# Set the connector for each blueprint
app.register_blueprint(user_bp)
app.register_blueprint(leaderboard_bp)
app.register_blueprint(arena_bp)
app.register_blueprint(inventory_bp)
app.register_blueprint(match_result_bp)
app.register_blueprint(weapon_bp)

def access_token_verify(access_token):
    verifier = os.environ.get('access_token')
    if access_token != verifier:
        return jsonify({'success': False}), 403

@app.route('/')
def index():
    return render_template('unAuth.html')

if __name__ == '__main__':
    # Register the blueprints after setting the connector
    app.run(debug=True)
