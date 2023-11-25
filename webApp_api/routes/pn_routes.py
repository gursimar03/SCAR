from flask import Flask, render_template , Blueprint , jsonify , request
import sys
sys.path.append('webApp_api')
# import pythonmonkey as pm
import sys
sys.path.append('webApp_api')
from pn_functions import publish_update,setup_pubnub

# from pn_functions import publishUpdate,setup_pubnub
# from flask import Blueprint, jsonify, request

# app = Blueprint('user_bp', __name__)
# connector = app.connector


# from webApp_api.static.js.main import publishUpdate





pb_route = Blueprint('pb_route', __name__)



@pb_route.route('/api/setup-pubnub', methods=['GET'])
def set():
    #  pm.eval('setupPubNub')
     setup_pubnub()
     return jsonify({'success': True}), 200
    # result = publishUpdate()
    # return result

@pb_route.route('/api/testing', methods=['GET'])
def test():
     publish_update('johns_sd3b_pi', {"motion": "Motion Detected"})
     return jsonify({'success': True}), 200

# @app.route('/api/test')
# def test():
#     # Use the 'connector' here for database operations
#     return jsonify({'success': True}), 200