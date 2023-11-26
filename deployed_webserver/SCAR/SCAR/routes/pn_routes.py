from flask import Blueprint , jsonify , request
from SCAR.pn_functions import publish_update,setup_pubnub





pb_route = Blueprint('pb_route', __name__)



@pb_route.route('/api/get/setup-pubnub', methods=['GET'])
def set():
     setup_pubnub()
     return jsonify({'success': True}), 200


@pb_route.route('/api/get/testing', methods=['GET'])
def test():
     publish_update('johns_sd3b_pi', {"motion": "Motion Detected"})
     return jsonify({'success': True}), 200
