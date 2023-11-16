import RPi.GPIO as GPIO
import time

# Set GPIO mode to BCM
GPIO.setmode(GPIO.BCM)

# Define LED pins
NORTH_LED = 17
WEST_LED = 27
EAST_LED = 22
SOUTH_LED = 5

# Set up GPIO pins for LEDs
GPIO.setup(NORTH_LED, GPIO.OUT)
GPIO.setup(WEST_LED, GPIO.OUT)
GPIO.setup(EAST_LED, GPIO.OUT)
GPIO.setup(SOUTH_LED, GPIO.OUT)

# Function to turn on a specific LED
def turn_on_led(led_pin):
    GPIO.output(led_pin, GPIO.HIGH)

# Function to turn off all LEDs
def turn_off_all_leds():
    GPIO.output(NORTH_LED, GPIO.LOW)
    GPIO.output(WEST_LED, GPIO.LOW)
    GPIO.output(EAST_LED, GPIO.LOW)
    GPIO.output(SOUTH_LED, GPIO.LOW)

try:
    while True:
        # Get user input
        user_input = input("Enter a number between 1 and 4 (0 to exit): ")

        # Check if the input is valid
        if user_input.isdigit():
            number = int(user_input)

            # Check if the number is within the valid range
            if 1 <= number <= 4:
                # Turn off all LEDs before turning on the selected one
                turn_off_all_leds()

                # Turn on the selected LED
                if number == 1:
                    turn_on_led(NORTH_LED)
                elif number == 2:
                    turn_on_led(WEST_LED)
                elif number == 3:
                    turn_on_led(EAST_LED)
                elif number == 4:
                    turn_on_led(SOUTH_LED)
            elif number == 0:
                # Exit the program if the user enters 0
                break
            else:
                print("Invalid input. Please enter a number between 1 and 4.")
        else:
            print("Invalid input. Please enter a number.")

except KeyboardInterrupt:
    pass  # Handle Ctrl+C to gracefully exit the program

finally:
    # Clean up GPIO on program exit
    GPIO.cleanup()
