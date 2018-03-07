package infrastructure;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by eugeniya.kruchok on 06.03.2018.
 */
public class CsvDataProvider {

    private static Map<String, String> dataMap;

    public static String get(String field) {
        return dataMap.get(field);
    }

    public static void init() {
        CsvDataProvider.cacheData();
    }

    private static void cacheData() {
        if (dataMap == null) {
            String filePath = "./src/main/resources/TestData.csv";

            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                dataMap = stream.map(line -> line.split(";"))
                        .collect(Collectors.toMap(line -> line[0], line -> line[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
