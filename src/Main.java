import java.util.*;

public class Main {
    public static void main(String[] args) {
        Indexer indexer = new Indexer(new SimpleTokenizer());
        while (true){
            System.out.println("Choose the option" + "\n" +
                    "1. Index a file" + "\n" +
                    "2. Index a directory" + "\n" +
                    "3. Query files with a given word" + "\n" +
                    "4. Close the application");
            Scanner scanner = new Scanner(System.in);
            int option = 0;
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException ignored){
            }
            switch (option) {
                case 1:
                    System.out.println("Input your file directory:");
                    Scanner directory = new Scanner(System.in);
                    indexer.indexFile(directory.next());
                    break;
                case 2:
                    System.out.println("Input your directory:");
                    Scanner directory2 = new Scanner(System.in);
                    indexer.indexDirectory(directory2.next());
                    break;
                case 3:
                    System.out.println("Input the word you're looking for:");
                    Scanner word = new Scanner(System.in);
                    List<String> files = indexer.findWord(word.next());
                    System.out.println(files == null ? "No matching files found" : files.toString());
                    break;
                case 4:
                    System.out.println("Bye bye");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}