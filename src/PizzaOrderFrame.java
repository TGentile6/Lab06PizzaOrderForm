import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        MainPanel = new JPanel(new BorderLayout());
        Organizer1 = new JPanel();
        Organizer1.setLayout(new BoxLayout(Organizer1, BoxLayout.Y_AXIS));
        Organizer2 = new JPanel();
        createCrustPanel();
        createSizePanel();
        createTopPanel();
        createRecPanel();
        createMenuPanel();
        Organizer1.add(CrustPanel);
        Organizer1.add(SizePanel);
        Organizer1.add(TopPanel);
        Organizer2.add(Organizer1);
        Organizer2.add(RecPanel);
        MainPanel.add(Organizer2, BorderLayout.CENTER);
        MainPanel.add(MenuPanel, BorderLayout.SOUTH);
        add(MainPanel);
    }

    private void createCrustPanel() {
        CrustPanel = new JPanel();
        CrustButtons = new ButtonGroup();
        Thin = new JRadioButton("Thin");
        Thin.setSelected(true);
        Regular = new JRadioButton("Regular");
        DeepDish = new JRadioButton("Deep-dish");
        CrustButtons.add(Thin);
        CrustButtons.add(Regular);
        CrustButtons.add(DeepDish);
        CrustPanel.add(Thin);
        CrustPanel.add(Regular);
        CrustPanel.add(DeepDish);
        TitledBorder CrustBorder;
        CrustBorder = BorderFactory.createTitledBorder("Crust");
        CrustPanel.setBorder(CrustBorder);

    }

    private void createSizePanel() {
        SizePanel = new JPanel();
        SizeBox = new JComboBox(SizeStrings);
        SizeBox.setSelectedIndex(0);
        //SizeBox.addActionListener();
        SizePanel.add(SizeBox);
        TitledBorder SizeBorder;
        SizeBorder = BorderFactory.createTitledBorder("Size");
        SizePanel.setBorder(SizeBorder);
    }

    private void createTopPanel() {
        TopPanel = new JPanel();
        PepBox = new JCheckBox("Pepperoni");
        SauBox = new JCheckBox("Sausage");
        BanBox = new JCheckBox("Banana Peppers");
        TomBox = new JCheckBox("Tomato");
        PinBox = new JCheckBox("Pineapple");
        VanBox = new JCheckBox("Vanilla Pudding");
        TopPanel.add(PepBox);
        TopPanel.add(SauBox);
        TopPanel.add(BanBox);
        TopPanel.add(TomBox);
        TopPanel.add(PinBox);
        TopPanel.add(VanBox);
        TitledBorder TopBorder;
        TopBorder = BorderFactory.createTitledBorder("Toppings");
        TopPanel.setBorder(TopBorder);
    }

    private void createRecPanel() {
        RecPanel = new JPanel();
        RecDisplay = new JTextArea("",30,30);
        RecDisplay.setEditable(false);
        RecDisplay.append("=========================================\n" +
                    "Type of Crust & Size\t\tPrice\n" +
                    "Ingredient \t\t\tPrice\n" +
                    "\n" +
                    "Sub-total: \t\t\tAmount\n" +
                    "Tax:\t\t\tAmount\n" +
                    "---------------------------------------------------------------------\n" +
                    "Total: \t\t\tTotal\n" +
                    "=========================================\n");
        RecPanel.add(RecDisplay);
    }

    private void createMenuPanel() {
        MenuPanel = new JPanel();
        QuitButton = new JButton("Quit");
        QuitButton.addActionListener((ActionEvent ae) -> {System.exit(0);});
        ClearButton = new JButton("Clear");
        OrderButton = new JButton("Order");
        OrderButton.addActionListener(new OrderListener());
        MenuPanel.add(OrderButton);
        MenuPanel.add(ClearButton);
        MenuPanel.add(QuitButton);
    }

    public class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Check for Size
            Object Size;
            String Crust = "";
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
            //Check the toppings


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

            SubTotal = BaseCost + Toppings.size();

//            RecDisplay.append("=========================================\n" +
//                    "Type of Crust & Size\t\tPrice\n" +
//                    "Ingredient \t\t\tPrice\n" +
//                    "\n" +
//                    "Sub-total: \t\t\tAmount\n" +
//                    "Tax:\t\t\tAmount\n" +
//                    "---------------------------------------------------------------------\n" +
//                    "Total: \t\t\tTotal\n" +
//                    "=========================================\n");
            RecDisplay.removeAll();
            RecDisplay.append("=========================================\n");
            RecDisplay.append(String.format("%-50s",(Size.toString() + " " + Crust + " crust " + "Pizza")) + BaseCost);


        }
    }
}
