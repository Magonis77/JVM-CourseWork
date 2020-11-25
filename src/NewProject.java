import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

public class NewProject extends JFrame {
    private JButton createProjectButton;
    private JTextField projTitle;
    private JComboBox assigntoTeam;
    private JTextField assignTask;
    private JButton addTaskButton;
    private JButton returnButton;
    private JPanel MainPnl;
    private JTable TableTasks;
    private JTextField txtDays;
    private JCheckBox confirmSettingsCheckBox;
    private JScrollPane taskTable;
    private JLabel lblProjTitle;
    private JLabel lblAssignTeama;
    private JLabel lblAssignTask;
    private JLabel lblTaskComp;
    private Projects projects;
    private Tasks tasks;
    private ProjectsHandler assignteamname;


    NewProject() {
        super("New Project Entry");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        ArrayList<String> Tasks = new ArrayList<String>();
        assignteamname = new ProjectsHandler();
        String filePath = "Tasks.txt";
        String filePath2 = "Teams.txt";

        try {
            File file = new File(filePath2);
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].toString();
                assigntoTeam.addItem(line);
            }
            br.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        ProjectsKt.rewrite();

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

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (assignTask.getText().equals("")) {
                    JOptionPane optionPane = new JOptionPane("Please Enter Task Name!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
                if (txtDays.getText().equals("")) {
                    JOptionPane optionPane = new JOptionPane("Please Enter Task Completion in Days!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                } else {
                    String task = assignTask.getText() + " / " + txtDays.getText() + " days / " + "0 %";
                    Tasks.add(task);
                    tasks = assignteamname.addTasks(assignTask.getText(), txtDays.getText());
                    tasks.toString();
                    // assignTask.setText("");
                    // txtDays.setText("");
                    File file = new File(filePath);

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String firstLine = br.readLine().trim();
                        String[] columnsName = firstLine.split(",");
                        DefaultTableModel model = (DefaultTableModel) TableTasks.getModel();
                        model.setColumnIdentifiers(columnsName);
                        model.setRowCount(0);
                        Object[] tableLines = br.lines().toArray();

                        for (int i = 0; i < tableLines.length; i++) {
                            String line = tableLines[i].toString().trim();
                            String[] dataRow = line.split("/");
                            model.addRow(dataRow);
                        }


                    } catch (Exception ex) {

                    }
                }
            }
        });

        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createProjectButton.setEnabled(false);
                projTitle.setEnabled(true);
                lblProjTitle.setEnabled(true);
                assigntoTeam.setEnabled(true);
                lblAssignTeama.setEnabled(true);
                lblAssignTask.setEnabled(true);
                assignTask.setEnabled(true);
                lblTaskComp.setEnabled(true);
                txtDays.setEnabled(true);
                addTaskButton.setEnabled(true);
                taskTable.setEnabled(true);
                confirmSettingsCheckBox.setSelected(false);
                txtDays.setText("");

                StringBuffer sbTableData = new StringBuffer();
                for (int row = 0; row < TableTasks.getRowCount(); row++) {
                    for (int column = 0; column < TableTasks.getColumnCount(); column++) {
                        sbTableData.append(TableTasks.getValueAt(row, column)).append("/");
                    }

                }
                String edit = sbTableData.toString();

                projects = assignteamname.addProject(projTitle.getText(), assigntoTeam.getSelectedItem().toString(), Tasks.toString());
                projects.toString();
                projTitle.setText("");
                assignTask.setText("");
                ProjectsKt.rewrite();
                File file = new File(filePath);
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String firstLine = br.readLine().trim();
                    String[] columnsName = firstLine.split(",");
                    DefaultTableModel model = (DefaultTableModel) TableTasks.getModel();
                    model.setColumnIdentifiers(columnsName);
                    model.setRowCount(0);
                    Object[] tableLines = br.lines().toArray();

                    for (int i = 0; i < tableLines.length; i++) {
                        String line = tableLines[i].toString().trim();
                        String[] dataRow = line.split("/");
                        model.addRow(dataRow);
                    }

                } catch (Exception ex) {

                }
            }
        });

        createProjectButton.setEnabled(false);

        confirmSettingsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((TableTasks != null && TableTasks.getModel().getRowCount() <= 0 ? true : false) && projTitle.getText().equals("") && txtDays.getText().equals("")) {
                    JOptionPane optionPane = new JOptionPane("Please enter project title, task(s) and completion time.", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    confirmSettingsCheckBox.setSelected(false);
                } else {
                    if ((TableTasks != null && TableTasks.getModel().getRowCount() <= 0 ? true : false) && txtDays.getText().equals("")) {
                        JOptionPane optionPane = new JOptionPane("Please add at last 1 task AND set completion time.", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Error!");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                        confirmSettingsCheckBox.setSelected(false);
                    } else {
                        if ((TableTasks != null && TableTasks.getModel().getRowCount() <= 0 ? true : false) && projTitle.getText().equals("")) {
                            JOptionPane optionPane = new JOptionPane("Please enter at least 1 task AND add project title", JOptionPane.ERROR_MESSAGE);
                            JDialog dialog = optionPane.createDialog("Error!");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                            confirmSettingsCheckBox.setSelected(false);
                        } else {
                            if ((projTitle.getText().equals("") && assignTask.getText().equals(""))) {
                                JOptionPane optionPane = new JOptionPane("Please enter project title AND add at least 1 task.", JOptionPane.ERROR_MESSAGE);
                                JDialog dialog = optionPane.createDialog("Error!");
                                dialog.setAlwaysOnTop(true);
                                dialog.setVisible(true);
                                confirmSettingsCheckBox.setSelected(false);
                            } else {
                                if ((TableTasks != null && TableTasks.getModel().getRowCount() <= 0 ? true : false)) {
                                    JOptionPane optionPane = new JOptionPane("Please add at least 1 task.", JOptionPane.ERROR_MESSAGE);
                                    JDialog dialog = optionPane.createDialog("Error!");
                                    dialog.setAlwaysOnTop(true);
                                    dialog.setVisible(true);
                                    confirmSettingsCheckBox.setSelected(false);
                                } else {
                                    if ((projTitle.getText().equals(""))) {
                                        JOptionPane optionPane = new JOptionPane("Please enter project title", JOptionPane.ERROR_MESSAGE);
                                        JDialog dialog = optionPane.createDialog("Error!");
                                        dialog.setAlwaysOnTop(true);
                                        dialog.setVisible(true);
                                        confirmSettingsCheckBox.setSelected(false);
                                    } else {
                                        if (confirmSettingsCheckBox.isSelected()) {
                                            createProjectButton.setEnabled(true);
                                            projTitle.setEnabled(false);
                                            lblProjTitle.setEnabled(false);
                                            assigntoTeam.setEnabled(false);
                                            lblAssignTeama.setEnabled(false);
                                            lblAssignTask.setEnabled(false);
                                            assignTask.setEnabled(false);
                                            lblTaskComp.setEnabled(false);
                                            txtDays.setEnabled(false);
                                            addTaskButton.setEnabled(false);
                                            taskTable.setEnabled(false);
                                        } else {
                                            createProjectButton.setEnabled(false);
                                            projTitle.setEnabled(true);
                                            lblProjTitle.setEnabled(true);
                                            assigntoTeam.setEnabled(true);
                                            lblAssignTeama.setEnabled(true);
                                            lblAssignTask.setEnabled(true);
                                            assignTask.setEnabled(true);
                                            lblTaskComp.setEnabled(true);
                                            txtDays.setEnabled(true);
                                            addTaskButton.setEnabled(true);
                                            taskTable.setEnabled(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        txtDays.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    long number = Long.parseLong(txtDays.getText());
                } catch (Exception IO) {
                    JOptionPane.showMessageDialog(rootPane, "Please only enter numbers for task completion.");
                    txtDays.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        NewProject project = new NewProject();
        project.setBounds(1500, 1500, 1200, 900);
        project.setLocationRelativeTo(null);
        project.setResizable(false);
        project.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


