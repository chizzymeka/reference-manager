import analysers.CSVAnalyser;

import java.io.IOException;

public class Main {

    public  static void main(String[] args) throws IOException {
        CSVAnalyser csvAnalyser = new CSVAnalyser();
        csvAnalyser.createRelevantPapersIntersectionCSVFile();
    }
}
