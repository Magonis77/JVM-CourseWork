import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProject extends JFrame {
    private JButton createProjectButton;
    private JTextField projTitle;
    private JComboBox assigntoTeam;
    private JTextField assignTask;
    private JComboBox taskDiff;
    private JComboBox taskComp;
    private JButton addTaskButton;
    private JButton returnButton;
    private JPanel MainPnl;
    private JTextField textField1;
    private JTextField textField2;

    NewProject() {
        super("New Project Entry");
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
        NewProject project = new NewProject();
        project.setBounds(1500,1500, 1200 ,900);
        project.setLocationRelativeTo(null);
        project.setResizable(false);
        project.setVisible(true);
    }









}


