
package Swing;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Swing.Admin.AdminAdd;
import Swing.Admin.AdminDelete;
import Swing.Admin.AdminDisplay;
import Swing.Admin.AdminEdit;
import Swing.Course.CourseAdd;
import Swing.Course.CourseDlt;
import Swing.Course.CourseUpdate;
import Swing.Course.CoursesDisplay;
import Swing.Results.ResultAdd;
import Swing.Results.ResultDisplay;
import Swing.Results.ResultEdit;
import Swing.Results.ResultShow;
import Swing.Results.StudentReport;
import Swing.Students.StudentAdd;
import Swing.Students.StudentDelete;
import Swing.Students.StudentDisplay;
import Swing.Students.StudentEdit;
import Swing.Teachers.TeacherAdd;
import Swing.Teachers.TeacherDelete;
import Swing.Teachers.TeacherEdit;
import Swing.Teachers.TeachersDisplay;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private CoursesDisplay coursesDis;
	private StudentDisplay studentDis;
	private TeachersDisplay teacherDis;
	private AdminDisplay adminDis;
	private ResultDisplay resultDis;
	
	//for refreshing
	private JLabel label_1;
	private JLabel label_1_1;
	private JLabel label_1_2;
	private JLabel label_1_3;
	

	/**
	 * Launch the application.
	 */
	//getter setter(ENcapsulation
	
	private static String userEmail;
	
	//getting
	public String getUserEmail() {
		return userEmail;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard(userEmail);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField txtCources;
	

	
	/**
	 * Create the frame.
	 */
	String url = "jdbc:mysql://localhost:3306/CMS";
    String dbUsername = "root";
    String dbPassword = "";
    private JTextField fnameS;
    private JTextField surnameS;
    private JTextField emailS;
    private JPasswordField oldpasswordS;
    private JPasswordField newpasswordS;
	
	public Map<String, String> getUserData(String userEmail) {
        Map<String, String> userData = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {

            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Getting data from the result set
                    userData.put("firstname", resultSet.getString("firstname"));
                    userData.put("surname", resultSet.getString("surname"));
                    userData.put("email", resultSet.getString("email"));
                    userData.put("password", resultSet.getString("password"));
                    userData.put("role", resultSet.getString("role"));
                   
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }
	//total number of admin,teacher,student
	public int getTotalCount(String role) {
	    String url = "jdbc:mysql://localhost:3306/CMS";
	    String dbUsername = "root";
	    String dbPassword = "";

	    String query = "SELECT COUNT(*) FROM users WHERE role = ?";

	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, role);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception as needed
	    }

	    return 0; // Return 0 if something went wrong
	}
	
	//to get total courses
	public class DatabaseOperations {

	    // Your existing database connection parameters
	    private String url = "jdbc:mysql://localhost:3306/CMS";
	    private String dbUsername = "root";
	    private String dbPassword = "";

	    // Method to get the total number of courses
	    public int getTotalCourseCount() {
	        String query = "SELECT COUNT(*) FROM courses";

	        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                return resultSet.getInt(1);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	        }

	        return 0; // Return 0 if something went wrong
	    }
	}

	// for setting
	public int updateUserData(String oldEmail, String newFirstName, String newSurname, String newEmail, String newPassword) {
	    String url = "jdbc:mysql://localhost:3306/CMS";
	    String dbUsername = "root";
	    String dbPassword = "";

	    String query = "UPDATE users SET firstname = ?, surname = ?, email = ?, password = ? WHERE  email = ?";

	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, newFirstName);
	        preparedStatement.setString(2, newSurname);
	        preparedStatement.setString(3, newEmail);
	        preparedStatement.setString(4, newPassword);
	        preparedStatement.setString(5, oldEmail);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            // Display success message
	            JOptionPane.showMessageDialog(null,"Profile updated successfully. Please re-login !!");
	            UserLogin logIn = new UserLogin();
	            logIn.setVisible(true);
	            dispose();
	        }else {
	        	JOptionPane.showMessageDialog(null,"Failed to update profile !!");
	        }

	        return rowsAffected;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception as needed
	    }

	    return 0; // Return 0 if something went wrong
	}



	public Dashboard(String userEmail) {
		
		this.userEmail = userEmail;
		Map<String, String> userData = getUserData(userEmail);

		String firstname = userData.get("firstname");
		String surname = userData.get("surname");
		String email = userData.get("email");
		String role = userData.get("role");
		String passw = userData.get("password");
		
		
		DatabaseOperations databaseOperations = new DatabaseOperations();
        int totalCourses = databaseOperations.getTotalCourseCount();
        String totalCourse = String.valueOf(totalCourses);


        int totalStds = getTotalCount("Student");
        int totalA = getTotalCount("Admin");
        int totalT = getTotalCount("Teacher");

        String totalAdmins = String.valueOf(totalA);
        String totalTeachers = String.valueOf(totalT);
        String totalStudents = String.valueOf(totalStds);
        
		

		setBackground(new Color(94, 92, 100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 624);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(94, 92, 100));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(173, 0, 516, 582);
		contentPane.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Home", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblTab_2 = new JLabel("DashBoard");
		lblTab_2.setFont(new Font("Dyuthi", Font.BOLD, 30));
		lblTab_2.setBounds(12, 0, 163, 53);
		panel_2.add(lblTab_2);
		
		JLabel lblNewLabel = new JLabel(firstname);
		lblNewLabel.setForeground(new Color(46, 194, 126));
		lblNewLabel.setFont(new Font("Dyuthi", Font.BOLD, 24));
		lblNewLabel.setBounds(369, 11, 130, 33);
		panel_2.add(lblNewLabel);
		
		JLabel lblWelcome = new JLabel("Welcome,");
		lblWelcome.setBounds(297, 20, 70, 15);
		panel_2.add(lblWelcome);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(98, 160, 234));
		panel_7.setBounds(23, 79, 130, 113);
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Admins");
		lblAdmin.setBounds(29, 12, 75, 29);
		lblAdmin.setFont(new Font("Dyuthi", Font.BOLD, 22));
		panel_7.add(lblAdmin);
		
		JLabel label_1 = new JLabel(totalAdmins);
		label_1.setFont(new Font("Dialog", Font.BOLD, 44));
		label_1.setBounds(39, 41, 75, 60);
		panel_7.add(label_1);
		
		JPanel panel_7_1 = new JPanel();
		panel_7_1.setBackground(new Color(255, 163, 72));
		panel_7_1.setBounds(182, 79, 123, 113);
		panel_2.add(panel_7_1);
		panel_7_1.setLayout(null);
		
		JLabel lblTeacher = new JLabel("Teachers");
		lblTeacher.setBounds(24, 12, 87, 23);
		lblTeacher.setFont(new Font("Dyuthi", Font.BOLD, 22));
		panel_7_1.add(lblTeacher);
		
		JLabel label_1_1 = new JLabel(totalTeachers);
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 44));
		label_1_1.setBounds(48, 41, 75, 60);
		panel_7_1.add(label_1_1);
		
		JPanel panel_7_2 = new JPanel();
		panel_7_2.setBackground(new Color(192, 97, 203));
		panel_7_2.setBounds(333, 79, 123, 113);
		panel_2.add(panel_7_2);
		panel_7_2.setLayout(null);
		
		JLabel lblStudent = new JLabel("Students");
		lblStudent.setBounds(24, 12, 99, 23);
		lblStudent.setFont(new Font("Dyuthi", Font.BOLD, 22));
		panel_7_2.add(lblStudent);
		
		JLabel label_1_2 = new JLabel(totalStudents);
		label_1_2.setFont(new Font("Dialog", Font.BOLD, 44));
		label_1_2.setBounds(45, 41, 78, 60);
		panel_7_2.add(label_1_2);
		
		JPanel panel_7_3 = new JPanel();
		panel_7_3.setLayout(null);
		panel_7_3.setBackground(new Color(143, 240, 164));
		panel_7_3.setBounds(23, 227, 130, 113);
		panel_2.add(panel_7_3);
		
		JLabel lblAdmin_1 = new JLabel("Courses");
		lblAdmin_1.setFont(new Font("Dyuthi", Font.BOLD, 22));
		lblAdmin_1.setBounds(29, 12, 75, 29);
		panel_7_3.add(lblAdmin_1);
		
		JLabel label_1_3 = new JLabel(totalCourse);
		label_1_3.setFont(new Font("Dialog", Font.BOLD, 44));
		label_1_3.setBounds(43, 41, 75, 60);
		panel_7_3.add(label_1_3);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Teacher", null, panel_3, null);
		panel_3.setLayout(null);
		
		
		JLabel lblTab_2_1 = new JLabel("Teacher");
		lblTab_2_1.setBounds(12, 12, 141, 40);
		lblTab_2_1.setFont(new Font("Dyuthi", Font.BOLD, 30));
		panel_3.add(lblTab_2_1);
		
		JButton btnRefresh_1_1_2 = new JButton("Refresh");
		btnRefresh_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacherDis = new TeachersDisplay();
				teacherDis.setBounds(12, 176, 468, 331);
				teacherDis.setVisible(true);
				panel_3.add(teacherDis);
				teacherDis.setLayout(null);
			}
		});
		btnRefresh_1_1_2.setBackground(new Color(51, 209, 122));
		btnRefresh_1_1_2.setBounds(371, 21, 117, 25);
		panel_3.add(btnRefresh_1_1_2);
		
		JLabel lblSearch_1_1 = new JLabel("Search : ");
		lblSearch_1_1.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblSearch_1_1.setBounds(57, 64, 73, 20);
		panel_3.add(lblSearch_1_1);
		
		JButton btnAddTeacher = new JButton("Add Teacher");
		btnAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherAdd addTeacher = new TeacherAdd();
				addTeacher.setVisible(true);
			}
		});
		btnAddTeacher.setBackground(new Color(53, 132, 228));
		btnAddTeacher.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnAddTeacher.setBounds(33, 114, 117, 33);
		panel_3.add(btnAddTeacher);
		
		JButton btnEditTeacher = new JButton("Edit Teacher");
		btnEditTeacher.setBackground(new Color(229, 165, 10));
		btnEditTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherEdit editTeacher = new TeacherEdit();
				editTeacher.setVisible(true);
			}
		});
		btnEditTeacher.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnEditTeacher.setBounds(181, 114, 117, 33);
		panel_3.add(btnEditTeacher);
		
		JButton btnDelTeacher = new JButton("Del Teacher");
		btnDelTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherDelete deleteTeacher = new TeacherDelete();
				deleteTeacher.setVisible(true);
			}
		});
		btnDelTeacher.setBackground(new Color(165, 29, 45));
		btnDelTeacher.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnDelTeacher.setBounds(319, 114, 109, 33);
		panel_3.add(btnDelTeacher);
		
		teacherDis = new TeachersDisplay();
		teacherDis.setBounds(12, 176, 468, 331);
		teacherDis.setVisible(true);
		panel_3.add(teacherDis);
		teacherDis.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Student", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblTab_2_2 = new JLabel("Student");
		lblTab_2_2.setBounds(12, 12, 137, 40);
		lblTab_2_2.setFont(new Font("Dyuthi", Font.BOLD, 30));
		panel_4.add(lblTab_2_2);
		
		JButton btnRefresh_1_1 = new JButton("Refresh");
		btnRefresh_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentDis = new StudentDisplay();
				studentDis.setBounds(12, 176, 468, 331);
				studentDis.setVisible(true);
				panel_4.add(studentDis);
				studentDis.setLayout(null);
			}
		});
		btnRefresh_1_1.setBounds(356, 21, 117, 25);
		btnRefresh_1_1.setBackground(new Color(51, 209, 122));
		panel_4.add(btnRefresh_1_1);
		
		JLabel lblSearch_1 = new JLabel("Search : ");
		lblSearch_1.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblSearch_1.setBounds(51, 88, 73, 20);
		panel_4.add(lblSearch_1);
		
//		textField = new JTextField();
//		textField.setFont(new Font("Dyuthi", Font.PLAIN, 12));
//		textField.setColumns(10);
//		textField.setBounds(131, 77, 316, 30);
//		panel_4.add(textField);
		
	

	
		JButton btnAddStudent = new JButton("Add Std");
		btnAddStudent.setBackground(new Color(53, 132, 228));
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentAdd addStd = new StudentAdd();
				addStd.setVisible(true);	
			}
		});
		btnAddStudent.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnAddStudent.setBounds(55, 120, 94, 32);
		panel_4.add(btnAddStudent);
		
		JButton btnEditStd = new JButton("Edit Std");
		btnEditStd.setBackground(new Color(229, 165, 10));
		btnEditStd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentEdit editStd = new StudentEdit();
				editStd.setVisible(true);
			}
		});
		btnEditStd.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnEditStd.setBounds(178, 120, 109, 32);
		panel_4.add(btnEditStd);
		
		JButton btnDltStd = new JButton("Dlt Std");
		btnDltStd.setBackground(new Color(165, 29, 45));
		btnDltStd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentDelete dltStudent = new StudentDelete();
				dltStudent.setVisible(true);
			}
		});
		btnDltStd.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnDltStd.setBounds(319, 120, 99, 32);
		panel_4.add(btnDltStd);
		
		
		
		studentDis = new StudentDisplay();
		studentDis.setBounds(12, 176, 468, 331);
		studentDis.setVisible(true);
		panel_4.add(studentDis);
		studentDis.setLayout(null);
		
		
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Admin", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblTab_2_2_1 = new JLabel("Admin");
		lblTab_2_2_1.setBounds(24, 12, 102, 30);
		lblTab_2_2_1.setFont(new Font("Dyuthi", Font.BOLD, 30));
		panel_5.add(lblTab_2_2_1);
		
		JButton btnRefresh_1_1_1 = new JButton("Refresh");
		btnRefresh_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminDis = new AdminDisplay();
				adminDis.setBounds(12, 176, 468, 331);
				adminDis.setVisible(true);
				panel_5.add(adminDis);
				adminDis.setLayout(null);
			}
		});
		btnRefresh_1_1_1.setBackground(new Color(51, 209, 122));
		btnRefresh_1_1_1.setBounds(353, 16, 117, 25);
		panel_5.add(btnRefresh_1_1_1);
		
		JButton btnAddStudent_1 = new JButton("Add Admin");
		btnAddStudent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAdd addAdmin = new AdminAdd();
				addAdmin.setVisible(true);
			}
		});
		btnAddStudent_1.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnAddStudent_1.setBackground(new Color(53, 132, 228));
		btnAddStudent_1.setBounds(48, 122, 94, 32);
		panel_5.add(btnAddStudent_1);
		
		JButton btnEditStd_1 = new JButton("Edit Admin");
		btnEditStd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminEdit editAdmin = new AdminEdit();
				editAdmin.setVisible(true);
			}
		});
		btnEditStd_1.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnEditStd_1.setBackground(new Color(229, 165, 10));
		btnEditStd_1.setBounds(165, 122, 109, 32);
		panel_5.add(btnEditStd_1);
		
		JButton btnDltStd_1 = new JButton("Dlt Admin");
		btnDltStd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDelete deleteAdmin = new AdminDelete();
				deleteAdmin.setVisible(true);
			}
		});
		btnDltStd_1.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnDltStd_1.setBackground(new Color(165, 29, 45));
		btnDltStd_1.setBounds(302, 122, 99, 32);
		panel_5.add(btnDltStd_1);
		
		adminDis = new AdminDisplay();
		adminDis.setBounds(12, 176, 468, 331);
		adminDis.setVisible(true);
		panel_5.add(adminDis);
		adminDis.setLayout(null);
		
		
		
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Course", null, panel_6, null);
		panel_6.setLayout(null);
		
		
		JLabel lblTab_2_2_1_1 = new JLabel("Courses");
		lblTab_2_2_1_1.setBounds(173, 12, 102, 30);
		lblTab_2_2_1_1.setFont(new Font("Dyuthi", Font.BOLD, 30));
		panel_6.add(lblTab_2_2_1_1);
		
		JLabel label = new JLabel("");
		label.setBounds(122, 20, 0, 0);
		panel_6.add(label);																							
		
		JLabel lblSearch = new JLabel("Search : ");
		lblSearch.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblSearch.setBounds(39, 71, 73, 20);
		panel_6.add(lblSearch);
		
		txtCources = new JTextField();
		txtCources.setFont(new Font("Dyuthi", Font.PLAIN, 12));
		txtCources.setBounds(120, 68, 316, 30);
		panel_6.add(txtCources);
		txtCources.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Course");
		btnNewButton.setBackground(new Color(53, 132, 228));
		btnNewButton.setFont(new Font("Dyuthi", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseAdd addCourse = new CourseAdd();
				addCourse.setVisible(true);
			}
		});
		btnNewButton.setBounds(49, 110, 109, 41);
		panel_6.add(btnNewButton);
		
		JButton btnEditCourse = new JButton("Edit Course");
		btnEditCourse.setBackground(new Color(229, 165, 10));
		btnEditCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseUpdate updateC = new CourseUpdate();
				updateC.setVisible(true);
			}
		});
		btnEditCourse.setFont(new Font("Dyuthi", Font.BOLD, 14));
		btnEditCourse.setBounds(197, 110, 102, 41);
		panel_6.add(btnEditCourse);
		
		JButton btnDltCourse = new JButton("Dlt Course");
		btnDltCourse.setBackground(new Color(165, 29, 45));
		btnDltCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseDlt deleteC = new CourseDlt();
				deleteC.setVisible(true);			
				
			}
		});
		
		
		
		btnDltCourse.setFont(new Font("Dyuthi", Font.BOLD, 14));
		btnDltCourse.setBounds(348, 110, 102, 41);
		panel_6.add(btnDltCourse);
		
		coursesDis = new CoursesDisplay();
		coursesDis.setBounds(12, 176, 468, 331);
		coursesDis.setVisible(true);
		panel_6.add(coursesDis);
		coursesDis.setLayout(null);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coursesDis = new CoursesDisplay();
				coursesDis.setBounds(12, 176, 468, 331);
				coursesDis.setVisible(true);
				panel_6.add(coursesDis);
				coursesDis.setLayout(null);
			}
		});
		btnRefresh_1.setBackground(new Color(51, 209, 122));
		btnRefresh_1.setBounds(382, 16, 117, 25);
		panel_6.add(btnRefresh_1);
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Std ", null, panel_8, null);
		panel_8.setLayout(null);
		
		JLabel lblStudentProgress = new JLabel("Student Progress");
		lblStudentProgress.setFont(new Font("Dyuthi", Font.BOLD, 26));
		lblStudentProgress.setBounds(27, 12, 217, 61);
		panel_8.add(lblStudentProgress);
		
		JButton btnAddMark = new JButton("Add Mark");
		btnAddMark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultAdd addResult = new ResultAdd();
				addResult.setVisible(true);
			}
		});
		btnAddMark.setBackground(new Color(53, 132, 228));
		btnAddMark.setFont(new Font("Dyuthi", Font.BOLD, 18));
		btnAddMark.setBounds(72, 114, 124, 39);
		panel_8.add(btnAddMark);
		
		JButton btnStudentReport = new JButton("Edit Mark");
		btnStudentReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultEdit editResult = new ResultEdit();
				editResult.setVisible(true);
			}
		});
		btnStudentReport.setBackground(new Color(229, 165, 10));
		btnStudentReport.setFont(new Font("Dyuthi", Font.BOLD, 18));
		btnStudentReport.setBounds(288, 114, 136, 39);
		panel_8.add(btnStudentReport);
		
		
		resultDis = new ResultDisplay();
		resultDis.setBounds(12, 176, 468, 331);
		resultDis.setVisible(true);
		panel_8.add(resultDis);
		resultDis.setLayout(null);
		
		JButton btnRefresh_2 = new JButton("Refresh");
		btnRefresh_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultDis = new ResultDisplay();
				resultDis.setBounds(12, 176, 468, 331);
				resultDis.setVisible(true);
				panel_8.add(resultDis);
				resultDis.setLayout(null);
			}
		});
		btnRefresh_2.setBackground(new Color(38, 162, 105));
		btnRefresh_2.setBounds(363, 31, 117, 25);
		panel_8.add(btnRefresh_2);
		
		JPanel panel_9 = new JPanel();
		tabbedPane.addTab("Setting", null, panel_9, null);
		panel_9.setLayout(null);
		
		JLabel label_2 = new JLabel("----------------------------------------------------------------------------------------------");
		label_2.setBounds(12, 41, 487, 15);
		panel_9.add(label_2);
		
		JLabel label_2_1 = new JLabel("----------------------------------------------------------------------------------------------");
		label_2_1.setBounds(12, 421, 487, 15);
		panel_9.add(label_2_1);
		
		JLabel lblSetting = new JLabel("Setting");
		lblSetting.setBounds(12, 12, 184, 37);
		lblSetting.setFont(new Font("Dyuthi", Font.BOLD, 28));
		panel_9.add(lblSetting);
		
		JLabel lblHelpServices = new JLabel("Help & Services :");
		lblHelpServices.setBounds(12, 443, 125, 15);
		lblHelpServices.setFont(new Font("Dyuthi", Font.BOLD, 16));
		panel_9.add(lblHelpServices);
		
		JLabel lblReportTechnicalIssue = new JLabel("Report technical issue ?");
		lblReportTechnicalIssue.setBounds(12, 470, 172, 15);
		lblReportTechnicalIssue.setForeground(new Color(165, 29, 45));
		lblReportTechnicalIssue.setFont(new Font("Dyuthi", Font.BOLD, 14));
		panel_9.add(lblReportTechnicalIssue);
		
		JLabel lblUserManual = new JLabel("User manual ?");
		lblUserManual.setBounds(12, 484, 172, 15);
		lblUserManual.setForeground(new Color(38, 162, 105));
		lblUserManual.setFont(new Font("Dyuthi", Font.BOLD, 14));
		panel_9.add(lblUserManual);
		
		JLabel lblReportUser = new JLabel("Report user ?");
		lblReportUser.setBounds(12, 497, 172, 15);
		lblReportUser.setForeground(new Color(198, 70, 0));
		lblReportUser.setFont(new Font("Dyuthi", Font.BOLD, 14));
		panel_9.add(lblReportUser);
		
		JLabel lblTermsConditions = new JLabel("Terms & Conditions");
		lblTermsConditions.setBounds(290, 448, 153, 15);
		lblTermsConditions.setForeground(new Color(94, 92, 100));
		lblTermsConditions.setFont(new Font("Dyuthi", Font.BOLD, 16));
		panel_9.add(lblTermsConditions);
		
		JLabel lblContact = new JLabel("Contact :");
		lblContact.setBounds(290, 469, 153, 15);
		lblContact.setFont(new Font("Dyuthi", Font.BOLD, 16));
		panel_9.add(lblContact);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(336, 482, 70, 15);
		panel_9.add(label_3);
		
		JLabel lblHeraldcollegekathmanducom = new JLabel("heraldcollege@kathmandu.com");
		lblHeraldcollegekathmanducom.setBounds(290, 484, 187, 15);
		lblHeraldcollegekathmanducom.setForeground(new Color(38, 162, 105));
		lblHeraldcollegekathmanducom.setFont(new Font("Dyuthi", Font.BOLD, 14));
		panel_9.add(lblHeraldcollegekathmanducom);
		
		JLabel lblTel = new JLabel("Tel :    01-123908");
		lblTel.setBounds(290, 497, 172, 15);
		lblTel.setForeground(new Color(38, 162, 105));
		lblTel.setFont(new Font("Dyuthi", Font.BOLD, 14));
		panel_9.add(lblTel);
		
		JLabel lblGeneral = new JLabel("General ");
		lblGeneral.setBounds(12, 61, 82, 27);
		lblGeneral.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblGeneral.setForeground(new Color(94, 92, 100));
		panel_9.add(lblGeneral);
		
		JLabel lblFirstname = new JLabel("Firstname  :");
		lblFirstname.setBounds(12, 97, 106, 21);
		lblFirstname.setFont(new Font("Dyuthi", Font.BOLD, 20));
		panel_9.add(lblFirstname);
		
		JLabel lblSurname = new JLabel("Surname  :");
		lblSurname.setBounds(12, 130, 106, 21);
		lblSurname.setFont(new Font("Dyuthi", Font.BOLD, 20));
		panel_9.add(lblSurname);
		
		JLabel lblEmail = new JLabel("Email  :");
		lblEmail.setBounds(12, 163, 106, 21);
		lblEmail.setFont(new Font("Dyuthi", Font.BOLD, 20));
		panel_9.add(lblEmail);
		
		fnameS = new JTextField();
		fnameS.setBounds(136, 97, 257, 27);
		panel_9.add(fnameS);
		fnameS.setColumns(10);
		
		surnameS = new JTextField();
		surnameS.setBounds(136, 130, 257, 27);
		surnameS.setColumns(10);
		panel_9.add(surnameS);
		
		emailS = new JTextField();
		emailS.setBounds(136, 163, 257, 27);
		emailS.setColumns(10);
		panel_9.add(emailS);
		
		JLabel lblSecurity = new JLabel("Security");
		lblSecurity.setBounds(12, 215, 82, 27);
		lblSecurity.setForeground(new Color(94, 92, 100));
		lblSecurity.setFont(new Font("Dyuthi", Font.BOLD, 18));
		panel_9.add(lblSecurity);
		
		JLabel lblOldPassword = new JLabel("Old Password  :");
		lblOldPassword.setBounds(12, 254, 153, 21);
		lblOldPassword.setFont(new Font("Dyuthi", Font.BOLD, 20));
		panel_9.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password  :");
		lblNewPassword.setBounds(12, 323, 153, 21);
		lblNewPassword.setFont(new Font("Dyuthi", Font.BOLD, 20));
		panel_9.add(lblNewPassword);
		
		oldpasswordS = new JPasswordField();
		oldpasswordS.setBounds(12, 274, 244, 27);
		panel_9.add(oldpasswordS);
		
		newpasswordS = new JPasswordField();
		newpasswordS.setBounds(12, 344, 244, 27);
		panel_9.add(newpasswordS);
		
		JButton btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setBounds(194, 384, 136, 27);
		btnUpdateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = fnameS.getText();
				String surname = surnameS.getText();
				String eMail = emailS.getText();
				String oldpass = oldpasswordS.getText();
				String newpass = newpasswordS.getText();
				if(!fname.isEmpty() && !surname.isEmpty() && !eMail.isEmpty() && !oldpass.isEmpty() && !newpass.isEmpty()) {
					if(oldpass.equals(passw)) {
							updateUserData(email, fname, surname, eMail, newpass);
						
					}else {
						JOptionPane.showMessageDialog(null,"Old password is incorrect !!");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Inputs cannot be empty!!");
				}
			}
		});
		btnUpdateProfile.setBackground(new Color(38, 162, 105));
		btnUpdateProfile.setFont(new Font("Dyuthi", Font.BOLD, 16));
		panel_9.add(btnUpdateProfile);
		
		JLabel lblCopyright = new JLabel("Copyright © 2024 CMS. All rights reserved.");
		lblCopyright.setBounds(95, 528, 235, 15);
		lblCopyright.setForeground(new Color(119, 118, 123));
		lblCopyright.setFont(new Font("Dyuthi", Font.BOLD, 12));
		panel_9.add(lblCopyright);
		
		JLabel lblFirstname_1 = new JLabel(firstname + " " +surname);
		lblFirstname_1.setBounds(336, 12, 162, 15);
		lblFirstname_1.setForeground(new Color(26, 95, 180));
		lblFirstname_1.setFont(new Font("FreeMono", Font.BOLD, 16));
		panel_9.add(lblFirstname_1);
		
		JLabel lblFirstname_1_1 = new JLabel(email);
		lblFirstname_1_1.setBounds(341, 25, 136, 15);
		lblFirstname_1_1.setForeground(new Color(38, 162, 105));
		lblFirstname_1_1.setFont(new Font("FreeMono", Font.BOLD, 12));
		panel_9.add(lblFirstname_1_1);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 153, 150));
		panel.setBounds(0, 0, 161, 582);
		contentPane.add(panel);
		
		JButton btnTeacher = new JButton("Teacher");
		btnTeacher.setBounds(35, 149, 96, 25);
		btnTeacher.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		panel.setLayout(null);
		panel.add(btnTeacher);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.setBounds(35, 181, 96, 25);
		btnStudent.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		panel.add(btnStudent);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(35, 218, 96, 25);
		btnAdmin.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		panel.add(btnAdmin);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(35, 112, 96, 25);
		btnHome.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		panel.add(btnHome);
		
		JLabel lblCms = new JLabel("Course Management");
		lblCms.setBounds(14, 12, 147, 25);
		lblCms.setForeground(new Color(99, 69, 44));
		lblCms.setBackground(new Color(249, 240, 107));
		lblCms.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCms.setFont(new Font("Dyuthi", Font.BOLD, 16));
		panel.add(lblCms);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(14, 512, 135, 25);
		btnLogOut.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin lgn = new UserLogin();
				lgn.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBackground(new Color(165, 29, 45));
		btnLogOut.setForeground(new Color(36, 31, 49));
		panel.add(btnLogOut);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(14, 437, 135, 25);
		btnRefresh.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int totalStds = getTotalCount("Student");
		        int totalA = getTotalCount("Admin");
		        int totalT = getTotalCount("Teacher");
				String totalAdmins = String.valueOf(totalA);
		        String totalTeachers = String.valueOf(totalT);
		        String totalStudents = String.valueOf(totalStds);
		        
		     // Update the JLabels with the new counts
		        label_1.setText(totalAdmins);
		        label_1_1.setText(totalTeachers);
		        label_1_2.setText(totalStudents);
		        label_1_3.setText(totalCourse);

		        // Repaint the frame
		        revalidate();
		        repaint();
		        
			}
		});
		btnRefresh.setBackground(new Color(245, 194, 17));
		panel.add(btnRefresh);
		
		JButton btnCourse = new JButton("Course");
		btnCourse.setBounds(35, 255, 96, 25);
		btnCourse.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		panel.add(btnCourse);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(14, 75, 46, 25);
		lblUser.setForeground(new Color(0, 0, 0));
		lblUser.setFont(new Font("Dyuthi", Font.BOLD, 18));
		panel.add(lblUser);
		
		JLabel lblNewLabel_1 = new JLabel(role);
		lblNewLabel_1.setBounds(61, 75, 88, 25);
		lblNewLabel_1.setForeground(new Color(26, 95, 180));
		lblNewLabel_1.setFont(new Font("FreeMono", Font.BOLD, 20));
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Std Progress");
		btnNewButton_1.setBounds(14, 334, 117, 25);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
			}
		});
		btnNewButton_1.setFont(new Font("Dyuthi", Font.BOLD, 12));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Std Report");
		btnNewButton_1_1.setBounds(14, 371, 117, 25);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultShow showResult = new ResultShow();
				showResult.setVisible(true);
			}
		});
		btnNewButton_1_1.setFont(new Font("Dyuthi", Font.BOLD, 12));
		panel.add(btnNewButton_1_1);
		
		JButton btnSetting = new JButton("Setting");
		btnSetting.setBounds(14, 474, 135, 25);
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(6);
			}
		});
		btnSetting.setFont(new Font("Dyuthi", Font.BOLD, 16));
		btnSetting.setBackground(new Color(119, 118, 123));
		panel.add(btnSetting);
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setBounds(49, 35, 70, 15);
		lblSystem.setForeground(new Color(99, 69, 44));
		lblSystem.setFont(new Font("Dyuthi", Font.BOLD, 16));
		panel.add(lblSystem);
		
		JLabel lblfebam = new JLabel("3Feb03:10:30am2024");
		lblfebam.setFont(new Font("FreeMono", Font.BOLD, 10));
		lblfebam.setBounds(14, 0, 147, 15);
		panel.add(lblfebam);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(119, 118, 123));
		panel_1.setBounds(701, 0, 163, 582);
		contentPane.add(panel_1);
		
		
		
		if ("Admin".equals(role)) {
		    // students
		    btnAddStudent.setVisible(true);
		    btnEditStd.setVisible(true);
		    btnDltStd.setVisible(true);
		    //teacher
		    btnAddTeacher.setVisible(true);
		    btnEditTeacher.setVisible(true);
		    btnDelTeacher.setVisible(true);
		    //admin
		    btnAddStudent_1.setVisible(true);
		    btnEditStd_1.setVisible(true);
		    btnDltStd_1.setVisible(true);
		    //course
		    btnNewButton.setVisible(true);
		    btnEditCourse.setVisible(true);
		    btnDltCourse.setVisible(true);
		} else {
		    
		    btnAddStudent.setVisible(false);
		    btnEditStd.setVisible(false);
		    btnDltStd.setVisible(false);
		    
		    btnAddTeacher.setVisible(false);
		    btnEditTeacher.setVisible(false);
		    btnDelTeacher.setVisible(false);
		    
		    btnAddStudent_1.setVisible(false);
		    btnEditStd_1.setVisible(false);
		    btnDltStd_1.setVisible(false);
		    
		    btnNewButton.setVisible(false);
		    btnEditCourse.setVisible(false);
		    btnDltCourse.setVisible(false);
		}
		
		if("Teacher".equals(role)) {
			btnNewButton_1.setVisible(true);
		}else {
			btnNewButton_1.setVisible(false);
		}
		
		
	}
}
