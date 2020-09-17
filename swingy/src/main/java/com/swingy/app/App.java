package com.swingy.app;
import javax.swing.JOptionPane;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("arg"+args[0]);
        System.out.println( "Hello World!!!!" );
        if (args[0] == "gui"){
            String fn = JOptionPane.showInputDialog("enter first no.");
            String sn = JOptionPane.showInputDialog("enter second no.");
            int no = Integer.parseInt(fn);
            int not = Integer.parseInt(sn);
            JOptionPane.showMessageDialog(null, "your sum is: "+ (no + not), "the title", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
