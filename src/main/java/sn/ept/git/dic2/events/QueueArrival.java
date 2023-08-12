package sn.ept.git.dic2.events;

import java.util.LinkedList;
import sn.ept.git.dic2.Customer;
import sn.ept.git.dic2.ReplayOneDay;
import umontreal.ssj.simevents.Event;

public class QueueArrival extends Event {
    Customer cust;
    ReplayOneDay rd;

    public QueueArrival(Customer cust, ReplayOneDay rd){
        this.cust = cust;
        this.rd = rd;
    }
    
    public static double getMoyenne(LinkedList<Double> liste){
        if(liste.isEmpty()){
            return 0;
        }
        Double sum = 0.0;
        for(double i : liste){
            sum+=i;
        }
        return sum/liste.size();
    }

    @Override
    public void actions() {
        int type = rd.getMap().get(cust.getType());
        int queue_length = rd.getArray_queue_length()[type];
        
        cust.setQueue_length(rd.getArray_queue_length());
//      Initialisation des attributs du customer
//        - des predicteurs(LES, AvgLES, AvgCLES, WAvgCLES)
        cust.setLES(rd.getArray_LES()[type]);
      
        cust.setAvg_LES(getMoyenne(rd.getArray_Avg_LES()[type]));
        
        cust.setAvgC_LES(getMoyenne(rd.getArray_AvgC_LES()[type][queue_length]));
        
        cust.setWAvgC_LES(rd.getWAvgC_LES(type, queue_length));
//      - nb_busy_servers(le nombre de serveurs occupes)
        cust.setNb_server(rd.getNb_busy_servers());
//      Placer le cust dans la file
        rd.addToWaitList(cust);
//      scheduler son depart(abandon ou servis) de la file dans wainting_time;
        new QueueDeparture(cust, rd).schedule(cust.getWaiting_time());
//      Incrementer le nombre de cust de ce type   (array_queue_length)
        rd.updateArray_queue_length(type, 1);
        //System.out.println("Customer arrived");
    }
}
