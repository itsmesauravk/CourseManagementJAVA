package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	private boolean checkLogin(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/CMS";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	//encapsulation
	
	private String userEmail;
	
	public String getUserEmail() {
		return userEmail;
	}
	
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 518);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(150, 233, 198));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login ");
		lblLogin.setBounds(227, 31, 118, 35);
		lblLogin.setFont(new Font("Dyuthi", Font.BOLD, 30));
		contentPane.add(lblLogin);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(196, 88, 88, 35);
		lblEmail.setFont(new Font("Dyuthi", Font.BOLD, 20));
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(196, 172, 88, 35);
		lblPassword.setFont(new Font("Dyuthi", Font.BOLD, 20));
		contentPane.add(lblPassword);
		
		emailField = new JTextField();
		emailField.setBounds(196, 128, 210, 32);
		emailField.setBackground(new Color(255, 255, 255));
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(196, 203, 210, 32);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(228, 266, 117, 35);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emailLogin = emailField.getText();
				String passwordLogin = passwordField.getText();
				if(checkLogin(emailLogin,passwordLogin)) {
					JOptionPane.showMessageDialog(null,"Login succesFul.");
					userEmail = emailLogin;
					Dashboard dashboard = new Dashboard(userEmail);
					dashboard.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null,"Unable to login.");
				}
			
			}
		});
		btnLogin.setFont(new Font("Dyuthi", Font.BOLD, 20));
		contentPane.add(btnLogin);
		
		JLabel lblCreateANew = new JLabel("Create a new account ?");
		lblCreateANew.setBounds(207, 331, 170, 15);
		lblCreateANew.setFont(new Font("Dyuthi", Font.BOLD, 16));
		contentPane.add(lblCreateANew);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(228, 349, 117, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration newReg = new Registration();  //creating obj this will redirect to Registration page
				newReg.setVisible(true);
				dispose(); 
				//dispose will remove unused tab
			}
		});
		btnNewButton.setFont(new Font("Dyuthi", Font.BOLD, 16));
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setBounds(61, 243, 70, 15);
		contentPane.add(label);
	}
}
