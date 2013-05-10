package org.projii.serverside.gs;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.gs.interaction.OutLayer;
import java.util.Timer;
import  java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Spreader {
    final int  timeBetweenCalls=50;
    Timer timer;//timer to send messages
    ConcurrentLinkedQueue<InteractionMessage> messageQueue;
    OutLayer outLayer;
    public Spreader(OutLayer outLayer ){
        this.outLayer=outLayer;
        timer= new Timer();
        timer.schedule(timerTask,timeBetweenCalls );

    }
    public void addEvent(InteractionMessage interactionMessage)
    {
          messageQueue.add(interactionMessage);
    }
    TimerTask timerTask = new TimerTask() {
        public void run()
        {
            for(InteractionMessage message :messageQueue)
            {
                outLayer.send(message);
            }
        }
    };

}
