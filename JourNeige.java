import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JourNeige extends JFrame implements ActionListener {
    public Precip precip;
    public Temp temp;
    public Vent vent;

    JLabel villeLabel = new JLabel("Ville: ");
    JTextField villeText = new JTextField(10);
    JLabel infoLabel = new JLabel("Infos: ");
    JTextField infoText = new JTextField(40);
    JButton boutonPredire = new JButton("Prédire");
    GridLayout leGrid = new GridLayout(3, 1);
    FlowLayout leFlow = new FlowLayout();

    public JourNeige(Precip precip, Temp temp, Vent vent) {
        super("Jour de Neige");
        this.precip = precip;
        this.temp = temp;
        this.vent = vent;

        setLayout(leGrid);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel rangee1 = new JPanel();
        rangee1.setBorder(new EmptyBorder(40, 0, 0, 0));
        rangee1.add(villeLabel);
        rangee1.add(villeText);
        rangee1.setLayout(leFlow);
        add(rangee1);

        JPanel rangee2 = new JPanel();
        rangee2.setLayout(leFlow);
        rangee2.add(boutonPredire);
        boutonPredire.setPreferredSize(new Dimension(100, 100));
        boutonPredire.addActionListener(this);
        add(rangee2);

        JPanel rangee3 = new JPanel();
        rangee3.setBorder(new EmptyBorder(40, 0, 0, 0));
        rangee3.add(infoLabel);
        rangee3.add(infoText);
        rangee3.setLayout(leFlow);
        add(rangee3);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == boutonPredire) {
            infoText.setText("Précipitation: " + precip.getPrecip()
                    + ", Température: " + temp.getTemp()
                    + ", Vitesse du Vent: " + vent.getVent());
        }
    }

    public static void main(String[] args) {
        Precip precip = new Precip();
        Temp temp = new Temp();
        Vent vent = new Vent();

        new JourNeige(precip, temp, vent);
    }  
}
