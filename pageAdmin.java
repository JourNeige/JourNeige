import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class pageAdmin extends JFrame implements ActionListener {
    public Precip precip;
    public Temp temp;
    public Vent vent;


    JLabel villeEntrez = new JLabel("Entrez la Ville: ");
    JTextField villeEntrezText = new JTextField(10);
    JLabel postaleEntrez = new JLabel("Entrez la Postale: ");
    JTextField postaleEntrezText = new JTextField(10);
    JLabel tempEntrez = new JLabel("Entrez la Température: ");
    JTextField tempEntrezText = new JTextField(10);
    JLabel ventEntrez = new JLabel("Entrez la Vitesse du Vent: ");
    JTextField ventEntrezText = new JTextField(10);
    JLabel precipEntrez = new JLabel("Entrez la Préciptation: ");
    JTextField precipEntrezText = new JTextField(10);
    JButton boutonEntrez = new JButton("Entrez");
    GridLayout leGrid = new GridLayout(6, 1);
    FlowLayout leFlow = new FlowLayout();
    JButton boutonRetour = new JButton("Retour");


    public pageAdmin(Precip precip, Temp temp, Vent vent) {
        super("Page Admin");
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
        rangee1.add(villeEntrez);
        rangee1.add(villeEntrezText);
        rangee1.setLayout(leFlow);
        add(rangee1);


        JPanel rangee2 = new JPanel();
        rangee2.setLayout(leFlow);
        rangee2.add(postaleEntrez);
        rangee2.add(postaleEntrezText);
        add(rangee2);

        JPanel rangee3 = new JPanel();
        rangee3.setLayout(leFlow);
        rangee3.add(precipEntrez);
        rangee3.add(precipEntrezText);
        add(rangee3);

        JPanel rangee4 = new JPanel();
        rangee4.setLayout(leFlow);
        rangee4.add(tempEntrez);
        rangee4.add(tempEntrezText);
        add(rangee4);

        JPanel rangee5 = new JPanel();
        rangee5.setLayout(leFlow);
        rangee5.add(ventEntrez);
        rangee5.add(ventEntrezText);
        add(rangee5);

        JPanel rangee6 = new JPanel();
        rangee6.setLayout(leFlow);
        rangee6.add(boutonEntrez);
        boutonEntrez.addActionListener(this);
        rangee6.add(boutonRetour);
        boutonRetour.addActionListener(this);
        add(rangee6);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        int precip1 = 0;
        int temp1 = 0;
        int vent1 = 0;

        if (actionEvent.getSource() == boutonEntrez) {
            String ville1 = villeEntrezText.getText();
            String postale1 = postaleEntrezText.getText();
            precip1 = Integer.parseInt(precipEntrezText.getText());
            temp1 = Integer.parseInt(tempEntrezText.getText());
            vent1 = Integer.parseInt(ventEntrezText.getText());
            precip.setPrecip(precip1);
            temp.setTemp(temp1);
            vent.setVent(vent1);
            JOptionPane.showMessageDialog(this, "Données entrées pour " + ville1 + " (" + postale1 + "):\nPrécipitation: " + precip1 + "\nTempérature: " + temp1 + "\nVitesse du Vent: " + vent1);
        }
        if (actionEvent.getSource() == boutonRetour) {
            new Debut(precip, temp, vent);
            dispose();
        }
    }

    public static void main(String[] args) {
        Precip precip = new Precip();
        Temp temp = new Temp();
        Vent vent = new Vent();
        new pageAdmin(precip, temp, vent);
    }
}
