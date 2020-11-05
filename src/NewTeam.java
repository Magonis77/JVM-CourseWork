import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class NewTeam extends JFrame {
    private JTextField txtTeamTitle;
    private JTextField txtAddMemberName;
    private JButton addButton;
    private JButton createTeamButton;
    private JButton returnButton;
    private JPanel MainPnl;
    private JPanel titlePnl;
    private JPanel bottomPnl;
    private JPanel infoPnl;
    private JPanel membersPnl;
    private JButton removeButton;
    private JComboBox Comboboxmembers;
    private JButton createMemberButton;
    private JList TeamMembers;
    private Teams teams;
    private CreateHandler createteam;
    private MembersHandler createmember;

    NewTeam() {
        super("New Team Entry");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        createteam = new CreateHandler();
        createmember = new MembersHandler();

        DefaultListModel liss = new DefaultListModel();
       String filePath = "selectedmembers.txt";
        String filePath1 = "tempTeams.txt";
       try {
        File file = new File(filePath);
           PrintWriter writer = null;
            writer = new PrintWriter(file);
            writer.print("");
           writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            File file = new File(filePath1);
            PrintWriter writer = null;
            writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

            String filePath2 = "members.txt";
            File file = new File(filePath2 );

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                Object[] lines = br.lines().toArray();

                for(int i = 0; i < lines.length; i++){
                    String line = lines[i].toString();
                    Comboboxmembers.addItem(line);
                }

            } catch (FileNotFoundException ex) {

            }


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
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teams = createteam.createTeam(txtTeamTitle.getText());
                teams.toString();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               teams = createmember.addMember(Comboboxmembers.getSelectedItem().toString());
               teams.toString();

        String member = Comboboxmembers.getSelectedItem().toString();
        System.out.println(member);

                String  s = Comboboxmembers.getSelectedItem().toString();
                liss.addElement(s);
                TeamMembers.setModel(liss);

            }
        });
        createMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateMember nt = new CreateMember();
                nt.setVisible(true);
                nt.setLocationRelativeTo(null);
                nt.setResizable(false);
                dispose();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = TeamMembers.getSelectedIndex();
                if (selectedIndex != -1) {
                    liss.remove(selectedIndex);
                }
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


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
