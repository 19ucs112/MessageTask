This is the maven project that executes the provided task in the E-Assessment for Junior Software Developer role at 360T

author: Shaik Dastagiri

The task execution is divided into two parts, one related to communication of two Player objects in single process and
the latter relates to communication of two Player objects in different processes.

There is a script in file named InitializationScript, using which you can directly execute the task.

You can run this script from the terminal using command 'source ./InitializationScript.sh' after navigating to the
projects root directory where the script is accessible.

There are some options to be chosen while executing the script, all the options are case-sensitive, considering replying
with capital letters.
You have to choose the operating system you use when asked, while you selected yes to initialize communication in two
different processes.
Finally, you will be asked about the Java Documentation and if you want to go through it you may select Y and the script
will automatically open the html generated which contains the documentation of the task.

Overview of how the task is being executed:
case 1: single process communication
    - The single process communication will get executed automatically when you run the script
    - The messages sent by initiator and responder will get displayed on console
    - The execution will stop once the initiator sends and receives 10 messages.
case 2: Multi process communication (2 processes)
    - The script allows you to choose whether you want to initiate the communication between the initiator
      and the responder in different processes.
    - If you choose Y then the script opens 3 new terminal windows each acting as a console for server, initiator client
      and responderClient respectively.
    - Initiator sends and receives messages from responder and responder also works the same.
    - All the messages received from the initiator to the server will be broadcast to responder and vice-versa.
    - once the message count reach 10 the execution will stop.

To keep it simple messages are formatted in this way:
initiator sends message: "Message #[number of message]"
responder replies it with Message: "Reply to Message: 'Message #[number of message]'".
