import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class UIComponentLibrary
{
    public static JButton CreateJButton(String name, int sizeX, int sizeY, int posX, int posY, ActionListener listener, JPanel panel, SpringLayout layout)
    {
        JButton myButton = new JButton(name); //Creates button and sets text
        myButton.addActionListener(listener); //Adds action listener to register click events
        myButton.setPreferredSize(new Dimension(sizeX,sizeY)); //Sets button size
        layout.putConstraint(SpringLayout.WEST,myButton,posX, SpringLayout.WEST,  panel); //Sets button's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myButton,posY, SpringLayout.NORTH,  panel); //Sets button's Y Coordinates
        panel.add(myButton); //Adds button to panel
        return myButton; //Returns completed button to caller.
    }

    public static JTextField CreateAJTextField(int size, int posX, int posY, JPanel frame, SpringLayout layout)
    {
        JTextField myTextField = new JTextField(size); //Creates JTextField and sets size
        layout.putConstraint(SpringLayout.WEST,myTextField,posX, SpringLayout.WEST,  frame);//Sets text field's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myTextField,posY, SpringLayout.NORTH,  frame);//Sets text field's Y Coordinates
        frame.add(myTextField); //Adds text field to frame
        return  myTextField; //Returns completed text field to caller
    }

    public static JTextArea CreateAJTextArea(int cols, int rows, int posX, int posY, JPanel frame, SpringLayout layout)
    {
        JTextArea myTextArea = new JTextArea(rows, cols); //Creates JTextField and sets size
        layout.putConstraint(SpringLayout.WEST,myTextArea,posX, SpringLayout.WEST,  frame);//Sets text field's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myTextArea,posY, SpringLayout.NORTH,  frame);//Sets text field's Y Coordinates
        frame.add(myTextArea); //Adds text field to frame
        myTextArea.setBorder(new LineBorder(Color.BLACK));
        return  myTextArea; //Returns completed text field to caller
    }

    public static JLabel CreateAJLabel(String text, int posX, int posY, JPanel frame, SpringLayout layout)
    {
        JLabel myLabel = new JLabel(text); //Creates label and sets text
        layout.putConstraint(SpringLayout.WEST,myLabel,posX, SpringLayout.WEST,  frame);//Sets labels's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myLabel,posY, SpringLayout.NORTH,  frame);//Sets label's Y Coordinates
        frame.add(myLabel); // Adds label to frame
        return myLabel; //Returns completed label to caller.
    }
}