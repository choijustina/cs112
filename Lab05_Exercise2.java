import java.awt.*;
import javax.swing.*;
import java.util.EventObject;

public class Lab05_Exercise2{
    
    
    public static void main(String args[]){
        JFrame frame = new JFrame("String Concatenator");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton button1 = new JButton("Add");
        JButton button2 = new JButton("Multiply");
        JTextField field1 = new JTextField("");
        JTextField field2 = new JTextField("");
        JTextField field3 = new JTextField("");
        
        JBox body= 
            JBox.vbox(
                      JBox.vglue(),
                      JBox.vbox(field1),
                      JBox.vglue(),
                      JBox.vbox(field2),
                      JBox.vglue(),
                      JBox.hbox(button1, button2, JBox.hglue()),
                      JBox.vbox(field3)
                     );
        
        
        frame.add(body); 
        frame.setVisible(true);
        
        JEventQueue events=new JEventQueue();
        events.listenTo(button1, "button1");    //do not have to match, when button1 is clicked "button1" msg is added to queue
        events.listenTo(button2, "button2");
        while(true){
            EventObject event=events.waitEvent();
            String name=events.getName(event);
            if(name.equals("button1")){
                int num1 = Integer.parseInt(field1.getText());
                int num2 = Integer.parseInt(field2.getText());
                field3.setText(add(num1, num2));    
            }
            if(name.equals("button2")){
                int num1 = Integer.parseInt(field1.getText());
                int num2 = Integer.parseInt(field2.getText());
                field3.setText(multiply(num1, num2));
            }
        }
    }
    
    public static String add(int num1, int num2) {
        int sum = num1 + num2;
        return "" + sum;
    }
    public static String multiply(int num1, int num2) {
        int product = num1 * num2;
        return "" + product;
    }
}
            