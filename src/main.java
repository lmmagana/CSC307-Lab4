/*
CSC 307 - Lab 4
Name: Luis Magana
Partner: Logan Schwarz
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Main extends JFrame implements ActionListener {

    private DrawArea drawArea;
    private static int shape = 0; //Rect = 0, Circle = 1, Arc = 2
    private static String color = "Black";

    public Main() {
        super("Paint Application");

        // west panel
        setLayout(new BorderLayout());
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(6,1));
        westPanel.setBackground(Color.CYAN);

        //Drop Menu, Color Selection Menu
        JComboBox colorMenu;
        String[] colors = {"Black", "Red", "Blue", "Green", "Yellow", "Orange", "Pink"};
        colorMenu = new JComboBox(colors);
        colorMenu.addActionListener(this);

        // Shape Selection
        westPanel.add(colorMenu);
        JRadioButton rectangleRadio = new JRadioButton("Rectangle");
        JRadioButton circleRadio = new JRadioButton("Circle");
        JRadioButton arcRadio = new JRadioButton("Arc");

        rectangleRadio.addActionListener(this);
        circleRadio.addActionListener(this);
        arcRadio.addActionListener(this);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rectangleRadio);
        buttonGroup.add(circleRadio);
        buttonGroup.add(arcRadio);

        westPanel.add(rectangleRadio);
        westPanel.add(circleRadio);
        westPanel.add(arcRadio);

        rectangleRadio.setSelected(true); //Initial Selection

        add(westPanel,BorderLayout.WEST);

        // draw area
        drawArea = new DrawArea();
        drawArea.setBackground(Color.LIGHT_GRAY);
        drawArea.setShape (rectangleRadio.getText());
        drawArea.setColor((String) colorMenu.getSelectedItem());
        add(drawArea,BorderLayout.CENTER);

        // south panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        JButton undo = new JButton("Undo");
        JButton erase = new JButton("Erase");

        undo.addActionListener(this);
        erase.addActionListener(this);

        southPanel.add(undo);
        southPanel.add(erase);

        add(southPanel,BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(600,400);
        app.setVisible(true);
        app.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case("Rectangle"):
                shape = 0;
                break;
            case("Circle"):
                shape = 1;
                break;
            case("Arc"):
                shape = 2;
                break;
            case("comboBoxChanged"):
                JComboBox tmp = (JComboBox) e.getSource();
                Object selectedItem = tmp.getSelectedItem();
                if ("Black".equals(selectedItem)) {
                    color = "Black";
                } else if ("Red".equals(selectedItem)) {
                    color = "Red";
                } else if ("Blue".equals(selectedItem)) {
                    color = "Blue";
                } else if ("Green".equals(selectedItem)) {
                    color = "Green";
                } else if ("Yellow".equals(selectedItem)) {
                    color = "Yellow";
                } else if ("Orange".equals(selectedItem)) {
                    color = "Orange";
                } else if ("Pink".equals(selectedItem)) {
                    color = "Pink";
                }
            }

        if(e.getSource().getClass().getName().equals("javax.swing.JButton")) {
            if( ((JButton) e.getSource()).getText().equals("Erase")) {
            drawArea.erase();
            } else {
             drawArea.undo();
            }
        } else if(e.getSource().getClass().getName().equals("javax.swing.JRadioButton")) {
            drawArea.setShape (((JRadioButton)e.getSource()).getText());
        } else if(e.getSource().getClass().getName().equals("javax.swing.JComboBox")) {
            drawArea.setColor((String)((JComboBox)e.getSource()).getSelectedItem());
        }
    }

    public static int getShapeInt() {
        return shape;
    }

    public static String getColor() {
        return color;
    }
}