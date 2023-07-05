package sn.ept.git.dic2.events;

import sn.ept.git.dic2.Customer;
import umontreal.ssj.simevents.Event;

public class QueueArrival extends Event {
    Customer cust;

    public QueueArrival(Customer cust){
        this.cust = cust;
    }

    public void actions() {
//            Initialisation des attributs du customer
//              - des predicteurs(LES, AvgLES, AvgCLES, WAvgCLES)
//              - nb_busy_servers(le nombre de serveurs occupes)
//            Placer le cust dans la file
//            scheduler son depart(abandon ou servis) de la file dans wainting_time;
//            Incrementer le nombre de cust de ce type   (array_queue_length)

    }
}
