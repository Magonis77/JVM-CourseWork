import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageProjects extends JFrame {
    private JButton editButton;
    private JPanel MainPnl;
    private JButton returnButton;
    private JTable projTable;


    ManageProjects() {
         super("Manage Projects");
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
        ManageProjects mp = new ManageProjects();
        mp.setBounds(1500,1500, 1200 ,900);
        mp.setLocationRelativeTo(null);
        mp.setResizable(false);
        mp.setVisible(true);
    }




















}
