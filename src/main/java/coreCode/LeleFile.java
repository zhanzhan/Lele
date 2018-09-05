package coreCode;

import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Stanley
 * @since 180604
 */
public class LeleFile {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        BufferedReader reader = null;
        CSVWriter csvWriter1 = null;
        // C:\zApp\Lele\inFiles
        // C:\zApp\Lele\outFiles
        String textFileToRead = "C:\\zApp\\Lele\\inFiles\\Lele180802.txt";
        String dirCSVtoWrite = "C:\\zApp\\Lele\\outFiles\\";
        String fileNameSuffixToWrite = ".csv"; // File Extension
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        StringBuilder sb1 = new StringBuilder();
        sb1.append(dirCSVtoWrite);
        sb1.append("Lele.");
        sb1.append(timeStamp);
        sb1.append(fileNameSuffixToWrite);

        File file = new File(textFileToRead);
        try {
            reader = new BufferedReader(new FileReader(file));
            csvWriter1 = new CSVWriter(new FileWriter(sb1.toString()));
            String[] cells = new String[30];
            String line;
            int lineNum = 0;

            while ((line = reader.readLine()) != null) {

                System.out.println(++lineNum + ": " + line);
                String middle1[] = line.split(" ", 2);

                String arr[] = line.split(" ", 20);
                /**
                 * First Word
                 */
                String firstWord = arr[0];
                System.out.println("FirstWord: " + firstWord);
                cells[0] = firstWord;
                
                /**
                 * Description String (middle)
                 */
                StringBuilder description1 = new StringBuilder();
                for (int i = 1; i < arr.length - 1; i++) {
                    description1.append(arr[i]);
                    description1.append(" ");
                }
                System.out.println("Description: " + description1);
                cells[1] = description1.toString();

                /**
                 * Last word
                 */
                String lastWord = arr[arr.length - 1];
                System.out.println("LastWord: " + lastWord);
                
                cells[2] = lastWord;
                csvWriter1.writeNext(cells);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                csvWriter1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
