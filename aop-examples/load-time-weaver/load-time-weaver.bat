@echo off
@if exist %~dp0load-time-weaver-1.0-SNAPSHOT-jar-with-dependencies.jar (
    java -cp %~dp0load-time-weaver-1.0-SNAPSHOT-jar-with-dependencies.jar com.demo.App %*
) else (
    java -cp %~dp0target\load-time-weaver-1.0-SNAPSHOT-jar-with-dependencies.jar com.demo.App %*
)
