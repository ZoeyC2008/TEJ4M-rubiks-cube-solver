
#define enPin 2
#define stepPin 7
#define dirPin 8
#define ledPin 13

void setup(){
  pinMode(enPin, OUTPUT);
  pinMode(stepPin, OUTPUT);
  pinMode(dirPin, OUTPUT);
  pinMode(ledPin, OUTPUT);

  digitalWrite(enPin, LOW);

  move();
}

void loop(){
  digitalWrite(ledPin, HIGH);
}

void move(){
  digitalWrite(dirPin, HIGH);
  // Spin the stepper motor 1 revolution slowly:
  delayMicroseconds(5);

  for (int i = 0; i < 100; i++) {
    // These four lines result in 1 step:
    digitalWrite(stepPin, HIGH);
    delayMicroseconds(1000);
    digitalWrite(stepPin, LOW);
    delayMicroseconds(1000);
  }

  delay(50);

  digitalWrite(dirPin, LOW);
  // Spin the stepper motor 1 revolution slowly:
  delayMicroseconds(5);

  for (int i = 0; i < 100; i++) {
    // These four lines result in 1 step:
    digitalWrite(stepPin, HIGH);
    delayMicroseconds(1000);
    digitalWrite(stepPin, LOW);
    delayMicroseconds(1000);
  }


  digitalWrite(dirPin, LOW);

}