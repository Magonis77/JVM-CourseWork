import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NewProject extends JFrame {
    private JButton createProjectButton;
    private JTextField projTitle;
    private JComboBox assigntoTeam;
    private JTextField assignTask;
    private JComboBox taskComp;
    private JButton addTaskButton;
    private JButton returnButton;
    private JPanel MainPnl;
    private JTextField textField1;
    private JTextField textField2;
    private JTable Tasks;
    private Projects projects;
    private CreateHandler2 createprojectname;
    private MembersHandler2 assignteamname;


    NewProject() {
        super("New Project Entry");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        createprojectname = new CreateHandler2();
        assignteamname = new MembersHandler2();
        String filePath = "projects.txt";




        try {
            String filePath2 = "Teams.txt";
            File file = new File(filePath2 );
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
            writer.print("" + "\n");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String filePath1 = "tempProjects.txt";

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
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projects = assignteamname.addMember(assignTask.getText());
                projects.toString();


                String filePath = "projects.txt";
                File file = new File(filePath);

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    // get the first line
                    // get the columns name from the first line
                    // set columns name to the jtable model
                    String firstLine = br.readLine().trim();
                    String[] columnsName = firstLine.split(",");
                    DefaultTableModel model = (DefaultTableModel)Tasks.getModel();
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
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projects = createprojectname.createProject(projTitle.getText());
                projects.toString();
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


