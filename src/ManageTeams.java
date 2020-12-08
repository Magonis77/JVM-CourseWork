import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ManageTeams extends JFrame {
    private JPanel MainPnl;
    private JButton editButton;
    private JButton returnButton;
    private JComboBox cbteams;
    private JList TeamMembers;
    private JComboBox Comboboxmembers;
    private JButton addButton;
    private JButton removeButton;
    private JButton deleteTeamButton;
    private JTextField txtTeamTitle;
    private JPanel SettingsPnl;
    private JPanel TitlePnl;
    private JCheckBox confirmSettingsCheckBox;
    private JLabel lblSelectTeam;
    private JLabel lblAddMembers;
    private JLabel lblTeamName;
    private Teams teams;
    private CreateHandler createteam;
    private members manager;
    private ManageHandler managing;
    private teamname gettingTN;


    ManageTeams() {

        super("Manage Teams");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        createteam = new CreateHandler();
        managing = new ManageHandler();
        //Liss created to show members in the JList
        DefaultListModel liss = new DefaultListModel();
        File inputFile = new File("Teams.txt");
        File tempFile = new File("TempTeams.txt");
        try {
            //Read from TXT file and add to the ComboBox
            String filePath1 = "Teams.txt";
            File file = new File(filePath1);
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].toString();
                cbteams.addItem(line);
            }
            br.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath2 = "members.txt";
        File file = new File(filePath2);

        try {
            //Read from TXT file and add to the ComboBox
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].toString();
                Comboboxmembers.addItem(line);
            }
            br.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                home.setBounds(1500, 1500, 1000, 800);
                home.setLocationRelativeTo(null);
                home.setResizable(false);
                home.setVisible(true);
                dispose();
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
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = TeamMembers.getSelectedIndex();
                if (selectedIndex != -1) {
                    liss.remove(selectedIndex);
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                editButton.setEnabled(false);
                lblSelectTeam.setEnabled(true);
                cbteams.setEnabled(true);
                txtTeamTitle.setEnabled(true);
                lblAddMembers.setEnabled(true);
                Comboboxmembers.setEnabled(true);
                addButton.setEnabled(true);
                removeButton.setEnabled(true);
                deleteTeamButton.setEnabled(true);
                confirmSettingsCheckBox.setSelected(false);

                String member = liss.toString().replaceFirst(",", "").replaceFirst(" ", "");
                teams = createteam.createTeam(txtTeamTitle.getText(), member);
                teams.toString();
                String abc = txtTeamTitle.getText() + member;
                cbteams.addItem(abc);
                liss.removeAllElements();
                TeamMembers.setModel(liss);
                txtTeamTitle.setText("");
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    int lineToRemove = cbteams.getSelectedIndex() + 1;
                    String currentLine;
                    int count = 0;

                    while ((currentLine = reader.readLine()) != null) {
                        count++;
                        if (count == lineToRemove) {
                            continue;
                        }
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    int index = cbteams.getSelectedIndex();
                    cbteams.removeItemAt(index);
                    liss.removeAllElements();
                    TeamMembers.setModel(liss);
                    txtTeamTitle.setText("");
                    writer.close();
                    reader.close();
                    inputFile.delete();
                    tempFile.renameTo(inputFile);
                } catch (IOException ioException) {
                    ioException.printStackTrace();

                }
            }

        });


        deleteTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    int lineToRemove = cbteams.getSelectedIndex() + 1;
                    String currentLine;
                    int count = 0;

                    while ((currentLine = reader.readLine()) != null) {
                        count++;
                        if (count == lineToRemove) {
                            continue;
                        }
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    int index = cbteams.getSelectedIndex();
                    cbteams.removeItemAt(index);
                    teams = createteam.createTeam(txtTeamTitle.getText(), liss.toString());
                    teams.toString();
                    liss.removeAllElements();
                    TeamMembers.setModel(liss);
                    txtTeamTitle.setText("");
                    writer.close();
                    reader.close();
                    inputFile.delete();
                    tempFile.renameTo(inputFile);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        cbteams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    liss.removeAllElements();
                    TeamMembers.setModel(liss);
                    ArrayList<String> al = new ArrayList<String>();
                    manager = managing.sendlist(cbteams.getSelectedItem().toString());
                    String test = manager.toString().replace("members(members=[", "").replace("])", "");
                    String[] members = test.split(",");
                    gettingTN = managing.getteamname(cbteams.getSelectedItem().toString());
                    txtTeamTitle.setText(gettingTN.toString().replace("teamname(teamname=", "").replace(")", ""));
                    for (String str : members) {
                        liss.addElement(str);
                        TeamMembers.setModel(liss);
                    }
                } catch (Exception exception) {

                }
            }
        });

        editButton.setEnabled(false);


        confirmSettingsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtTeamTitle.getText().equals("") && (liss.isEmpty())) {
                    JOptionPane optionPane = new JOptionPane("Please Add Team Members AND Enter Team Name!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    confirmSettingsCheckBox.setSelected(false);
                } else {
                    if (txtTeamTitle.getText().equals("")) {
                        JOptionPane optionPane = new JOptionPane("Please Enter Team Name!", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Error!");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                        confirmSettingsCheckBox.setSelected(false);
                    } else {
                        if (liss.isEmpty()) {
                            JOptionPane optionPane = new JOptionPane("Please Add Team Members!", JOptionPane.ERROR_MESSAGE);
                            JDialog dialog = optionPane.createDialog("Error!");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                            confirmSettingsCheckBox.setSelected(false);
                        } else {
                            if (confirmSettingsCheckBox.isSelected()) {
                                editButton.setEnabled(true);
                                lblSelectTeam.setEnabled(false);
                                cbteams.setEnabled(false);
                                lblTeamName.setEnabled(false);
                                txtTeamTitle.setEnabled(false);
                                lblAddMembers.setEnabled(false);
                                Comboboxmembers.setEnabled(false);
                                addButton.setEnabled(false);
                                removeButton.setEnabled(false);
                                deleteTeamButton.setEnabled(false);
                                TeamMembers.setEnabled(false);
                            } else {
                                editButton.setEnabled(false);
                                lblSelectTeam.setEnabled(true);
                                cbteams.setEnabled(true);
                                lblTeamName.setEnabled(true);
                                txtTeamTitle.setEnabled(true);
                                lblAddMembers.setEnabled(true);
                                Comboboxmembers.setEnabled(true);
                                addButton.setEnabled(true);
                                removeButton.setEnabled(true);
                                deleteTeamButton.setEnabled(true);
                                TeamMembers.setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        ManageTeams mt = new ManageTeams();
        mt.setBounds(1500, 1500, 1200, 900);
        mt.setLocationRelativeTo(null);
        mt.setResizable(false);
        mt.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

