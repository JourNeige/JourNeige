import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;



public class JourNeige extends JFrame implements ActionListener {
    JLabel villeLabel = new JLabel("Ville: ");
    JTextField villeText = new JTextField(10);
    JLabel InfoLabel = new JLabel("Infos: ");
    JTextField InfoText = new JTextField(10);
    JButton boutonPredire = new JButton("Prédire");
    GridLayout leGrid = new GridLayout(3, 1);
    FlowLayout leFlow = new FlowLayout();

    public JourNeige() {
        super("Jour de Neige");
        setLayout(leGrid);
        setSize(600, 400);

        boutonPredire.setPreferredSize(new Dimension(100, 100));

        JPanel rangee1 = new JPanel();
        rangee1.setBorder(new EmptyBorder(40, 0, 0, 0));
        rangee1.add(villeLabel);
        rangee1.add(villeText);
        rangee1.setLayout(leFlow);
        add(rangee1);

        JPanel rangee2 = new JPanel();
        rangee2.setLayout(leFlow);
        rangee2.add(boutonPredire);
        boutonPredire.addActionListener(this);
        add(rangee2);


        JPanel rangee3 = new JPanel();
        rangee3.setBorder(new EmptyBorder(40, 0, 0, 0));
        rangee3.add(InfoLabel);
        rangee3.add(InfoText);
        rangee3.setLayout(leFlow);
        add(rangee3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == boutonPredire) {
            JOptionPane.showMessageDialog(this, "Il va neiger demain!");
        }
    }


public static void main(String[] args) {
    JourNeige app = new JourNeige();
    }
}
