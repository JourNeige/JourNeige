import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class PageAdmin extends JFrame implements ActionListener {
    public Precip precip;
    public Temp temp;
    public Vent vent;
    public Periode periodeDebut;
    public Periode periodeFin;
    public Jour jourDebut;
    public Jour jourFin;
    public Score score;

    JLabel villeEntrez = new JLabel("Entrez la Ville: ");
    JTextField villeEntrezText = new JTextField(10);
    JLabel postaleEntrez = new JLabel("Entrez le code Postale: ");
    JTextField postaleEntrezText = new JTextField(10);
    JLabel precipEntrez = new JLabel("Entrez la Préciptation en cm: ");
    JTextField precipEntrezText = new JTextField(10);
    JLabel periodeDebutEntrez = new JLabel("Entrez l'heure du début des précipitations: ");
    JTextField periodeDebutEntrezText = new JTextField(10);
    JButton jourDebutBouton = new JButton("Demain?");
    JLabel periodeFinEntrez = new JLabel("Entrez l'heure de la fin des précipitations: ");
    JTextField periodeFinEntrezText = new JTextField(10);
    JButton jourFinBouton = new JButton("Demain?");
    JLabel tempEntrez = new JLabel("Entrez la Température en celsius: ");
    JTextField tempEntrezText = new JTextField(10);
    JLabel ventEntrez = new JLabel("Entrez la Vitesse du Vent en km/h ");
    JTextField ventEntrezText = new JTextField(10);
    JButton boutonEntrez = new JButton("Entrez");
    GridLayout leGrid = new GridLayout(9, 1);
    FlowLayout leFlow = new FlowLayout();
    JButton boutonRetour = new JButton("Retour");

    public PageAdmin(Precip precip, Temp temp, Vent vent, Periode periodeDebut, Periode periodeFin, Score score, Jour jourDebut, Jour jourFin) {
        super("Page Admin");
        this.precip = precip;
        this.temp = temp;
        this.vent = vent;
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.jourDebut = jourDebut;
        this.jourFin = jourFin;
        this.score = score;

        setLayout(leGrid);
        setSize(1000, 400);
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
        rangee4.add(jourDebutBouton);
        jourDebutBouton.addActionListener(this);
        add(rangee4);

        JPanel rangee5 = new JPanel();
        rangee5.setLayout(leFlow);
        rangee5.add(periodeFinEntrez);
        rangee5.add(periodeFinEntrezText);
        rangee5.add(jourFinBouton);
        jourFinBouton.addActionListener(this);
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

    }
    int jourCalculDebut = 0;
    int jourCalculFin = 0;
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == jourDebutBouton) {
            jourCalculDebut = 1;
        }
        if (actionEvent.getSource() == jourFinBouton) {
            jourCalculFin = 1;
        }
        if (actionEvent.getSource() == boutonEntrez) {
            String ville1 = villeEntrezText.getText();
            String postale1 = postaleEntrezText.getText();
            int precip1 = Integer.parseInt(precipEntrezText.getText());
            int temp1 = Integer.parseInt(tempEntrezText.getText());
            int vent1 = Integer.parseInt(ventEntrezText.getText());
            int periode1 = (Integer.parseInt(periodeDebutEntrezText.getText()));
            int periode2 = (Integer.parseInt(periodeFinEntrezText.getText()));
            JOptionPane.showMessageDialog(this, "Données entrées pour du " + ville1 + " (" + postale1 + "):\nPrécipitation: " + precip1 + ", Début du Période: " + periode1 + ", Fin du Période: " + periode2 + "\nTempérature: " + temp1 + "\nVitesse du Vent: " + vent1 + "\nJour 1: " + jourCalculDebut + "\nJour 2: " + jourCalculFin);
                try {
                    FileWriter myWriter = new FileWriter("Donnees.txt", true);
                    myWriter.write("Ville: " + ville1 + ", Postale: " + postale1 + ", Précipitation: " + precip1 + ", Début du Période: " + periode1 + ", Fin du Période: " + periode2 + ", Température: " + temp1 + ", Vitesse du Vent: " + vent1 + ", Jour 1: " + jourCalculDebut + ", Jour 2: " + jourCalculFin + "\n");
                    myWriter.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Aucun fichier de données trouvé.");
                    e.printStackTrace();
                }
        }
        if (actionEvent.getSource() == boutonRetour) {
            new Debut(precip, temp, vent, periodeDebut, periodeFin, score, jourDebut, jourFin);
            dispose();
        }
    }

    public static void main(String[] args) {
            Precip precip = new Precip();
            Temp temp = new Temp();
            Vent vent = new Vent();
            Periode periodeDebut = new Periode();
            Periode periodeFin = new Periode();
            Jour jourDebut = new Jour();
            Jour jourFin = new Jour();
            Score score = new Score();
            new PageAdmin(precip, temp, vent, periodeDebut, periodeFin, score, jourDebut, jourFin);
        }
    }
