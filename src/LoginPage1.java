import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class LoginPage1 extends JFrame implements ActionListener
{

    private JLabel lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnAdmin, btnUser;

    public LoginPage1() {
        setTitle("Home Page");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        btnAdmin = new JButton("Admin");
        btnAdmin.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(btnAdmin, constraints);

        btnUser = new JButton("User");
        btnUser.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(btnUser, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdmin || e.getSource() == btnUser) {
            // create and show the login page
            JFrame loginFrame = new JFrame("Login Page");
            loginFrame.setSize(400, 300);
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            loginFrame.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.fill = GridBagConstraints.HORIZONTAL;

            lblUsername = new JLabel("Username");
            constraints.gridx = 0;
            constraints.gridy = 0;
            loginFrame.add(lblUsername, constraints);

            txtUsername = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 0;
            loginFrame.add(txtUsername, constraints);

            lblPassword = new JLabel("Password");
            constraints.gridx = 0;
            constraints.gridy = 1;
            loginFrame.add(lblPassword, constraints);

            txtPassword = new JPasswordField(20);
            constraints.gridx = 1;
            constraints.gridy = 1;
            loginFrame.add(txtPassword, constraints);

            btnLogin = new JButton("Login");
            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = txtUsername.getText();
                    char[] password = txtPassword.getPassword();
                    String passwordStr = new String(password);

                    if (username.equals("admin") && passwordStr.equals("admin")) {
                        JOptionPane.showMessageDialog(null, "Login Successful as Admin");
                    } else if (username.equals("user") && passwordStr.equals("user")) {
                        JOptionPane.showMessageDialog(null, "Login Successful as User");
                        String name = JOptionPane.showInputDialog(null, "Enter your name:");
                        String branch = JOptionPane.showInputDialog(null, "Enter your branch:");
                        String enrollment = JOptionPane.showInputDialog(null, "Enter your enrollment number:");
                        String year = JOptionPane.showInputDialog(null, "Enter your year:");
                        String contact = JOptionPane.showInputDialog(null, "Enter your contact number:");
                        String email = JOptionPane.showInputDialog(null, "Enter your email:");
                        String dob = JOptionPane.showInputDialog(null, "Enter your date of birth (DD/MM/YYYY):");
                        String address = JOptionPane.showInputDialog(null, "Enter your address:");

                        // generate the QR code image
                        String qrCodeData = "Name: " + name + "\nBranch: " + branch + "\nEnrollment: " + enrollment + "\nYear: " + year + "\nContact: " + contact + "\nEmail: " + email + "\nDOB: " + dob + "\nAddress: " + address;
                        int size = 150;
                        Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
                        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                        BitMatrix bitMatrix;
                        try {
                            bitMatrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes("UTF-8"), "ISO-8859-1"), BarcodeFormat.QR_CODE, size, size, hintMap);
                        } catch (UnsupportedEncodingException | WriterException e1) {
                            e1.printStackTrace();
                            return;
                        }

                        File qrFile = new File("qrcode.png");
                        try {
                            MatrixToImageWriter.writeToFile(bitMatrix, "png", qrFile);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            return;
                        }

                        ImageIcon qrCodeIcon = new ImageIcon(qrFile.getAbsolutePath());
                        JLabel qrCodeLabel = new JLabel(qrCodeIcon);
                        JFrame idCardFrame = new JFrame("ID Card");
                        idCardFrame.setSize(450, 600);
                        idCardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        idCardFrame.setLayout(new GridBagLayout());
                        constraints.insets = new Insets(10, 10, 10, 10);

                        JLabel lblName = new JLabel("Name: " + name);
                        constraints.gridx = 0;
                        constraints.gridy = 0;
                        idCardFrame.add(lblName, constraints);

                        JLabel lblBranch = new JLabel("Branch: " + branch);
                        constraints.gridx = 0;
                        constraints.gridy = 1;
                        idCardFrame.add(lblBranch, constraints);

                        JLabel lblEnrollment = new JLabel("Enrollment Number: " + enrollment);
                        constraints.gridx = 0;
                        constraints.gridy = 2;
                        idCardFrame.add(lblEnrollment, constraints);

                        JLabel lblYear = new JLabel("Year: " + year);
                        constraints.gridx = 0;
                        constraints.gridy = 3;
                        idCardFrame.add(lblYear, constraints);

                        JLabel lblContact = new JLabel("Contact Number: " + contact);
                        constraints.gridx = 0;
                        constraints.gridy = 4;
                        idCardFrame.add(lblContact, constraints);

                        JLabel lblEmail = new JLabel("Email: " + email);
                        constraints.gridx = 0;
                        constraints.gridy = 5;
                        idCardFrame.add(lblEmail, constraints);

                        JLabel lblDOB = new JLabel("Date of Birth: " + dob);
                        constraints.gridx = 0;
                        constraints.gridy = 6;
                        idCardFrame.add(lblDOB, constraints);

                        JLabel lblAddress = new JLabel("Address: " + address);
                        constraints.gridx = 0;
                        constraints.gridy = 7;
                        idCardFrame.add(lblAddress, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 8;
                        idCardFrame.add(qrCodeLabel, constraints);

                        idCardFrame.setVisible(true);
                        System.out.println("ID card created successfully!");

                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed. Try Again");
                    }
                }
            });
            constraints.gridx = 1;
            constraints.gridy = 2;
            loginFrame.add(btnLogin, constraints);

            loginFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        LoginPage1 homePage = new LoginPage1();
        homePage.setVisible(true);

    }
}