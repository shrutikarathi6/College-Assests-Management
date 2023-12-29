
import java.awt.Dialog.ModalityType;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class compReport {
    private JDialog win;
    private JTable tableAllComps;

    public compReport(compFile cfile) {
        // Ask for the password before creating the report dialog
        if (requestPassword()) {
            win = new JDialog();
            win.setModalityType(ModalityType.APPLICATION_MODAL);
            win.setTitle("Request Granted");
            win.setSize(500, 500);
            win.setLayout(new GridLayout(1, 1));

            tableAllComps = cfile.returnTable();
            tableAllComps.setEnabled(false);
            win.add(new JScrollPane(tableAllComps));
            win.setVisible(true);
        } else {
            // Password incorrect or canceled, handle accordingly
            System.out.println("Access Denied");
        }
    }

    private boolean requestPassword() {
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {"Enter Password:", passwordField};
        int option = JOptionPane.showConfirmDialog(null, message, "Password", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            // Check the entered password (for example, you can hardcode a password here)
            String enteredPassword = new String(passwordField.getPassword());
            return enteredPassword.equals("pass"); // Replace "your_password" with your actual password
        }
        return false; // User canceled the password prompt
    }
}
