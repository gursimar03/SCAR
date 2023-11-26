from flask import Flask, render_template , Blueprint , jsonify , request

# import sys
# sys.path.append('SCAR')
from SCAR.pn_functions import publish_update,setup_pubnub





pb_route = Blueprint('pb_route', __name__)



@pb_route.route('/api/get/setup-pubnub', methods=['GET'])
def set():
    #  pm.eval('setupPubNub')
     setup_pubnub()
     return jsonify({'success': True}), 200
    # result = publishUpdate()
    # return result

@pb_route.route('/api/get/testing', methods=['GET'])
def test():
     publish_update('johns_sd3b_pi', {"motion": "Motion Detected"})
     return jsonify({'success': True}), 200

# @app.route('/api/test')
# def test():
#     # Use the 'connector' here for database operations
#     return jsonify({'success': True}), 200