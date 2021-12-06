package dataStrucrures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;


public class CodeBlock {
    LinkedList<String> code;


    public CodeBlock() {
      code = new LinkedList<>();
    }

    public void emit(String opcode) {
        code.add(opcode);
    }

    public void dump(String filename) {
        //Runtime class para executar o comando do jasmin

        try {
            FileOutputStream fout = new FileOutputStream(filename+".j", false);
            PrintStream f = new PrintStream(fout);
            f.println(readFile("../start"));
            f.println("aconst_null");
            f.println("astore 4");
            for (String instruction : code) {
                f.println(instruction);
            }
            f.println(readFile("../end"));
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String readFile(String file) {
        StringBuilder output = new StringBuilder();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                output.append(data).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return output.toString();
    }

    /**
     * creates unique symbols
     * @return creates unique symbols
     */
    public String genSymb(){
        return ""; //TODO
    }

}
