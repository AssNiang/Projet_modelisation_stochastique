package sn.ept.git.dic2.events;


import sn.ept.git.dic2.ReplayOneDay;
import umontreal.ssj.simevents.Event;



public class EndOfService extends Event {
    // ---
    
    ReplayOneDay rd;
    

    public EndOfService(ReplayOneDay rd) {
        this.rd = rd;
    }
    




    @Override
    public void actions() {
//          Decrementer le nb_busy_servers
       rd.setNb_busy_servers(rd.getNb_busy_servers()-1);
        System.out.println("Customer gone");
    }
}