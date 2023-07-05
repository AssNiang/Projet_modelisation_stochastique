package sn.ept.git.dic2.events;

import sn.ept.git.dic2.Customer;
import umontreal.ssj.simevents.Event;

public class QueueDeparture extends Event {
    Customer cust;

    public QueueDeparture(Customer cust){
        this.cust = cust;
    }

    public void actions() {
//         Mettre a jours les donnees utilisees par les predicteurs
//         Decrementer le nb cust de ce type()  (array_queue_length)
//         Si depart == abandon alors mettre cust dans la liste des cust abandonnes  (abandon_customer)
//         Si depart == debutService alors incrementer nb_busy_servers
//              schudeler la fin de service

    }
}