import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTeam extends JFrame {
    private JTextField textField1;
    private JTextField textField3;
    private JButton addButton;
    private JButton createTeamButton;
    private JButton returnButton;
    private JPanel MainPnl;
    private JComboBox projCombo;
    private JPanel titlePnl;
    private JPanel bottomPnl;
    private JPanel infoPnl;
    private JPanel membersPnl;

    NewTeam() {
        super("New Team Entry");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                home.setBounds(1500,1500, 1200 ,900);
                home.setLocationRelativeTo(null);
                home.setResizable(false);
                home.setVisible(true);
                dispose();
            }
        });
    }
        public static void main(String[] args) {
        NewTeam nt = new NewTeam();
        nt.setBounds(1500,1500, 1200 ,900);
        nt.setLocationRelativeTo(null);
        nt.setResizable(false);
        nt.setVisible(true);
    }



}
