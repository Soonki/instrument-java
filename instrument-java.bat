@echo off
setlocal enabledelayedexpansion
set PATH=c:\perl\bin;%PATH%

if "%1" == "" goto help

set d=%1
set d=%d:/=\%
set u=%d:\=/%

perl %~dpn0.pl "%u%"

goto exit

:help
echo usage: %~nx0 ^<filename.java^>

:exit

endlocal
