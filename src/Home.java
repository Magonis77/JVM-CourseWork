import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JButton newProjectButton;
    private JButton manageTeamsButton;
    private JButton newTeamButton;
    private JButton manageProjectButton;
    private JButton createNewProjectButton;
    private JButton createNewTeamButton;
    private JButton manageProjectsButton;
    private JButton manageTeamsButton1;
    private JPanel MainPnl;
    private JPanel TopPnl;
    private JPanel SidePnl;
    private JPanel MidPnl;
    private JButton exitButton;

    Home() {
        super("Home");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();

        createNewProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            NewProject project = new NewProject();
            project.setVisible(true);
            project.setLocationRelativeTo(null);
            project.setResizable(false);
            dispose();

            }
        });
        createNewTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTeam nt = new NewTeam();
                nt.setVisible(true);
                nt.setLocationRelativeTo(null);
                nt.setResizable(false);
                dispose();
            }
        });

        manageProjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageProjects mp = new ManageProjects();
                mp.setVisible(true);
                mp.setLocationRelativeTo(null);
                mp.setResizable(false);
                dispose();
            }
        });
        manageTeamsButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageTeams mt = new ManageTeams();
                mt.setVisible(true);
                mt.setLocationRelativeTo(null);
                mt.setResizable(false);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Home home = new Home();
        home.setBounds(1500,1500, 1200 ,900);
        home.setLocationRelativeTo(null);
        home.setResizable(false);
        home.setVisible(true);
    }


}
