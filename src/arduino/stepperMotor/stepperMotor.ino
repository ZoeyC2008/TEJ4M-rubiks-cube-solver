/* Example sketch to control a stepper motor with 
   A4988/DRV8825 stepper motor driver and 
   Arduino without a library. 
   More info: https://www.makerguides.com */

// Define stepper motor connections and steps per revolution:

#define enPin 2
#define resetPin 3
#define sleepPin 4
#define oneStepPin 5
#define oneDirPin 6
#define twoStepPin 7
#define twoDirPin 8

#define ledPin 12

#define stepsPerRevolution 200

void setup() {
  // Declare pins as output:
  pinMode(enPin, OUTPUT);
  pinMode(resetPin, OUTPUT);
  pinMode(sleepPin, OUTPUT);

  pinMode(oneStepPin, OUTPUT);
  pinMode(oneDirPin, OUTPUT);

  pinMode(twoStepPin, OUTPUT);
  pinMode(twoDirPin, OUTPUT);

  pinMode(ledPin, OUTPUT);


  digitalWrite(enPin, LOW);
  digitalWrite(sleepPin, HIGH);

  digitalWrite(resetPin, HIGH);

  oneUp();



  digitalWrite(resetPin, LOW);
  delay(1000);


  digitalWrite(resetPin, HIGH);

  //twoUp();

  digitalWrite(sleepPin, LOW);


  
}

void loop() {
  // Set the spinning direction clockwise:
  digitalWrite(ledPin, HIGH);
}

void oneUp(){
  digitalWrite(oneDirPin, HIGH);  
  // Spin the stepper motor 1 revolution slowly:
  

    for (int i = 0; i < 100; i++) {
    // These four lines result in 1 step:
    digitalWrite(oneStepPin, HIGH);
    delayMicroseconds(2000);
    digitalWrite(oneStepPin, LOW);
    delayMicroseconds(2000);
  }
}

void twoUp(){
  digitalWrite(twoDirPin, HIGH);  
  // Spin the stepper motor 1 revolution slowly:
  

    for (int i = 0; i < 100; i++) {
    // These four lines result in 1 step:
    digitalWrite(twoStepPin, HIGH);
    delayMicroseconds(2000);
    digitalWrite(twoStepPin, LOW);
    delayMicroseconds(2000);
  }
}

