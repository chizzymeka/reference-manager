// Open CSV: https://howtodoinjava.com/java/library/parse-read-write-csv-opencsv/

package analysers;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSVAnalyser {

    public void createRelevantPapersIntersectionCSVFile() throws IOException {

        HashSet<String[]> chizzy_references = new HashSet<>();
        HashSet<String[]> gerald_references = new HashSet<>();

        CSVReader chizzy_reader = new CSVReader(new FileReader("src/main/java/csvFiles/chizzy.csv"), ',' , '"' , 1);

        String[] nextLine;
        while ((nextLine = chizzy_reader.readNext()) != null) {
            if (nextLine != null) {
                chizzy_references.add(nextLine);
            }
        }

        CSVReader gerald_reader = new CSVReader(new FileReader("src/main/java/csvFiles/gerald.csv"), ',' , '"' , 1);
        while ((nextLine = gerald_reader.readNext()) != null) {
            if (nextLine != null) {
                gerald_references.add(nextLine);
            }
        }

        CSVWriter writer = new CSVWriter(new FileWriter("intersection.csv"));
        boolean hasReadHeader = false;

        for (String[] chizzy_reference : chizzy_references) {
            for (String[] gerald_reference : gerald_references) {
                if (Arrays.deepEquals(chizzy_reference, gerald_reference)) {
                    // Write CSV header for intersection.csv only once.
                    if (hasReadHeader == false) {
                        CSVReader headerReader = new CSVReader(new FileReader("src/main/java/csvFiles/chizzy.csv"), ',', '"', 0);
                        String[] headerLine;
                        while ((headerLine = headerReader.readNext()) != null) {
                            if (headerLine != null) {
                                writer.writeNext(headerLine);
                                break;
                            }
                        }
                        hasReadHeader = true;
                    }
                    // Write content for intersection.csv.
                    writer.writeNext(chizzy_reference);
                }
            }
        }
        writer.close();
        System.out.println("intersection.csv created!");
    }
}
