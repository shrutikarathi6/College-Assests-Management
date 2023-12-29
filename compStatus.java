import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class compStatus implements ActionListener {
    private JDialog win;
    private compFile cFile;
    private JTextField tfCompNum;
    private JTextArea taStatus;
    private JTable table;

    public compStatus(compFile cFile) {
        this.cFile = cFile;

        win = new JDialog();
        win.setModalityType(ModalityType.APPLICATION_MODAL);
        win.setTitle("Issue Status");
        win.setSize(600, 400);

        // Main Panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for input and button
        JPanel inputPanel = new JPanel();
        JLabel lblCompNum = new JLabel("Enter Request Number : ");
        tfCompNum = new JTextField(20);
        JButton btnCheckStatus = new JButton("Check Status");
        btnCheckStatus.addActionListener(this);

        inputPanel.add(lblCompNum);
        inputPanel.add(tfCompNum);
        inputPanel.add(btnCheckStatus);

        // Panel for displaying status
        JPanel statusPanel = new JPanel(new BorderLayout());
        JLabel lblStatus = new JLabel("Status ");
        taStatus = new JTextArea(10, 40);
        taStatus.setEditable(false);
        taStatus.setBackground(Color.GRAY);
        taStatus.setFont(new Font("POPPINS", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(taStatus);
        statusPanel.add(lblStatus, BorderLayout.NORTH);
        statusPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel for displaying complaint details in a table
        JPanel tablePanel = new JPanel(new BorderLayout());
        //table = createTable();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Add panels to the main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(statusPanel, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        // Set the main panel as the content pane
        win.setContentPane(mainPanel);

        win.setVisible(true);
    }

    // private JTable createTable() {
    //     Object columnNames[] = {"Complaint No.", "Profession", "Complaint", "Solution", "Priority", "Type", "Email", "Address","Assets"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
    //         @Override
    //         public boolean isCellEditable(int row, int column) {
    //             return false;
    //         }
    //     };

    //     JTable table = new JTable(model);
    //     table.setBackground(Color.CYAN);
    //     table.setFont(new Font("Poppins", Font.BOLD, 20));
    //     table.setAutoCreateRowSorter(true);

    //     return table;
    // }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int compNum = Integer.parseInt(tfCompNum.getText());
            complaint comp = cFile.getComp(compNum);
            if (comp == null) {
                taStatus.setText("Invalid Request No.");
            } else {
                String status = "Type: " + comp.getType() + "\n"
                        + "Priority: " + comp.getPriority() + "\n"
                        + "Email: " + comp.getEmail() + "\n"
                        + "Solution: " + cFile.getSoln(compNum);
                taStatus.setText(status);

                // Update the table with the complaint details
                updateTable(comp);
            }
        } catch (NumberFormatException | NullPointerException exc) {
            taStatus.setText("Invalid Request No.");
        }
    }

    private void updateTable(complaint comp) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        // Add a new row with complaint details
        Object[] rowData = {
                comp.getcNo(),
                comp.getDept(),
                comp.getComp(),
                comp.getSoln(),
                comp.getPriority(),
                comp.getType(),
                comp.getEmail(),
                comp.getAddress(),
                comp.getAsset()
        };

        model.addRow(rowData);
    }
}



