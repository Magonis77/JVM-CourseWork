import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageTeams extends JFrame {
    private JPanel MainPnl;
    private JButton editButton;
    private JButton returnButton;
    private JComboBox comboBox1;


    ManageTeams() {
        super("Manage Teams");
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
        ManageTeams mt = new ManageTeams();
        mt.setBounds(1500,1500, 1200 ,900);
        mt.setLocationRelativeTo(null);
        mt.setResizable(false);
        mt.setVisible(true);
    }
}
