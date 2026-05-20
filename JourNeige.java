import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JourNeige extends JFrame implements ActionListener {
    public Meteo meteo;
    public Periode periodeDebut;
    public Periode periodeFin;
    public Score score;
    public Jour jourDebut;
    public Jour jourFin;

    JLabel villeLabel = new JLabel("Ville: ");
    JTextField villeText = new JTextField(10);
    JLabel postaleLabel = new JLabel("Code Postale: ");
    JTextField postaleText = new JTextField(10);
    JLabel infoLabel = new JLabel("Infos: ");
    JTextField infoText = new JTextField(40);
    JButton boutonPredire = new JButton("Prédire");
    JButton boutonRetour = new JButton("Retour");
    GridLayout leGrid = new GridLayout(4, 1);
    FlowLayout leFlow = new FlowLayout(FlowLayout.CENTER, 20, 0);

    public JourNeige(Meteo meteo, Periode periodeDebut, Periode periodeFin, Score score, Jour jourDebut, Jour jourFin) {
    
        super("Jour de Neige");
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.score = score;
        this.jourDebut = jourDebut;
        this.jourFin = jourFin;

        setLayout(leGrid);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel rangee1 = new JPanel();
        rangee1.setBorder(new EmptyBorder(40, 0, 0 , 0));
        rangee1.add(villeLabel);
        rangee1.add(villeText);
        rangee1.add(postaleLabel);
        rangee1.add(postaleText);
        rangee1.setLayout(leFlow); 
        add(rangee1);


        JPanel rangee2 = new JPanel();
        rangee2.setLayout(leFlow);
        rangee2.add(boutonPredire);
        boutonPredire.setPreferredSize(new Dimension(100, 100));
        boutonPredire.addActionListener(this);
        add(rangee2);


        JPanel rangee3 = new JPanel();
        rangee3.setLayout(leFlow);
        rangee3.setBorder(new EmptyBorder(40, 0, 20 , 0));
        rangee3.add(infoLabel);
        rangee3.add(infoText);
        infoText.setEditable(false);
        add(rangee3);
        


        JPanel rangee4 = new JPanel();
        rangee4.setBorder(new EmptyBorder(20, 0, 20 , 0));
        rangee4.setLayout(leFlow);
        rangee4.add(boutonRetour);
        add(rangee4);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == boutonRetour) { // If loop pour quand le bouton retour est cliqué, qui vas retourné l'utilsateur au page debut
            new Debut(meteo, periodeDebut, periodeFin, score, jourDebut, jourFin);
            dispose();
        }
        if (actionEvent.getSource() == boutonPredire) { // If loop pour quand le bouton prédire est cliqué
            File file = new File("Donnees.txt");
            Scanner scanner = null;
            String villeString = villeText.getText(); // Prend la ville entrée par l'utilisateur dans le JTextField villeText
            String postaleString = postaleText.getText(); // Prend le code postale entrée par l'utilisateur dans le JTextField villeText
            boolean villeTrouve = false;
            boolean postaleTrouve = false;
                try {
                    scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.contains(villeString)) {
                            String[] parts = line.split(",\\s* "); // Split la ligne du Donnees.txt en plusieurs parties pour prendre les différentes données
                            String ville1 = parts[0].split(": ")[1]; // Prend la ville dans le Donnees.txt
                            String postale1 = parts[1].split(": ")[1]; // Prend le code postale dans le Donnees.txt
                            int precip1 = Integer.parseInt(parts[2].split(": ")[1]); // Prend la précipitation dans le Donnees.txt et le convertit en int
                            int periode1 = Integer.parseInt(parts[3].split(": ")[1]); // Prend la température dans le Donnees.txt et le convertit en int
                            int periode2 = Integer.parseInt(parts[4].split(": ")[1]); // Prend la vitesse du vent dans le Donnees.txt et le convertit en int
                            int temp1 = Integer.parseInt(parts[5].split(": ")[1]); // Set l'heure que la neige commence (période début) au heure que la neige commence prise dans le Donnees.txt
                            int vent1 = Integer.parseInt(parts[6].split(": ")[1]); // Set l'heure que la neige finit (période fin) au heure que la neige finit prise dans le Donnees.txt
                            int jourCalculDebut = Integer.parseInt(parts[7].split(": ")[1]); // Set un nouveau varialbe pour calculer le jour de début de la neige (0 = Aujourd'hui)
                            int jourCalculFin = Integer.parseInt(parts[8].split(": ")[1]); // Set un nouveau varialbe pour calculer le jour de fin de la neige (1 = Demain)
                            System.out.println(jourCalculDebut);
                            System.out.println(jourCalculFin);
                            int scoreCalcul = 0; //Nouvelle variable pour calculer le score (qui vas déterminer s'il y a un jour de neige ou pas)
                            if (ville1.equals(villeString)) {
                                villeTrouve = true;
                            } 
                            if (postale1.equals(postaleString)) { //if loop qui vérifie si le ville et code postale entrez par l'utilisateur est dans le fichier données
                                postaleTrouve = true;
                            }
                            if ((villeTrouve == true) && (postaleTrouve == true)) {
                                infoText.setText("Précipitation: " + precip1 + ", Début du Période de neige: " + periode1 + ", Fin du Période de neige: " + periode2 + ", Température: " + temp1 + ", Vitesse du Vent: " + vent1);
                            }
                        
                        // Calcul le score de la température, début du gros loop
                        if (temp1 <= -50) {
                            scoreCalcul += 50;
                        } else if (temp1 <= -40) {
                            scoreCalcul += 40;
                        } else if (temp1 <= -35) {
                            scoreCalcul += 25;
                        } else if (temp1 <= -30) {
                            scoreCalcul += 15;
                        } else if (temp1 <= -25) {
                            scoreCalcul += 10;
                        } else if (temp1 <= -20) {
                            scoreCalcul += 5;
                        } else if (temp1 <= -15) {
                            scoreCalcul += 3;
                        } else if (temp1 <= -10) {
                            scoreCalcul += 2;
                        } else if (temp1 <= -5) {
                            scoreCalcul += 1;
                        }
                        // Calcul le score de la précipitation
                        if (precip1 >= 30) {
                            if (periode1 <= 22 && jourCalculDebut == 0) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 60;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 50;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 25;
                                } else if (periode2 >= 0 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else {
                                scoreCalcul += 5;
                                }
                            } else if (periode1 <= 2 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 45;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 25;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else {
                                    scoreCalcul += 3;
                                }
                            } else if (periode1 <= 6 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 30;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else {
                                    scoreCalcul += 2;
                                }
                            }
                        } else if (precip1 >= 20) {
                            if (periode1 <= 22 && jourCalculDebut == 0) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 45;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 35;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 20;
                                } else if (periode2 >= 0 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else {
                                    scoreCalcul += 5;
                                }
                            } else if (periode1 <= 2 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 30;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 20;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else {
                                    scoreCalcul += 3;
                                }
                            } else if (periode1 <= 6 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 15;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else {
                                    scoreCalcul += 2;
                                }
                            }
                        } else if (precip1 >= 15) {
                            if (periode1 <= 22 && jourCalculDebut == 0) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 30;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 20;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else if (periode2 >= 0 && jourCalculFin == 1) {
                                    scoreCalcul += 5;
                                } else {
                                    scoreCalcul += 3;
                                }
                            } else if (periode1 <= 2 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 15;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 5;
                                } else {
                                    scoreCalcul += 2;
                                }
                            } else if (periode1 <= 6 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 5;
                                } else {
                                    scoreCalcul += 2;
                                }
                            }
                        } else if (precip1 >= 10) {
                            if (periode1 <= 22 && jourCalculDebut == 0) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 15;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 5;
                                } else if (periode2  >= 0 && jourCalculFin == 1) {
                                    scoreCalcul += 3;
                                } else {
                                    scoreCalcul += 2;
                                }
                            } else if (periode1 <= 2 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 5;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 3;
                                } else {
                                    scoreCalcul += 2;
                                }
                            } else if (periode1 <= 6 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 5;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 3;
                                } else {
                                    scoreCalcul += 1;
                                }
                            }
                        } else if (precip1 >= 5) {
                            if (periode1 <= 22 && jourCalculDebut == 0) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 10;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 5;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 3;
                                } else if (periode2  >= 0 && jourCalculFin == 1) {
                                    scoreCalcul += 2;
                                } else {
                                  scoreCalcul += 1;
                                }
                            } else if (periode1 <= 2 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 5;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 3;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 2;
                                } else {
                                    scoreCalcul += 1;
                                }
                            } else if (periode1 <= 6 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 3;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 2;
                                } else {
                                    scoreCalcul += 1;
                                }
                            }
                        } else if (precip1 >= 1) {
                            if (periode1 <= 22 && jourCalculDebut == 0) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 5;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 3;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 2;
                                } else if (periode2  >= 0 && jourCalculFin == 1) {
                                    scoreCalcul += 1;
                                }
                            } else if (periode1 <= 2 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 3;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 2;
                                } else if (periode2 >= 4 && jourCalculFin == 1) {
                                    scoreCalcul += 1;
                                }
                            } else if (periode1 <= 6 && jourCalculDebut == 1) {
                                if (periode2 >= 12 && jourCalculFin == 1) {
                                    scoreCalcul += 2;
                                } else if (periode2 >=8 && jourCalculFin == 1) {
                                    scoreCalcul += 1;
                                }
                            }
                        }
                        // Calcul le score de la vitesse du vent
                        if (vent1 >= 70) {
                            scoreCalcul += 30;
                        } else if (vent1 >= 50) {
                            scoreCalcul += 17;
                        } else if (vent1 >= 40) {
                            scoreCalcul += 12;
                        } else if (vent1 >= 30) {
                            scoreCalcul += 8;
                        } else if (vent1 >= 20) {
                            scoreCalcul += 5;
                        } else if (vent1 >= 10) {
                            scoreCalcul += 3;
                        } //Fin du grand loop de calculer le score

                        if (!villeTrouve) {
                            JOptionPane.showMessageDialog(this, "La ville entrée n'a pas été trouvée.");
                            infoText.setText("");
                            scoreCalcul = 0;
                        }
                        if (!postaleTrouve) {
                            JOptionPane.showMessageDialog(this, "Le code postale entrée n'a pas été trouvée.");
                            infoText.setText("");
                            scoreCalcul = 0;
                        }
                        System.out.println("Score: " + scoreCalcul);
                        if (scoreCalcul >= 100) { //Reset le score à 100, pas de point à l'avoir plus haut que le max (100%)
                            scoreCalcul = 100;
                        }
                        System.out.println("Score: " + scoreCalcul);
                        boutonPredire.setText(scoreCalcul + "%");
                        break;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erreur de fichier");
            } finally {
                if (scanner != null) {
                    scanner.close();
                } 
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
        new JourNeige(meteo, periodeDebut, periodeFin, score, jourDebut, jourFin);
    }  
}
