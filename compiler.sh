cd generated_files
rm *.class
rm *.j
cd ..
cd src
javacc Parser0.jj
javac Parser0.java
javac ICLCompiler.java
java ICLCompiler ../source.icl
cd ../generated_files
java -jar "../jasmin.jar" *.j
java Main