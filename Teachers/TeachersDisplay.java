// CoursesDisplay.java

package Swing.Teachers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.sql.*;

public class TeachersDisplay extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public TeachersDisplay() {
//        setLayout(new BorderLayout());
        setLayout(null);

        // Getting the data
        DefaultTableModel model = new DefaultTableModel();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS", "root", "");
            Statement stmt = con.createStatement();
            String query = "SELECT id, firstname, surname, email FROM users WHERE role = 'Teacher'";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane();
        // Set the bounds for the JScrollPane explicitly
        scrollPane.setBounds(0, 0, 465, 311);
        add(scrollPane);

        table = new JTable(model);
        scrollPane.setViewportView(table);
        table.setRowSelectionAllowed(false);

        TableColumnModel columnModel = table.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(3).setPreferredWidth(130);

        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);

        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setVisible(true);
        table.setIntercellSpacing(new java.awt.Dimension(7, 7));
    }
}