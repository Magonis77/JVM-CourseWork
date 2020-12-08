import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class RecordProgress extends JFrame {
    private JButton confirmButton;
    private JPanel MainPnl;
    private JButton returnButton;
    private JComboBox JcbProjects;
    private JTable TableTasks;
    private JTextField txtTeam;
    private JTable JTableCriticalPath;
    private JTextField txtDays;
    private JTextField txtInitialNodes;
    private JTextField txtFinDate;
    private JCheckBox kotlinCheckBox;
    private JCheckBox scalaCheckBox;
    private JLabel lblSelectProj;
    private JLabel lblTeam;
    private JLabel lblCriticalPath;
    private JLabel lblInitialNodes;
    private JLabel lblFinishDate;
    private JScrollPane tasksTable;
    private JScrollPane criticalTable;
    private JTextField txt;
    private Progress progress;
    private ProjectsHandler assignteamname;
    private Projects projects;
    private ProjectsKt projectsKt;
    private CriticalPathKt CriticalPath;
    private CPHandling CPHandler;


    RecordProgress() {
        super("Manage Projects");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        assignteamname = new ProjectsHandler();
        String filePath = "Tasks.txt";
        String filePath2 = "projects.txt";
        File inputFile = new File("projects.txt");
        File tempFile = new File("TempProjects.txt");

        try {
            File file = new File(filePath2);
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].toString();
                String[] splitter = line.split(";");
                String write = splitter[0];
                JcbProjects.addItem(write);
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
        JcbProjects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kotlinCheckBox.isSelected()){
                    ProjectsKt.rewrite();
                    try {
                        String word = JcbProjects.getSelectedItem().toString();
                        File file = new File(filePath2);
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        Scanner input = new Scanner(new File(filePath2));
                        Integer Dayadd = 1;

                        // Let's loop through each line of the file
                        while (input.hasNext()) {
                            String line = input.nextLine();
                
                            // Now, check if this line contains our keyword. If it does, print the line
                            if (line.contains(word)) {
                                String[] splitter = line.split(";");
                                String write = splitter[3];
                                String dates = splitter[2];
                                String[] date = dates.split("-");
                                Calendar cal = Calendar.getInstance();
                                cal.set(Calendar.DATE, Integer.parseInt(date[2]));
                                cal.set(Calendar.YEAR,Integer.parseInt(date[0]));
                                cal.set(Calendar.MONTH,Integer.parseInt(date[1])-1);
                                Date d = cal.getTime();
                                File myObj = new File("CriticalPathDays.txt");
                                Scanner myReader = new Scanner(myObj);
                                while (myReader.hasNextLine()) {
                                    String data = myReader.nextLine();
                                    txtDays.setText("This Project will take " + data + " days");
                                    Dayadd = Integer.parseInt(data);

                                }
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                cal.add(Calendar.DATE, Dayadd); // Adding 5 days
                                String output = sdf.format(cal.getTime());
                                txtFinDate.setText(output);
                                String team = splitter[1];
                                txtTeam.setText(team);
                                String replace1 = write.replace("[", " ").replace("]", " ");
                                String[] replaced = replace1.split(",");
                                for (int a = 0; a < replaced.length; a++) {
                                    String put = replaced[a];
                                    progress = assignteamname.addprogress(put);
                                    progress.toString();
                                }
                                br.close();
                            }
                        }
                        input.close();
                    } catch (FileNotFoundException ex) {

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


                    try {
                        File file = new File("Tasks.txt");
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
                        br.close();
                        CriticalPath.main();

                        File myObj = new File("CriticalPathDays.txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            txtDays.setText("This Project will take " + data + " days");


                        }
                        myReader.close();
                    } catch (Exception ex) {

                    }
                    try {
                        File myObj = new File("InitialNodes.txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            txtInitialNodes.setText(data);
                        }
                        myReader.close();
                    } catch (Exception ex) {

                    }
                    try {
                        File file = new File("CriticalPath.txt");
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String firstLine = br.readLine().trim();
                        String[] columnsName = firstLine.split(",");
                        DefaultTableModel model = (DefaultTableModel) JTableCriticalPath.getModel();
                        model.setColumnIdentifiers(columnsName);
                        model.setRowCount(0);
                        Object[] tableLines = br.lines().toArray();
                        for (int i = 0; i < tableLines.length; i++) {
                            String line = tableLines[i].toString().trim();
                            String[] dataRow = line.split("/");
                            model.addRow(dataRow);
                        }
                        br.close();
                    } catch (Exception ex) {

                    }
                }
                if (scalaCheckBox.isSelected()) {
                    ProjectsKt.rewrite();

                    try {
                        String word = JcbProjects.getSelectedItem().toString();
                        File file = new File(filePath2);
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        Scanner input = new Scanner(new File(filePath2));
                        Integer Dayadd = 1;

                        // Let's loop through each line of the file
                        while (input.hasNext()) {
                            String line = input.nextLine();

                            // Now, check if this line contains our keyword. If it does, print the line
                            if (line.contains(word)) {
                                String[] splitter = line.split(";");
                                String write = splitter[3];
                                String dates = splitter[2];
                                String[] date = dates.split("-");
                                Calendar cal = Calendar.getInstance();
                                cal.set(Calendar.DATE, Integer.parseInt(date[2]));
                                cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
                                cal.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
                                Date d = cal.getTime();
                                File myObj = new File("CriticalPathDays.txt");
                                Scanner myReader = new Scanner(myObj);
                                while (myReader.hasNextLine()) {
                                    String data = myReader.nextLine();
                                    txtDays.setText("This Project will take " + data + " days");
                                    Dayadd = Integer.parseInt(data);

                                }
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                cal.add(Calendar.DATE, Dayadd); // Adding 5 days
                                String output = sdf.format(cal.getTime());
                                txtFinDate.setText(output);
                                String team = splitter[1];
                                txtTeam.setText(team);
                                String replace1 = write.replace("[", " ").replace("]", " ");
                                String[] replaced = replace1.split(",");
                                for (int a = 0; a < replaced.length; a++) {
                                    String put = replaced[a];
                                    progress = assignteamname.addprogress(put);
                                    progress.toString();
                                }
                                br.close();
                            }
                        }
                        input.close();
                    } catch (FileNotFoundException ex) {

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


                    try {
                        File file = new File("Tasks.txt");
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
                        br.close();
                        CriticalPath.main();

                        File myObj = new File("CriticalPathDays.txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            txtDays.setText("This Project will take " + data + " days");


                        }
                        myReader.close();
                    } catch (Exception ex) {

                    }
                    try {
                        File myObj = new File("InitialNodes.txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            txtInitialNodes.setText(data);
                        }
                        myReader.close();
                    } catch (Exception ex) {

                    }
                    try {
                        File file = new File("CriticalPath.txt");
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String firstLine = br.readLine().trim();
                        String[] columnsName = firstLine.split(",");
                        DefaultTableModel model = (DefaultTableModel) JTableCriticalPath.getModel();
                        model.setColumnIdentifiers(columnsName);
                        model.setRowCount(0);
                        Object[] tableLines = br.lines().toArray();
                        for (int i = 0; i < tableLines.length; i++) {
                            String line = tableLines[i].toString().trim();
                            String[] dataRow = line.split("/");
                            model.addRow(dataRow);
                        }
                        br.close();
                    } catch (Exception ex) {

                    }
                }}
        });

        confirmButton.setEnabled(false);
        kotlinCheckBox.setSelected(false);
        confirmButton.setEnabled(false);
        lblSelectProj.setEnabled(false);
        JcbProjects.setEnabled(false);
        lblTeam.setEnabled(false);
        txtTeam.setEnabled(false);
        lblCriticalPath.setEnabled(false);
        txtDays.setEnabled(false);
        lblInitialNodes.setEnabled(false);
        txtInitialNodes.setEnabled(false);
        lblFinishDate.setEnabled(false);
        txtFinDate.setEnabled(false);
        tasksTable.setEnabled(false);

        kotlinCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kotlinCheckBox.isSelected()) {
                    scalaCheckBox.setSelected(false);
                    scalaCheckBox.setEnabled(false);
                    confirmButton.setEnabled(true);
                    lblSelectProj.setEnabled(true);
                    JcbProjects.setEnabled(true);
                    lblTeam.setEnabled(true);
                    txtTeam.setEnabled(true);
                    lblCriticalPath.setEnabled(true);
                    txtDays.setEnabled(true);
                    lblInitialNodes.setEnabled(true);
                    txtInitialNodes.setEnabled(true);
                    lblFinishDate.setEnabled(true);
                    txtFinDate.setEnabled(true);
                    tasksTable.setEnabled(true);

                } else {
                    if (!kotlinCheckBox.isSelected()) {
                        scalaCheckBox.setEnabled(true);
                        confirmButton.setEnabled(false);
                        lblSelectProj.setEnabled(false);
                        JcbProjects.setEnabled(false);
                        lblTeam.setEnabled(false);
                        txtTeam.setEnabled(false);
                        lblCriticalPath.setEnabled(false);
                        txtDays.setEnabled(false);
                        lblInitialNodes.setEnabled(false);
                        txtInitialNodes.setEnabled(false);
                        lblFinishDate.setEnabled(false);
                        txtFinDate.setEnabled(false);
                        tasksTable.setEnabled(false);

                    }
                }
            }
        });
        scalaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scalaCheckBox.isSelected()) {
                    kotlinCheckBox.setSelected(false);
                    kotlinCheckBox.setEnabled(false);
                    confirmButton.setEnabled(true);
                    lblSelectProj.setEnabled(true);
                    JcbProjects.setEnabled(true);
                    lblTeam.setEnabled(true);
                    txtTeam.setEnabled(true);
                    lblCriticalPath.setEnabled(true);
                    txtDays.setEnabled(true);
                    lblInitialNodes.setEnabled(true);
                    txtInitialNodes.setEnabled(true);
                    lblFinishDate.setEnabled(true);
                    txtFinDate.setEnabled(true);
                    tasksTable.setEnabled(true);

                } else {
                    if (!scalaCheckBox.isSelected()) {
                        kotlinCheckBox.setEnabled(true);
                        confirmButton.setEnabled(false);
                        lblSelectProj.setEnabled(false);
                        JcbProjects.setEnabled(false);
                        lblTeam.setEnabled(false);
                        txtTeam.setEnabled(false);
                        lblCriticalPath.setEnabled(false);
                        txtDays.setEnabled(false);
                        lblInitialNodes.setEnabled(false);
                        txtInitialNodes.setEnabled(false);
                        lblFinishDate.setEnabled(false);
                        txtFinDate.setEnabled(false);
                        tasksTable.setEnabled(false);
                    }
                }
            }
        });


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StringBuffer sbTableData = new StringBuffer();
                for (int row = 0; row < TableTasks.getRowCount(); row++) {
                    for (int column = 0; column < TableTasks.getColumnCount(); column++) {
                        sbTableData.append(TableTasks.getValueAt(row, column)).append("/");
                    }

                }
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    int lineToRemove = JcbProjects.getSelectedIndex() + 1;
                    String currentLine;
                    int count = 0;

                    while ((currentLine = reader.readLine()) != null) {
                        count++;
                        if (count == lineToRemove) {
                            continue;
                        }
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    writer.close();
                    reader.close();
                    inputFile.delete();
                    tempFile.renameTo(inputFile);
                    String edit = sbTableData.toString();
                    edit = edit.replaceAll("([^/]*/[^/]*/[^/]*)/", "$1,");
                    String TeamName = JcbProjects.getSelectedItem().toString();
                    projects = assignteamname.addProject(JcbProjects.getSelectedItem().toString(), txtTeam.getText(), edit);
                    projects.toString();
                    int index = JcbProjects.getSelectedIndex();
                    JcbProjects.removeItemAt(index);
                    JcbProjects.addItem(TeamName);
                    CriticalPath.main();
                    ProjectsKt.rewrite();

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        RecordProgress mp = new RecordProgress();
        mp.setBounds(1500, 1500, 1200, 900);
        mp.setLocationRelativeTo(null);
        mp.setResizable(false);
        mp.setVisible(true);
    }


}
