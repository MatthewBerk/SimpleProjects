package mattBerk.myProjects.ct;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventPanel {

    private JPanel threadEventPanel;
    private JButton deleteButton;
    private String threadEventName;
    private String threadEventDate;
    private String threadEventTime;
    private CountdownGUI countdownGuiAttached;
    private JLabel eventName;
    private JLabel timeRemaining;

    private DateCountDownThread thread;


    public EventPanel(CountdownGUI countdownGuiAttached, JPanel threadEventPanel, String name, String timeRemaining){
        this.countdownGuiAttached = countdownGuiAttached;
        this.threadEventPanel = threadEventPanel;

        //need to add button and label
        eventName = new JLabel(name);
        this.timeRemaining = new JLabel(timeRemaining);
        deleteButton = new JButton("X");
        deleteButton.addActionListener(new DeleteEventListener());
        threadEventPanel.add(eventName);
        threadEventPanel.add(this.timeRemaining);
        threadEventPanel.add(deleteButton);
        thread = new DateCountDownThread();
        thread.start();
    }


    public class DeleteEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //so have to terminate thread and remove panel from GUI
            thread.terminate();
            countdownGuiAttached.removePanel(threadEventPanel);
        }
    }

    public class DateCountDownThread extends Thread{

        private volatile boolean running = true;

        public void terminate(){
            running = false;
        }

        public void run(){
            while(running){
                for(int i = 1; i < 100; i++){
                    //threadEventLabel.setText(i+"");
                    timeRemaining.setText(i + "");//todo thinking I should have separate labels for each part of time
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                        running = false;
                    }
                }
            }

        }

    }
}
