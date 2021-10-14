import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PizzaOrderFrame extends JFrame {

    //Main
    JPanel MainPanel;
    JPanel Organizer1; //Holds Crust, Size, and Topping panels
    JPanel Organizer2; //Holds Organizer1 and Receipt panels

    //Crust
    JPanel CrustPanel;
    JRadioButton Thin;
    JRadioButton Regular;
    JRadioButton DeepDish;
    ButtonGroup CrustButtons;

    //Size
    //Prices: small - 8 , medium - 12 , large - 16 , super - 20
    JPanel SizePanel;
    String[] SizeStrings = { "Small", "Medium", "Large", "Super"};
    JComboBox SizeBox;


    //Toppings
    JPanel TopPanel;
    //Toppings list: "Pepperoni", "Sausage", "Banana Peppers", "Tomato", "Pineapple", "Vanilla Pudding"
    JCheckBox PepBox;
    JCheckBox SauBox;
    JCheckBox BanBox;
    JCheckBox TomBox;
    JCheckBox PinBox;
    JCheckBox VanBox;


    //Receipt
    JPanel RecPanel;
    JTextArea RecDisplay;

    //Menu
    JPanel MenuPanel;
    JButton OrderButton;
    JButton ClearButton;
    JButton QuitButton;

    //OrderFunction
    double BaseCost;
    ArrayList<String> Toppings = new ArrayList<>();
    double SubTotal;

    public PizzaOrderFrame() throws HeadlessException {
        //initialize panels and set layout managers
        MainPanel = new JPanel(new BorderLayout());
        Organizer1 = new JPanel();
        Organizer1.setLayout(new BoxLayout(Organizer1, BoxLayout.Y_AXIS));
        Organizer2 = new JPanel();
        //create the panels using their functions
        createCrustPanel();
        createSizePanel();
        createTopPanel();
        createRecPanel();
        createMenuPanel();
        //add the pizza selection panels to organizer 1
        Organizer1.add(CrustPanel);
        Organizer1.add(SizePanel);
        Organizer1.add(TopPanel);
        //add organizer 1 and the receipt panel to organizer 2
        Organizer2.add(Organizer1);
        Organizer2.add(RecPanel);
        //add organizer 2 and the menu panel to the main panel
        MainPanel.add(Organizer2, BorderLayout.CENTER);
        MainPanel.add(MenuPanel, BorderLayout.SOUTH);
        add(MainPanel);
    }

    private void createCrustPanel() {
        CrustPanel = new JPanel();
        //create the button group
        CrustButtons = new ButtonGroup();
        //Create the radio buttons and set Thin to the default selection
        Thin = new JRadioButton("Thin");
        Thin.setSelected(true);
        Regular = new JRadioButton("Regular");
        DeepDish = new JRadioButton("Deep-dish");
        //Add the radio buttons to the button group and the panel
        CrustButtons.add(Thin);
        CrustButtons.add(Regular);
        CrustButtons.add(DeepDish);
        CrustPanel.add(Thin);
        CrustPanel.add(Regular);
        CrustPanel.add(DeepDish);
        //create a border and add the border to the panel
        TitledBorder CrustBorder;
        CrustBorder = BorderFactory.createTitledBorder("Crust");
        CrustPanel.setBorder(CrustBorder);

    }

    private void createSizePanel() {
        SizePanel = new JPanel();
        //create the combo box using the size strings from the array
        SizeBox = new JComboBox(SizeStrings);
        SizeBox.setSelectedIndex(0);
        SizePanel.add(SizeBox);
        //create a border and add the border to the panel
        TitledBorder SizeBorder;
        SizeBorder = BorderFactory.createTitledBorder("Size");
        SizePanel.setBorder(SizeBorder);
    }

    private void createTopPanel() {
        TopPanel = new JPanel();
        //create the check boxes with titles
        PepBox = new JCheckBox("Pepperoni");
        SauBox = new JCheckBox("Sausage");
        BanBox = new JCheckBox("Banana Peppers");
        TomBox = new JCheckBox("Tomato");
        PinBox = new JCheckBox("Pineapple");
        VanBox = new JCheckBox("Vanilla Pudding");
        //add check boxes to the panel
        TopPanel.add(PepBox);
        TopPanel.add(SauBox);
        TopPanel.add(BanBox);
        TopPanel.add(TomBox);
        TopPanel.add(PinBox);
        TopPanel.add(VanBox);
        //create a border and add the border to the panel
        TitledBorder TopBorder;
        TopBorder = BorderFactory.createTitledBorder("Toppings");
        TopPanel.setBorder(TopBorder);
    }

    private void createRecPanel() {
        RecPanel = new JPanel();
        //Create receipt display as a text area and set the font
        RecDisplay = new JTextArea("",30,45);
        RecDisplay.setEditable(false);
        RecDisplay.setFont(new Font("Consolas", Font.PLAIN, 12));
        //set default display text
        RecDisplay.setText("Please place an order.");
        //add text area to panel
        RecPanel.add(RecDisplay);
    }

    private void createMenuPanel() {
        MenuPanel = new JPanel();
        //Create buttons and add listeners
        QuitButton = new JButton("Quit");
        QuitButton.addActionListener((ActionEvent ae) -> {System.exit(0);});
        ClearButton = new JButton("Clear");
        ClearButton.addActionListener(new ClearListener());
        OrderButton = new JButton("Order");
        OrderButton.addActionListener(new OrderListener());
        //Add Buttons to Panel
        MenuPanel.add(OrderButton);
        MenuPanel.add(ClearButton);
        MenuPanel.add(QuitButton);
    }

    public class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Initialize variables needed
            DecimalFormat df2 = new DecimalFormat("#.##");
            Object Size;
            String Crust = "";

            //Check for Size
            Size = SizeBox.getSelectedItem();
            if (Size == "Small"){
                BaseCost = 8.00;
            }
            else if (Size == "Medium"){
                BaseCost = 12.00;
            }
            else if (Size == "Large"){
                BaseCost = 16.00;
            }
            else if (Size == "Super"){
                BaseCost = 20.00;
            }
            else{
                BaseCost = 99999.00;
            }

            //Check crust choice
            if (Thin.isSelected()){
                Crust = "Thin";
            }
            else if (Regular.isSelected()){
                Crust = "Regular";
            }
            else if (DeepDish.isSelected()){
                Crust = "Deep-dish";
            }
            else {
                Crust = "Error";

            }

            //Reset Toppings Array
            Toppings.clear();
            //Check the toppings using if statements, add them to the array if button is checked


            if(PepBox.isSelected()){
                Toppings.add("Pepperoni");
            }
            if(SauBox.isSelected()){
                Toppings.add("Sausage");
            }
            if(BanBox.isSelected()){
                Toppings.add("Banana Peppers");
            }
            if(TomBox.isSelected()){
                Toppings.add("Tomato");
            }
            if(PinBox.isSelected()){
                Toppings.add("Pineapple");
            }
            if(VanBox.isSelected()){
                Toppings.add("Vanilla Pudding");
            }

            //Calculate Subtotal using the base price + the amount of toppings
            SubTotal = BaseCost + Toppings.size();

            //Build the receipt in the Receipt Display
            RecDisplay.setText("=============================================\n");
            RecDisplay.append(String.format("%-39s",(Size.toString() + " " + Crust + " Crust " + "Pizza")) + String.format("%6s",BaseCost) + "\n");
            for(String t : Toppings){
                RecDisplay.append(String.format("%-39s",t) + String.format("%6s","1.0") + "\n");
            }
            RecDisplay.append("\n" + String.format("%-39s","Sub-Total:") + String.format("%6s",SubTotal) + "\n");
            RecDisplay.append(String.format("%-39s","Tax:") + String.format("%6s",df2.format(SubTotal * 0.07)) + "\n");
            RecDisplay.append("---------------------------------------------\n");
            RecDisplay.append(String.format("%-39s","Total:") + String.format("%6s",df2.format(SubTotal * 1.07)) + "\n");
            RecDisplay.append("=============================================\n");
        }
    }

    public class ClearListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //Reset Crust to Thin which is the default
            Thin.setSelected(true);

            //Reset the size to Small which is default
            SizeBox.setSelectedIndex(0);

            //Reset all toppings to off which is default
            PepBox.setSelected(false);
            SauBox.setSelected(false);
            BanBox.setSelected(false);
            TomBox.setSelected(false);
            PinBox.setSelected(false);
            VanBox.setSelected(false);

            //Reset receipt display to default text
            RecDisplay.setText("Please place an order.");
        }
    }
}
