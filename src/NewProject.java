import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

            for(int i = 0; i < lines.length; i++){
                String line = lines[i].toString();
                assigntoTeam.addItem(line);
            }
        br.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(filePath);
            PrintWriter writer = null;
            writer = new PrintWriter(file);
            writer.print("Task Name, Days to complete, progress %" + "\n");
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
                }
                else{
                String task = assignTask.getText() + " / " + txtDays.getText() + " days / " + "0 %";
                Tasks.add(task);
                tasks = assignteamname.addTasks(assignTask.getText(), txtDays.getText());
                tasks.toString();
                assignTask.setText("");
                txtDays.setText("");
                File file = new File(filePath);

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    // get the first line
                    // get the columns name from the first line
                    // set columns name to the jtable model
                    String firstLine = br.readLine().trim();
                    String[] columnsName = firstLine.split(",");
                   DefaultTableModel model = (DefaultTableModel)TableTasks.getModel();
                    model.setColumnIdentifiers(columnsName);
                   model.setRowCount(0);

                    // get lines from txt file
                    Object[] tableLines = br.lines().toArray();
                    // extract data from lines
                    // set data to jtable model
                    for(int i = 0; i < tableLines.length; i++)
                    {
                        String line = tableLines[i].toString().trim();
                        String[] dataRow = line.split("/");
                        model.addRow(dataRow);
                    }


                } catch (Exception ex) {

                }
            }}
        });

        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuffer sbTableData = new StringBuffer();
                for(int row = 0; row < TableTasks.getRowCount(); row ++){
                    for(int column = 0; column < TableTasks.getColumnCount(); column ++){
                        sbTableData.append(TableTasks.getValueAt(row, column)).append("/");
                    }

                }
                String edit = sbTableData.toString();
                System.out.println(edit);
                if (projTitle.getText().equals("")) {
                    JOptionPane optionPane = new JOptionPane("Please Enter Team Name!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
                if(sbTableData == null || sbTableData.length() == 0){
                    JOptionPane optionPane = new JOptionPane("Please Add Tasks!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
                else {
                    projects = assignteamname.addProject(projTitle.getText(), assigntoTeam.getSelectedItem().toString(), Tasks.toString());
                    projects.toString();
                    projTitle.setText("");
                    assignTask.setText("");
                    try {
                        File file = new File(filePath);
                        PrintWriter writer = null;
                        writer = new PrintWriter(file);
                        writer.print("Task Name, Days to complete, progress %" + "\n");
                        writer.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    File file = new File(filePath);
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        // get the first line
                        // get the columns name from the first line
                        // set columns name to the jtable model
                        String firstLine = br.readLine().trim();
                        String[] columnsName = firstLine.split(",");
                        DefaultTableModel model = (DefaultTableModel) TableTasks.getModel();
                        model.setColumnIdentifiers(columnsName);
                        model.setRowCount(0);

                        // get lines from txt file
                        Object[] tableLines = br.lines().toArray();
                        // extract data from lines
                        // set data to jtable model
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
    }

    public static void main(String[] args) {
        NewProject project = new NewProject();
        project.setBounds(1500,1500, 1200 ,900);
        project.setLocationRelativeTo(null);
        project.setResizable(false);
        project.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


