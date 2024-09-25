import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Indexer {
    private final HashMap<String, List<String>> indices = new HashMap<>();
    private final Tokenizer tokenizer;

    public Indexer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public List<String> findWord(String word){
        return indices.get(word.toLowerCase());
    }
    public void indexFile(String pathname) {
        try {
            File file = new File(pathname);
            if (!getFileExtension(pathname).equals("txt")){
                System.out.println("Something went wrong :(");
                return;
            }
            Scanner scanner = new Scanner(file);
            pathname = file.getAbsolutePath();
            while(scanner.hasNextLine()){
                List<String> tokens = tokenizer.tokenize(scanner.nextLine());
                for (String token : tokens){
                    token = token.toLowerCase();
                    if (indices.containsKey(token) && !indices.get(token).contains(pathname)){
                        indices.get(token).add(pathname);
                    } else if (!indices.containsKey(token)){
                        List<String> list = new ArrayList<>();
                        list.add(pathname);
                        indices.put(token, list);
                    }
                }
            }
            System.out.println(pathname + " successfully indexed");
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found :(");
        }
    }

    public void indexDirectory(String path) {
        try {
            File directory = new File(path);
            Files.walk(directory.toPath())
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(x -> getFileExtension(x.getAbsolutePath()).equals("txt"))
                    .forEach(file -> {
                        indexFile(file.getAbsolutePath());
                    });
        } catch (IOException e) {
            System.out.println("No such directory found :(");
        }

    }

    public static String getFileExtension(String name) {
        int dotIndex = name.lastIndexOf('.');
        return (dotIndex == -1) ? "" : name.substring(dotIndex + 1);
    }
}
