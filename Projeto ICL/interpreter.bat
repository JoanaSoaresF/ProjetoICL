@echo off
cd src
call javacc Parser0.jj
javac Parser0.java
javac ICLInterpreter.java
java ICLInterpreter
@PAUSE