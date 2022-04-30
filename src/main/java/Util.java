import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Util {

    public static Set<String> listDiff(Set<String> listA, Set<String> listB) {
        return listA.stream()
                .filter(element -> !listB.contains(element))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public static void printToFile(Set<String> set, String targetFileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(set.size()).append(System.lineSeparator());

        for (String setElement : set) {
            stringBuilder.append(setElement);
            stringBuilder.append(System.lineSeparator());
        }

        String setString = stringBuilder.toString().trim();
        byte[] setBytes = setString.getBytes(StandardCharsets.UTF_8);
        Files.write(Paths.get(targetFileName), setBytes);
    }

    public static Set<String> fileToSet(String inputFile) throws IOException {
        Set<String> users = new TreeSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            users.add(currentLine.trim());
        }
        reader.close();

        return users;
    }

    public static void refactorUsersFile() throws IOException {
        File inputFile = new File("following.txt");
        File tempFile = new File("following.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (!trimmedLine.contains("profile picture")) continue;
            writer.write(currentLine.replace("'s profile picture", "") + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
    }

    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
