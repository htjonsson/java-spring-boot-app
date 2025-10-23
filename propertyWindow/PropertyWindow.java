import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

// https://www.codejava.net/java-se/swing/a-simple-jtable-example-for-display
// https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable

public class PropertyWindow extends JFrame
{
    public PropertyWindow()
    {
        // headers for the table
        String[] columns = new String[] {
            "Name", "Value"
        };

        // actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {"Align", "alNone"},
            {"AutoScroll", "False"},
            {"+BorderIcons", "[biSystemMenu]"},
            {"BorderStyle", "bsSizable"},
            {"Bottom", "116"},
            {"Caption", "Form1"},
            {"ClientHeight", "116"},
            {"ClientWidth", "272"},
            {"Color", "clDlgWindow"},
            {"Ctl3d", "True"},
            {"+EnableDock", "[]"},
            {"Enabled", "True"},
            {"Font", "(TFont)"},
        };

        final Class[] columnClass = new Class[] {
            String.class,
            String.class
        };

        // create table model with data
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
        };

        JTable propertyTable = new JTable(model);
        propertyTable.setRowHeight(22);
        propertyTable.setDefaultRenderer(String.class, new CustomDateCellRenderer());
        propertyTable.getTableHeader().setUI(null);

        // -------------------------------------------------------------------------

        final DefaultComboBoxModel fruitsName = new DefaultComboBoxModel();

        fruitsName.addElement("Apple");
        fruitsName.addElement("Grapes");
        fruitsName.addElement("Mango");
        
        fruitsName.addElement("Peer");
        String[] comboboxStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

        JComboBox combobox = new JComboBox(fruitsName);

        // -------------------------------------------------------------------------

        JPanel propertyPanel = new JPanel();
        propertyPanel.setLayout(new BorderLayout());
        propertyPanel.add(new JScrollPane(propertyTable), BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);

        tabbedPane.addTab("Properties", propertyPanel);

        tabbedPane.addTab("Actions", actionPanel);

        // -------------------------------------------------------------------------

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
    
        this.add(combobox, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);

        // this.setMargin(new Insets(50, 50, 50, 50));

        // this.add(new JScrollPane(table), BorderLayout.CENTER);

        // -------------------------------------------------------------------------

        this.setTitle("Object Inspector");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(320, 500);
        // this.pack();
        this.setVisible(true);
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

                    // MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                } 
                catch (Exception e) { 
                    System.out.println("Look and Feel not set"); 
                } 

                new PropertyWindow();
            }
        });
    }
}

class CustomDateCellRenderer extends DefaultTableCellRenderer{
    @Override
        public Component getTableCellRendererComponent(JTable table, 
                                                        Object value, 
                                                        boolean isSelected,
                                                        boolean hasFocus, 
                                                        int row, 
                                                        int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);    

        if(value instanceof String){
            System.out.println("row: " + row + " column: " + column);
            // this is where I display the age from the value variable
        }

        if (column == 0) {
            // cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
            cellComponent.setForeground(Color.black);
        }
        
        if (column == 1) {
            cellComponent.setForeground(Color.blue);
        }


        return cellComponent;
    }
}
