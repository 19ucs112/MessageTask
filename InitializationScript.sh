echo "Shaik Dastagiri - E-Assessment (JUNIOR SOFTWARE ENGINEER (f/m/d))"
sleep 2
echo "Initializing the communication between two instances of a Player class in single process"
mvn exec:java -Dexec.mainClass="com.chat.app.execution.v1.MessageTaskExecutor"
echo "Would you like to initialize the communication between two instances of Player class in two different processes? (Y/N): "
read choice
if [ $choice = "Y" ]
then
  echo "Choose operating system: "
  echo "1. MacOs"
  echo "2. Linux"
  echo "enter a number representing os from above menu: "
  read os
  if [ $os = 1 ]
  then
    source ./MacOsScript.sh
  elif [ $os = 2 ]
  then
    source ./LinuxScript.sh
  else
    echo "Invalid os chosen please choose a number representing the os from the menu."
  fi
else
  echo "skipping multi process communication between two player class objects"
fi
echo "do you wish to go through the java documentation for this project[Y/N}: "
read docChoice
if [ $docChoice = "Y" ]
then
  mvn javadoc:javadoc -Dadditionalparam=-Xdoclint:none
  open target/site/apidocs/index.html
else
  echo "skipping javadoc."
fi
echo "bye..!"