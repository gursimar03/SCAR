from flask import Blueprint, jsonify, request
from SCAR.app_factory import db
from SCAR.models.match_result import MatchResult
from SCAR.decorator import require_auth
from SCAR.models.arena import Arena


match_result_bp = Blueprint('match_result_bp', __name__)

@match_result_bp.route('/api/post/match_result', methods=['POST'])
@require_auth
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


@match_result_bp.route('/api/get/match_history/<int:user_id>', methods=['GET'])
@require_auth
def get_user_inventory(user_id):
    try:
        # Retrieve match history for a specific user with arena information
        match_history = (
            MatchResult.query
            .join(Arena, MatchResult.arena_id == Arena.arena_id)
            .filter(MatchResult.user_id == user_id)
            .all()
        )

        # Convert the match history to the desired format
        data = [
            {
                "match_id": match.match_id,
                "user_id": match.user_id,
                "weapon_id": match.weapon_id,
                "arena": {
                    "arena_id": match.arena_id,
                    "arena_name": match.arena.arena_name if match.arena else None
                },
                "enemies_spotted": match.enemies_spotted,
                "kills": match.kills,
                "score": match.score,
                "travelled": match.travelled,  # Assuming 'travelled' is a field in your match_result table
                "match_time": match.match_time,
                "match_date": str(match.match_date)  # Assuming 'match_date' is a field in your match_result table
            }
            for match in match_history
        ]

        return jsonify({'success': True, 'data': data}), 200

    except Exception as e:

        return jsonify({'success': False, 'error': str(e)}), 500

# Route to get the users gps location history throughout the match
@match_result_bp.route('/api/get/gps_history', methods=['GET'])
@require_auth
def get_gps_history():
	try:
		data = request.json
		user_id = data.get('user_id')
		match_id = data.get('match_id')

		gps_history = MatchResult.query.filter_by(user_id=user_id, match_id=match_id, status="finished").all()
  
		
		data = [{'match_id': gps.match_id, 'user_id': gps.user_id, 'gps_data': gps.gps_data}
				for gps in gps_history]

		return jsonify({'success': True, 'data': data}), 200

	except Exception as e:
		return jsonify({'success': False, 'error': str(e)}), 500


# Route to update the match status to finished
@match_result_bp.route('/api/post/match_status', methods=['POST'])
@require_auth
def post_match_status():
	try:
		data = request.json
		match_id = data.get('match_id')

		match_status = MatchResult.query.filter_by(match_id=match_id).first()
		match_status.status = "finished"
		db.session.commit()

		return jsonify({'success': True}), 200

	except Exception as e:
		return jsonify({'success': False, 'error': str(e)}), 500

@match_result_bp.route('/api/pi/post_result', methods=['POST'])
def post_result():
    try:
        data = request.get_json()
        kills = data.get('count', 0)
        distance_travelled = data.get('distance', 0)
        user_id = 1
        weapon_id = 1
        arena_id = 1

        # Update the MatchResult model
        match_result = MatchResult(
            user_id=user_id,
            weapon_id=weapon_id,
            arena_id=arena_id,
            kills=kills,
            travelled=distance_travelled
        )

        # Add and commit the changes to the database
        db.session.add(match_result)
        db.session.commit()

        return jsonify({"success": True}), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 500
