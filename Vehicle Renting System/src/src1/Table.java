package src1;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class Table{
public static void main(String[] args){
   Connection con = null;
   Statement st = null;
   ResultSet rs = null;
   String s;





//connect your app to mysql database
   try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

   
   try{
       con = DriverManager.getConnection("jdbc:mysql://localhost/vehicle_renting_db","root","root");
       st = con.createStatement();
       s = "select * from vehicle_table";
       rs = st.executeQuery(s);
       ResultSetMetaData rsmt = rs.getMetaData();
       int c = rsmt.getColumnCount();
       Vector column = new Vector(c);
       for(int i = 1; i <= c; i++)
       {
           column.add(rsmt.getColumnName(i));
       }
       Vector data = new Vector();
       Vector row = new Vector();
       while(rs.next())
       {
           row = new Vector(c);
           for(int i = 1; i <= c; i++){
               row.add(rs.getString(i));
           }
           data.add(row);
       }
       JFrame frame = new JFrame();
       frame.setSize(500,120);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JTable table = new JTable(data,column);
        JScrollPane jsp = new JScrollPane(table);  //AGAR LINE 58 AUR 59 KO COMMENT ME DALO AUR PANEL.ADD ME TABLE PAS KRAO 
        panel.setLayout(new BorderLayout());        //IN PLACE OF JSP TO BORDERLAYOUT.EAST,WEST,SOUTH ..SB SAME AA RAHE H
        panel.add(jsp,BorderLayout.EAST);
        frame.setContentPane(panel);
        frame.setVisible(true);
               
               
   }catch(Exception e){
       JOptionPane.showMessageDialog(null, "ERROR");
   }finally{
       try{
       st.close();
       rs.close();
       con.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "ERROR CLOSE");
       }
   }
      
  }
}