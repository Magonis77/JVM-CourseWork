import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTable Team_Members;
    private JButton removeButton;
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
        String filePath = "members.txt";
        
        try {
            File file = new File(filePath);
            PrintWriter writer = null;
            writer = new PrintWriter(file);
            writer.print("" + "\n");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String filePath1 = "tempTeams.txt";

        try {
            File file = new File(filePath1);
            PrintWriter writer = null;
            writer = new PrintWriter(file);
            writer.print("" + "\n");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
               teams = createmember.addMember(txtAddMemberName.getText());
               teams.toString();


                String filePath = "members.txt";
                File file = new File(filePath);

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    // get the first line
                    // get the columns name from the first line
                    // set columns name to the jtable model
                    String firstLine = br.readLine().trim();
                    String[] columnsName = firstLine.split(",");
                    DefaultTableModel model = (DefaultTableModel)Team_Members.getModel();
                    model.setColumnIdentifiers(columnsName);
                    model.setRowCount(0);
                    // get lines from txt file
                    Object[] tableLines = br.lines().toArray();

                    // extratct data from lines
                    // set data to jtable model
                    for(int i = 0; i < tableLines.length; i++)
                    {
                        String line = tableLines[i].toString().trim();
                        String[] dataRow = line.split("/");
                        model.addRow(dataRow);
                    }


                } catch (Exception ex) {

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
