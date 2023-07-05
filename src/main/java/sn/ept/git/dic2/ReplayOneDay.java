package sn.ept.git.dic2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sn.ept.git.dic2.events.QueueArrival;
import umontreal.ssj.simevents.*;
import umontreal.ssj.rng.*;
import umontreal.ssj.randvar.*;
import umontreal.ssj.stat.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import umontreal.ssj.charts.HistogramChart;
import umontreal.ssj.charts.EmpiricalChart;

public class ReplayOneDay {
    LinkedList<Customer> waitList = new LinkedList<Customer> ();

    //Contains the length of queues
    private int[] array_queue_length =  new int[27];
    private double[] array_LES = new double[27];
    private LinkedList[] array_Avg_LES = new LinkedList[27];
    private LinkedList[][] array_AvgC_LES = new LinkedList[27][50];
    private double[][] array_WAvgC_LES = new double[27][50];
    private int nb_busy_servers = 0;
    private ArrayList<Customer> served_customer = new ArrayList<>();
    private ArrayList<Customer> abandon_customer = new ArrayList<>();

    private HashMap<Integer, Integer> map = new HashMap<>();


    public ReplayOneDay() {
        for (int i=0; i<27; i++){
            array_Avg_LES[i] = new LinkedList<Double>();
            for (int j=0; j<50; j++){
                array_AvgC_LES[i][j] = new LinkedList<Double>();
            }
        }
        // mapping type:index
        map.put(30175, 0);
        map.put(30560, 1);
        map.put(30172, 2);
        map.put(30066, 3);
        map.put(30176, 4);
        map.put(30179, 5);
        map.put(30173, 6);
        map.put(30511, 7);
        map.put(30181, 8);
        map.put(30519, 9);
        map.put(30241, 10);
        map.put(30174, 11);
        map.put(30325, 12);
        map.put(30363, 13);
        map.put(30334, 14);
        map.put(30180, 15);
        map.put(30236, 16);
        map.put(30177, 17);
        map.put(30178, 18);
        map.put(30584, 19);
        map.put(30598, 20);
        map.put(30518, 21);
        map.put(30170, 22);
        map.put(30694, 23);
        map.put(30729, 24);
        map.put(30747, 25);
        map.put(30764, 26);
    }

    public void createCustomerOfTheDay(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        String read_line = br.readLine();
        while (read_line != null){
            Customer cust = new Customer();
            String[] elements =  read_line.split(",");
            cust.setArrival_time(getTime(elements[0]));
            cust.setType(Integer.parseInt(elements[1]));
            cust.setWaiting_time(getWaitingTime(elements[0], elements[3], elements[6]));
            if (elements[3] == "NULL") {
                cust.setIs_served(false);
            }
            if (elements[3] != "NULL") {
                cust.setService_time(getTime(elements[6]) - getTime(elements[3]));
            }

            //On programme la rentree du cust dans la
            new QueueArrival(cust).schedule(cust.getArrival_time());
            read_line = br.readLine();
        }
    }

    public double getTime(String s) {
        String s1 = s.split(" ")[1];
        String[] time = s1.split(":");
        return Integer.parseInt(time[0])*3600 + Integer.parseInt(time[1])*60 + Integer.parseInt(time[2]) - 8*3600;
    }

    public double getWaitingTime(String arrival, String answered, String hangup){
        if (answered != "NULL") {
            return getTime(answered) - getTime(arrival);
        } else {
            return getTime(hangup) - getTime(arrival);
        }
    }




    


}
