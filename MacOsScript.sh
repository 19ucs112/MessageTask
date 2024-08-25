osascript -e 'tell application "Terminal"
      do script "cd '$(pwd)'; mvn exec:java -Dexec.mainClass='com.chat.app.execution.v2.MessageTaskExecutorServer'"
      delay 1
      tell front window
          set custom title to "server"
      end tell
  end tell'
  sleep 2
  osascript -e 'tell application "Terminal"
        do script "cd '$(pwd)'; mvn exec:java -Dexec.mainClass='com.chat.app.execution.v2.MessageTaskExecutorClient' -Dexec.args='responder'"
        delay 1
        tell front window
            set custom title to "responder"
        end tell
    end tell'
  osascript -e 'tell application "Terminal"
          do script "cd '$(pwd)'; mvn exec:java -Dexec.mainClass='com.chat.app.execution.v2.MessageTaskExecutorClient' -Dexec.args='initiator'"
          delay 1
          tell front window
              set custom title to "initiator"
          end tell
      end tell'