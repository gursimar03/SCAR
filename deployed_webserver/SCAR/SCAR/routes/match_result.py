from flask import Blueprint, jsonify, request
from SCAR.app_factory import db
from SCAR.models import match_result

match_result_bp = Blueprint('match_result_bp', __name__)

@match_result_bp.route('/api/post/match_result', methods=['POST'])
def post_match_result():
    try:
        data = request.json

        # Uncomment the following lines when you have an access token verification function
        # if not access_token_verify(data.get('access_token')):
        #     return jsonify({'success': False, 'error': 'Invalid access token'}), 401

        winner = data.get('winner')
        loser = data.get('loser')

        # Add your match result logic here, including storing the match result in the database
        new_match_result = MatchResult(
            user_id=data.get('user_id'),
            weapon_id=data.get('weapon_id'),
            arena_id=data.get('arena_id'),
            enemies_spotted=data.get('enemies_spotted'),
            kills=data.get('kills'),
            deaths=data.get('deaths'),
            score=data.get('score')
        )

        db.session.add(new_match_result)
        db.session.commit()

        return jsonify({'success': True}), 200

    except Exception as e:
        db.session.rollback()
        return jsonify({'success': False, 'error': str(e)}), 500

@match_result_bp.route('/api/get/match_history', methods=['GET'])
def get_match_history():
    try:
        data = request.json
        user_id = data.get('user_id')

        # Add your get match history logic here, including retrieving the match history from the database
        match_history = MatchResult.query.filter_by(user_id=user_id).all()

        # Convert the match history to a format you want to return
        data = [{'match_id': match.match_id, 'user_id': match.user_id, 'weapon_id': match.weapon_id,
                 'arena_id': match.arena_id, 'enemies_spotted': match.enemies_spotted,
                 'kills': match.kills, 'deaths': match.deaths, 'score': match.score}
                for match in match_history]

        return jsonify({'success': True, 'data': data}), 200

    except Exception as e:
        return jsonify({'success': False, 'error': str(e)}), 500
