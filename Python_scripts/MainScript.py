import cv2
import numpy as np
import mediapipe as mp
import math as Math
import RPi.GPIO as GPIO
from pubnub.pnconfiguration import PNConfiguration
from pubnub.pubnub import PubNub
from pubnub.enums import PNStatusCategory, PNOperationType
from callback import MySubscribeCallback


#import time

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)

NORTH_LED = 11
WEST_LED = 13
EAST_LED = 15
SOUTH_LED = 29

    
GPIO.setup(NORTH_LED, GPIO.OUT)
GPIO.setup(WEST_LED, GPIO.OUT)
GPIO.setup(EAST_LED, GPIO.OUT)
GPIO.setup(SOUTH_LED, GPIO.OUT)

alive_second = 0
heartbeat_rate = 5000
my_channel = "johns_sd3b_pi"

pubnub = None

def setup_pubnub():
    global pubnub
    pnconfig = PNConfiguration()
    pnconfig.publish_key = "pub-c-8007c0d5-9aad-4fd5-a754-0205df40c8d7"
    pnconfig.subscribe_key = "sub-c-ad6eb9d1-059a-4ca3-88e0-6905ca4b469a"
    pnconfig.uuid = "Device id"  # Get device name

    pubnub = PubNub(pnconfig)

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

setup_pubnub()

def my_publish_callback(envelope, status):
    if not status.is_error():
        pass
    else:
        pass   

def publish_update(channel, message):
    # return 2
  #  pubnub.publish().channel(channel).message(message).sync()
    pubnub.publish().channel(channel).message(message).pn_async(my_publish_callback)
    pubnub.add_listener(MySubscribeCallback())
  #  pubnub.publish().channel(channel).message(message).pn_async(my_publish_callback)



#Setup the video capture to read from the webcam
cap = cv2.VideoCapture(1)   
#Load the mediapipe pose model
mpPose = mp.solutions.pose
pose = mpPose.Pose()
mpDraw = mp.solutions.drawing_utils
# Get the width and height of the frame
ret, frame = cap.read()
height, width, _ = frame.shape

# Define the center and radius of the circle
center = (width // 2, height // 2)
radius = min(width, height) // 4



# function to turn on a specific LED
def turn_on_led(num):
    if num == 3:
        GPIO.output(NORTH_LED, GPIO.HIGH)
    if num == 0:
        GPIO.output(EAST_LED, GPIO.HIGH)
    if num == 1:
        GPIO.output(SOUTH_LED, GPIO.HIGH)
    if num == 2:
        GPIO.output(WEST_LED, GPIO.HIGH)

def turn_off_led(num):
    if num == 3:
        GPIO.output(NORTH_LED, GPIO.LOW)
    elif num == 0:
        GPIO.output(EAST_LED, GPIO.LOW)
    elif num == 1:
        GPIO.output(SOUTH_LED, GPIO.LOW)
    elif num == 2:
        GPIO.output(WEST_LED, GPIO.LOW)

# function to turn off all LEDs
def turn_off_all_leds():
    GPIO.output(NORTH_LED, GPIO.LOW)
    GPIO.output(WEST_LED, GPIO.LOW)
    GPIO.output(EAST_LED, GPIO.LOW)
    GPIO.output(SOUTH_LED, GPIO.LOW)


def run():
    count = 0 
    out =  False
    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break

        # Convert the BGR image to RGB
        image = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

        # Process the image and detect bodies
        results = pose.process(image)
    
        # Draw the pose annotations on the image
        annotatedImage = cv2.cvtColor(image, cv2.COLOR_RGB2BGR)
        if results.pose_landmarks:
            mpDraw.draw_landmarks(annotatedImage, results.pose_landmarks, mpPose.POSE_CONNECTIONS)

        # Draw the scope border in the middle of the screen
        cv2.circle(annotatedImage, center, radius, (0, 0, 255), 2)
        # Check if a body is detected
        if results.pose_landmarks:

            # Set the body center to the nose landmark
            body_center = (int(results.pose_landmarks.landmark[mpPose.PoseLandmark.NOSE].x * width),
                        int(results.pose_landmarks.landmark[mpPose.PoseLandmark.NOSE].y * height))

            # Check if the body center is inside the circle
            inside = (body_center[0] - center[0]) ** 2 + (body_center[1] - center[1]) ** 2 < radius ** 2
            
            # Setup the led indicators
            ledIndicators = [(center[0] + radius * Math.cos(i * Math.pi / 2), center[1] + radius * Math.sin(i * Math.pi / 2)) for i in range(4)]

            if inside:
                if out : 
                    count= count +1 
                    out = False 
                                
                # If the body is inside the circle, light up the whole border of the window green
                for i in range(4):
                    color = (0, 255, 0)
                    cv2.circle(annotatedImage, (int(ledIndicators[i][0]), int(ledIndicators[i][1])), 10, color, -1)
                    turn_on_led(i)
                    
            else:
                # If the body is outside the circle, 8 black dots are drawn on the circle
                ## to indicate the 8 segments of the circle
                ### The 3 closest dots will be lit up blue
                out = True 
                # Calculate the distances from the body center to each LED indicator
                distances = [((body_center[0] - led[0]) ** 2 + (body_center[1] - led[1]) ** 2) ** 0.5 for led in ledIndicators]
                # Get the indices of the 3 closest LED indicators
                closest_leds = sorted(range(len(distances)), key=lambda i: distances[i])[:2]
                # Draw the LED indicators
                for i in range(4):
                    color = (255, 0, 0) if i in closest_leds else (34, 34, 34)
                    cv2.circle(annotatedImage, (int(ledIndicators[i][0]), int(ledIndicators[i][1])), 10, color, -1)
                    if i in closest_leds:
                        turn_on_led(i)
                    else:
                        turn_off_led(i)
            

        
        cv2.imshow('Body Detection', annotatedImage)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    cap.release()
    cv2.destroyAllWindows()
    GPIO.cleanup()
    publish_update(my_channel, {"count":count})  

def main():

    # Run the program
    run()

# Run the program 
if __name__ == "__main__":
    run()


