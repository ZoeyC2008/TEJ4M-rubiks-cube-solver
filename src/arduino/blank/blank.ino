#define enPin 2
#define ledPin 13

void setup(){
  pinMode(enPin, OUTPUT);
  pinMode(ledPin, OUTPUT);
}

void loop(){
  digitalWrite(enPin, HIGH);
  digitalWrite(ledPin, LOW);
}