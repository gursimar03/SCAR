from flask import Blueprint, jsonify, request
from SCAR.app_factory import db
from SCAR.models.weapon import Weapon
from SCAR.decorator import require_auth

weapon_bp = Blueprint('weapon_bp', __name__)

@weapon_bp.route('/api/get/weapons/<int:user_id>', methods=['GET'])
def get_weapons(user_id):
    try:
        # Add your get weapons logic here, including retrieving the weapons from the database
        weapons = Weapon.query.filter_by(user_id=user_id).all()

        # Convert the weapons to a format you want to return
        data = [{
            'user_id': user_id,
            'weapons': [
                {
                    'weapon_id': weapon.weapon_id,
                    'weapon_name': weapon.weapon_name,
                    'weapon_type': weapon.type,
                    'image': weapon.image
                } for weapon in weapons
            ]
        }
        ]

        return jsonify({'success': True, 'data': data}), 200

    except Exception as e:
        return jsonify({'success': False, 'error': str(e)}), 500

@weapon_bp.route('/api/post/weapon', methods=['POST'])
@require_auth
def post_weapon():
    try:
        data = request.json
        weapon_name = data.get('weapon_name')
        weapon_type = data.get('type')

        # Add your post weapon logic here, including storing the weapon in the database
        new_weapon = Weapon(weapon_name=weapon_name, type=weapon_type)
        db.session.add(new_weapon)
        db.session.commit()

        return jsonify({'success': True, 'message': 'Weapon added successfully'}), 200

    except Exception as e:
        db.session.rollback()
        return jsonify({'success': False, 'error': str(e)}), 500
