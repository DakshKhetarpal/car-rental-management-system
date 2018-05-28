package src1;

import java.awt.Color;
import java.awt.Container;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FirstScreen  {
	 JButton imagebutton1;
	 JLabel image1label;
	 JButton imagebutton2;
	 JLabel image2label;
	 JButton imagebutton3,returncar;
	 JLabel image3label;
	 JMenuBar menubar;
	 JMenu miscellaneous;
	 JMenuItem changepasswd;
	 JMenuItem logout;
	 
	 
		Connection conrefvar,conrefvar2;
		Statement stmt;
		ResultSet virtbl4,virtbl2,virtbl;
		PreparedStatement pstmt,pstmt2,pstmt3,pstmt4;
	 

	 		 
	 
	public void CreateInterface()
	{
			 
		JFrame mainwindow2=new JFrame("CAR RENTAL MANAGEMENT SYSTEM");
		mainwindow2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow2.setBounds(140, 60, 1000, 650); 
		mainwindow2.setResizable(false);
		
		Container cntr=mainwindow2.getContentPane();
		cntr.setLayout(null);
		//mainwindow2.setLayout(null);
		
		//Placing company image at the back of Frame
		 ImageIcon imageref = new ImageIcon("C:\\Users\\daksh\\Pictures\\beautiful-roads-at-night-roads-at-night-38467694-1920-1200.jpg");
		 JLabel imagehost = new JLabel(imageref);
		 JPanel jp=new JPanel();
		// imagehost.setBounds(0, 0, 800, 50); this seBounds has no effect
		 jp.add(imagehost);
		 jp.setBounds(0,0,1000,650);
		 cntr.add(jp);

		
		//using 2nd line from here we add icons(that is image on buttons)
		imagebutton1=new JButton();
		imagebutton1.setIcon(new ImageIcon("C:\\Users\\daksh\\Downloads\\rsz_specific-employee-icon-clipart-1.jpg"));
		imagebutton1.setBounds(150,220,150,150);
		
		
		imagebutton1.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
					
						one obj=new one();
						
						obj.CreateInterface();
						
					}
			
				
				
				
				
				
				
	});
		
		image1label=new JLabel("Employee");
		image1label.setForeground(Color.WHITE);    //SETTING COLOR OF LABEL
		image1label.setBounds(195,380,100,50);
		
		imagebutton2=new JButton();
		imagebutton2.setIcon(new ImageIcon("C:\\Users\\daksh\\Downloads\\rsz_1male-customer-clipart-1.jpg"));
		imagebutton2.setBounds(400,220,150,150);
		
		
		
		imagebutton2.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 new tt().buildGUI();
				
			}
	
		
		
		
		
		
		
});
		
		image2label=new JLabel("Customer");
		image2label.setBounds(445,380,100,50);  
		image2label.setForeground(Color.WHITE);
		
		imagebutton3=new JButton();
		imagebutton3.setIcon(new ImageIcon("C:\\Users\\daksh\\Downloads\\rsz_2vehicle.jpg"));
		imagebutton3.setBounds(650,220,150,150);
		
		image3label=new JLabel("Vehicle");
		image3label.setBounds(700,380,100,50);
		image3label.setForeground(Color.WHITE);
		
		
		imagebutton3.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VehicleEntryPage obj=new VehicleEntryPage();
				obj.CreateInterface();
				obj.DatabaseOps();
				
			}
	
		
		
		
		
		
		
});
		
		returncar=new JButton("RETURN CAR");
		returncar.setBounds(700,40,180,50);
		
		
		returncar.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFrame frame=new JFrame();
						frame.setBounds(400,90,400,200);
						frame.setLayout(null);
						JLabel l=new JLabel("Customer Id");
						l.setBounds(20,20,80,30);
						TextField tf=new TextField();
						tf.setBounds(160,20,150,25);
						JButton b=new JButton("Return");
						b.addActionListener(new ActionListener()
								{

									@Override
									public void actionPerformed(ActionEvent arg0) {
							if (tf.getText().trim().isEmpty()) 
						{    JOptionPane.showMessageDialog(null, "Customer Id is compulsary");}
							else{
								
							String s=tf.getText().trim();
							System.out.println(s);
							
							try {
								Class.forName("com.mysql.jdbc.Driver");
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							try {

								// Step 2 : Connecting the front end with back end

								conrefvar2 = DriverManager.getConnection("jdbc:mysql://localhost/vehicle_renting_db", "root", "root");
										
								String msg = "select * from customer_table where cust_id = ?";
								
								pstmt2 = conrefvar2.prepareStatement(msg);

								pstmt2.setString(1, s);      //here 1 is no. of parameters that is only one -number

								virtbl2 = pstmt2.executeQuery();

								if (virtbl2.next()) {
								//below 2 lines r used to insert into previousrecord_table and delete from customer table	
									PreparedStatement msg2=conrefvar2.prepareStatement("INSERT INTO previousrecord_table select * from customer_table where cust_id =?");
									msg2.setString(1, s); //will set the value of s in pace of question mark
									msg2.executeUpdate(); //will execute msg2 statement
									
								
									PreparedStatement msg3=conrefvar2.prepareStatement("DELETE FROM customer_table where cust_id = ?");
									msg3.setString(1, s);
									msg3.executeUpdate();
									
									//using virtbl2 to set status of car and driver to available again
									String number=virtbl2.getString(15);
									
									PreparedStatement msg4=conrefvar2.prepareStatement("update vehicle_table set status='unrented' where number=? ");
									msg4.setString(1, number);
									msg4.executeUpdate();
									
									String id=virtbl2.getString(9);
									PreparedStatement msg5=conrefvar2.prepareStatement("update driver_table set status='available' where driver_id=? ");
									msg5.setString(1, number);
									msg5.executeUpdate();
									
									
									
									
									
									JOptionPane.showMessageDialog(null, "Car returned successfully");
									
									
								} else {
									JOptionPane.showMessageDialog(null, s + " is not in database");
								}
								
							}
							catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}         
							}	
									
									}
								});
						b.setBounds(20,80,100,30);
						frame.add(b);
						frame.add(tf);
						frame.add(l);
						frame.setVisible(true);
								
		
						
					}	
		
		
		
	});
		
		menubar = new JMenuBar();
		miscellaneous = new JMenu("Miscellaneous");
		changepasswd = new JMenuItem("Change Password");
		logout = new JMenuItem("Logout");
		
		//menu items added here
		
		mainwindow2.setJMenuBar(menubar);         //casting of container object-mainwindow2 as setJMenuBar is only defined for JFrames objects
		menubar.add(miscellaneous);
		miscellaneous.add(changepasswd);
		miscellaneous.add(logout);
		
		changepasswd.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFrame mainwindow6=new JFrame();
						
						mainwindow6.setTitle("Change Password");
						mainwindow6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //allows to close only frame of change password to close without closing the  older frame
						mainwindow6.setLayout(null);
						mainwindow6.setBounds(150,150,400,240);
						JLabel currentpasswd=new JLabel("Current Password");
						currentpasswd.setBounds(20,20,130,30);
						JTextField currentpasswdtf=new JTextField();
						currentpasswdtf.setBounds(170,20,120,25);
						
						JLabel newpasswd=new JLabel("New Password");
						newpasswd.setBounds(20, 70, 130, 30);
						JTextField newpasswdtf=new JTextField();
						newpasswdtf.setBounds(170,70,120,25);
						
						JLabel newpasswd2=new JLabel("New Password");
						newpasswd2.setBounds(20, 120, 130, 30);
						JTextField newpasswdtf2=new JTextField();
						newpasswdtf2.setBounds(170,120,120,25);
						
						JButton changepasswd=new JButton("CHANGE PASSWORD");
						changepasswd.setBounds(75,125,160,30);
						
		
						mainwindow6.add(currentpasswd);
						mainwindow6.add(currentpasswdtf);
						mainwindow6.add(newpasswd);
						mainwindow6.add(newpasswdtf);
						
						mainwindow6.add(changepasswd);
						mainwindow6.setVisible(true);
						
						DatabaseOps();
						changepasswd.addActionListener(new ActionListener()
								{

									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										

								try{
											virtbl4.last();
											String currentpassword=currentpasswdtf.getText().trim();
											String password_in_db=virtbl4.getString("password");
											String username_in_db=virtbl4.getString("username");
											String newpassword=newpasswdtf.getText().trim();
											if(currentpassword.equals(password_in_db))
											{
												
												
												
										//	virtbl4.updateString(1,username_in_db);
												virtbl4.updateString(2,newpassword);
												virtbl4.updateRow();
												JOptionPane.showMessageDialog(mainwindow6, "Password Changed");
											}
											else
												JOptionPane.showMessageDialog(null,"Current Password is not correct");
								}
								catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
										
										
									}
									
							     
							
								}	
								);
								}
			
				}
				);
		
		logout.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
				
					
					//return;	
						JFrame log=new JFrame();
						
						log.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //allows to close only frame of change password to close without closing the  older frame
						log.setLayout(null);
						log.setBounds(150,150,300,180);
						JLabel sure=new JLabel("Are you sure u want to close the application");
						sure.setBounds(20,10,340,100);
						log.add(sure);
						JButton yes=new JButton("YES");
						yes.setBounds(20, 80, 60, 30);
						log.add(yes);
						yes.addActionListener(new ActionListener()
								{

									@Override
									public void actionPerformed(ActionEvent arg0) {
										
										System.exit(1);
									}				     
					
					
					
				   	});
						
						
						JButton no=new JButton("NO");
						no.setBounds(200, 80, 60, 30);
						log.add(no);
						no.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0) {
								
								//log.setVisible(false);
								log.dispose();
							}				     
			
			
			
		   	});

						
						
						log.setVisible(true);
						
					
					
	
						
						
						
				
					
					
					
					
					
					
					
					}			
				
				
				
				
				
	});
		
		
		
		//buttons and labels added here
		cntr.add(imagebutton1);
		cntr.add(image1label);
		cntr.add(imagebutton2);
		cntr.add(image2label);
		cntr.add(imagebutton3);
		cntr.add(image3label);
		cntr.add(returncar);
		cntr.add(jp);
		mainwindow2.add(jp);
		
		
		mainwindow2.setVisible(true);
	    
		
	 	
		
		
		
		
		
		
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

			stmt = conrefvar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//kya ho raha h is statemnt se??

			virtbl4 = stmt.executeQuery(msg);

			
			 // while(virtbl4.next()) {
			 // System.out.println(virtbl4.getString("username"));
			 // System.out.println(virtbl4.getString("password"));
			 
			 
			  //}

			
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		FirstScreen first_obj=new FirstScreen();
		first_obj.CreateInterface();
		
		
		
	}

}
