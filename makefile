target:
	javac -cp .:jopt-simple-5.0.2.jar *.java
	java -cp .:jopt-simple-5.0.2.jar GuessWho  game1.config game1.chosen random random
