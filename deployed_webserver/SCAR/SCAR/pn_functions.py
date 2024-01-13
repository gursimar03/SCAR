from pubnub.pnconfiguration import PNConfiguration
from pubnub.pubnub import PubNub
from pubnub.enums import PNStatusCategory, PNOperationType
from SCAR.callback import MySubscribeCallback

alive_second = 0
heartbeat_rate = 5000
my_channel = "johns_sd3b_pi"

pnconfig = PNConfiguration()
pnconfig.publish_key = "pub-c-8007c0d5-9aad-4fd5-a754-0205df40c8d7"
pnconfig.subscribe_key = "sub-c-ad6eb9d1-059a-4ca3-88e0-6905ca4b469a"
pnconfig.uuid = "Device id"  # Get device name

pubnub = PubNub(pnconfig)

def setup_pubnub():
    global pubnub
    

    # def status_callback(status, event):
    #     if status.category == PNStatusCategory.PN_CONNECTED_CATEGORY:
    #         print("Connected")

    # def message_callback(message, event):
    #     print(message.message)
    #     received = message.message
    #     if "motion" in received:
    #         if received["motion"] == "Motion Detected":
    #             print("Motion Detected")
    #         else:
    #             print("No Motion Detected")

    # def presence_callback(presence, event):
    #     pass  # Handle presence

    # pubnub.add_listener(status=status_callback, message=message_callback, presence=presence_callback)
    # pubnub.add_listener(MySubscribeCallback())
    pubnub.subscribe().channels([my_channel]).execute()
    pubnub.add_listener(MySubscribeCallback())

   

def publish_update(channel, message):
    
    pubnub.publish().channel(channel).message(message).sync()
    pubnub.add_listener(MySubscribeCallback())


def time():
    import time
    current_second = int(time.time() * 1000)
    if current_second - alive_second > heartbeat_rate + 1000:
        print("DEAD!!!!")
    else:
        print("Alive")
    time.sleep(1)
    time()

def keep_alive():
    import requests
    global alive_second
    try:
        response = requests.get('/keep_alive')
        response.raise_for_status()
        date = int(response.headers['Date'])
        alive_second = date
        response_json = response.json()
        if response_json.get("motion") == 1:
            print("Motion Detected")
        else:
            print("No Motion Detected")
    except Exception as e:
        print(e)
    time.sleep(heartbeat_rate // 1000)
    keep_alive()

def handle_click(cb):
    if cb:
        value = "on"
    else:
        value = "off"
    publish_update(my_channel, {"buzzer": value})

# Call setup_pubnub() to initialize PubNub

# Uncomment and call the necessary functions based on your requirements
# time()
# keep_alive()
# handle_click(True/False)  # Pass True for "on" and False for "off"


