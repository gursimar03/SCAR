from flask import Blueprint, jsonify, request


leaderboard_bp = Blueprint('leaderboard_bp', __name__)

connector = leaderboard_bp.connector

@leaderboard_bp.route('/api/leaderboard', methods=['GET'])
def get_leaderboard():
    # Add your leaderboard logic here, including retrieving the leaderboard from the database
    data = request.json
    
    # if access_token_verify(data.get('access_token')) == False:
    #     return access_token_verify(data.get('access_token'))
    
    cursor = connector.cursor()
    query = "SELECT * FROM leaderboard ORDER BY elo DESC"
    
    return jsonify({'success': True}), 200

