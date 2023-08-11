/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Saly
 */
public class Main {

    private static void writeDataToCSV(String csvFilePath, ReplayOneDay rd) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath, true))) {

            List<String> csvFile = Files.readAllLines(Paths.get(csvFilePath));

            if (csvFile.isEmpty()) {
                String[] headers = {
                        "Type",
                        "Queue Length 30175",
                        "Queue Length 30560",
                        "Queue Length 30172",
                        "Queue Length 30066",
                        "Queue Length 30176",
                        "Queue Length 30179",
                        "Queue Length 30173",
                        "Queue Length 30511",
                        "Queue Length 30181",
                        "Queue Length 30519",
                        "Queue Length 30241",
                        "Queue Length 30174",
                        "Queue Length 30325",
                        "Queue Length 30363",
                        "Queue Length 30334",
                        "Queue Length 30180",
                        "Queue Length 30236",
                        "Queue Length 30177",
                        "Queue Length 30178",
                        "Queue Length 30584",
                        "Queue Length 30598",
                        "Queue Length 30518",
                        "Queue Length 30170",
                        "Queue Length 30694",
                        "Queue Length 30729",
                        "Queue Length 30747",
                        "Queue Length 30764",
                        "Date",
                        "Arrival Time",
                        "Number of Busy Servers",
                        "LES",
                        "Avg_LES",
                        "AvgC_Les",
                        "WAvgC_LES",
                        "Waiting Time",
                        "Is Served",
                        "Service Time"
                };

                // Écrire les en-têtes dans le fichier CSV
                writer.writeNext(headers);
            }

            // Parcourir les objets Customer de served_customer et écrire leurs données
            for (Customer customer : rd.getServed_customer()) {
                String[] rowData = {
                        Integer.toString(customer.getType()),
                        Integer.toString(customer.getQueue_length()[0]),
                        Integer.toString(customer.getQueue_length()[1]),
                        Integer.toString(customer.getQueue_length()[2]),
                        Integer.toString(customer.getQueue_length()[3]),
                        Integer.toString(customer.getQueue_length()[4]),
                        Integer.toString(customer.getQueue_length()[5]),
                        Integer.toString(customer.getQueue_length()[6]),
                        Integer.toString(customer.getQueue_length()[7]),
                        Integer.toString(customer.getQueue_length()[8]),
                        Integer.toString(customer.getQueue_length()[9]),
                        Integer.toString(customer.getQueue_length()[10]),
                        Integer.toString(customer.getQueue_length()[11]),
                        Integer.toString(customer.getQueue_length()[12]),
                        Integer.toString(customer.getQueue_length()[13]),
                        Integer.toString(customer.getQueue_length()[14]),
                        Integer.toString(customer.getQueue_length()[15]),
                        Integer.toString(customer.getQueue_length()[16]),
                        Integer.toString(customer.getQueue_length()[17]),
                        Integer.toString(customer.getQueue_length()[18]),
                        Integer.toString(customer.getQueue_length()[19]),
                        Integer.toString(customer.getQueue_length()[20]),
                        Integer.toString(customer.getQueue_length()[21]),
                        Integer.toString(customer.getQueue_length()[22]),
                        Integer.toString(customer.getQueue_length()[23]),
                        Integer.toString(customer.getQueue_length()[24]),
                        Integer.toString(customer.getQueue_length()[25]),
                        Integer.toString(customer.getQueue_length()[26]),
                        rd.getDateOfTheDay(),
                        Double.toString(customer.getArrival_time()),
                        Integer.toString(customer.getNb_server()),
                        Double.toString(customer.getLES()),
                        Double.toString(customer.getAvg_LES()),
                        Double.toString(customer.getAvgC_LES()),
                        Double.toString(customer.getWAvgC_LES()),
                        Double.toString(customer.getWaiting_time()),
                        Boolean.toString(customer.getIs_served()),
                        Double.toString(customer.getService_time())
                };
                writer.writeNext(rowData);
            }

            System.out.println("Les données du " + rd.getDateOfTheDay() + " ont été écrites avec succès dans le fichier CSV : " + csvFilePath);
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) throws IOException {

        // Spécifiez le chemin complet du fichier que vous souhaitez lire
        String fileDir = "D:\\DIC2\\Modélisation Stochastique\\data";

        File dir = new File(fileDir);
        File[] files = dir.listFiles();

        // Utilisez Files.readAllLines() pour lire toutes les lignes du fichier en une liste
        for (File file : files) {
            System.out.println("-----------------------------" + file.getName() + "-------------------------------------------------");
            try {
                List<String> lines = Files.readAllLines(Paths.get(fileDir + "\\" + file.getName()));
                lines.remove(0);

                while (!lines.isEmpty()) {
                    ReplayOneDay rod = new ReplayOneDay(lines);
                    writeDataToCSV("D:\\DIC2\\Modélisation Stochastique\\results\\" + file.getName(), rod);
                }
            } catch (IOException e) {
                // Gérez toute exception éventuelle

            }
        }

    }
}
    
