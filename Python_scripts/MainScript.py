import cv2
import numpy as np
import mediapipe as mp
import math as Math
import RPi.GPIO as GPIO
import time

NORTH_LED = 17
WEST_LED = 27
EAST_LED = 22
SOUTH_LED = 5

GPIO.setup(NORTH_LED, GPIO.OUT)
GPIO.setup(WEST_LED, GPIO.OUT)
GPIO.setup(EAST_LED, GPIO.OUT)
GPIO.setup(SOUTH_LED, GPIO.OUT)

# Setup the video capture to read from the webcam
cap = cv2.VideoCapture(0)

# Load the mediapipe pose model
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
def turn_on_led(led_pin):
    GPIO.output(led_pin, GPIO.HIGH)

# function to turn off all LEDs
def turn_off_all_leds():
    GPIO.output(NORTH_LED, GPIO.LOW)
    GPIO.output(WEST_LED, GPIO.LOW)
    GPIO.output(EAST_LED, GPIO.LOW)
    GPIO.output(SOUTH_LED, GPIO.LOW)


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
        ledIndicators = [(center[0] + radius * Math.cos(i * Math.pi / 4), center[1] + radius * Math.sin(i * Math.pi / 4)) for i in range(8)]

        if inside:
            # If the body is inside the circle, light up the whole border of the window green
            for i in range(8):
                color = (0, 255, 0)
                cv2.circle(annotatedImage, (int(ledIndicators[i][0]), int(ledIndicators[i][1])), 10, color, -1)
                turn_on_led(NORTH_LED)
                turn_on_led(WEST_LED)
                turn_on_led(EAST_LED)
                turn_on_led(SOUTH_LED)
        else:
            # If the body is outside the circle, 8 black dots are drawn on the circle
            ## to indicate the 8 segments of the circle
            ### The 3 closest dots will be lit up blue

            # Calculate the distances from the body center to each LED indicator
            distances = [((body_center[0] - led[0]) ** 2 + (body_center[1] - led[1]) ** 2) ** 0.5 for led in ledIndicators]

            # Get the indices of the 3 closest LED indicators
            closest_leds = sorted(range(len(distances)), key=lambda i: distances[i])[:3]

            # Draw the LED indicators
            for i in range(8):
                color = (255, 0, 0) if i in closest_leds else (34, 34, 34)
                cv2.circle(annotatedImage, (int(ledIndicators[i][0]), int(ledIndicators[i][1])), 10, color, -1)
            
            # Turn on the LEDs
            if 1 in closest_leds:
                turn_on_led(NORTH_LED)
            if 3 in closest_leds:
                turn_on_led(WEST_LED)
            if 5 in closest_leds:
                turn_on_led(EAST_LED)
            if 7 in closest_leds:
                turn_on_led(SOUTH_LED)
        


    cv2.imshow('Body Detection', annotatedImage)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()