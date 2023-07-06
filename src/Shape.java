import java.awt.*;

public class Shape {

    protected int x1, y1, x2, y2;
    protected Color color;
    protected int shape;

    public Shape(int x1,int y1,int x2,int y2,String c, int shape) {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.shape = shape;

        switch(c) {
            case("Black"):
                color=Color.BLACK;
                break;
            case("Red"):
                color=Color.RED;
                break;
            case("Blue"):
                color=Color.BLUE;
                break;
            case("Green"):
                color=Color.GREEN;
                break;
            case("Yellow"):
                color=Color.YELLOW;
                break;
            case("Orange"):
                color=Color.ORANGE;
                break;
            case("Pink"):
                color=Color.PINK;
                break;
            default: color=Color.WHITE;
        }
    }

    public void draw(Graphics g){
        g.setColor(color);
        switch(shape){
            case(0):
                g.fillRect((x2>x1)?x1:x2, (y2>y1)?y1:y2, (x2>x1)?x2-x1:x1-x2, (y2>y1)?y2-y1:y1-y2);
                break;
            case(1):
                g.fillOval((x2>x1)?x1:x2, (y2>y1)?y1:y2, (x2>x1)?x2-x1:x1-x2, (y2>y1)?y2-y1:y1-y2);
                break;
            case(2):
                g.fillArc((x2>x1)?x1:x2, (y2>y1)?y1:y2, (x2>x1)?x2-x1:x1-x2, (y2>y1)?y2-y1:y1-y2, 0, 180);
                break;
        }
    }
}
