import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

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
                if (column != 1) {
                    return false;
                }

                return true;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Object value = super.getValueAt(rowIndex, columnIndex);

                System.out.println("[getValue] " + value.toString());
                return value;
            }
            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                System.out.println("[setValue]" + aValue.toString());
                super.setValueAt(aValue, rowIndex, columnIndex);
            }
        };

        ArrayList<String> listCountry = new ArrayList<>();
        listCountry.add(new String("USA"));
        listCountry.add(new String("UK"));
        listCountry.add(new String("Japan"));
        listCountry.add(new String("South Korea"));
        listCountry.add(new String("Canada"));

        JTable propertyTable = new JTable(model);
        propertyTable.setRowHeight(22);
        propertyTable.setDefaultRenderer(String.class, new CustomDateCellRenderer());
        propertyTable.setDefaultEditor(String.class, new CustomCellEditor(listCountry));
        propertyTable.getTableHeader().setUI(null);

        // -------------------------------------------------------------------------

        final DefaultComboBoxModel classNames = new DefaultComboBoxModel();

        classNames.addElement("XmPushButton");
        classNames.addElement("XmLabel");
        classNames.addElement("XmPrimitive");
        classNames.addElement("Core");
        classNames.addElement("Rect");
        classNames.addElement("Object");

        JComboBox combobox = new JComboBox(classNames);

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

        // -------------------------------------------------------------------------

        this.setTitle("Object Inspector");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 280);
        // this.setPreferredSize(new Dimension(800,250));
        this.pack();
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
                    // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); 
                    // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

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
            // System.out.println("row: " + row + " column: " + column);
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

class CustomCellTextChooser {
    private JTextField _textField = new JTextField();

    public CustomCellTextChooser(ActionListener actionListener) {
        _textField.addActionListener(actionListener);
    }

    public Component getTableCellEditorComponent(JTable table, int row, int column) {
        _textField.setText("JTextField");

        return _textField;
    }   
}

class CustomCellOptionChooser {
    private ArrayList<String> _stringList = new ArrayList();
    private JComboBox<String> _comboBox = new JComboBox<String>();
    private ActionListener _actionListener;

    public CustomCellOptionChooser(ArrayList<String> stringList, ActionListener actionListener) {
        for (String stringValue : stringList) {
            _comboBox.addItem(stringValue);
        }        
        _comboBox.addActionListener(actionListener);
    }

    public Component getTableCellEditorComponent(JTable table, int row, int column) {
        return _comboBox;
    }
}

class CustomCellColorChooser {
    private JColorChooser   _colorChooser;
    private JDialog         _dialog;
    private JLabel          _label;
    private Color           _currentColor;   

    public CustomCellColorChooser(ActionListener okListener) {
        _label = new JLabel();
        _colorChooser = new JColorChooser();
        _dialog = JColorChooser.createDialog(_label,
                                    "Pick a Color",
                                    true,  //modal
                                    _colorChooser,
                                    okListener,  //OK button handler
                                    null); //no CANCEL button handler   
        // _label.setActionCommand("CustomCellColorChooser");    
    }

    public void setCurrentColor(Color color) {
        _currentColor = color;
    }

    public Color getCurrentColor() {
        return _currentColor;
    }

    public Component getTableCellEditorComponent(JTable table, int row, int column) {
        _colorChooser.setColor(_currentColor);

        // _dialog.setLocationRelativeTo(_button);
            
        setLocation(table, row, column);
            
        _dialog.setVisible(true);    
  
        return _label;
    }

    private void setLocation(JTable table, int row, int column) {
        Rectangle cellRectangle = table.getCellRect(row, column, true);

        Point tableLocationOnScreen = table.getLocationOnScreen();

        _dialog.setLocation((int)(cellRectangle.getX() + tableLocationOnScreen.getX()), 
                            (int)(cellRectangle.getY() + tableLocationOnScreen.getY()));

        // System.out.println("locationOnScreen [x,y] [" + table.getLocationOnScreen().getX() + "," + table.getLocationOnScreen().getY()  + "]");
        // System.out.println("table [x,y] [" + table.getX() + "," + table.getY() + "]");
        // System.out.println("cell [x,y] [" + cellRectangle.getX() + "," + cellRectangle.getY() + "]");
    }
}

class CustomCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private CustomCellColorChooser  _colorChooser;
    private CustomCellOptionChooser _optionChooser;
    private CustomCellTextChooser   _textChooser;

    public CustomCellEditor(ArrayList<String> stringList) {
        _colorChooser = new CustomCellColorChooser(this);
        _optionChooser = new CustomCellOptionChooser(stringList, this);
        _textChooser = new CustomCellTextChooser(this);
    }
     
    @Override
    public Object getCellEditorValue() {
        System.out.println("[getCellEditorValue]");

        return "Object";
        // return this.cellValue;
        // return this.currentColor.toString();
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        System.out.println("[row,column] " + "[" + row + "," + column + "]");

        if (row == 0) {
            return _optionChooser.getTableCellEditorComponent(table, row, column);    
        }
        if (row == 1) {
            return _colorChooser.getTableCellEditorComponent(table, row, column);
        }
        
        return _textChooser.getTableCellEditorComponent(table, row, column);
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("[actionCommand] [" + event.getActionCommand() + "]");
        System.out.println(event.getSource().getClass().getName());

        /*
        if (EDIT.equals(event.getActionCommand())) {
            System.out.println("[actionPerformed::edit]");
            // fireEditingStopped(); //Make the renderer reappear.
        } 
        else { //User pressed dialog's "OK" button.
            System.out.println("[actionPerformed::getColor]");
            // currentColor = colorChooser.getColor();
            // System.out.println("[currentColor] " + currentColor.toString());
        }
        */

        if (event.getSource() instanceof CustomCellColorChooser) {
            System.out.println("instanceof CustomCellColorChooser");
        }

        fireEditingStopped();

        // JButton btn = (JButton) event.getSource();

        // System.out.println("Button x: " + btn.getLocation().getX());

        // JComboBox<String> comboCountry = (JComboBox<String>) event.getSource();
        // this.country = (String) comboCountry.getSelectedItem();
    }
}
