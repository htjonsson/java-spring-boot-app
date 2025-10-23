import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

// https://www.codejava.net/java-se/swing/a-simple-jtable-example-for-display

public class TableExample extends JFrame
{
    public TableExample()
    {
        //headers for the table
        String[] columns = new String[] {
            "Name", "Type", "Default", "Create", "Set", "Get"
        };
         
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {"XmNaccelerators", "XtAccelerators", "", true, true, true},
            {"XmNancestorSensitive", "Boolean", "", false, false, false},
            {"XmNbackground", "Pixel", "", true, true, true},
            {"XmNbackgroundPixmap", "Pixmap", "XmUNSPECIFIED_PIXMAP", true, true, true},
        };
         
        final Class[] columnClass = new Class[] {
            // Integer.class, 
            String.class, 
            String.class, 
            String.class, 
            // Double.class, 
            Boolean.class,
            Boolean.class,
            Boolean.class
        };

        //create table model with data
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
         
        JTable table = new JTable(model);
         
        //add the table to the frame
        this.add(new JScrollPane(table));
         
        this.setTitle("Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TableExample();
            }
        });
    }   
}
