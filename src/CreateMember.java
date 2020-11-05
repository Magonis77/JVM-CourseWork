import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMember extends JFrame {
    private JTextField txtMemberName;
    private JButton createButton;
    private JButton doneButton;
    private JPanel CreateMember;
    private MembersCreator createmembers;
    private CreateMembers members;



    public CreateMember() {
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
                txtMemberName.setText("");
            }

        });
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTeam nt = new NewTeam();
                nt.setVisible(true);
                nt.setLocationRelativeTo(null);
                nt.setResizable(true);
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        CreateMember memberc = new CreateMember();
        memberc.setBounds(1500,1500, 1200 ,900);
        memberc.setLocationRelativeTo(null);
        memberc.setResizable(false);
        memberc.setVisible(true);
    }
}
