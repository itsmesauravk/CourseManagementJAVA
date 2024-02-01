package Swing.Students;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
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

public class StudentEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField surname;
	private JTextField email;
	private JPasswordField password;
	private JTextField studentId;
	private JComboBox level;
	private JComboBox course;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentEdit frame = new StudentEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//updating student
	private void updateStudent(int id, String firstname, String surname, String email, String password, int std_level, String std_course) {
	    String url = "jdbc:mysql://localhost:3306/CMS";
	    String dbUsername = "root";
	    String dbPassword = "";

	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "UPDATE users SET firstname=?, surname=?, email=?, password=?, std_level=?, std_course=? WHERE id=?")) {

	        preparedStatement.setString(1, firstname);
	        preparedStatement.setString(2, surname);
	        preparedStatement.setString(3, email);
	        preparedStatement.setString(4, password);
	        preparedStatement.setInt(5, std_level);
	        preparedStatement.setString(6, std_course);
	        preparedStatement.setInt(7, id);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Student Successfully Updated");
	        } else {
	            JOptionPane.showMessageDialog(null, "No student found with the given ID");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error occurred while updating student.");
	    }
	}


	/**
	 * Create the frame.
	 */
	public StudentEdit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUpdateStudent = new JLabel("Update Student");
		lblUpdateStudent.setBounds(151, 12, 192, 35);
		lblUpdateStudent.setFont(new Font("Dyuthi", Font.BOLD, 24));
		contentPane.add(lblUpdateStudent);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(12, 91, 82, 35);
		lblName.setFont(new Font("Dyuthi", Font.BOLD, 18));
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setBounds(12, 122, 82, 35);
		lblSurname.setFont(new Font("Dyuthi", Font.BOLD, 18));
		contentPane.add(lblSurname);
		
		JLabel lblEmail = new JLabel("Email  :");
		lblEmail.setBounds(12, 155, 82, 35);
		lblEmail.setFont(new Font("Dyuthi", Font.BOLD, 18));
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(12, 187, 99, 35);
		lblPassword.setFont(new Font("Dyuthi", Font.BOLD, 18));
		contentPane.add(lblPassword);
		
		JLabel lblLevel = new JLabel("Level :");
		lblLevel.setBounds(12, 222, 82, 35);
		lblLevel.setFont(new Font("Dyuthi", Font.BOLD, 18));
		contentPane.add(lblLevel);
		
		JLabel lblCourse = new JLabel("Course :");
		lblCourse.setBounds(12, 258, 82, 35);
		lblCourse.setFont(new Font("Dyuthi", Font.BOLD, 18));
		contentPane.add(lblCourse);
		
		name = new JTextField();
		name.setBounds(142, 98, 175, 28);
		name.setColumns(10);
		contentPane.add(name);
		
		surname = new JTextField();
		surname.setBounds(142, 129, 175, 28);
		surname.setColumns(10);
		contentPane.add(surname);
		
		email = new JTextField();
		email.setBounds(142, 162, 175, 28);
		email.setColumns(10);
		contentPane.add(email);
		
		password = new JPasswordField();
		password.setBounds(142, 194, 175, 28);
		contentPane.add(password);
		
		JComboBox level = new JComboBox();
		level.setBounds(142, 226, 46, 28);
		level.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6"}));
		level.setFont(new Font("Dyuthi", Font.BOLD, 18));
		contentPane.add(level);
		
		JComboBox course = new JComboBox();
		course.setBounds(142, 262, 175, 28);
		course.setModel(new DefaultComboBoxModel(new String[] {"BSc Computer Science", "BIT", "IMBA"}));
		course.setFont(new Font("Dyuthi", Font.BOLD, 14));
		contentPane.add(course);
		
		JButton btnUpdateStudent = new JButton("Update Std");
		btnUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stdId= studentId.getText();
				String Firstname = name.getText();
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
                        	int ID = Integer.parseInt(stdId);
                            int lvl = Integer.parseInt(selectLevel);
                            
                            updateStudent(ID,  Firstname,  Surname,  Email, Password, lvl, selectCourse);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed...");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Inputs cannot be empty !!");
                }
				
			}
		});
		btnUpdateStudent.setBounds(58, 309, 129, 35);
		btnUpdateStudent.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnUpdateStudent.setBackground(new Color(38, 162, 105));
		contentPane.add(btnUpdateStudent);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setBounds(217, 309, 149, 35);
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnReturn.setBackground(new Color(229, 165, 10));
		contentPane.add(btnReturn);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setForeground(new Color(129, 61, 156));
		lblId.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblId.setBounds(26, 52, 46, 35);
		contentPane.add(lblId);
		
		studentId = new JTextField();
		studentId.setBounds(74, 51, 74, 35);
		contentPane.add(studentId);
		studentId.setColumns(10);
	}

}
