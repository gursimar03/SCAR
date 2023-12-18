from flask import Blueprint, jsonify, request
from SCAR.app_factory import db
from SCAR.models.arena  import Arena

arena_bp = Blueprint('arena_bp', __name__)

@arena_bp.route('/api/get/arenas', methods=['GET'])
def get_arenas():
    try:
        # Retrieve all arenas
        arenas = Arena.query.all()

        # Convert the arenas to a format you want to return
        data = [{'arena_id': arena.arena_id, 'arena_name': arena.arena_name} for arena in arenas]

        return jsonify({'success': True, 'data': data}), 200

    except Exception as e:
        return jsonify({'success': False, 'error': str(e)}), 500

@arena_bp.route('/api/post/arena', methods=['POST'])
def post_arena():
    try:
        data = request.json
        arena_name = data.get('arena_name')

        # Add your post arena logic here, including storing the arena in the database
        new_arena = Arena(arena_name=arena_name)
        db.session.add(new_arena)
        db.session.commit()

        return jsonify({'success': True, 'message': 'Arena added successfully'}), 200

    except Exception as e:
        db.session.rollback()
        return jsonify({'success': False, 'error': str(e)}), 500

@arena_bp.route('/api/get/tested', methods=['GET'])
def tested():
	return jsonify({'success': True, 'message': 'Arena added successfully'}), 500
