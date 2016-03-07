@echo off
setlocal enabledelayedexpansion
set PATH=c:\perl\bin;%PATH%

perl -version > NUL 2>&1
if NOT %ERRORLEVEL% == 0 goto no_perl

if .%1. == .. (
	for /r %%f in (*.java) do (
		set d=%%f
		set d=!d:/=\!
		set u=!d:\=/!
		perl %~dp0instrument-java.pl "!u!"
	)
) else (
	for /r %%f in (*%1*) do (
		set d=%%f
		set d=!d:/=\!
		set u=!d:\=/!
		perl %~dp0instrument-java.pl "!u!"
	)
)
goto exit

:no_perl
echo No perl found. Please install it and add c:\perl\bin to PATH variable.
goto exit

:exit