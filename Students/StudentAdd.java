package Swing.Students;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Swing.UserLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class StudentAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstname;
	private JTextField surname;
	private JTextField email;
	private JPasswordField password;
	private JComboBox level;
	private JComboBox course ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentAdd frame = new StudentAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//adding student
	// adding student
	private void addStudent(String firstname, String surname, String email, String password, int std_level, String std_course) {
	    String url = "jdbc:mysql://localhost:3306/CMS";
	    String dbUsername = "root";
	    String dbPassword = "";

	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "INSERT INTO users (firstname, surname, email, password, role, std_level, std_course) VALUES (?, ?, ?, ?, 'Student', ?, ?)")) {

	        preparedStatement.setString(1, firstname);
	        preparedStatement.setString(2, surname);
	        preparedStatement.setString(3, email);
	        preparedStatement.setString(4, password);
	        preparedStatement.setInt(5, std_level);
	        preparedStatement.setString(6, std_course);

	        preparedStatement.executeUpdate();

	        JOptionPane.showMessageDialog(null, "Student Successfully Added");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error occurred while Adding student.");
	    }
	}

	/**
	 * Create the frame.
	 */
	public StudentAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddStudent = new JLabel("Add Student");
		lblAddStudent.setBounds(190, 0, 145, 35);
		lblAddStudent.setFont(new Font("Dyuthi", Font.BOLD, 24));
		contentPane.add(lblAddStudent);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblName.setBounds(12, 48, 82, 35);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblSurname.setBounds(12, 82, 82, 35);
		contentPane.add(lblSurname);
		
		JLabel lblEmail = new JLabel("Email  :");
		lblEmail.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblEmail.setBounds(12, 117, 82, 35);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblPassword.setBounds(12, 155, 99, 35);
		contentPane.add(lblPassword);
		
		JLabel lblLevel = new JLabel("Level :");
		lblLevel.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblLevel.setBounds(12, 195, 82, 35);
		contentPane.add(lblLevel);
		
		JLabel lblCourse = new JLabel("Course :");
		lblCourse.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblCourse.setBounds(12, 236, 82, 35);
		contentPane.add(lblCourse);
		
		firstname = new JTextField();
		firstname.setBounds(153, 51, 175, 28);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(153, 85, 175, 28);
		contentPane.add(surname);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(153, 120, 175, 28);
		contentPane.add(email);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Firstname = firstname.getText();
				String Surname = surname.getText();
				String Email = email.getText();
				String Password = password.getText();
				String selectLevel = (String)level.getSelectedItem();
				String selectCourse = (String)course.getSelectedItem();
				
				//regex
				
				String regexFN = "[a-zA-Z]+";
                Pattern Fname = Pattern.compile(regexFN);
                
                Matcher FN = Fname.matcher(Firstname);
                boolean fname = FN.matches();
                
                // Regex for LastName
                String regexLN = "[a-zA-Z]+";
                Pattern Lname = Pattern.compile(regexLN);
                
                Matcher LN = Fname.matcher(Surname);
                boolean lname = LN.matches();
                
                //for email
                String regexEmail =  "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern verifyEmail = Pattern.compile(regexEmail);
                
                Matcher eM = verifyEmail.matcher(Email);
                boolean checkEmail = eM.matches();
                
                // Regex for New password
                String regexP = "[a-zA-Z]+";
                Pattern passN = Pattern.compile(regexP);
                
                Matcher pN = passN.matcher(Password);
                boolean passNew = pN.matches();
                
                if (!Firstname.equals("") && !Surname.equals("") && !Email.equals("") && !Password.equals("") && !selectLevel.equals("") && !selectCourse.equals("")) {
                    if (fname && lname && checkEmail && passNew ) {
                        
                            int lvl = Integer.parseInt(selectLevel);
                            
                            addStudent( Firstname, Surname , Email, Password ,  lvl,  selectCourse);
                     
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed...");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Inputs cannot be empty !!");
                }
				
				
			}
	
		});
		btnAddStudent.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnAddStudent.setBackground(new Color(38, 162, 105));
		btnAddStudent.setBounds(56, 289, 129, 35);
		contentPane.add(btnAddStudent);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnReturn.setBackground(new Color(229, 165, 10));
		btnReturn.setBounds(229, 289, 145, 34);
		contentPane.add(btnReturn);
		
		password = new JPasswordField();
		password.setBounds(153, 158, 175, 28);
		contentPane.add(password);
		
		level = new JComboBox();
		level.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6"}));
		level.setFont(new Font("Dyuthi", Font.BOLD, 18));
		level.setBounds(153, 198, 46, 28);
		contentPane.add(level);
		
		course = new JComboBox();
		course.setFont(new Font("Dyuthi", Font.BOLD, 14));
		course.setModel(new DefaultComboBoxModel(new String[] {"BSc Computer Science", "BIT", "IMBA"}));
		course.setBounds(149, 235, 175, 28);
		contentPane.add(course);
	}
}
