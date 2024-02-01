package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CourseAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField courseName;
	private JTextField courseDuration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseAdd frame = new CourseAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//adding the course
	String url = "jdbc:mysql://localhost:3306/CMS";
	String dbUsername = "root";
	String dbPassword = "";
	
	public void addCourseData(String course_name, int course_duration ) {
	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO courses (course_name, course_duration) VALUES (?, ?);\n"
	            		+ "")) {

	    	preparedStatement.setString(1, course_name);
	        preparedStatement.setInt(2, course_duration);
	        
	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Course Added successfully!");
	            JOptionPane.showMessageDialog(null, "Course Added Sucessfully");
	        } else {
	            System.out.println("Course not found with ID: ");
	            JOptionPane.showMessageDialog(null, "Failed to Add Course !");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Create the frame.
	 */
	public CourseAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddCourse = new JLabel("Add Course");
		lblAddCourse.setFont(new Font("Dyuthi", Font.BOLD, 24));
		lblAddCourse.setBounds(139, 0, 127, 35);
		contentPane.add(lblAddCourse);
		
		JLabel lblCourseName = new JLabel("Course Name :");
		lblCourseName.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblCourseName.setBounds(46, 58, 127, 35);
		contentPane.add(lblCourseName);
		
		JLabel lblCourseDuration = new JLabel("Course Duration :");
		lblCourseDuration.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblCourseDuration.setBounds(46, 105, 150, 35);
		contentPane.add(lblCourseDuration);
		
		courseName = new JTextField();
		courseName.setBounds(214, 61, 169, 28);
		contentPane.add(courseName);
		courseName.setColumns(10);
		
		courseDuration = new JTextField();
		courseDuration.setColumns(10);
		courseDuration.setBounds(214, 112, 169, 28);
		contentPane.add(courseDuration);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseN = courseName.getText();
				String ScourseD = courseDuration.getText();
				
				if( !courseN.isEmpty() && !ScourseD.isEmpty()) {
					int course_duration = Integer.parseInt(ScourseD);
					addCourseData(courseN,course_duration );
//					JOptionPane.showMessageDialog(null, "Course Updated Sucessfully");
				}else {
					JOptionPane.showMessageDialog(null, "Inputes Cannot be empty !!");
				}
				
				//addCourseData(course_name,course_duration )
			}
		});
		btnAdd.setBackground(new Color(38, 162, 105));
		btnAdd.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnAdd.setBounds(79, 196, 117, 25);
		contentPane.add(btnAdd);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(new Color(229, 165, 10));
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setBounds(243, 196, 117, 25);
		contentPane.add(btnReturn);
	}

}
