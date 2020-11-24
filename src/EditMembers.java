import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EditMembers extends JFrame {
    private JTextField txtMemberName;
    private JButton createButton;
    private JButton doneButton;
    private JPanel CreateMember;
    private JButton removeButton;
    private JList TeamMembers;
    private MembersCreator createmembers;
    private CreateMembers members;


    DefaultListModel liss = new DefaultListModel();
    File inputFile = new File("members.txt");
    File tempFile = new File("TempMembers.txt");
    public EditMembers() {
        super("Create Member");
        this.setContentPane(this.CreateMember);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        createmembers = new MembersCreator();
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                members = createmembers.createMember(txtMemberName.getText());
                members.toString();
                        String line = txtMemberName.getText();
                        liss.addElement(line);
                        TeamMembers.setModel(liss);
                txtMemberName.setText("");

            }

        });
        String filePath2 = "members.txt";
        File file = new File(filePath2);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();

            for(int i = 0; i < lines.length; i++){
                String line = lines[i].toString();
                liss.addElement(line);
                TeamMembers.setModel(liss);
            }
            br.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTeam nt = new NewTeam();
                nt.setVisible(true);
                nt.setBounds(500,500, 600 ,600);
                nt.setLocationRelativeTo(null);
                nt.setResizable(false);
                dispose();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = TeamMembers.getSelectedIndex();
                String selected = TeamMembers.getSelectedValue().toString();
                if (selectedIndex != -1) {
                    liss.remove(selectedIndex);
                }

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    int lineToRemove = selectedIndex + 1;
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

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }});


}

    public void main(String[] args) {
        EditMembers memberc = new EditMembers();
        memberc.setBounds(1500,1500, 1200 ,2000);
        memberc.setLocationRelativeTo(null);
        memberc.setResizable(true);
        memberc.setVisible(true);
    }
}


