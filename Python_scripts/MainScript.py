import cv2
import numpy as np
import mediapipe as mp
import math as Math
import RPi.GPIO as GPIO
from pubnub.pnconfiguration import PNConfiguration
from pubnub.pubnub import PubNub
from pubnub.enums import PNStatusCategory, PNOperationType
from callback import MySubscribeCallback
import serial
import time
import pynmea2
from math import radians, sin, cos, sqrt, atan2
import logging

port = "/dev/serial0"
priv_coord=(0,0)

def haversine(lat1, lon1, lat2, lon2):
    # Convert latitude and longitude from degrees to radians
    lat1, lon1, lat2, lon2 = map(radians, [lat1, lon1, lat2, lon2])

    # Haversine formula
    dlat = lat2 - lat1
    dlon = lon2 - lon1
    a = sin(dlat / 2) ** 2 + cos(lat1) * cos(lat2) * sin(dlon / 2) ** 2
    c = 2 * atan2(sqrt(a), sqrt(1 - a))

    # Radius of the Earth in kilometers (change it to miles if needed)
    radius = 6371.0

    # Calculate the distance
    distance = radius * c

    return distance

def is_inside_area(gps_reading, area_center, area_radius):
    # Extract latitude and longitude from the GPS reading
    lat_reading, lon_reading = gps_reading

    # Calculate the distance between the GPS reading and the area center
    distance = haversine(lat_reading, lon_reading, area_center[0], area_center[1])

    # Check if the distance is within the specified radius
    return distance <= area_radius

def calculate_distance(gps_data,current_data):
    
    lat_reading, lon_reading = gps_data
    distance = haversine(lat_reading, lon_reading, current_data[0], current_data[1])
    priv_coord =currnet_data 
    return distance 

def parseGPS(gps_str):
    if 'GGA' in gps_str:
        try:
            msg = pynmea2.parse(gps_str)
            print("Timestamp: %s -- Lat: %s %s -- Lon: %s %s -- Satellites: %s" % (msg.timestamp, msg.lat, msg.lat_dir, msg.lon, msg.lon_dir, msg.num_sats))
            lat = float(msg.lat)
            lon = float(msg.lon)
            return lat, lon
        except pynmea2.nmea.ParseError as e:
            logging.error(f"error parseig{e}")
            return 0,0




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
    
cap = cv2.VideoCapture(0)   
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

def run():
    
    start_time = time.time()
    serialPort = serial.Serial(port, baudrate=9600, timeout=0.5)
    outside = True
    timeout_counter = 0
    max_timeout =  5 # Adjust as needed
    out =  False
    runp = True
    count =0
    distance= 0 
    gps_str = serialPort.readline().decode('utf-8')
    priv_coor= parseGPS(gps_str)

    while runp:
       
        ret, frame = cap.read()
        elapsed_time = time.time()-start_time
        gps_str = serialPort.readline().decode('utf-8')
        coord = parseGPS(gps_str)
        
       
        #distance = calculate_distance(priv_coord,coord)
            
        
        # Convert the BGR image to RGB
        image = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        # Process the image and detect bodies
        results = pose.process(image) 
        # Draw the pose annotations on the image
        annotatedImage = cv2.cvtColor(image, cv2.COLOR_RGB2BGR)
        if results.pose_landmarks :
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
                    print ("count")
                    print (count)
                    print("distance")
                    print (distance)
                    
                # If the body is inside the circle, light up the whole border of the window green
                for i in range(4):
                    turn_off_led(i)
                    color = (0, 255, 0)
                    cv2.circle(annotatedImage, (int(ledIndicators[i][0]), int(ledIndicators[i][1])), 10, color, -1)
                    
                     
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
                        turn_off_led(i)
                    else:
                        turn_on_led(i)
            
        if elapsed_time % 10 == 0:
          
            lat,lon = coord
            runp =  not is_inside_area((lat, lon), (53.986836, -6.397867),1000)
            print (hi) 
            if runp == False :
                print("Exited the loop. GPS coordinates are inside the quadrilateral.")

        
        cv2.imshow('Body Detection', annotatedImage)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            publish_update(my_channel,{"count":count,"distance": distance})
            break

    cap.release()
    cv2.destroyAllWindows()
    GPIO.cleanup()
     

def main():

    # Run the program
    run()

if __name__ == "__main__":
    main()

