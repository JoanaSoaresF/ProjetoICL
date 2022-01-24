@echo off
cd generated_files
del *.class
del *.j
cd ..
cd src
call javacc Parser0.jj
javac Parser0.java
javac ICLCompiler.java
java ICLCompiler ..\source.icl
cd ../generated_files
java -jar "..\jasmin.jar" *.j
java Main
@PAUSE