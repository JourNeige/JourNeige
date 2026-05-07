import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Debut extends JFrame implements ActionListener {
    public Precip precip;
    public Temp temp;
    public Vent vent;
    JLabel labelConnexion = new JLabel("Connexion: ");
    JButton boutonUtilisateur = new JButton("Utilisateur");
    JButton boutonAdmin = new JButton("Admin");

    public Debut(Precip precip, Temp temp, Vent vent) {
        super("Debut");
        this.vent = vent;
        this.temp = temp;
        this.precip = precip;
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

            new JourNeige(precip, temp, vent);
            dispose();
        } // Ferme la fenêtre actuelle et ouvre la fenêtre utilisateur
        if (e.getSource() == boutonAdmin) {
            Precip precip = new Precip();
            Temp temp = new Temp();
            Vent vent = new Vent();
            new pageAdmin(precip, temp, vent);
            dispose();
        } // Ferme la fenêtre actuelle et ouvre la fenêtre admin
    }

    public static void main(String[] args) {
        Precip precip = new Precip();
        Temp temp = new Temp();
        Vent vent = new Vent();
        new Debut(precip, temp, vent);
    }
}
