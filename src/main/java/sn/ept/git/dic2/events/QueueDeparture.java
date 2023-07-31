package sn.ept.git.dic2.events;

import sn.ept.git.dic2.Customer;
import sn.ept.git.dic2.ReplayOneDay;
import umontreal.ssj.simevents.Event;
import umontreal.ssj.simevents.Sim;


public class QueueDeparture extends Event {
    Customer cust;
    ReplayOneDay rd;
    Double alpha;

    public QueueDeparture(Customer cust, ReplayOneDay rd) {
        this.cust = cust;
        this.rd = rd;
    }

    public void actions() {
        int type = rd.getMap().get(cust.getType());
        int queue_length = rd.getArray_queue_length()[type];
//      Mettre a jours les donnees utilisees par les predicteurs
        rd.updateArray_LES(type, cust.getWaiting_time());

        rd.updateArray_Avg_LES(type, cust.getWaiting_time());
        rd.updateArray_AvgC_LES(type, queue_length, cust.getWaiting_time());


        rd.updateArray_WAvgC_LES(type, queue_length, cust.getWaiting_time(), 0.5);
//         Decrementer le nb cust de ce type()  (array_queue_length)
        rd.updateArray_queue_length(type, -1);
//         Si depart == abandon alors mettre cust dans la liste des cust abandonnes  (abandon_customer)
        if (!cust.getIs_served()) {
            rd.addAbandon_customer(cust);
        } else {
            rd.addServed_customer(cust);
            //         Si depart == debutService alors incrementer nb_busy_servers
            rd.setNb_busy_servers(rd.getNb_busy_servers() + 1);
            //              schudeler la fin de service
            new EndOfService(rd).schedule(cust.getService_time());
        }

        //System.out.println("Customer left the queue");

    }
}