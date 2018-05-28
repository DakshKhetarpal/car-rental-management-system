package src1;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
//import java.util.Date;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

class Customerpage
{    
	
	Connection conrefvar,conrefvar2;
	 Statement stmt,stmt2;
	 ResultSet virtbl,virtbl2;
	 PreparedStatement pstmt,pstmt2;
	
     JTextField name_tf;
	 JTextField mobile_tf;
	 JTextField cust_id;
	 JComboBox day;
	 JComboBox month;
	 JComboBox year;
	 JTextField invisible;
	 JTextField age_tf;
	 JRadioButton male;
	 JRadioButton female;
	 String  gender_variable;
	 JTextArea add_ta;
     JComboBox driver;
	 JTextField driverid;
	 JLabel driverid_label;
     JButton submit;
	 JTextArea output;
	 JComboBox cmb;
	 JLabel model_label;
	 JComboBox cmb2;
	 JLabel rent_label;
     JTextField rent_tf;
	 JLabel noofdays_label;
	 JTextField noofdays_tf;
	 JButton totalrent_button;
	 JTextField totalrent_tf;
	 
	
	 public void CreateInterface()
		
	{
		JFrame frame=new JFrame();
		//frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(80, 80, 1000, 650);
		 																			
		Container cntr=frame.getContentPane();
		cntr.setLayout(null);
		
		JLabel name_label=new JLabel("Name");
		name_label.setBounds(50,50,60,30);
		
		
		name_tf=new JTextField();
		name_tf.setBounds(150, 50, 150, 25);  //50+60=110, so keep txt filed distance frm left side as 150
		
		JLabel mobile_label=new JLabel("Mobile");
		mobile_label.setBounds(50,100,60,30);
		
	    mobile_tf=new JTextField();
		mobile_tf.setBounds(150, 100, 150, 25);
		
		JLabel cust_label=new JLabel("Customer Id");
		cust_label.setBounds(50,150,70,30);
		
		cust_id=new JTextField();
		cust_id.setBounds(150,150,150,25);
		
		JLabel dob_label=new JLabel("DOB");
		dob_label.setBounds(50,200,60,30);
		
		String[] day_arr=new String[31];            //way of forming string array
		for(int i=1;i<=31;i++)
			day_arr[i-1]=Integer.toString(i);
		day=new JComboBox(day_arr);
		day.setBounds(150,200,50,25);
		
		String[] month_arr={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"}; //array new use krke v bna skte h aur bina new use kre v
		month=new JComboBox(month_arr);
		month.setBounds(220,200,70,25);  //150+50=200 so keep its width as 220(approx)
		
		String[] year_arr=new String[70];    //string array bnaya h of year
		for(int i=1951;i<=2020;i++)
			year_arr[i-1951]=Integer.toString(i);
		year=new JComboBox(year_arr);     //fir uss array ko combobox ke object me dal dia
		year.setBounds(310,200,60,25);
		
				
		invisible=new JTextField();
		invisible.setBounds(500,500,70,30);
		
		day.addItemListener(new ItemListener() {         //cool//very cool//nested additemlistener implemented 
			
			public void itemStateChanged(ItemEvent arg0) {   
		month.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
		year.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				String yyyy=(String)year.getSelectedItem();
				String mm=(String)month.getSelectedItem();
				String dd=(String)day.getSelectedItem();
				invisible.setText(yyyy+"-"+mm+"-"+dd);
			}
			
		});
			}
			});
		}
		});
		
		JLabel age_label=new JLabel("Age");
		age_label.setBounds(50,250,60,30);
		
		age_tf=new JTextField();
		age_tf.setBounds(150,250,40,25);
		
		
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(current_year);
		
		//printing age work starts
		
         year.addItemListener(new ItemListener() {
	        
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				 {
			            
			            int y= Integer.parseInt((String)year.getSelectedItem());  //pehle object ko string bnaya fir integer me convert kia as getselecteditem() returns object.
			            
			            int age=current_year-y;
			            age_tf.setText(Integer.toString(age));
			        }
				
			}
	});

		
		
		JLabel gender_label=new JLabel("Gender");
		gender_label.setBounds(50,300,60,30);
		
		male=new JRadioButton("Male");    //these are made for 2 options for male and female
		male.setBounds(150,300,80,30);
        
		female=new JRadioButton("Female");
		female.setBounds(250,300,80,30);
		
		male.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						String  gender_variable="Male";
						
						
					}
				
				
				
				
				}
				);
		female.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				String  gender_variable="Female";
				
				
			}
		
		
		
		
		}
		);
        
		ButtonGroup gender=new ButtonGroup();       //buttongroup class is used so that when we are
		gender.add(male);                             //able to select only one of male or female
		gender.add(female);                            //iske object ko cntr.add nhi krte
		
		
		JLabel address_label=new JLabel("Address");
		address_label.setBounds(50,350,60,30);
		
	    add_ta=new JTextArea();
		add_ta.setBounds(150,350,230,50);        //50+60=110 so take 150 here
		
				
		JLabel drivertype_label=new JLabel("Driver Options");
		drivertype_label.setBounds(50, 450, 90, 30);
		
		String[] driver_type={"Driver Needed","Driver Not Needed"}; //array 'new' use krke v bna skte h aur bina new use kre v
		driver=new JComboBox(driver_type);
	    driver.setBounds(150,450,120,25);
	    
	    driverid_label=new JLabel("Driver Id");
	    driverid_label.setBounds(50,500,80,30);
	    
	    driverid=new JTextField();
	    driverid.setBounds(150,500,60,25);

	    driverid.setEnabled(false);
        driverid_label.setEnabled(false);

	    driver.addActionListener(new ActionListener()             //addactionlistener  main ke andr call nhi oga(btw kisi v function ke andr call nhi oga)
	    		{														
	    			      public void actionPerformed(ActionEvent event )
	    			      {                                     
	    			          
	    			          driverid.setEnabled(false); 
	    			          driverid_label.setEnabled(false); 
	    			           
	    			         // obj.updateState();
	    			          
	    			          if(driver.getSelectedItem().equals("Driver Not Needed")) 
	    			          {
	    			          driverid.setEnabled(false);
	    			          driverid_label.setEnabled(false);
	    			          }
	    			        else if(driver.getSelectedItem().equals("Driver Needed"))
	    			          {
	    			          driverid.setEnabled(true); 
	    			          driverid_label.setEnabled(true);
	    			          }
	    			  	}
	    			    	  		            	
	    			    		                                   
	    		}                                                    
	    		);
	     
        
		JLabel carcompany_label=new JLabel("Car Company");
		carcompany_label.setBounds(450,50,100,30);
		
		
		//work for jcombo box for company starts.....
		
	    cmb = new JComboBox();
	    cmb.setBounds(560,50,120,25);
	    
	    Database();
	    
		try {
			while(virtbl.next()) {
			    String result = virtbl.getString(1); // Retrieves the value of the designated column in the current row of this ResultSet object as a String
			    if (result != null) {
			        result = result.trim();
			    }
			    cmb.addItem(result);
			} 
			virtbl.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model_label=new JLabel("Model");
		model_label.setBounds(720,50,100,30);
		
		cmb2=new JComboBox();
		cmb2.setBounds(790,50,120,25);
		
		//work for 2nd combobox starts
		
		cmb.addItemListener(new ItemListener() {
	        
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				 {
			            String s =cmb.getSelectedItem().toString();
			            String sql = "Select model from vehicle_table where company='" + s + "'";
			            try {
			                PreparedStatement pst = conrefvar.prepareStatement(sql);
			                virtbl = pst.executeQuery();
			                cmb2.removeAllItems();
			                while (virtbl.next()) {
			                    cmb2.addItem(virtbl.getString("model"));
			                }
			            } catch (SQLException e) {
			                e.printStackTrace();
			            }
			        }
				
			}
	});
		
		
		rent_label=new JLabel("Rent");
		rent_label.setBounds(450,100,100,30);
		
		rent_tf=new JTextField();
		rent_tf.setBounds(560,100,120,25);
		
		//work for rent_tf starts
		
      /*  cmb2.addItemListener(new ItemListener() {
	        
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				 {
			            String s =cmb2.getSelectedItem().toString();
			            String sql = "Select rent from vehicle_table where model='" + s + "'";
			            try {
			                PreparedStatement pst = conrefvar.prepareStatement(sql);
			                virtbl = pst.executeQuery();
			                
			                while (virtbl.next()) {
			                	rent_tf.setText(virtbl.getString(1));
			                }
			            } catch (SQLException e) {
			                e.printStackTrace();
			            }
			        }
				
			}
	}); */
        
        noofdays_label=new JLabel("No. Of Days");
        noofdays_label.setBounds(720,100,100,30);
        
        noofdays_tf=new JTextField();
        noofdays_tf.setBounds(810,100,100,25);
        
        //work for total rent starts

                
        totalrent_button=new JButton("Total Rent");
        totalrent_button.setBounds(450,150,100,30);
        
        totalrent_tf=new JTextField();
        totalrent_tf.setBounds(580,155,120,25);
        
        totalrent_button.addActionListener(new ActionListener()       
        		{														
		      public void actionPerformed(ActionEvent event )
		    		  {
		    	  int rent = Integer.parseInt(rent_tf.getText());
		    	  int noofdays = Integer.parseInt(noofdays_tf.getText());
		    	  
		    	  int totalrent=rent*noofdays;
		    	  totalrent_tf.setText(Integer.toString(totalrent));
		    		  }                                  
	}                                                   
	);
        
        submit=new JButton("ADD CUSTOMER");
		submit.setBounds(450,250,150,40);
		
		//work for submit starts
		
		Database1();
		submit.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {

							virtbl2.moveToInsertRow();

							String name = (name_tf.getText());
							double mobile = Double.parseDouble(mobile_tf.getText());
							String customerid =(cust_id.getText());
							
							//String date = invisible.getText();//-- "yyyy-mm-dd"
							//Date dobj = new Date(date);
							
							//SimpleDateFormat 
							
							
							String dob=invisible.getText();
							Integer age=Integer.parseInt(age_tf.getText());
							String gender=gender_variable;
							String address=add_ta.getText();
							String driver_options=((String)driver.getSelectedItem());
							String driver_id=driverid.getText();
							String company=(String)cmb.getSelectedItem();
							String model=(String)cmb2.getSelectedItem();
							Integer rent=Integer.parseInt(rent_tf.getText());
							Integer no_of_days=Integer.parseInt(noofdays_tf.getText());
							Integer totalrent=Integer.parseInt(totalrent_tf.getText());
							

							virtbl2.updateString(1, name);
							virtbl2.updateDouble(2, mobile);
							try{
								virtbl2.updateString(3, customerid);
							}
							catch(MySQLIntegrityConstraintViolationException e3){
								JOptionPane.showMessageDialog(cntr, "Duplicate Entry for Customer Id not allowed"); 								
								
								
							}
							virtbl2.updateString(4, dob);
							virtbl2.updateInt(5, age);
							virtbl2.updateString(6, gender);
							virtbl2.updateString(7, address);
							virtbl2.updateString(8,driver_options);
							virtbl2.updateString(9, driver_id);
							virtbl2.updateString(10, company);
							virtbl2.updateString(11, model);
							virtbl2.updateInt(12, rent);
							virtbl2.updateInt(13, no_of_days);
							virtbl2.updateInt(14, totalrent);
							
							
							
							try{
								virtbl2.insertRow();
							}
							catch(MySQLIntegrityConstraintViolationException e3){
								JOptionPane.showMessageDialog(cntr, "Oops!! Duplicate Entry for Customer Id not allowed"); 								
								
								//return; //programe stops executing after this //but finally statemment may work after that also
								System.exit(1);
							}
							

						//	virtbl2.insertRow();

							JOptionPane.showMessageDialog(cntr, "Record got inserted");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

			
			
			
			
			
			
			
				}	
				);					
		
		


		
		cntr.add(name_label);           //frame.add(name_label) would also work
		cntr.add(name_tf);
		cntr.add(mobile_label);
		cntr.add(mobile_tf);
		cntr.add(cust_label);
		cntr.add(cust_id);
		cntr.add(day);     //combobox ka object day h
		cntr.add(month);
		cntr.add(year);
		cntr.add(male);
		cntr.add(female);
		cntr.add(age_tf);
		cntr.add(age_label);
		cntr.add(dob_label);
		cntr.add(gender_label);
		cntr.add(address_label);
		cntr.add(drivertype_label);
		cntr.add(driver);
		cntr.add(driverid_label);
		cntr.add(driverid);
		//cntr.add(output);
		cntr.add(add_ta);
		cntr.add(carcompany_label);
		cntr.add(cmb);
		cntr.add(model_label);
		cntr.add(cmb2);
		cntr.add(rent_label);
		cntr.add(rent_tf);
		cntr.add(noofdays_label);
		cntr.add(noofdays_tf);
		cntr.add(totalrent_button);
		cntr.add(totalrent_tf);		
        cntr.add(submit);
        cntr.add(invisible);
		frame.setVisible(true);
		
	}
	
	 public void Database()
	 {
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
						String msg = "select company from vehicle_table where status='unrented'";

						stmt = conrefvar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			             
						virtbl = stmt.executeQuery(msg);

						
						 /* while(virtbl.next()) {
						  System.out.println(virtbl.getString("company"));
						  System.out.println(virtbl.getString("model"));
						  System.out.println(virtbl.getString("number"));
						  System.out.println(virtbl.getDouble("rent"));
						  
						  }*/
						 

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	 }
	 
	 public void Database1()
	 {
		// Step 1 : Load the Driver
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {

						// Step 2 : Connecting the front end with back end

						conrefvar2 = DriverManager.getConnection("jdbc:mysql://localhost/vehicle_renting_db", "root", "root");

						
						// Step 3 : Create a message
						String msg = "select * from customer_table";

						stmt2 = conrefvar2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			             
						virtbl2 = stmt2.executeQuery(msg);
						 

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	 }
	 
	    
}
public class RegistrationDemo
{
	public static void main(String args[])
	{
		Customerpage obj=new Customerpage();
		obj.CreateInterface();
	}
}

	






