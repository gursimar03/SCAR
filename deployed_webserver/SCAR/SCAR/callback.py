
from pubnub.callbacks import SubscribeCallback
from pubnub.enums import PNStatusCategory, PNOperationType

from pubnub.callbacks import SubscribeCallback
from pubnub.enums import PNStatusCategory, PNOperationType
import requests


def my_publish_callback(envelope, status):
    # Check whether request successfully completed or not
    if not status.is_error():
        print("message published w/ timetoken %s" % envelope.result.timetoken)
        pass  # Message successfully published to specified channel.
    else:
        pass  # Handle message publish error. Check 'category' property to find out possible issue
        # because of which request did fail.
        # Request can be resent using: [status retry];


class MySubscribeCallback(SubscribeCallback):
    def presence(self, pubnub, presence):
        pass  # handle incoming presence data

    def status(self, pubnub, status):
        if status.category == PNStatusCategory.PNUnexpectedDisconnectCategory:
            print("Disconnected")
            pass  # This event happens when radio / connectivity is lost

        elif status.category == PNStatusCategory.PNConnectedCategory:
            print("Connected")
            # Connect event. You can do stuff like publish, and know you'll get it.
            # Or just use the connected event to confirm you are subscribed for
            # UI / internal notifications, etc
            # pubnub.publish().channel('my_channel').message('Hello world!').pn_async(my_publish_callback)
        elif status.category == PNStatusCategory.PNReconnectedCategory:
            print("Reconnected")
            pass
            # Happens as part of our regular operation. This event happens when
            # radio / connectivity is lost, then regained.
        elif status.category == PNStatusCategory.PNDecryptionErrorCategory:
            print("Decryption Error")
            pass
            # Handle message decryption error. Probably client configured to
            # encrypt messages and on live data feed it received plain text.

    def message(self, pubnub, message):
        try:
            # Assuming the Flask server is running locally, adjust the URL accordingly
            api_url = "http://scarsd3b.online/api/pi/post_result"

            # Extract the message data from the PubNub message
            message_data = message.message

            # Make a POST request to the Flask API with the message data
            response = requests.post(api_url, json=message_data)

            # Check if the request was successful (status code 2xx)
            if response.ok:
                print("Message successfully stored using API.")
            else:
                print(f"Error storing message. Status code: {response.status_code}")

        except requests.RequestException as req_error:
            print(f"Request error: {req_error}")

        except Exception as e:
            print(f"Unexpected error: {str(e)}")