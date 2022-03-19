import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.lang.StringBuilder;
import java.util.stream.IntStream;

// windows MINGW64 bash commands
// javac -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" WhereDemo_Test.java
// java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore WhereDemo_Test

// linux commands
// javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar WhereDemo_Test.java
// java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore WhereDemo_Test

public class WhereDemo_Test {
    //test to fail
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void call_thisDirectory() throws IOException {
        String toTest = WhereDemo.thisDirectory();
        String expect = pwdThisDir();
        assertEquals(expect, toTest);
    }
    
    //TEST with this linux-cmd :~:$ [ pwd > thisDir.txt ] within working/testing directory
    @Test
    public void call_dirFromFile() throws IOException {
        String testFile = "thisDir.txt";
        String toTest = WhereDemo.dirFromFile(testFile);
        String expect = pwdThisDir();
        assertEquals(expect, toTest);
    }

    private String pwdThisDir() throws IOException {
        try { 
            Process process = Runtime.getRuntime().exec("pwd"); // for Linux
            // Process process = Runtime.getRuntime().exec("cmd /c dir"); //for Windows

            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder thisDir = new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null) {
                System.out.println("Test ran pwd: " + line);
                thisDir = thisDir.append(line);
                System.out.println("codePoints of readLine are: ");
                IntStream this_Stream = thisDir.codePoints();
                this_Stream.forEach(System.out::println);  
            } 
            process.destroy();
            return thisDir.toString();
        }       
        catch(Exception e) { 
            System.out.println(e);
            return null; 
        }
    }
}
