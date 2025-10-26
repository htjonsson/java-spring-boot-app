import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.plaf.metal.*;

// https://www.codejava.net/java-se/swing/a-simple-jtable-example-for-display
// https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable

public class ResourceWindow extends JFrame
{
    public ResourceWindow()
    {
        ArrayList<String> listCountry = new ArrayList<>();
        listCountry.add(new String("USA"));
        listCountry.add(new String("UK"));
        listCountry.add(new String("Japan"));
        listCountry.add(new String("South Korea"));
        listCountry.add(new String("Canada"));

        this.setTitle("Object Inspector");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(412, 460);
        this.setPreferredSize(new Dimension(412,460));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.add(panel, BorderLayout.CENTER);
        setup(panel);

        this.setVisible(true);
    }

    // default offset (55)
    private void addResource(JPanel panel, String name, String value, int y, Boolean isButton, Boolean isMarket)
    {
        Color backgroundColor = new Color(227, 201, 202);

        JCheckBox cbb = new JCheckBox();
        cbb.setBounds(10, y + 5, 16, 16);
        panel.add(cbb);

        if (isButton) {
            JButton bt = new JButton();
            bt.setText(name);
            bt.setBounds(31, y, 198, 27);
            panel.add(bt);
        }
        else {
            JLabel lb = new JLabel(name, SwingConstants.CENTER);
            lb.setBounds(31, y, 198, 27);
            panel.add(lb);
        }

        /*
        JComboBox<String> cb = new JComboBox<String>();
        cb.addItem("True");
        cb.addItem("False");
        cb.setBounds(233, y, 152, 27);
        panel.add(cb);
        */
        JRadioButton rbt = new JRadioButton();
        rbt.setText("True");
        rbt.setBounds(233, y, 60, 27);
        panel.add(rbt);

        JRadioButton rbf = new JRadioButton();
        rbf.setText("False");
        rbf.setBounds(233 + 60, y, 60, 27);
        rbf.setSelected(true);
        panel.add(rbf);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbt);
        bg.add(rbf);

        /*
        JTextField tf = new JTextField();
        tf.setBounds(233, y, 152, 27);
        tf.setText(value);
        if (isMarket == true) {
            tf.setBackground(backgroundColor);
        }
        panel.add(tf);
        */
        // https://en-human-begin.blogspot.com/2007/11/javas-icons-by-default.html
        Icon icon = UIManager.getIcon("OptionPane.informationIcon");
        JLabel lbi = new JLabel();
        lbi.setIcon(icon);
        lbi.setBounds(389, y-2, 32, 32);
        panel.add(lbi);
    }

    public void setup(JPanel panel) {
        JLabel lb = new JLabel();
        lb.setText("Option");
        lb.setBounds(16, 16, 58, 20);
        panel.add(lb);

        JComboBox cb = new JComboBox();
        cb.setBounds(70, 16, 71, 20);
        panel.add(cb);

        JSeparator sp = new JSeparator(SwingConstants.HORIZONTAL);
        sp.setBounds(10, 46, 400, 2);
        panel.add(sp);

        // ----------------------------------------------------------------

        setupResource(panel);
    }

    private void setupResource(JPanel panel)
    {
        addResource(panel, "Decimal points", "(0)", 55, false, true);
        addResource(panel, "Minimum", "(0)", 55 + (34 * 1), false, false);
        addResource(panel, "Minimum", "(100)", 55 + (34 * 2), false, false);
        addResource(panel, "Value", "(0)", 55 + (34 * 3), false, false);
        addResource(panel, "Font", "<Default>", 55 + (34 * 4), true, true);
        addResource(panel, "Title", "()", 55 + (34 * 5), true, true);
        addResource(panel, "Scale width", "(0)", 55 + (34 * 6), false, false);
        addResource(panel, "Scale height", "(0)", 55 + (34 * 7), false, false);
        addResource(panel, "Scale multiple", "(10)", 55 + (34 * 8), false, true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                try { 

                    // UIManager.setLookAndFeel("javax.swing.plaf.basic.BasicLookAndFeel");
                    // UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    // UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

                    MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                } 
                catch (Exception e) { 
                    System.out.println("Look and Feel not set"); 
                } 

                new ResourceWindow();
            }
        });
    }
}