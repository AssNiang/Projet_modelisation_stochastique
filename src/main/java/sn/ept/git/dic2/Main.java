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

    // Méthode utilitaire pour convertir un tableau en chaîne séparée par des virgules
    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private static void writeDataToCSV(String csvFilePath, ReplayOneDay rd) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath, true))) {

            List<String> csvFile = Files.readAllLines(Paths.get(csvFilePath));

            if(csvFile.isEmpty()){
                String[] headers = {
                        "Type",
                        "Queue Length",
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
                        arrayToString(customer.getQueue_length()),
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

            System.out.println("Les données ont été écrites avec succès dans le fichier CSV " + csvFilePath);
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
                System.out.println(lines.size());

//                CSVWriter writer = new CSVWriter(new FileWriter("D:\\DIC2\\Modélisation Stochastique\\results\\" + file.getName()));
//                // Créer un tableau de chaînes pour les en-têtes de colonnes
//                List<String> csvFile = Files.readAllLines(Paths.get("D:\\DIC2\\Modélisation Stochastique\\results\\" + file.getName()));
//
//                String[] headers = {
//                        "Type",
//                        "Queue Length",
//                        "Date",
//                        "Arrival Time",
//                        "Number of Busy Servers",
//                        "LES",
//                        "Avg_LES",
//                        "AvgC_Les",
//                        "WAvgC_LES",
//                        "Waiting Time",
//                        "Is Served",
//                        "Service Time"
//                };
//
//                // Écrire les en-têtes dans le fichier CSV
//                writer.writeNext(headers);

                while (!lines.isEmpty()) {
                    ReplayOneDay rod = new ReplayOneDay(lines);
                    writeDataToCSV("D:\\DIC2\\Modélisation Stochastique\\results\\" + file.getName(), rod);
                }
                //writeDataToCSV("C:\\Users\\HP Probook\\Desktop\\customersData.csv", rod);
            } catch (IOException e) {
                // Gérez toute exception éventuelle

            }
            System.out.println(">>>>>" + file.getName());
        }


    }
}
    
