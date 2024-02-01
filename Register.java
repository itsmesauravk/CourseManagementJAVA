package Swing;

import java.awt.BorderLayout;
import java.util.regex.*;
import java.awt.EventQueue;

import javax.swing.JOptionPane;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Registration;
	private JTextField firstName;
	private JTextField surname;
	private JTextField email;
	private JPasswordField password;
	private JPasswordField passwordConfi;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//data base (user Registration..)
	
	private void registerUser(String email, String password, String role, String firstname,String lastname, int std_level, String std_course) {
        String url = "jdbc:mysql://localhost:3306/CMS";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstname, surname, email, password, role, std_level, std_course) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

        	preparedStatement.setString(1, firstname);
        	preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, role);
            preparedStatement.setInt(6, std_level);
            preparedStatement.setString(7, std_course);
            

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registered as " + role);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred during registration.");
        }
    }
	
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 532);
		Registration = new JPanel();
		Registration.setBackground(new Color(246, 245, 244));
		Registration.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Registration);
		Registration.setLayout(null);
		
		JLabel lblRegistration = new JLabel("REGISTRATION");
		lblRegistration.setFont(new Font("Dyuthi", Font.BOLD, 25));
		lblRegistration.setBounds(254, 23, 189, 35);
		Registration.add(lblRegistration);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblFirstName.setBounds(104, 69, 101, 25);
		Registration.add(lblFirstName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblSurname.setBounds(104, 106, 101, 25);
		Registration.add(lblSurname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblEmail.setBounds(104, 143, 101, 25);
		Registration.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblPassword.setBounds(104, 180, 101, 25);
		Registration.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblConfirmPassword.setBounds(104, 215, 146, 25);
		Registration.add(lblConfirmPassword);
		
		firstName = new JTextField();
		firstName.setBounds(307, 70, 206, 23);
		Registration.add(firstName);
		firstName.setColumns(10);
		
		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(307, 106, 206, 23);
		Registration.add(surname);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(307, 143, 206, 23);
		Registration.add(email);
		
		password = new JPasswordField();
		password.setBounds(307, 180, 206, 23);
		Registration.add(password);
		
		passwordConfi = new JPasswordField();
		passwordConfi.setBounds(307, 215, 206, 23);
		Registration.add(passwordConfi);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.setFont(new Font("Dyuthi", Font.BOLD, 20));
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstname = firstName.getText();
				String lastname = surname.getText();
				String eMail = email.getText();
				String newPass = password.getText();
				String confirmPass = passwordConfi.getText();
				String selectMode = (String)comboBox.getSelectedItem();
				int selectLevel = 0;
				String selectCourse = null;
				
				  // Regex for FirstName
                String regexFN = "[a-zA-Z]+";
                Pattern Fname = Pattern.compile(regexFN);
                
                Matcher FN = Fname.matcher(firstname);
                boolean fname = FN.matches();
                
                // Regex for LastName
                String regexLN = "[a-zA-Z]+";
                Pattern Lname = Pattern.compile(regexLN);
                
                Matcher LN = Fname.matcher(lastname);
                boolean lname = LN.matches();
                
                //for email
                String regexEmail =  "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern verifyEmail = Pattern.compile(regexEmail);
                
                Matcher eM = verifyEmail.matcher(eMail);
                boolean checkEmail = eM.matches();
                
                // Regex for New password
                String regexP = "[a-zA-Z]+";
                Pattern passN = Pattern.compile(regexP);
                
                Matcher pN = passN.matcher(newPass);
                boolean passNew = pN.matches();
                
                // Regex for Confirm password
                String regexPC = "[a-zA-Z0-9]+";
                Pattern passC = Pattern.compile(regexPC);
                
                Matcher pC = passC.matcher(confirmPass);
                boolean passConfirm = pC.matches();
				
				
                if (!firstname.equals("") && !lastname.equals("") && !eMail.equals("") && !newPass.equals("") && !confirmPass.equals("")) {
                    if (fname && lname && checkEmail && passNew && newPass.equals(confirmPass)) {
                        if (!" - - Select mode - - ".equals(selectMode)) {
                            if ("Student".equals(selectMode)) {
                            	selectLevel = Integer.parseInt((String) comboBox_1.getSelectedItem());
                                selectCourse = (String) comboBox_2.getSelectedItem();
                            }
                            registerUser(eMail, newPass, selectMode, firstname, lastname, selectLevel, selectCourse);
                            UserLogin lg = new UserLogin();
                            lg.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Select User Mode");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed...");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Inputs cannot be empty !!");
                }

				
				}
			
		});
		registerBtn.setBounds(278, 387, 130, 35);
		Registration.add(registerBtn);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin l = new UserLogin(); //creating obj this will redirect to Login page
				l.setVisible(true);
				dispose();
			}
		});
		
		btnLogin.setBounds(329, 450, 117, 25);
		Registration.add(btnLogin);
		
		JLabel lblAlreadyHaveAccount = new JLabel("Already have account ?");
		lblAlreadyHaveAccount.setFont(new Font("Dyuthi", Font.BOLD, 12));
		lblAlreadyHaveAccount.setForeground(new Color(28, 113, 216));
		lblAlreadyHaveAccount.setBounds(198, 455, 130, 15);
		Registration.add(lblAlreadyHaveAccount);
		
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Dyuthi", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {" - - Select mode - - ", "Admin", "Teacher", "Student"}));
		comboBox.setBounds(307, 250, 206, 24);
		Registration.add(comboBox);
		
		JLabel userMode = new JLabel("User Mode");
		userMode.setFont(new Font("Dyuthi", Font.BOLD, 18));
		userMode.setBounds(104, 250, 101, 25);
		Registration.add(userMode);
			
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Dyuthi", Font.BOLD, 18));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {" -- Level --", "4", "5", "6"}));
		comboBox_1.setBounds(202, 291, 206, 24);
		comboBox_1.setVisible(false);
		Registration.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Dyuthi", Font.BOLD, 18));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"  - - Course - -", "BSc Computer Science", "BIT", "IMBA"}));
		comboBox_2.setBounds(202, 327, 206, 24);
		comboBox_2.setVisible(false); 
		Registration.add(comboBox_2);
		
		// Add this code in the constructor after initializing comboBox and comboBox_1
		comboBox.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        String selectedMode = (String) comboBox.getSelectedItem();
		        
		        // Check if the selected mode is Student
		        if ("Student".equals(selectedMode)) {
		            comboBox_1.setVisible(true);  // Show Level comboBox
		            comboBox_2.setVisible(true);  // Show Course comboBox
		        } else {
		            comboBox_1.setVisible(false);  // Hide Level comboBox
		            comboBox_2.setVisible(false);  // Hide Course comboBox
		        }
		    }
		});
	}
}
