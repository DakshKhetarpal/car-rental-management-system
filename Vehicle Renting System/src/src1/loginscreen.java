package src1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginscreen
{   //database ke reference variables
	Connection conrefvar;
	Statement stmt;
	ResultSet virtbl;
	PreparedStatement pstmt;
	
	//frame(interface) ke reference variables
	JFrame mainwindow;
	JLabel username;
	JLabel passwd;
	JButton login_button;
	
	JTextField username_tf;
	JPasswordField passwd_tf;
	
	
	
	public void CreateInterface() 
	{
		mainwindow = new JFrame();
		mainwindow.setTitle("Login");
		
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.setBounds(500, 100, 400, 220); 
		
		mainwindow.setLayout(null);
		
		username=new JLabel("UserName");
		username.setBounds(20,20,100,30);
		
		username_tf=new JTextField();
		username_tf.setBounds(140, 20, 150, 25);
		
		passwd=new JLabel("Password");
		passwd.setBounds(20,70,100,30);
		
		passwd_tf=new JPasswordField();
		passwd_tf.setBounds(140,70,150,25);
		
		login_button=new JButton("Login");
		login_button.setBounds(150,120,100,30);
		
		
		login_button.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) 
					{
						String user=username_tf.getText().trim();   //get text will assign entered value to variable 'user'
						String pass=passwd_tf.getText().trim();
						
						
						String sql="select username,password from login_table where username='"+user+"'and password='"+pass+"'";
						try {
							virtbl=stmt.executeQuery(sql);  //creating a new virtual table based on our query
						} catch (SQLException e1) {         //and doing directly  because we don't need to do any changes in real table for now
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int count=0;
						try {
							while(virtbl.next())
							{
								count=count+1;	
							}
							if (count==1)
							{
								JOptionPane.showMessageDialog(null, "User Found");   //showmessagedialog accepts two arguments-null and sg to be printed
							}
							else if(count>=1)    //that means same user exist twice in db
							{
								JOptionPane.showMessageDialog(null,"Dupliacte user acess denied");
							}
							else  //that is count is 0
							{
								JOptionPane.showMessageDialog(null,"User not found");
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (count==1)
						{   //connecting next frame to this frame
							//mainwindow.setVisible(false);
							FirstScreen first_obj=new FirstScreen();
							first_obj.CreateInterface();
							mainwindow.setVisible(false);
						}
					}
				
				
				
				
	            }
				);
		
		
		mainwindow.add(username);
		mainwindow.add(username_tf);
		mainwindow.add(passwd);
		 mainwindow.add(passwd_tf);
		mainwindow.add(login_button);
		
	    mainwindow.setVisible(true);
		
	}
	
	
	public void DatabaseOps() {

		// Step 1 : Load the Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		 	e.printStackTrace();
		}

		try {

			// Step 2 : Connecting the front end with back end

			conrefvar = DriverManager.getConnection("jdbc:mysql://localhost/vehicle_renting_db", "root", "root");

			// Step 3 : Create a message
			String msg = "select * from login_table";

			stmt = conrefvar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			virtbl = stmt.executeQuery(msg);

			
			  while(virtbl.next()) {
			  System.out.println(virtbl.getString("username"));
			  System.out.println(virtbl.getString("password"));
			 //System.out.println(virtbl.getDouble("medprice"));
			 
			  }

			
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main(String[] args)
	{
		loginscreen loginobj=new loginscreen();
		loginobj.CreateInterface();
		loginobj.DatabaseOps();
	}

}
