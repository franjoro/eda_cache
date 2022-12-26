import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {

    /**
     * Create a new file with the name passed as argument.
     *
     * @param fileName Name of the file to create.
     * @return True if file was created.
     */
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

    /**
     * Create a new folder with the name passed as argument.
     *
     * @param folderName Name of the folder to create.
     * @return True if folder was created.
     */
    public static boolean createFolder(String folderName) {
        File newFolder = new File(folderName);
        boolean folderCreated = newFolder.mkdir();
        return  folderCreated;
    }

    /**
     * Delete a file with the name passed as argument.
     *
     * @param fileName Name of the file to write.
     * @return True if file was deleted.
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Write a string in a file.
     *
     * @param fileName Name of the file to write.
     * @param text  Content to write in the file.
     * @return True if file was written.
     */
    public static void writeInFile(String fileName, String text) throws IOException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Creates a file in a folder.
     *
     * @param fileName Name of the file to look for.
     * @param folderName Name of the folder to look for.
     */
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

    /**
     * Check if a file exists.
     * @param fileName Name of the file to look for.
     * @return true if file exists.
     */
    public static boolean existFile(String fileName){
        File file = new File(fileName);
        return file.exists();
    }
}
