package mattBerk.myProjects.ct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownGUI {

    private JFrame frame;
    private JPanel eventListPanel;
    private JPanel inputPanel;
    private JLabel label1;

    private JTextField eventNameField;
    private JTextField eventDateField;
    private JTextField eventTimeField;

    private static int counter = 30;

    //Want to allow terminating a timer. Also allowing for multiple events.


    public CountdownGUI(){
        frame = new JFrame();

        eventListPanel = new JPanel();
        eventListPanel.setLayout(new BoxLayout(eventListPanel,BoxLayout.PAGE_AXIS));//Try out other configurations to see which looks best.

        inputPanel = new JPanel();//todo unsure if want to change from FollowLayout (its default). Try BoxLayout
        label1 = new JLabel("This is some placeholder text.");

        //Need to adjust, thinking will have more fields for date (i.e. month, day, year)
        eventNameField = new JTextField("Event Name");
        eventDateField = new JTextField("Enter date");
        eventTimeField = new JTextField("Enter time");
        JButton button = new JButton("Start");
        button.addActionListener(new StartListener());

        frame.setBounds(500,300,300,700);//Seems to affect where its initially opens on screen and the size of it
        //Play around with settings until get initial size you like.

        inputPanel.setSize(300,200);//Need to change, need to specify min and max

        inputPanel.add(label1);//Label can only belong to one belong, so if add same label to different panels, only one panel will have it.

        inputPanel.add(button);
        inputPanel.add(eventNameField);
        inputPanel.add(eventDateField);
        inputPanel.add(eventTimeField);


        frame.add(new JScrollPane(eventListPanel));//Adding constraint of BorderLayout would lead to
        // ScrollPanel pushing inputPanel out of view when gets to long. Seems shouldn't add
        // border constraint when dealing with a panel that grows dynamically.
        frame.add(inputPanel, BorderLayout.SOUTH);
        //frame.add(panel2, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Timer");

        //frame.pack();//this is what causes it to shrink, so only space used is what components take up
        frame.setVisible(true);

    }


    public String getEventName(){
        //TODO setup warning message if event date is blank
        String eventName = eventNameField.getText();
        return eventName;
    }
    public String getEventDate(){
        //todo setup warning message if event date is blank or incorrectly formatted
        String eventDate = eventDateField.getText();
        return eventDate;
    }
    public String getEventTime(){
        //todo setup warning message if time is incorrectly formatted or if number would cause overflow.
        String eventTime = eventTimeField.getText();
        //todo if its empty, then default to midnight
        return eventTime;
    }

    public void removePanel(JPanel val){
        eventListPanel.remove(val);
    }

    //Would create a countdown event panel.
    public class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel aPanel = new JPanel();
            aPanel.setMinimumSize(new Dimension(50,25));
            aPanel.setPreferredSize(new Dimension(100,60));
            aPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,60));

            aPanel.setBackground(new Color(120,counter-30,0));
            counter += 30;

            eventListPanel.add(aPanel);

            frame.revalidate();
            frame.repaint();//NEED these so we see panel on gui without having to manually resize.

            new EventPanel(CountdownGUI.this,aPanel,getEventName(),"HOLDER");
        }
    }

}
