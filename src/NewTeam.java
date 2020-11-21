import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    private JButton editMemberButton;
    private JList TeamMembers;
    private JButton confirmNameButton;
    private Teams teams;
    private CreateHandler createteam;

    NewTeam() {
        super("New Team Entry");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        createteam = new CreateHandler();

        createTeamButton.setEnabled(false);


        try{
            try{
                //File reader method
                FileReader file = new FileReader("members.txt");
                BufferedReader reader = new BufferedReader(file);
                String text = "";
                String line = reader.readLine();
                while (line != null)
                {
                    Comboboxmembers.addItem(line);
                    line = reader.readLine();

                }
                reader.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        DefaultListModel liss = new DefaultListModel();


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
                teams = createteam.createTeam(txtTeamTitle.getText(),liss.toString());
                teams.toString();
                liss.removeAllElements();
                TeamMembers.setModel(liss);
                txtTeamTitle.setText("");
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

        String member = Comboboxmembers.getSelectedItem().toString();
                liss.addElement(member);
                TeamMembers.setModel(liss);




            }
        });
        editMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditMembers nt = new EditMembers();
                nt.setVisible(true);
                nt.setBounds(500,500, 500 ,500);
                nt.setLocationRelativeTo(null);
                nt.setResizable(true);
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
