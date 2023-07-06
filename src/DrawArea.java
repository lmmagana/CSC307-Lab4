import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;
import javax.swing.JPanel;

public class DrawArea extends JPanel implements MouseListener, MouseMotionListener {

    private Stack<Shape> shapes;
    private String shape;
    private String color;
    private boolean drawing;
    private int x, y, x2, y2;

    public DrawArea() {
        addMouseListener(this);
        addMouseMotionListener(this);
        shapes = new Stack<Shape>();
        drawing=false;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void undo() {
        if(!shapes.isEmpty()) shapes.pop();
        repaint();
    }

    public void erase() {
        if(!shapes.isEmpty()) shapes.clear();
        repaint();
    }

    public void paintComponent (Graphics g) {

        setBackground(Color.LIGHT_GRAY);
        super.paintComponent(g);

        if(shapes!=null) {
            for(Shape shape: shapes) shape.draw(g);
        }
        g.setColor(Color.GRAY);
        if (drawing) {
            switch (Main.getShapeInt()){
                case(0):
                    g.drawRect((x2>x)?x:x2, (y2>y)?y:y2, (x2>x)?x2-x:x-x2, (y2>y)?y2-y:y-y2);
                    break;
                case(1):
                    g.drawOval((x2>x)?x:x2, (y2>y)?y:y2, (x2>x)?x2-x:x-x2, (y2>y)?y2-y:y-y2);
                    break;
                case(2):
                    g.drawArc((x2>x)?x:x2, (y2>y)?y:y2, (x2>x)?x2-x:x-x2, (y2>y)?y2-y:y-y2, 0, 180);
                    g.drawLine(x,(y + (y2 -y)/2),x2,(y + (y2 -y)/2));
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawing=true;
        x=e.getX();
        y=e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawing=false;
        x2 = e.getX();
        y2 = e.getY();
        shapes.push(new Shape(x,y,x2,y2,Main.getColor(), Main.getShapeInt()));

        /*if(shape.equals("Rectangle")) {shapes.push(new Rectangle(x,y,x2,y2,color)); }
        if(shape.equals("Circle")) {shapes.push(new /Circle(x,y,x2,y2,color)); }
        if(shape.equals("Arc")) {shapes.push(new Arc(x,y,x2,y2,color)); }*/

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2=e.getX();
        y2=e.getY();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

}