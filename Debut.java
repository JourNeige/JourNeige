import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Debut extends JFrame implements ActionListener {
    public Meteo meteo;
    public Periode periodeDebut;
    public Periode periodeFin;
    public Jour jourDebut;
    public Jour jourFin;
    public Score score;

    JLabel labelConnexion = new JLabel("Connexion: ");
    JButton boutonUtilisateur = new JButton("Utilisateur");
    JButton boutonAdmin = new JButton("Admin");
    GridLayout leGrid = new GridLayout(2, 1);
    FlowLayout leFlow = new FlowLayout();

    public Debut(Meteo meteo, Periode periodeDebut, Periode periodeFin, Score score, Jour jourDebut, Jour jourFin) {
        super("Debut");
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.jourDebut = jourDebut;
        this.jourFin = jourFin;
        this.score = score;

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(leGrid);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel rangee1 = new JPanel();
        rangee1.setBorder(new EmptyBorder(80, 0, 0 , 0));
        rangee1.setLayout(leFlow);
        rangee1.add(labelConnexion);
        add(rangee1);

        JPanel rangee2 = new JPanel();
        rangee2.setLayout(leFlow);
        boutonUtilisateur.setPreferredSize(new Dimension(150, 150));
        boutonAdmin.setPreferredSize(new Dimension(150, 150));
        rangee2.setBorder(new EmptyBorder(0, 0, 200 , 0));
        rangee2.add(boutonUtilisateur);
        rangee2.add(boutonAdmin);
        add(rangee2);

        boutonUtilisateur.addActionListener(this);
        boutonAdmin.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == boutonUtilisateur) {
            new JourNeige(meteo, periodeDebut, periodeFin, score, jourDebut, jourFin);
            dispose();
        } // Ferme la fenêtre actuelle et ouvre la fenêtre utilisateur
        if (actionEvent.getSource() == boutonAdmin) {
            Meteo meteo = new Meteo();
            Periode periodeDebut = new Periode();
            Periode periodeFin = new Periode();
            Jour jourDebut = new Jour();
            Jour jourFin = new Jour();
            Score score = new Score();
            new PageAdmin(meteo, periodeDebut, periodeFin, score, jourDebut, jourFin);
            dispose();
        } // Ferme la fenêtre actuelle et ouvre la fenêtre admin
    }

    public static void main(String[] args) {
        Meteo meteo = new Meteo();
        Periode periodeDebut = new Periode();
        Periode periodeFin = new Periode();
        Jour jourDebut = new Jour();
        Jour jourFin = new Jour();
        Score score = new Score();
        new Debut(meteo, periodeDebut, periodeFin, score, jourDebut, jourFin);
    }
}
