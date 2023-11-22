from flask import Blueprint, jsonify, request

match_result_bp = Blueprint('match_result_bp', __name__)

connector = match_result_bp.connector

@match_result_bp.route('/api/post/match_result', methods=['POST'])
def post_match_result():
    data = request.json
    
    # if access_token_verify(data.get('access_token')) == False:
    #     return access_token_verify(data.get('access_token'))
    
    winner = data.get('winner')
    loser = data.get('loser')
    # Add your match result logic here, including storing the match result in the database
    return jsonify({'success': True}), 200


@match_result_bp.route('api/get/match_history', methods=['GET'])
def get_match_history():
    data = request.json
    user = data.get('user')
    # Add your get match history logic here, including retrieving the match history from the database
    return jsonify({'success': True}), 200

