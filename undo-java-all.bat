@echo off
setlocal enabledelayedexpansion
set PATH=c:\perl\bin;%PATH%

if .%1. == .. (
	for /r . %%f in (*.java) do (
		call undo-java.bat %%f
	)
) else (
	for /r . %%f in (*%1*) do (
		call undo-java.bat %%f
	)
)