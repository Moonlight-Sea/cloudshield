package pers.sea.shield.dispatch.common.util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * @author moon on 2024/5/9 by idea
 */
public class FileTraversal {

    public static void main(String[] args) {
        try (Stream<Path> paths = Files.walk(Paths.get("path_to_your_directory"))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> System.out.println("File: " + path.toAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
