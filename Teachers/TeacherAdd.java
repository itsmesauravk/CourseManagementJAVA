package Swing.Teachers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TeacherAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField surname;
	private JTextField email;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherAdd frame = new TeacherAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//db
	// adding student
		private void addTeacher(String firstname, String surname, String email, String password) {
		    String url = "jdbc:mysql://localhost:3306/CMS";
		    String dbUsername = "root";
		    String dbPassword = "";

		    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
		         PreparedStatement preparedStatement = connection.prepareStatement(
		                 "INSERT INTO users (firstname, surname, email, password, role, std_level, std_course) VALUES (?, ?, ?, ?, 'Teacher', 0, NULL)")) {

		        preparedStatement.setString(1, firstname);
		        preparedStatement.setString(2, surname);
		        preparedStatement.setString(3, email);
		        preparedStatement.setString(4, password); 

		        preparedStatement.executeUpdate();

		        JOptionPane.showMessageDialog(null, "Teacher Successfully Added");

		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error occurred while Adding Teacher.");
		    }
		}


	/**
	 * Create the frame.
	 */
	public TeacherAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddTeacher = new JLabel("Add Teacher");
		lblAddTeacher.setBounds(144, 12, 153, 25);
		lblAddTeacher.setFont(new Font("Dyuthi", Font.BOLD, 24));
		contentPane.add(lblAddTeacher);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblSurname.setBounds(12, 70, 82, 35);
		contentPane.add(lblSurname);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblName.setBounds(12, 37, 82, 35);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email  :");
		lblEmail.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblEmail.setBounds(12, 104, 82, 35);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblPassword.setBounds(12, 144, 99, 35);
		contentPane.add(lblPassword);
		
		JButton btnAddTeacher = new JButton("Add Teacher");
		btnAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Fname = name.getText();
				String Sname = surname.getText();
				String Email = email.getText();
				String Password = password.getText();
				
				if(!Fname.isEmpty() && !Sname.isEmpty() && !Email.isEmpty() && !Password.isEmpty()) {
					addTeacher(Fname,  Sname,Email,Password);
					
				}else {
					JOptionPane.showMessageDialog(null, "Inputes Cannot be empty !!");
				}
			}
		});
		btnAddTeacher.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnAddTeacher.setBackground(new Color(38, 162, 105));
		btnAddTeacher.setBounds(42, 201, 129, 35);
		contentPane.add(btnAddTeacher);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnReturn.setBackground(new Color(229, 165, 10));
		btnReturn.setBounds(208, 201, 145, 34);
		contentPane.add(btnReturn);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(144, 44, 175, 28);
		contentPane.add(name);
		
		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(144, 77, 175, 28);
		contentPane.add(surname);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(144, 111, 175, 28);
		contentPane.add(email);
		
		password = new JPasswordField();
		password.setBounds(144, 147, 175, 28);
		contentPane.add(password);
	}

}
