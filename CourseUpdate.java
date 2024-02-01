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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CourseUpdate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField courseId;
	private JTextField courseName;
	private JTextField courseDuration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseUpdate frame = new CourseUpdate();
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
	// for updating course

	String url = "jdbc:mysql://localhost:3306/CMS";
	String dbUsername = "root";
	String dbPassword = "";
	
	public void updateCourseData(String course_name, int course_duration ,int courseId) {
	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE courses SET course_name = ?, course_duration = ? WHERE course_id = ?")) {

	    	preparedStatement.setString(1, course_name);
	        preparedStatement.setInt(2, course_duration);
	        preparedStatement.setInt(3, courseId);
	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Course Updated successfully!");
	            JOptionPane.showMessageDialog(null, "Course Updated Sucessfully");
	        } else {
	            System.out.println("Course not found with ID: " + courseId);
	            JOptionPane.showMessageDialog(null, "Failed to update Course !");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public CourseUpdate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Course");
		lblNewLabel.setFont(new Font("Dyuthi", Font.BOLD, 24));
		lblNewLabel.setBounds(134, 0, 164, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblCourseId = new JLabel("Course Id :");
		lblCourseId.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblCourseId.setBounds(31, 66, 93, 20);
		contentPane.add(lblCourseId);
		
		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblCourseName.setBounds(30, 98, 109, 24);
		contentPane.add(lblCourseName);
		
		JLabel lblCourseDuration = new JLabel("Course Duration:");
		lblCourseDuration.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblCourseDuration.setBounds(30, 134, 139, 20);
		contentPane.add(lblCourseDuration);
		
		courseId = new JTextField();
		courseId.setBounds(224, 61, 139, 29);
		contentPane.add(courseId);
		courseId.setColumns(10);
		
		courseName = new JTextField();
		courseName.setColumns(10);
		courseName.setBounds(224, 95, 139, 29);
		contentPane.add(courseName);
		
		courseDuration = new JTextField();
		courseDuration.setColumns(10);
		courseDuration.setBounds(224, 129, 139, 29);
		contentPane.add(courseDuration);
		
		JButton btnUpdateCourse = new JButton("Update Course");
		btnUpdateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ScourseId = courseId.getText();
				String courseN = courseName.getText();
				String ScourseD = courseDuration.getText();
				
				if(!ScourseId.isEmpty() && !courseN.isEmpty() && !ScourseD.isEmpty()) {
					int course_id = Integer.parseInt(ScourseId);
					int course_duration = Integer.parseInt(ScourseD);
					updateCourseData(courseN, course_duration , course_id);
//					JOptionPane.showMessageDialog(null, "Course Updated Sucessfully");
				}else {
					JOptionPane.showMessageDialog(null, "Inputes Cannot be empty !!");
				}
				
				
				}
			
		});
		btnUpdateCourse.setBackground(new Color(38, 162, 105));
		btnUpdateCourse.setFont(new Font("Dyuthi", Font.BOLD, 18));
		btnUpdateCourse.setBounds(50, 203, 151, 29);
		contentPane.add(btnUpdateCourse);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 18));
		btnReturn.setBackground(new Color(229, 165, 10));
		btnReturn.setBounds(235, 203, 151, 29);
		contentPane.add(btnReturn);
	}

}
