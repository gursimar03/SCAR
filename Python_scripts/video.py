import cv2
import numpy as np

cap = cv2.VideoCapture(0)

# Use full body haarcascade
BodyCascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_fullbody.xml')

# Get the width and height of the frame
ret, frame = cap.read()
height, width, _ = frame.shape

# Define the center and radius of the circle
center = (width // 2, height // 2)
radius = min(width, height) // 4

while True:
    ret, frame = cap.read()
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    bodies = BodyCascade.detectMultiScale(gray, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))

    # Draw the big red circle in the middle of the screen
    cv2.circle(frame, center, radius, (0, 0, 255), 2)

    for (x, y, w, h) in bodies:
        # Calculate the center of the detected body
        body_center = (x + w // 2, y + h // 2)

        # Check if the body is inside the circle
        inside = (body_center[0] - center[0]) ** 2 + (body_center[1] - center[1]) ** 2 < radius ** 2

        if inside:
            # If the body is inside the circle, light up the whole border of the window green
            cv2.rectangle(frame, (0, 0), (width - 1, height - 1), (0, 255, 0), 10)
        else:
            # If the body is outside the circle, light up the closest side green
            distances = [body_center[0], width - body_center[0], body_center[1], height - body_center[1]]
            closest_side = distances.index(min(distances))
            if closest_side == 0:
                cv2.line(frame, (0, 0), (0, height - 1), (0, 255, 0), 10)
            elif closest_side == 1:
                cv2.line(frame, (width - 1, 0), (width - 1, height - 1), (0, 255, 0), 10)
            elif closest_side == 2:
                cv2.line(frame, (0, 0), (width - 1, 0), (0, 255, 0), 10)
            else:
                cv2.line(frame, (0, height - 1), (width - 1, height - 1), (0, 255, 0), 10)

    cv2.imshow('Body Detection', frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
