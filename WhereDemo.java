import java.lang.StringBuilder;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class WhereDemo {
	
    public static String thisDirectory() {
        StringBuilder myDir = new StringBuilder(System.getProperty("user.dir"));
        System.out.println("WhereDemo.thisDirectory() called\n" + "codePoints for this string:");
        IntStream thisStream = myDir.codePoints();
        thisStream.forEach(System.out::println);
        return myDir.toString();

    }
    
    public static String dirFromFile(String txtFile) throws IOException{
        Path fileName = Path.of(txtFile);
        StringBuilder sb_txtDir = new StringBuilder(Files.readString(fileName));
        
        // Files.readString(Path) includes extra eof character in returning String - FAILS assertEquals
        int index_eof = sb_txtDir.length();
        sb_txtDir = sb_txtDir.deleteCharAt(--index_eof);

        System.out.println("WhereDemo.thisDirectory("+txtFile+") called\n" + "codePoints for this string:");
        IntStream fileStream = sb_txtDir.codePoints();
        fileStream.forEach(System.out::println);
        return sb_txtDir.toString();



    }

    public static void main(String[] args) throws IOException{

        Scanner input = new Scanner(System.in);
        // Getting String input
        System.out.println("Demo program to print out directories");
        System.out.println("[Enter 1] to provide valid .txt file containing saved directory path **must be within this directory");
        System.out.println("-OR-\n[Enter 2] to print out this current directory ** type anything else to exit");
        int option = input.nextInt();
        if (option == 1){
            System.out.println("Enter valid .txt file containing a saved directory path **must be within this directory");
            String myString = input.next();
            System.out.println("Trying: " + myString);
            System.out.println("the path provided is: \n" + dirFromFile(myString));
        } else if (option == 2){
            System.out.println("Calling thisDirectory() from main()\n" + thisDirectory());
        } else {
            input.close();
            System.exit(0);
        }
        input.close();
    }
}
