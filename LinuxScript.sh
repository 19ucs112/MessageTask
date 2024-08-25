gnome-terminal --title="server" -- bash -c "cd $(pwd); mvn exec:java -Dexec.mainClass='com.chat.app.execution.v2.MessageTaskExecutorServer'; exec bash"
sleep 2
gnome-terminal --title="responder" -- bash -c "cd $(pwd); mvn exec:java -Dexec.mainClass='com.chat.app.execution.v2.MessageTaskExecutorClient' -Dexec.args='responder'; exec bash"
gnome-terminal --title="initiator" -- bash -c "cd $(pwd); mvn exec:java -Dexec.mainClass='com.chat.app.execution.v2.MessageTaskExecutorClient' -Dexec.args='initiator'; exec bash"
