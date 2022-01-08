@echo off
cd src
call javacc Parser0.jj
javac Parser0.java
javac ICLCompiler.java
java ICLCompiler ..\source.icl
cd ../files
java -jar "C:\jasmin-2.4\jasmin.jar" *.j
java Main
@PAUSE