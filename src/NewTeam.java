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
    private JCheckBox confirmSettingsCheckBox;
    private JLabel lblAddMembers;
    private JLabel lblTeamName;
    private JButton confirmNameButton;
    private Teams teams;
    private CreateHandler createteam;

    NewTeam() {
        super("New Team Entry");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        createteam = new CreateHandler();

        try {
            try {
                //File reader method
                FileReader file = new FileReader("members.txt");
                BufferedReader reader = new BufferedReader(file);
                String text = "";
                String line = reader.readLine();
                while (line != null) {
                    Comboboxmembers.addItem(line);
                    line = reader.readLine();

                }
                reader.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        DefaultListModel liss = new DefaultListModel();


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

        createTeamButton.setEnabled(false

        );
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                teams = createteam.createTeam(txtTeamTitle.getText(), liss.toString());
                teams.toString();
                liss.removeAllElements();
                TeamMembers.setModel(liss);
                txtTeamTitle.setText("");

                createTeamButton.setEnabled(false);
                removeButton.setEnabled(true);
                editMemberButton.setEnabled(true);
                addButton.setEnabled(true);
                Comboboxmembers.setEnabled(true);
                lblAddMembers.setEnabled(true);
                txtTeamTitle.setEnabled(true);
                lblTeamName.setEnabled(true);
                TeamMembers.setEnabled(true);
                confirmSettingsCheckBox.setSelected(false);

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
                nt.setBounds(500, 500, 500, 500);
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

        confirmSettingsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtTeamTitle.getText().equals("") && (liss.isEmpty())) {
                    JOptionPane optionPane = new JOptionPane("Please add team members AND enter team name!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    confirmSettingsCheckBox.setSelected(false);
                } else {
                    if (txtTeamTitle.getText().equals("")) {
                        JOptionPane optionPane = new JOptionPane("Please enter team name!", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Error!");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                        confirmSettingsCheckBox.setSelected(false);
                    } else {
                        if (liss.isEmpty()) {
                            JOptionPane optionPane = new JOptionPane("Please add team members!", JOptionPane.ERROR_MESSAGE);
                            JDialog dialog = optionPane.createDialog("Error!");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                            confirmSettingsCheckBox.setSelected(false);
                        } else {
                            if (confirmSettingsCheckBox.isSelected()) {
                                createTeamButton.setEnabled(true);
                                removeButton.setEnabled(false);
                                editMemberButton.setEnabled(false);
                                addButton.setEnabled(false);
                                Comboboxmembers.setEnabled(false);
                                lblAddMembers.setEnabled(false);
                                txtTeamTitle.setEnabled(false);
                                lblTeamName.setEnabled(false);
                                TeamMembers.setEnabled(false);
                            } else {
                                createTeamButton.setEnabled(false);
                                removeButton.setEnabled(true);
                                editMemberButton.setEnabled(true);
                                addButton.setEnabled(true);
                                Comboboxmembers.setEnabled(true);
                                lblAddMembers.setEnabled(true);
                                txtTeamTitle.setEnabled(true);
                                lblTeamName.setEnabled(true);
                                TeamMembers.setEnabled(true);

                            }
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        NewTeam nt = new NewTeam();
        nt.setBounds(1500, 1500, 1200, 900);
        nt.setLocationRelativeTo(null);
        nt.setResizable(false);
        nt.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
