import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ManageTeams extends JFrame {
    private JPanel MainPnl;
    private JButton editButton;
    private JButton returnButton;
    private JComboBox cbteams;
    private JList TeamMembers;
    private JComboBox Comboboxmembers;
    private JButton addButton;
    private JButton removeButton;


    ManageTeams() {

        super("Manage Teams");
        this.setContentPane(this.MainPnl);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        DefaultListModel liss = new DefaultListModel();
        try {
            String filePath1 = "Teams.txt";
            File file = new File(filePath1 );
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();

            for(int i = 0; i < lines.length; i++){
                String line = lines[i].toString();
                cbteams.addItem(line);
            }

        } catch (FileNotFoundException ex) {

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

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public static void main(String[] args) {
        ManageTeams mt = new ManageTeams();
        mt.setBounds(1500,1500, 1200 ,900);
        mt.setLocationRelativeTo(null);
        mt.setResizable(false);
        mt.setVisible(true);
    }
}
