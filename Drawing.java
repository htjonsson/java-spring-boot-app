import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.*;

// https://docs.oracle.com/javase/tutorial/uiswing/events/componentlistener.html
public class Drawing 
{
    public static void main(String[] args) {
        JFrame f = new JFrame();
        MyCanvas p = new MyCanvas();
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}

class View
{
    private Rectangle  _rectangle;
    
    public View(int x, int y, int width, int height) {
        _rectangle = new Rectangle(x, y, width, height);
    }

    public void setRectangle(Rectangle r) {
        this._rectangle = r;
    }

    public Rectangle getRectangle() {
        return this._rectangle;
    }
}

class Renderer
{
    private Graphics2D _graphics;

    private Color _controlColor;
    private Color _lightControlColor;
    private Color _darkControlColor;
    private Color _shadowControlColor;

    public Renderer(Graphics g) {
        _graphics = (Graphics2D)g;

        _lightControlColor = new Color(255, 255, 255);  // White
        _darkControlColor = new Color(134, 130, 134);   // Dark Gray
        _controlColor = new Color(206, 206, 206);       // Gray
        _shadowControlColor = new Color(0, 0, 0);       // Black
    }

    public void drawBox(View view) {
        Rectangle r = view.getRectangle();

        drawBox(r.x, r.y, r.width, r.height);
    }

    // https://www.codejava.net/java-se/graphics/drawing-lines-examples-with-graphics2d
    public void drawBox(int x, int y, int width, int height) {
        _graphics.setColor(Color.red);

        float[] dashingPattern = {8f, 4f};
        Stroke stroke = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashingPattern, 0.0f);

        Stroke defaultStroke = _graphics.getStroke();
        _graphics.setStroke(stroke);

        _graphics.setStroke(defaultStroke);
    }

    public void drawButton(View view) {
        Rectangle r = view.getRectangle();

        _graphics.setColor(_controlColor);

        _graphics.fillRect(r.x - 5, r.y - 5, r.width + 10, r.height + 10);

        drawButton(r.x, r.y, r.width, r.height);
    }

    public void drawButton(int x, int y, int width, int height) {
        int top = y;
        int bottom = y + height;
        int left = x;
        int right = x + width;

        // Draw button color

        _graphics.setColor(_controlColor);

        _graphics.fillRect(left, top, width, height );

        // Draw dark area of button

        _graphics.setColor(_darkControlColor);

        _graphics.drawLine(left, top, right - 1, top);
        _graphics.drawLine(left, top, left, bottom);
        _graphics.drawLine(right - 1, top, right - 1, bottom - 1);
        _graphics.drawLine(right - 2, top + 2, right - 2, bottom - 1);
        _graphics.drawLine(left + 3, bottom - 2, right - 3, bottom - 2);
        _graphics.drawLine(left + 2, bottom - 1, right - 3, bottom - 1);

        // Draw light area of button

        _graphics.setColor(_lightControlColor);

        _graphics.drawLine(right, top, right, bottom);
        _graphics.drawLine(left + 1, bottom, right, bottom);
        _graphics.drawLine(left + 1, bottom, left + 1, top + 1);
        _graphics.drawLine(left + 1, top + 1, right - 2, top + 1);
        _graphics.drawLine(left + 2, top + 2, right - 3, top + 2);
        _graphics.drawLine(left + 2, bottom - 2, left + 2, top + 3);

        // Draw text

        _graphics.setColor(Color.black);

        _graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font f = new Font("Helvetica", Font.PLAIN, 10);                

        FontMetrics fontMetrics = _graphics.getFontMetrics();

        _graphics.drawString("Cancel",70,20); 
    }
}

class PopClickListener extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        PopUpDemo menu = new PopUpDemo();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

class MyCanvas extends JPanel implements ComponentListener
{
    public MyCanvas() {
        this.addComponentListener(this);

        this.setBackground(Color.white);

        this.addMouseListener(new PopClickListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        System.out.println("paintComponent");
        
        View view = new View(20, 20, 100, 100);
        View buttonView = new View(20, 140, 98, 28);

        Renderer r = new Renderer(g);

        r.drawButton(buttonView);
    }

    public void componentHidden(ComponentEvent e) {
        System.out.println(e.getComponent().getClass().getName() + " --- Hidden");
    }

    public void componentMoved(ComponentEvent e) {
        System.out.println(e.getComponent().getClass().getName() + " --- Moved");
    }

    public void componentResized(ComponentEvent e) {
        System.out.println(e.getComponent().getClass().getName() + " --- Resized "); 

        Dimension size = e.getComponent().getSize();

        System.out.println("Size --- " + size.getWidth() +" (width) " + size.getHeight() + " (height) ");          
    }

    public void componentShown(ComponentEvent e) {
        System.out.println(e.getComponent().getClass().getName() + " --- Shown");
    }
}

// https://stackoverflow.com/questions/766956/how-do-i-create-a-right-click-context-menu-in-java-swing
class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    public PopUpDemo() {
        add(new JMenuItem("Open"));

        add(new JSeparator());

        add(new JMenuItem("Reload"));
    }
}
