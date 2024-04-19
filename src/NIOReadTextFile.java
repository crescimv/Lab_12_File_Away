import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

public class NIOReadTextFile {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = " ";

        try {
            //gets the user's current working directory and returns it as a file
            File workingDirectory = new File(System.getProperty("user.dir"));
            //sets the user directory
            chooser.setCurrentDirectory(workingDirectory);

            //checks if user picked a file or not
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                //the user's choice
                selectedFile = chooser.getSelectedFile();
                //converts the file into a path
                Path file = selectedFile.toPath();

                InputStream in =
                        //creates a stream using the file
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                //reads the text file
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                int totalWords = 0;
                int totalCharacters = 0;
                //reads line by line. While reader is ready, reads lines as long as there are lines ready
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    System.out.printf("\n%4d %-60s ", line, rec);

                    //counts number of words in the file
                    int countWords = rec.split("\\s").length;
                    totalWords += countWords;

                    //counts characters in the file
                    totalCharacters += rec.length();
                }
                //closes the file
                reader.close();
                System.out.println("\n");
                System.out.println("Summary\n");

                //Print Summary
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Lines: " + line);
                System.out.println("Words: " + totalWords);
                System.out.println("Characters: " + totalCharacters);

            } else {
                System.out.println("No file selected");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}