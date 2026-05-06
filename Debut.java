import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Debut extends JFrame implements ActionListener {
    JLabel labelConnexion = new JLabel("Connexion: ");
    JButton boutonUtilisateur = new JButton("Utilisateur");
    JButton boutonAdmin = new JButton("Admin");

    public Debut() {
        super("Debut");
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
            Vent vent = new Vent();
            vent.setVent(vent.getVent());

            Temp temp = new Temp();
            temp.setTemp(temp.getTemp());

            Precip precip = new Precip();
            precip.setPrecip(precip.getPrecip());

            new JourNeige(vent, temp, precip);
            dispose();
        }
        if (e.getSource() == boutonAdmin) {
            Vent vent = new Vent();
            vent.setVent(vent.getVent());

            Temp temp = new Temp();
            temp.setTemp(temp.getTemp());

            Precip precip = new Precip();
            precip.setPrecip(precip.getPrecip());
            new pageAdmin(vent, temp, precip);
            dispose();
        }
    }

    public static void main(String[] args) {
        new Debut();
    }
}
