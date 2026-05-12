import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class pageAdmin extends JFrame implements ActionListener {
    public Precip precip;
    public Temp temp;
    public Vent vent;
    public Periode periodeDebut;
    public Periode periodeFin;
    public Jour jour;

    JLabel villeEntrez = new JLabel("Entrez la Ville: ");
    JTextField villeEntrezText = new JTextField(10);
    JLabel postaleEntrez = new JLabel("Entrez la Postale: ");
    JTextField postaleEntrezText = new JTextField(10);
    JLabel precipEntrez = new JLabel("Entrez la Préciptation: ");
    JTextField precipEntrezText = new JTextField(10);
    JLabel periodeDebutEntrez = new JLabel("Entrez le Début du neige: ");
    JTextField periodeDebutEntrezText = new JTextField(10);
    JLabel periodeFinEntrez = new JLabel("Entrez la Fin du neige: ");
    JTextField periodeFinEntrezText = new JTextField(10);
    JLabel tempEntrez = new JLabel("Entrez la Température: ");
    JTextField tempEntrezText = new JTextField(10);
    JLabel ventEntrez = new JLabel("Entrez la Vitesse du Vent: ");
    JTextField ventEntrezText = new JTextField(10);
    JButton boutonEntrez = new JButton("Entrez");
    GridLayout leGrid = new GridLayout(9, 1);
    FlowLayout leFlow = new FlowLayout();
    JButton boutonRetour = new JButton("Retour");

    public pageAdmin(Precip precip, Temp temp, Vent vent, Periode periodeDebut, Periode periodeFin, Jour jour) {
        super("Page Admin");
        this.precip = precip;
        this.temp = temp;
        this.vent = vent;
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.jour = jour;


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
        rangee4.add(periodeDebutEntrez);
        rangee4.add(periodeDebutEntrezText);
        add(rangee4);

        JPanel rangee5 = new JPanel();
        rangee5.setLayout(leFlow);
        rangee5.add(periodeFinEntrez);
        rangee5.add(periodeFinEntrezText);
        add(rangee5);

        JPanel rangee6 = new JPanel();
        rangee6.setLayout(leFlow);
        rangee6.add(tempEntrez);
        rangee6.add(tempEntrezText);
        add(rangee6);

        JPanel rangee7 = new JPanel();
        rangee7.setLayout(leFlow);
        rangee7.add(ventEntrez);
        rangee7.add(ventEntrezText);
        add(rangee7);

        JPanel rangee8 = new JPanel();
        rangee8.setLayout(leFlow);
        rangee8.add(boutonEntrez);
        boutonEntrez.addActionListener(this);
        rangee8.add(boutonRetour);
        boutonRetour.addActionListener(this);
        add(rangee8);

        JPanel rangee9 = new JPanel();
        rangee9.setLayout(leFlow);
        rangee9.add(boutonEntrez);
        boutonEntrez.addActionListener(this);
        rangee9.add(boutonRetour);
        boutonRetour.addActionListener(this);
        add(rangee9);
    }
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == boutonEntrez) {
            int jour1 = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez le Jour (1-31):"));
            String ville1 = villeEntrezText.getText();
            String postale1 = postaleEntrezText.getText();
            int precip1 = Integer.parseInt(precipEntrezText.getText());
            int temp1 = Integer.parseInt(tempEntrezText.getText());
            int vent1 = Integer.parseInt(ventEntrezText.getText());
            int periode1 = (Integer.parseInt(periodeDebutEntrezText.getText()));
            int periode2 = (Integer.parseInt(periodeFinEntrezText.getText()));
            precip.setPrecip(precip1);
            periodeDebut.setPeriodeDebut(periode1);
            periodeFin.setPeriodeFin(periode2);
            temp.setTemp(temp1);
            vent.setVent(vent1);
            jour.setJour(jour1);
            JOptionPane.showMessageDialog(this, "Données entrées pour du " + jour1 + "e jour\n" + ville1 + " (" + postale1 + "):\nPrécipitation: " + precip1 + ", Début du Période: " + periode1 + ", Fin du Période: " + periode2 + "\nTempérature: " + temp1 + "\nVitesse du Vent: " + vent1);
                try {
                    FileWriter myWriter = new FileWriter("Donnees.txt", true);
                    myWriter.write("Ville: " + ville1 + ", Postale: " + postale1 + ", Précipitation: " + precip1 + ", Début du Période: " + periode1 + ", Fin du Période: " + periode2 + ", Température: " + temp1 + ", Vitesse du Vent: " + vent1 + "\n");
                    myWriter.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Aucun fichier de données trouvé.");
                    e.printStackTrace();
                }
        }
        if (actionEvent.getSource() == boutonRetour) {
            new Debut(precip, temp, vent, periodeDebut, periodeFin, jour);
            dispose();
        }
    }

    public static void main(String[] args) {
            Precip precip = new Precip();
            Temp temp = new Temp();
            Vent vent = new Vent();
            Periode periodeDebut = new Periode();
            Periode periodeFin = new Periode();
            Jour jour = new Jour();
            new pageAdmin(precip, temp, vent, periodeDebut, periodeFin, jour);
        }
    }
