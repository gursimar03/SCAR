#!/usr/bin/python
import sys
import logging
import os
logging.basicConfig(stream=sys.stderr)
sys.path.insert(0,"/var/www/SCAR/")
os.environ['TEST'] = 'test'
os.environ['FACEBOOK_APP'] = 'Your facebook app id'
os.environ['FACEBOOK_SECRET']='Your facebook app secret'
os.environ['admin_token']='08eff29c780d53adc819e095b2b0f0fdbe88862cafafc9523642124cc5492672'


def application(environ, start_response):
 for key in ['TEST', 'FACEBOOK_APP', 'FACEBOOK_SECRET']:
    os.environ[key] = environ.get(key, '')
 from SCAR import app as _application
 _application.secret_key='secret'
 return _application(environ, start_response)
