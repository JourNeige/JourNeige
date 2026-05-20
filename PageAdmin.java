import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PageAdmin extends JFrame implements ActionListener {
    public Meteo meteo;
    public Periode periodeDebut;
    public Periode periodeFin;
    public Jour jourDebut;
    public Jour jourFin;
    public Score score;

    JLabel villeEntrez = new JLabel("Entrez la Ville: "); //Label avec text où l'utilisateur entre la ville
    JTextField villeEntrezText = new JTextField(10);
    JLabel postaleEntrez = new JLabel("Entrez le code Postale: "); //Label avec text où l'utilisateur entre le code postale
    JTextField postaleEntrezText = new JTextField(10);
    JLabel precipEntrez = new JLabel("Entrez la Préciptation en cm: "); //Label avec text où l'utilisateur entre les précipations de neige en cm
    JTextField precipEntrezText = new JTextField(10);
    JLabel periodeDebutEntrez = new JLabel("Entrez l'heure du début des précipitations: "); //Label avec text où l'utilisateur entre l'heure donc la neige commence
    JTextField periodeDebutEntrezText = new JTextField(10);
    JButton jourDebutBouton = new JButton("Demain?"); //Bouton pour si la neige commence aujourd'hui ou demain
    JLabel periodeFinEntrez = new JLabel("Entrez l'heure de la fin des précipitations: "); //Label avec text où l'utilisateur entre l'heure donc la neige fini 
    JTextField periodeFinEntrezText = new JTextField(10);
    JButton jourFinBouton = new JButton("Demain?"); //Bouton pour si la neige commence aujourd'hui ou demain
    JLabel tempEntrez = new JLabel("Entrez la Température en celsius: "); //Label avec text où l'utilisateur entre la température en celsius
    JTextField tempEntrezText = new JTextField(10);
    JLabel ventEntrez = new JLabel("Entrez la Vitesse du Vent en km/h "); //Label avec text où l'utilisateur entre le vitesse du vent en km"h
    JTextField ventEntrezText = new JTextField(10);
    JButton boutonEntrez = new JButton("Entrez"); //Bouton pour entrez tout les donées des labels/text d'en haut
    GridLayout leGrid = new GridLayout(9, 1);
    FlowLayout leFlow = new FlowLayout();
    JButton boutonRetour = new JButton("Retour"); //Bouton retour, qui te retourne au debut

    public PageAdmin(Meteo meteo, Periode periodeDebut, Periode periodeFin, Score score, Jour jourDebut, Jour jourFin) {
        super("Page Admin");
        this.meteo = meteo;
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.jourDebut = jourDebut;
        this.jourFin = jourFin;
        this.score = score;

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
        if (actionEvent.getSource() == boutonRetour) { //if loop si le bouton de retour est cliqué, qui te retour au page debut de connexion
                new Debut(meteo, periodeDebut, periodeFin, score, jourDebut, jourFin);
                dispose();
        }
        if (actionEvent.getSource() == jourDebutBouton) { //if loop qui incrémente jourCalculDébut s'il est cliqué. 0 = aujourd'hui, 1 = demain
            jourCalculDebut = 1;
        }
        if (actionEvent.getSource() == jourFinBouton) { //if loop qui incrémente jourCalculFin s'il est cliqué. 0 = aujourd'hui, 1 = demain
            jourCalculFin = 1;
        }
        if ((jourCalculDebut == 1) && (jourCalculFin == 0)) { //if loop qui cheque si le temp début et fin de neige sont cohérent, ex: la neige ne peut pas commencer demain, et finir aujourd'hui
            JOptionPane.showMessageDialog(this, "Erreur! La neige ne peut pas commencé demain et finir aujourd'hui... Veuillez revoir les données.");
            jourCalculDebut = 0; //reset les valeurs de jourCalcul
            jourCalculFin = 0;
        }
        if (actionEvent.getSource() == boutonEntrez) { //if loop lorsque le bouton entrez est cliqué
            String ville1 = villeEntrezText.getText(); //string pour le ville, getText de villeEntrezText
            String postale1 = postaleEntrezText.getText(); //string pour le code postale
            int precip1 = Integer.parseInt(precipEntrezText.getText()); //int pour le précipation de neige
            int temp1 = Integer.parseInt(tempEntrezText.getText()); //int pour le température donc la neige début
            int vent1 = Integer.parseInt(ventEntrezText.getText()); //int pour la vitesse de vent
            int periode1 = (Integer.parseInt(periodeDebutEntrezText.getText())); //int pour l'heure où la neige commence
            int periode2 = (Integer.parseInt(periodeFinEntrezText.getText())); //int pour l'heure où la neige fini
            meteo.setMeteo(precip1, temp1, vent1); //prend les valeurs pour set la classe meteo
            JOptionPane.showMessageDialog(this, "Données entrées pour du " + ville1 + " (" + postale1 + "):\nPrécipitation: " + precip1 + ", Début du Période: " + periode1 + ", Fin du Période: " + periode2 + "\nTempérature: " + temp1 + "\nVitesse du Vent: " + vent1 + "\nJour 1: " + jourCalculDebut + "\nJour 2: " + jourCalculFin); //affiche les donées à l'utilisateur pour vérifier que tout c'est passé bien
            try {
                FileWriter myWriter = new FileWriter("Donnees.txt", true); //un FileWriter pour entrez les valeurs dans le fichier Donnees.txt
                String infoWrite = ("Ville: " + ville1 + ", Postale: " + postale1 + ", Précipitation: " + precip1 + ", Début du Période: " + periode1 + ", Fin du Période: " + periode2 + ", Température: " + temp1 + ", Vitesse du Vent: " + vent1 + ", Jour 1: " + jourCalculDebut + ", Jour 2: " + jourCalculFin + "\n");
                Path file = Paths.get("Donnees.txt");
                List<String> lignes = Files.exists(file) ? Files.readAllLines(file) : new ArrayList<>();
                boolean trouve = false; //Un boolean, si false sa veut dire que la ville et code postale entrez par l'utilisateur n'était pas déja dans le fichier Donnees.txt, si le boolean est true, sa veut dire que le ville et code postale entrez par l'utilisateur est déja dans le fichier, qui vas servier à "overwrite" les donnees
                for (int i = 0; i < lignes.size(); i++) { //for loop qui scan tout les lignes du fichier donnees
                    if (lignes.get(i).contains("Ville: " + ville1 + ",") && (lignes.get(i).contains("Postale: " + postale1 + ","))) { //if loop lorsque la ville et code postale entrez par l'utilisateur est déja dans le fichier donnees.txt
                    lignes.set(i, infoWrite); //si le temps le permetrait,  possiblement ajouter un "reset" à chaque 24 heures 
                    trouve = true;
                    break;
                    }
                }
                if (!trouve) { //petit if loop si la ville n'as pas été trouvé, donc il n'y a rien à "overwrite"
                    lignes.add(infoWrite);
                }
                Files.write(file, lignes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) { //les catchs, s'il y a un erreur avec le fichier Donnees.txt et si les variables entrez par l'utilsateur sont cohérent
                JOptionPane.showMessageDialog(this, "Erreur de fichier");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erreur! Svp entrez des données valides.");
            }
        }
    }
    public static void main(String[] args) {
            Meteo meteo = new Meteo();
            Periode periodeDebut = new Periode();
            Periode periodeFin = new Periode();
            Jour jourDebut = new Jour();
            Jour jourFin = new Jour();
            Score score = new Score();
            new PageAdmin(meteo, periodeDebut, periodeFin, score, jourDebut, jourFin);
    }
}
