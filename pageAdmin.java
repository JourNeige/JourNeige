import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class pageAdmin extends JFrame implements ActionListener {
    private Vent vent;
    private Temp temp;
    private Precip precip;

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


    public pageAdmin(Vent vent, Temp temp, Precip precip) {
        super("Page Admin");
        this.vent = vent;
        this.temp = temp;
        this.precip = precip;

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
        rangee3.add(tempEntrez);
        rangee3.add(tempEntrezText);
        add(rangee3);

        JPanel rangee4 = new JPanel();
        rangee4.setLayout(leFlow);
        rangee4.add(ventEntrez);
        rangee4.add(ventEntrezText);
        add(rangee4);

        JPanel rangee5 = new JPanel();
        rangee5.setLayout(leFlow);
        rangee5.add(precipEntrez);
        rangee5.add(precipEntrezText);
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

        if (actionEvent.getSource() == boutonEntrez) {
            String ville = villeEntrezText.getText();
            String postale = postaleEntrezText.getText();
            int temp = Integer.parseInt(tempEntrezText.getText());
            int vent = Integer.parseInt(ventEntrezText.getText());
            int precip = Integer.parseInt(precipEntrezText.getText());
        }
        if (actionEvent.getSource() == boutonRetour) {
            new Debut();
            dispose();
        }
    }

    public static void main(String[] args) {
        Vent vent = new Vent();
        vent.setVent(vent.getVent());
        Temp temp = new Temp();
        temp.setTemp(temp.getTemp());
        Precip precip = new Precip();
        precip.setPrecip(precip.getPrecip());
        new pageAdmin(vent, temp, precip);
    }
}
