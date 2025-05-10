import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormMahasiswa extends JFrame {
    private JTextField txtNIM;
    private JTextField txtName;
    private JTextArea txtAddress;
    private JRadioButton rbMale;
    private JRadioButton rbFemale;
    private JComboBox<String> cbProgram;
    private JButton btnSubmit;
    private JButton btnReset;

    public FormMahasiswa() {
        setTitle("Form Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create panel for form inputs
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // NIM
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(new JLabel("NIM:"), gbc);
        txtNIM = new JTextField();
        gbc.gridx = 1;
        panelForm.add(txtNIM, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(new JLabel("Nama:"), gbc);
        txtName = new JTextField();
        gbc.gridx = 1;
        panelForm.add(txtName, gbc);

        // Address
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(new JLabel("Alamat:"), gbc);
        txtAddress = new JTextArea(3, 20);
        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);
        JScrollPane scrollAddress = new JScrollPane(txtAddress);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelForm.add(scrollAddress, gbc);
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Gender
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelForm.add(new JLabel("Jenis Kelamin:"), gbc);
        JPanel panelGender = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rbMale = new JRadioButton("Laki-laki");
        rbFemale = new JRadioButton("Perempuan");
        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(rbMale);
        bgGender.add(rbFemale);
        panelGender.add(rbMale);
        panelGender.add(rbFemale);
        gbc.gridx = 1;
        panelForm.add(panelGender, gbc);

        // Program Study
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelForm.add(new JLabel("Program Studi:"), gbc);
        cbProgram = new JComboBox<>(new String[]{
            "Informatika",
            "Sistem Informasi",
            "Teknik Komputer",
            "Manajemen",
            "Akuntansi"
        });
        gbc.gridx = 1;
        panelForm.add(cbProgram, gbc);

        // Buttons panel
        JPanel panelButtons = new JPanel();
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        panelButtons.add(btnSubmit);
        panelButtons.add(btnReset);

        // Add action listeners
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onReset();
            }
        });

        // Add panels to frame
        add(panelForm, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private void onSubmit() {
        String nim = txtNIM.getText().trim();
        String name = txtName.getText().trim();
        String address = txtAddress.getText().trim();
        String gender = rbMale.isSelected() ? "Laki-laki" : (rbFemale.isSelected() ? "Perempuan" : "");
        String program = (String) cbProgram.getSelectedItem();

        if (nim.isEmpty() || name.isEmpty() || address.isEmpty() || gender.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Mohon lengkapi semua data!",
                    "Data Tidak Lengkap",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String message = String.format(
                "Data Mahasiswa:\n\nNIM: %s\nNama: %s\nAlamat: %s\nJenis Kelamin: %s\nProgram Studi: %s",
                nim, name, address, gender, program
        );

        JOptionPane.showMessageDialog(this, message, "Data Mahasiswa", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onReset() {
        txtNIM.setText("");
        txtName.setText("");
        txtAddress.setText("");
        rbMale.setSelected(false);
        rbFemale.setSelected(false);
        cbProgram.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        // Set look and feel to system default for modern UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormMahasiswa form = new FormMahasiswa();
                form.setVisible(true);
            }
        });
    }
}

