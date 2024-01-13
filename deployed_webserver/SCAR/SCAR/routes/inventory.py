from flask import Blueprint, jsonify, request
from SCAR.app_factory import db
from SCAR.models.inventory import Inventory
from SCAR.__init__ import require_auth

inventory_bp = Blueprint('inventory_bp', __name__)

@inventory_bp.route('/api/get/inventory/<int:user_id>', methods=['GET'])
@require_auth
def get_user_inventory(user_id):
    try:
        # Retrieve inventory for a specific user
        user_inventory = Inventory.query.filter_by(user_id=user_id).all()

        # Convert the inventory to a format you want to return
        data = [{'user_id': entry.user_id, 'weapon_id': entry.weapon_id} for entry in user_inventory]

        return jsonify({'success': True, 'data': data}), 200

    except Exception as e:
        return jsonify({'success': False, 'error': str(e)}), 500

@inventory_bp.route('/api/post/inventory', methods=['POST'])
@require_auth
def post_user_inventory():
    try:
        data = request.json
        user_id = data.get('user_id')
        weapon_id = data.get('weapon_id')

        # Add your post inventory logic here, including storing the inventory in the database
        new_inventory_entry = Inventory(user_id=user_id, weapon_id=weapon_id)
        db.session.add(new_inventory_entry)
        db.session.commit()

        return jsonify({'success': True, 'message': 'Inventory entry added successfully'}), 200

    except Exception as e:
        db.session.rollback()
        return jsonify({'success': False, 'error': str(e)}), 500

@inventory_bp.route('/api/delete/inventory', methods=['DELETE'])
@require_auth
def delete_user_inventory():
    try:
        data = request.json
        user_id = data.get('user_id')
        weapon_id = data.get('weapon_id')

        # Add your delete inventory logic here, including removing the inventory entry from the database
        inventory_entry = Inventory.query.filter_by(user_id=user_id, weapon_id=weapon_id).first()

        if inventory_entry:
            db.session.delete(inventory_entry)
            db.session.commit()
            return jsonify({'success': True, 'message': 'Inventory entry deleted successfully'}), 200
        else:
            return jsonify({'success': False, 'error': 'Inventory entry not found'}), 404

    except Exception as e:
        db.session.rollback()
        return jsonify({'success': False, 'error': str(e)}), 500
