from flask import Blueprint, jsonify, request
from SCAR.app_factory import db
from SCAR.models.leaderboard import Leaderboard
from SCAR.models.user import User
from SCAR.decorator import require_auth

leaderboard_bp = Blueprint('leaderboard_bp', __name__)

@leaderboard_bp.route('/api/leaderboard', methods=['GET'])
@require_auth
def get_leaderboard():
    try:
        leaderboard_data = (
            Leaderboard.query
            .join(User)
            .add_columns(User.username.label('user_name'), User.score.label('score'), Leaderboard.leaderboard_id, Leaderboard.user_id)
            .all()
        )

        data = [{
            # 'leaderboard_id': entry.leaderboard_id,
            'user_id': entry.user_id,
            'user_name': entry.user_name,
            'score': entry.score
        } for entry in leaderboard_data]

        return jsonify({'success': True, 'data': data}), 200

    except Exception as e:
        # Log the traceback to help identify the issue
        traceback.print_exc()
        return jsonify({'success': False, 'error': str(e)}), 500

@leaderboard_bp.route('/api/leaderboard/<int:leaderboard_id>', methods=['GET'])
def get_leaderboard_entry(leaderboard_id):
    try:
        entry = Leaderboard.query.get(leaderboard_id)
        
        if entry:
            data = {'leaderboard_id': entry.leaderboard_id, 'user_id': entry.user_id}
            return jsonify({'success': True, 'data': data}), 200
        else:
            return jsonify({'success': False, 'error': 'Entry not found'}), 404

    except Exception as e:
        return jsonify({'success': False, 'error': str(e)}), 500

@leaderboard_bp.route('/api/leaderboard', methods=['POST'])
@require_auth
def create_leaderboard_entry():
    try:
        data = request.json
        new_entry = Leaderboard(user_id=data['user_id'])
        db.session.add(new_entry)
        db.session.commit()

        return jsonify({'success': True, 'message': 'Entry created successfully'}), 201

    except Exception as e:
        db.session.rollback()
        return jsonify({'success': False, 'error': str(e)}), 500

@leaderboard_bp.route('/api/leaderboard/<int:leaderboard_id>', methods=['PUT'])
@require_auth
def update_leaderboard_entry(leaderboard_id):
    try:
        entry = Leaderboard.query.get(leaderboard_id)

        if entry:
            data = request.json
            entry.user_id = data['user_id']
            db.session.commit()
            return jsonify({'success': True, 'message': 'Entry updated successfully'}), 200
        else:
            return jsonify({'success': False, 'error': 'Entry not found'}), 404

    except Exception as e:
        db.session.rollback()
        return jsonify({'success': False, 'error': str(e)}), 500

@leaderboard_bp.route('/api/leaderboard/<int:leaderboard_id>', methods=['DELETE'])
@require_auth
def delete_leaderboard_entry(leaderboard_id):
    try:
        entry = Leaderboard.query.get(leaderboard_id)

        if entry:
            db.session.delete(entry)
            db.session.commit()
            return jsonify({'success': True, 'message': 'Entry deleted successfully'}), 200
        else:
            return jsonify({'success': False, 'error': 'Entry not found'}), 404

    except Exception as e:
        db.session.rollback()
        return jsonify({'success': False, 'error': str(e)}), 500
