import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Debut extends JFrame implements ActionListener {
    public Precip precip;
    public Temp temp;
    public Vent vent;
    public Periode periodeDebut;
    public Periode periodeFin;
    public Jour jourDebut;
    public Jour jourFin;
    public Score score;

    JLabel labelConnexion = new JLabel("Connexion: ");
    JButton boutonUtilisateur = new JButton("Utilisateur");
    JButton boutonAdmin = new JButton("Admin");

    public Debut(Precip precip, Temp temp, Vent vent, Periode periodeDebut, Periode periodeFin, Score score, Jour jourDebut, Jour jourFin) {
        super("Debut");
        this.vent = vent;
        this.temp = temp;
        this.precip = precip;
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.jourDebut = jourDebut;
        this.jourFin = jourFin;
        this.score = score;

        setSize(600, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        add(labelConnexion);
        add(boutonUtilisateur);
        add(boutonAdmin);
        boutonUtilisateur.addActionListener(this);
        boutonAdmin.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonUtilisateur) {
            new JourNeige(precip, temp, vent, periodeDebut, periodeFin, score, jourDebut, jourFin);
            dispose();
        } // Ferme la fenêtre actuelle et ouvre la fenêtre utilisateur
        if (e.getSource() == boutonAdmin) {
            Precip precip = new Precip();
            Temp temp = new Temp();
            Vent vent = new Vent();
            Periode periodeDebut = new Periode();
            Periode periodeFin = new Periode();
            Jour jourDebut = new Jour();
            Jour jourFin = new Jour();
            Score score = new Score();
            new PageAdmin(precip, temp, vent, periodeDebut, periodeFin, score, jourDebut, jourFin);
            dispose();
        } // Ferme la fenêtre actuelle et ouvre la fenêtre admin
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
        new Debut(precip, temp, vent, periodeDebut, periodeFin, score, jourDebut, jourFin);
    }
}
