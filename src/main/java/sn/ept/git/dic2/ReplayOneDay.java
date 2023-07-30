package sn.ept.git.dic2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import sn.ept.git.dic2.events.QueueArrival;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import umontreal.ssj.simevents.Sim;


public class ReplayOneDay {
    LinkedList<Customer> waitList = new LinkedList<Customer> ();

    //Contains the length of queues
    private int[] array_queue_length =  new int[27];
    private double[] array_LES = new double[27];
    private LinkedList<Double>[] array_Avg_LES = new LinkedList[27];
    private LinkedList[][] array_AvgC_LES = new LinkedList[27][50];
    private double[][] array_WAvgC_LES = new double[27][50];
    private int nb_busy_servers = 0;
    private ArrayList<Customer> served_customer = new ArrayList<>();
    private ArrayList<Customer> abandon_customer = new ArrayList<>();

    private HashMap<Integer, Integer> map = new HashMap<>();
    private String dateOfTheDay;


    public ReplayOneDay(List<String> list) {
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
        
       
        Sim.init();
        createCustomerOfTheDay(list);
        Sim.start();
    }
    
    
    
    private void createCustomerOfTheDay(List<String> list) {
        dateOfTheDay = list.get(0).split(",")[0].split(" ")[0];
        String read_line = list.get(0);
        while(read_line != null && dateOfTheDay.equals(read_line.split(",")[0].split(" ")[0]) ){
            Customer cust = new Customer();

            String[] elements =  read_line.split(",");
            cust.setArrival_time(getTime(elements[0]));
            cust.setType(Integer.parseInt(elements[1]));
            cust.setWaiting_time(getWaitingTime(elements[0], elements[3], elements[6]));
            if ("NULL".equals(elements[3])) {
                cust.setIs_served(false);
            }
            if (!"NULL".equals(elements[3])) {
                cust.setService_time(getTime(elements[6]) - getTime(elements[3]));
            }
            //System.out.println(cust);
            //On programme la rentree du cust dans la
            //System.out.println(elements[0]);
            new QueueArrival(cust, this).schedule(cust.getArrival_time());
            list.remove(read_line);
            if(!list.isEmpty()){
            read_line = list.get(0);
            }
            else{
                read_line=null;
            }
        }
         
    }

   /* private void createCustomerOfTheDay(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        String read_line = br.readLine();
        dateOfTheDay = read_line.split(",")[0].split(" ")[0];
        while (read_line != null && dateOfTheDay.equals(read_line.split(",")[0].split(" ")[0]) ){
            Customer cust = new Customer();
            
            String[] elements =  read_line.split(",");
            cust.setArrival_time(getTime(elements[0]));
            cust.setType(Integer.parseInt(elements[1]));
            cust.setWaiting_time(getWaitingTime(elements[0], elements[3], elements[6]));
            if ("NULL".equals(elements[3])) {
                cust.setIs_served(false);
            }
            if (!"NULL".equals(elements[3])) {
                cust.setService_time(getTime(elements[6]) - getTime(elements[3]));
            }
            //System.out.println(cust);
            //On programme la rentree du cust dans la
            System.out.println(elements[0]);
            new QueueArrival(cust, this).schedule(cust.getArrival_time());
            read_line = br.readLine();
        }
    }*/

    public String getDateOfTheDay() {
        return dateOfTheDay;
    }

    public void setDateOfTheDay(String dateOfTheDay) {
        this.dateOfTheDay = dateOfTheDay;
    }

    public double getTime(String s) {
        String s1 = s.split(" ")[1];
        String[] time = s1.split(":");
        return Integer.parseInt(time[0])*3600 + Integer.parseInt(time[1])*60 + Integer.parseInt(time[2]) - 8*3600;
    }

    public double getWaitingTime(String arrival, String answered, String hangup){
        if (!"NULL".equals(answered)) {
            return getTime(answered) - getTime(arrival);
        } else {
            return getTime(hangup) - getTime(arrival);
        }
    }

    public LinkedList<Customer> getWaitList() {
        return waitList;
    }

    public void addToWaitList(Customer cust) {
        this.waitList.addLast(cust);
    }
    
     public void removeFromWaitList() {
        this.waitList.removeFirst();
    }


    public int[] getArray_queue_length() {
        return array_queue_length;
    }

    public void updateArray_queue_length(int type, int value) {
        this.array_queue_length[type]+=value;
    }

    public double[] getArray_LES() {
        return array_LES;
    }

    public void updateArray_LES(int type, double value) {
        this.array_LES[type] = value;
    }

    public LinkedList[] getArray_Avg_LES() {
        return array_Avg_LES;
    }

    public void updateArray_Avg_LES(int type, double value) {
          if(this.array_Avg_LES[type].size()<5){
          this.array_Avg_LES[type].addLast(value);
        }
        else{
          this.array_Avg_LES[type].addLast(value);
          this.array_Avg_LES[type].removeFirst();
        }   

    }

    public LinkedList[][] getArray_AvgC_LES() {
        return array_AvgC_LES;
    }

    public void updateArray_AvgC_LES(int type, int queue_length, double value) {
        if(this.array_AvgC_LES[type][queue_length].size()<5){
          this.array_AvgC_LES[type][queue_length].addLast(value);
        }
        else{
          this.array_AvgC_LES[type][queue_length].addLast(value);
          this.array_AvgC_LES[type][queue_length].removeFirst();
        }   
    }

    public double[][] getArray_WAvgC_LES() {
        return array_WAvgC_LES;
    }

    // s0=(1-alpha)s0 + alpha*w
    public void updateArray_WAvgC_LES(int type, int queue_length, double waiting_time, double alpha) {
        if(this.array_WAvgC_LES[type][queue_length] == 0){
            alpha = 1.0;
        }
        this.array_WAvgC_LES[type][queue_length] = (1-alpha)*this.array_WAvgC_LES[type][queue_length] + alpha*waiting_time;
    }

    public int getNb_busy_servers() {
        return nb_busy_servers;
    }

    public void setNb_busy_servers(int nb_busy_servers) {
        this.nb_busy_servers = nb_busy_servers;
    }

    public ArrayList<Customer> getServed_customer() {
        return served_customer;
    }

    public void addServed_customer(Customer cust) {
        this.served_customer.add(cust);
    }

    public ArrayList<Customer> getAbandon_customer() {
        return abandon_customer;
    }

    public void addAbandon_customer(Customer cust) {
        this.abandon_customer.add(cust);
    }

    public HashMap<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;
    }

 
    


}
