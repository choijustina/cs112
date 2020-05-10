import java.awt.*;
import javax.swing.*;
import java.util.EventObject;

public class Lab05{
    
    /*
     public static void main(String args[]){
     JFrame frame=new JFrame("String Concatenator");
     frame.setSize(600,600);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
     JButton button1=new JButton("Concatenate"); 
     JTextField field1=new JTextField(""); 
     
     JBox body= 
     JBox.vbox( button1, field1 ); 
     
     frame.add(body); 
     frame.setVisible(true);
     
     JEventQueue events=new JEventQueue();
     events.listenTo(button1,"button1");
     while(true){
     EventObject event=events.waitEvent();
     String name=events.getName(event);
     if(name.equals("button1")){
     String s = field1.getText();  
     field1.setText(s+s);    
     
     }
     }}*/
    public static void main (String args[]) {
        JFrame frame=new JFrame("CheckLabel0");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l1=new JLabel("Label 1");
        JLabel l2=new JLabel("Label 2");
        JLabel l3=new JLabel("Label 3");
        JBox body=
            JBox.vbox(
                      JBox.hbox(l1,JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(),l2,JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(),l3)
                     );
        frame.add(body);
        frame.setVisible(true);
    }
}