import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JourNeige extends JFrame implements ActionListener {
    public Precip precip;
    public Temp temp;
    public Vent vent;
    public Periode periodeDebut;
    public Periode periodeFin;
    public Jour jour;

    JLabel villeLabel = new JLabel("Ville: ");
    JTextField villeText = new JTextField(10);
    JLabel infoLabel = new JLabel("Infos: ");
    JTextField infoText = new JTextField(40);
    JButton boutonPredire = new JButton("Prédire");
    GridLayout leGrid = new GridLayout(3, 1);
    FlowLayout leFlow = new FlowLayout();

    public JourNeige(Precip precip, Temp temp, Vent vent, Periode periodeDebut, Periode periodeFin, Jour jour) {
        super("Jour de Neige");
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
        String villeString = villeText.getText();
        String villeScan = "";
        int score;
        if (actionEvent.getSource() == boutonPredire) {
            File file = new File("Donnees.txt");
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains(villeString)) {
                        String[] parts = line.split(", ");
                        villeScan = parts[0].split(": ")[1];
                        String postale1 = parts[1].split(": ")[1];
                        int precip1 = Integer.parseInt(parts[2].split(": ")[1]);
                        int temp1 = Integer.parseInt(parts[3].split(": ")[1]);
                        int vent1 = Integer.parseInt(parts[4].split(": ")[1]);
                        int periode1 = Integer.parseInt(parts[5].split(": ")[1]);
                        int periode2 = Integer.parseInt(parts[6].split(": ")[1]);
                        precip.setPrecip(precip1);
                        temp.setTemp(temp1);
                        vent.setVent(vent1);
                        periodeDebut.setPeriodeDebut(periode1);
                        periodeFin.setPeriodeFin(periode2);
                        System.out.println("Ville trouvée: " + villeScan);
                        break;
                        }
                    }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "La ville entrée n'a pas été trouvée.");
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
            if (villeScan.equals(villeString)) {
                infoText.setText("Précipitation: " + precip.getPrecip() + ", Début du Période de neige: " + periodeDebut.getPeriode() + ", Fin du Période de neige: " + periodeFin.getPeriode() + ", Température: " + temp.getTemp() + ", Vitesse du Vent: " + vent.getVent());
            } else {
                JOptionPane.showMessageDialog(this, "La ville entrée n'a pas été trouvée.");
            }

            if (actionEvent.getSource() == boutonPredire) { //If loop pour calculer le score de neige, si le bouton entrez est cliqué
                score = 0;
                int tempCalcul = temp.getTemp();
                int precipCalcul = precip.getPrecip();
                int ventCalcul = vent.getVent();
                int periodeDebutCalcul = periodeDebut.getPeriode();
                int periodeFinCalcul = periodeFin.getPeriode();

                // Calcul le score de la température
                if (tempCalcul <= -50) {
                    score += 50;
                } else if (tempCalcul <= -40) {
                    score += 40;
                } else if (tempCalcul <= -35) {
                    score += 25;
                } else if (tempCalcul <= -30) {
                    score += 15;
                } else if (tempCalcul <= -25) {
                    score += 10;
                } else if (tempCalcul <= -20) {
                    score += 5;
                } else if (tempCalcul <= -15) {
                    score += 3;
                } else if (tempCalcul <= -10) {
                    score += 2;
                } else if (tempCalcul <= -5) {
                    score += 1;
                }
                // Calcul le score de la précipitation
                if (precipCalcul >= 30) {
                    score += 60;
                    if (periodeDebutCalcul <= 22) {
                        if (periodeFinCalcul >= 12) {
                            score += 20;
                        }
                    } else if (periodeDebutCalcul == 0) {
                        score += 12;
                    } else if (periodeDebutCalcul == 6) {
                        score += 6;
                    }
                } else if (precipCalcul >= 25) {
                    score += 50;
                } else if (precipCalcul >= 20) {
                    score += 45;
                } else if (precipCalcul >= 17) {
                    score += 42;
                } else if (precipCalcul >= 15) {
                    score += 38;
                } else if (precipCalcul >= 12) {
                    score += 37;
                } else if (precipCalcul >= 10) {
                    score += 34;
                } else if (precipCalcul >= 7) {
                    score += 28;
                } else if (precipCalcul >= 5) {
                    score += 23;
                } else if (precipCalcul >= 3) {
                    score += 15;
                } else if (precipCalcul >= 2) {
                    score += 10;
                } else if (precipCalcul >= 1) {
                    score += 7;
                }

                // Calcul le score de la vitesse du vent
                if (ventCalcul >= 70) {
                    score += 30;
                } else if (ventCalcul >= 50) {
                    score += 17;
                } else if (ventCalcul >= 40) {
                    score += 12;
                } else if (ventCalcul >= 30) {
                    score += 8;
                } else if (ventCalcul >= 20) {
                    score += 5;
                } else if (ventCalcul >= 10) {
                    score += 3;
                }

            } 
        }
    }

    public static void main(String[] args) {
        Precip precip = new Precip();
        Temp temp = new Temp();
        Vent vent = new Vent();
        Periode periodeDebut = new Periode();
        Periode periodeFin = new Periode();
        Jour jour = new Jour();
        new JourNeige(precip, temp, vent, periodeDebut, periodeFin, jour);
    }  
}

