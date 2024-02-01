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

public class TeacherEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField surname;
	private JTextField email;
	private JPasswordField password;
	private JTextField id;

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
		private void editTeacher(int ID,String firstname, String surname, String email, String password) {
		    String url = "jdbc:mysql://localhost:3306/CMS";
		    String dbUsername = "root";
		    String dbPassword = "";

		    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
		         PreparedStatement preparedStatement = connection.prepareStatement(
		        		 "UPDATE users SET firstname=?, surname=?, email=?, password=? WHERE id=?")) {
		    	
		        preparedStatement.setString(1, firstname);
		        preparedStatement.setString(2, surname);
		        preparedStatement.setString(3, email);
		        preparedStatement.setString(4, password);
		        preparedStatement.setInt(5, ID);

		        preparedStatement.executeUpdate();

		        JOptionPane.showMessageDialog(null, "Teacher Successfully Updated");

		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error occurred while Update Teacher.");
		    }
		}


	/**
	 * Create the frame.
	 */
	public TeacherEdit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddTeacher = new JLabel("Edit Teacher");
		lblAddTeacher.setBounds(144, 12, 153, 25);
		lblAddTeacher.setFont(new Font("Dyuthi", Font.BOLD, 24));
		contentPane.add(lblAddTeacher);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblSurname.setBounds(12, 108, 82, 35);
		contentPane.add(lblSurname);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblName.setBounds(12, 74, 82, 35);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email  :");
		lblEmail.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblEmail.setBounds(12, 144, 82, 35);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblPassword.setBounds(12, 176, 99, 35);
		contentPane.add(lblPassword);
		
		JButton btnAddTeacher = new JButton("Edit Teacher");
		btnAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teacherId = id.getText();
				String Fname = name.getText();
				String Sname = surname.getText();
				String Email = email.getText();
				String Password = password.getText();
				
				if(!teacherId.isEmpty() && !Fname.isEmpty() && !Sname.isEmpty() && !Email.isEmpty() && !Password.isEmpty()) {
					int ID = Integer.parseInt(teacherId);
					editTeacher(ID,Fname,Sname,Email,Password);
					
				}else {
					JOptionPane.showMessageDialog(null, "Inputes Cannot be empty !!");
				}
			}
		});
		btnAddTeacher.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnAddTeacher.setBackground(new Color(38, 162, 105));
		btnAddTeacher.setBounds(37, 223, 129, 35);
		contentPane.add(btnAddTeacher);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnReturn.setBackground(new Color(229, 165, 10));
		btnReturn.setBounds(206, 223, 145, 34);
		contentPane.add(btnReturn);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(144, 81, 175, 28);
		contentPane.add(name);
		
		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(144, 115, 175, 28);
		contentPane.add(surname);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(144, 147, 175, 28);
		contentPane.add(email);
		
		password = new JPasswordField();
		password.setBounds(144, 179, 175, 28);
		contentPane.add(password);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Dyuthi", Font.BOLD, 22));
		lblId.setBounds(12, 39, 43, 23);
		contentPane.add(lblId);
		
		id = new JTextField();
		id.setBounds(52, 33, 59, 35);
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel lblenterIdOf = new JLabel("(Enter id of teacher to edit)");
		lblenterIdOf.setForeground(new Color(246, 97, 81));
		lblenterIdOf.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblenterIdOf.setBounds(119, 43, 161, 15);
		contentPane.add(lblenterIdOf);
	}

}
