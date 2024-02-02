package Swing.Results;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultShow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultShow frame = new ResultShow();
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
	public ResultShow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterStudentId = new JLabel("Enter Student ID");
		lblEnterStudentId.setFont(new Font("Dyuthi", Font.BOLD, 22));
		lblEnterStudentId.setBounds(123, 22, 165, 46);
		contentPane.add(lblEnterStudentId);
		
		studentId = new JTextField();
		studentId.setFont(new Font("Dialog", Font.PLAIN, 22));
		studentId.setBounds(146, 70, 114, 51);
		contentPane.add(studentId);
		studentId.setColumns(10);
		
		JButton btnShowReport = new JButton("Show Report");
		btnShowReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stdId = studentId.getText();
				if(!stdId.isEmpty()) {
					int studentId = Integer.parseInt(stdId);
					StudentReport reportStd = new StudentReport(studentId);
					reportStd.setVisible(true);
					dispose();
				}
			}
		});
		btnShowReport.setFont(new Font("Dyuthi", Font.BOLD, 20));
		btnShowReport.setBounds(55, 142, 148, 46);
		contentPane.add(btnShowReport);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 20));
		btnReturn.setBounds(237, 142, 148, 46);
		contentPane.add(btnReturn);
	}

}
