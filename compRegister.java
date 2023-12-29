import java.awt.Choice;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class compRegister implements ActionListener {
    private JDialog win;
    private compFile cfile;
    private JPanel panel1, panel2, panel3;
    private Choice dept, priorityChoice, typeChoice, assetChoice;
    private String[] depts = { "Faculty", "Staff", "Student" };
    private String[] priorities = { "1", "2", "3" };
    private String[] types = { "Internet", "Electronics", "Stationary", "AC", "Furniture" };
    private String[] assets = { "Projector", "Printer", "Wifi","Lanports","Benches", "Scanner", "Chairs","Windows","Fans","Air Conditioners","Stationary" };
    private JTextArea taComp, taAssetRequest;
    private JTextField complaintNoField, emailField, addressField;
    private JButton submitBtn, cancelBtn;
    private int cno;

    public compRegister(compFile cfile) {
        win = new JDialog();
        this.cfile = cfile;
        cno = cfile.getTotalComps() + 1;

        win.setModalityType(ModalityType.APPLICATION_MODAL);
        win.setTitle("Register Request");
        win.setSize(500, 500);
        win.setLayout(new FlowLayout());

        dept = new Choice();
        for (String d : depts) {
            dept.add(d);
        }

        priorityChoice = new Choice();
        for (String priority : priorities) {
            priorityChoice.add(priority);
        }

        typeChoice = new Choice();
        for (String type : types) {
            typeChoice.add(type);
        }

        assetChoice = new Choice();
        for (String asset : assets) {
            assetChoice.add(asset);
        }

        panel1 = new JPanel();
        panel1.add(new JLabel("Profession"));
        panel1.add(dept);
        panel1.add(new JLabel("Priority"));
        panel1.add(priorityChoice);
        panel1.add(new JLabel("Type"));
        panel1.add(typeChoice);
        panel1.add(new JLabel("Asset"));
        panel1.add(assetChoice);
        win.add(panel1);

        panel2 = new JPanel();
        panel2.add(new JLabel("Request no. "));
        complaintNoField = new JTextField(String.valueOf(cno), 5);
        complaintNoField.setEditable(false);
        panel2.add(complaintNoField);

        panel2.add(new JLabel("Email"));
        emailField = new JTextField(20);
        panel2.add(emailField);

        panel2.add(new JLabel("Address"));
        addressField = new JTextField(20);
        panel2.add(addressField);
        win.add(panel2);

        taComp = new JTextArea(10, 40);
        win.add(new JScrollPane(taComp));

        taAssetRequest = new JTextArea(5, 40);
        win.add(new JScrollPane(taAssetRequest));

        panel3 = new JPanel();
        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(this);
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(this);
        panel3.add(submitBtn);
        panel3.add(cancelBtn);

        win.add(panel3);
        win.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bPressed = (JButton) e.getSource();
        if (bPressed.equals(submitBtn)) {
            int selectedPriority = Integer.parseInt(priorityChoice.getSelectedItem());
            String selectedType = typeChoice.getSelectedItem();
            String selectedAsset = assetChoice.getSelectedItem();
            String assetRequestDetails = taAssetRequest.getText();

            // Get values for Email and Address
            String email = emailField.getText();
            String address = addressField.getText();

            complaint newComp = new complaint(cno, dept.getSelectedItem(), taComp.getText(), assetRequestDetails,
                    selectedPriority, selectedType, email, address, selectedAsset);

            cfile.addComp(newComp);
            JOptionPane.showMessageDialog(null,
                    "Request has been Registered.\nYour Request No. is " + cno);
            win.dispose();

        } else if (bPressed.equals(cancelBtn)) {
            int cancel = JOptionPane.showConfirmDialog(null, "Cancel Request Registration ?");
            if (cancel == JOptionPane.YES_OPTION) {
                win.dispose();
            }
        }
    }
}
