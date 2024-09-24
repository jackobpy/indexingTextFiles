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
            Scanner scanner = new Scanner(file);
            pathname = file.getPath();
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
            System.out.println("File successfully indexed");
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
                    .forEach(file -> {
                        indexFile(file.getPath());
                    });
        } catch (IOException e) {
            System.out.println("No such directory found :(");
        }

    }
}
