import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {

    public static void createFile(String fileName) {
        File newFile = new File(fileName);
        try {
            boolean fileCreated = newFile.createNewFile();
            if (fileCreated) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e);
        }
    }

    public static boolean createFolder(String folderName) {
        File newFolder = new File(folderName);
        boolean folderCreated = newFolder.mkdir();
        return  folderCreated;
    }

    public static void writeInFile(String fileName, String text) throws IOException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(text);
        fileWriter.close();
    }

    public static void createFileIn(String fileName, String folderName){
        File folder = new File(folderName);
        File newFile = new File(folder + "\\" + fileName);
        try {
            boolean fileCreated = newFile.createNewFile();
            if (fileCreated) {
                System.out.println("File: " + newFile.getName() + "created in: " + newFile.getParent());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e);
        }
    }


    public static boolean existFile(String fileName){
        File file = new File(fileName);
        return file.exists();
    }
}
