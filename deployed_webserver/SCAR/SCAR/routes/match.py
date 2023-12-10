from flask import Blueprint, jsonify, request
from SCAR.app_factory import db
from deployed_webserver.SCAR.SCAR.models import match

match_bp = Blueprint('match_bp', __name__)

@match_bp.route('/api/post/match', methods=['POST'])
def post_match():
    try:
        data = request.json

        # Uncomment the following lines when you have an access token verification function
        # if not access_token_verify(data.get('access_token')):
        #     return jsonify({'success': False, 'error': 'Invalid access token'}), 401

        winner = data.get('winner')
        loser = data.get('loser')

        # Add your match result logic here, including storing the match result in the database
        new_match = Match(
            user_id=data.get('user_id'),
            weapon_id=data.get('weapon_id'),
            arena_id=data.get('arena_id'),
            enemies_spotted=data.get('enemies_spotted'),
            kills=data.get('kills'),
            deaths=data.get('deaths'),
            score=data.get('score'),
            status=data.get('status')
        )

        db.session.add(new_match)
        db.session.commit()

        return jsonify({'success': True}), 200

    except Exception as e:
        db.session.rollback()
        return jsonify({'success': False, 'error': str(e)}), 500
    
# Route to set the status of a match to completed
@match_bp.route('/api/post/match_status', methods=['POST'])
def post_match_status():
	try:
		data = request.json

		# Uncomment the following lines when you have an access token verification function
		# if not access_token_verify(data.get('access_token')):
		#     return jsonify({'success': False, 'error': 'Invalid access token'}), 401

		match_id = data.get('match_id')

		# Add your match result logic here, including storing the match result in the database
		match = Match.query.filter_by(match_id=match_id).first()
		match.status = 'completed'
		db.session.commit()

		return jsonify({'success': True}), 200

	except Exception as e:
		db.session.rollback()
		return jsonify({'success': False, 'error': str(e)}), 500


@match_bp.route('/api/get/match_history', methods=['GET'])
def get_match_history():
    try:
        data = request.json
        user_id = data.get('user_id')

        # Add your get match history logic here, including retrieving the match history from the database
        match_history = Match.query.filter_by(user_id=user_id, status='completed').all()

        # Convert the match history to a format you want to return
        data = [{'match_id': match.match_id, 'user_id': match.user_id, 'weapon_id': match.weapon_id,
                 'arena_id': match.arena_id, 'enemies_spotted': match.enemies_spotted,
                 'kills': match.kills, 'deaths': match.deaths, 'score': match.score, 'status': match.status}
                for match in match_history]

        return jsonify({'success': True, 'data': data}), 200

    except Exception as e:
        return jsonify({'success': False, 'error': str(e)}), 500
