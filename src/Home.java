import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JButton newProjectSideButton;
    private JButton manageTeamsSideButton;
    private JButton newTeamSideButton;
    private JButton manageProjectSideButton;
    private JButton createNewProjectBigButton;
    private JButton createNewTeamBIgButton;
    private JButton manageProjectsBigButton;
    private JButton manageTeamsBigButton;
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

        createNewProjectBigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewProject project = new NewProject();
                project.setVisible(true);
                project.setBounds(1500,1500, 1200 ,900);
                project.setLocationRelativeTo(null);
                project.setResizable(true);
                dispose();
            }
        });
        createNewTeamBIgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTeam nt = new NewTeam();
                nt.setVisible(true);
                nt.setBounds(1500,1500, 1200 ,900);
                nt.setLocationRelativeTo(null);
                nt.setResizable(true);
                dispose();
            }
        });

        manageProjectsBigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageProjects mp = new ManageProjects();
                mp.setVisible(true);
                mp.setBounds(1500,1500, 1200 ,900);
                mp.setLocationRelativeTo(null);
                mp.setResizable(false);
                dispose();
            }
        });
        manageTeamsBigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageTeams mt = new ManageTeams();
                mt.setVisible(true);
                mt.setBounds(1500,1500, 1200 ,900);
                mt.setLocationRelativeTo(null);
                mt.setResizable(false);
                dispose();
            }
        });
        newProjectSideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewProject project = new NewProject();
                project.setVisible(true);
                project.setBounds(1500,1500, 1200 ,900);
                project.setLocationRelativeTo(null);
                project.setResizable(false);
                dispose();
            }
        });
        manageProjectSideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageProjects mp = new ManageProjects();
                mp.setVisible(true);
                mp.setBounds(1500,1500, 1200 ,900);
                mp.setLocationRelativeTo(null);
                mp.setResizable(false);
                dispose();
            }
        });
        newTeamSideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTeam nt = new NewTeam();
                nt.setVisible(true);
                nt.setBounds(1500,1500, 1200 ,900);
                nt.setLocationRelativeTo(null);
                nt.setResizable(false);
                dispose();
            }
        });
        manageTeamsSideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageTeams mt = new ManageTeams();
                mt.setVisible(true);
                mt.setBounds(1500,1500, 1200 ,900);
                mt.setLocationRelativeTo(null);
                mt.setResizable(false);
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Home home = new Home();
        home.setBounds(1500, 1500, 1200, 900);
        home.setLocationRelativeTo(null);
        home.setResizable(false);
        home.setVisible(true);
    }


}
