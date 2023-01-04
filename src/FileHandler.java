import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {

    /**
     * Create a new file with the name passed as argument.
     *
     * @param fileName Name of the file to create.
     * @return True if file was created.
     */
    public static boolean createFile(String fileName) {
        try {
            File newFile = new File(fileName);
            return newFile.createNewFile();

        } catch (IOException e) {
            System.out.println("Exception occurred: " + e);
            return false;
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
        System.out.println(file.getName());
        return file.delete();
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

    /**
     * Read all files in a folder.
     * @param folderName Name of the folder to look for.
     * @return Array of files names in the folder.
     */
    public static String[] readFolder(String folderName)  {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        String[] filesNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            filesNames[i] = files[i].getName().replace(".txt", "");
        }
        return filesNames;
    }

    /**
     * Read a file.
     * @param fileName Name of the file to read.
     * @return String with the content of the file.
     * @throws IOException
     */
    public String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String fileContent = "";
        while (sc.hasNextLine()) {
            fileContent += sc.nextLine();
        }
        return fileContent;
    }
}
