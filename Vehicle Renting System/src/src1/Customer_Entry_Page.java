package src1;

import javax.swing.*;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;
class tt
{
  JDesktopPane desktop ;
  
  
	JFrame mainwindow4,frame;
	
	JMenuBar menubar;

	JMenu filemenu;

	JMenuItem miaddcustomer, mipresentcust,mipreviouscust;
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
	 JComboBox driverid_cmb;
	 JLabel driverid_cmb_label;
    JButton submit;
	 JTextArea output;
	 JComboBox cmb;
	 JLabel model_label;
	 JComboBox cmb2;
	 JButton rent_btn;
    JTextField rent_tf;
	 JLabel noofdays_label;
	 JTextField noofdays_tf;
	 JButton totalrent_button;
	 JTextField totalrent_tf;
	 String number;
	 
	 JFrame frame2;
	 Double mobile;
	 
	 JInternalFrame frame1;
	 Connection con = null;
	   Statement st = null;
	   ResultSet rs = null;
	   String s,s2;

  
    int x = 0, y = 0;
    
    
    
   public void buildGUI()
  {
	   
	   desktop=new JDesktopPane();
    filemenu = new JMenu("File");
     miaddcustomer = new JMenuItem("Add Customer");
    
    filemenu.add(miaddcustomer);
    menubar = new JMenuBar();
    menubar.add(filemenu);
    mipresentcust = new JMenuItem("Present Records");
    filemenu.add(mipresentcust);
    mipreviouscust = new JMenuItem("Previous Records");
    filemenu.add(mipreviouscust);
  
    miaddcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.ALT_MASK)); //shortcut alt+a
	mipresentcust.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.ALT_MASK));
	mipreviouscust.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.ALT_MASK));
  
 
    JFrame f = new JFrame();
    f.setJMenuBar(menubar);
    f.add(desktop);
    f.setResizable(false);
    f.setSize(1000,710);
    f.setLocationRelativeTo(null);                      //settimg it null would make it middle of screen//check for not null!!!!!!!!!!!!!!!1
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    f.setVisible(true);
    miaddcustomer.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        JInternalFrame frame= new JInternalFrame("ADD CUSTOMER",true,true,true,false);
      //  frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE); //internal frame pr setdefaultcloseopperation nhi chlta 
		
		 																			
		Container cntr=frame.getContentPane();
		cntr.setLayout(null);
		
		JLabel name_label=new JLabel("Name");
		name_label.setBounds(50,50,60,30);
		
		//customer number work starts
		JLabel customernumber=new JLabel("CUSTOMER");
		customernumber.setBounds(700,500,120,30);
		
		
		
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
		
		String[] year_arr=new String[70];    
		for(int i=1951;i<=2020;i++)
			year_arr[i-1951]=Integer.toString(i);
		year=new JComboBox(year_arr);     
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
						
						gender_variable="Male";
						
						
					}
				
				
				
				
				}
				);
		female.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				gender_variable="Female";
				
				
			}
		
		
		
		
		}
		);
        
		ButtonGroup gender=new ButtonGroup();       //buttongroup class is used so that when we are
		gender.add(male);                             //able to select only one of male or female
		gender.add(female);                            //we dont do  cntr.add for btngrp
		
		
		JLabel address_label=new JLabel("Address");
		address_label.setBounds(50,350,60,30);
		
	    add_ta=new JTextArea();
		add_ta.setBounds(150,350,230,50);        //50+60=110 so take 150 here
		
				
		JLabel drivertype_label=new JLabel("Driver Options");
		drivertype_label.setBounds(50, 450, 90, 30);
		
		String[] driver_type={"Driver Needed","Driver Not Needed"}; //array 'new' use krke v bna skte h aur bina new use kre v
		driver=new JComboBox(driver_type);
	    driver.setBounds(150,450,120,25);
	    
	    driverid_cmb_label=new JLabel("Driver Id");
	    driverid_cmb_label.setBounds(50,500,80,30);
	    
	    driverid_cmb=new JComboBox();
	    driverid_cmb.setBounds(150,500,60,25);
	    driverid_cmb.insertItemAt("", 0); 
	    
	    //driver_cmb work starts
	    
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

		

			conrefvar = DriverManager.getConnection("jdbc:mysql://localhost/vehicle_renting_db", "root", "root");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	     String sql = "Select driver_id from driver_table where status='available'";
         try {	
             PreparedStatement pst = conrefvar.prepareStatement(sql);
             virtbl = pst.executeQuery();
             driverid_cmb.removeAllItems();
             while (virtbl.next()) {
            	 driverid_cmb.addItem(virtbl.getString("driver_id"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }


	    driverid_cmb.setEnabled(false);
        driverid_cmb_label.setEnabled(false);
         
        //driver_cmb work ends 
        
	    driver.addActionListener(new ActionListener()             //addactionlistener  main ke andr call nhi oga(btw kisi v function ke andr call nhi oga)
	    		{														
	    			      public void actionPerformed(ActionEvent event )
	    			      {                                     
	    			          
	    			          driverid_cmb.setEnabled(false); 
	    			          driverid_cmb_label.setEnabled(false); 
	    			           
	    			         // obj.updateState();
	    			          
	    			          if(driver.getSelectedItem().equals("Driver Not Needed")) 
	    			          {
	    			          driverid_cmb.setEnabled(false);
	    			          driverid_cmb_label.setEnabled(false);
	    			          }
	    			        else if(driver.getSelectedItem().equals("Driver Needed"))
	    			          {
	    			          driverid_cmb.setEnabled(true); 
	    			          driverid_cmb_label.setEnabled(true);
	    			          }
	    			  	}
	    			    	  		            	
	    			    		                                   
	    		}                                                    
	    		);
	     
        
		JLabel carcompany_label=new JLabel("Car Company");
		carcompany_label.setBounds(450,50,100,30);
		
		
		//work for jcombo box for company starts.....
		
	    cmb = new JComboBox();
	    cmb.setBounds(560,50,120,25);
	    cmb.insertItemAt("", 0);     //so that first item is not already selected for jcombox
	    
	   // Database();
	
	    
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
			            //String sql = "Select model from vehicle_table where company='" + s + "'";
			            String sql = "Select model from vehicle_table where company=?";
			            try {
			                PreparedStatement pst = conrefvar.prepareStatement(sql);
			                pst.setString(1, s);
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
		
		
		rent_btn=new JButton("Rent");
		rent_btn.setBounds(450,100,100,30);
		
		rent_tf=new JTextField();
		rent_tf.setBounds(560,100,120,25);
		
		//work for rent_tf starts
		
        rent_btn.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				 {
			            String s1 =cmb2.getSelectedItem().toString();
			            String sql2 = "Select rent from vehicle_table where model='" + s1 + "'";
			            try {
			                PreparedStatement pst = conrefvar.prepareStatement(sql2);
			                virtbl = pst.executeQuery();
			                
			                while (virtbl.next()) {
			                	rent_tf.setText(virtbl.getString(1));
			                }
			            } catch (SQLException e) {
			                e.printStackTrace();
			            }
			        }
				
			}
	}); 
        
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
		
		//Database1();
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
		//catch(NumberFormatException n)

		submit.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {

							virtbl2.moveToInsertRow();

							String name = (name_tf.getText());
							mobile = Double.parseDouble(mobile_tf.getText());
							
							
						
							String customerid =(cust_id.getText());
							if(name.isEmpty())
							{
								JOptionPane.showMessageDialog(null, "empty values not allowed");
								return;
							}
							
							
							
							String dob=invisible.getText();
							Integer age=Integer.parseInt(age_tf.getText());
							String gender=gender_variable;
							String address=add_ta.getText();
							String driver_options=((String)driver.getSelectedItem());
							String driver_id=(String)driverid_cmb.getSelectedItem();
							String company=(String)cmb.getSelectedItem();
							String model=(String)cmb2.getSelectedItem();
							
							//ab car number retrieve kr rahe h
					            String sql = "Select number from vehicle_table where model='" + model + "'";
					            try {
					                PreparedStatement pst = conrefvar.prepareStatement(sql);
					                virtbl = pst.executeQuery();
					                virtbl.next();
					                number=virtbl.getString(1);
					            }	
					            catch(SQLException e2)
					            {
					            	e2.printStackTrace();
					            }
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
							//return;	
								
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
							virtbl2.updateString(15, number);
							
							
							
							try{
								virtbl2.insertRow();
							}
							catch(SQLException e3){
								JOptionPane.showMessageDialog(cntr, "Oops!! Duplicate Entry for Customer Id not allowed"); 								
								
								return; //programe stops executing after this //but finally statemment may work after that also
								//System.exit(1);
							}
							

						//	virtbl2.insertRow();

							JOptionPane.showMessageDialog(cntr, "Record got inserted");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
					
						}
						
						//now status rented krne ke lie car ka work starts
						
						String s1 =cmb2.getSelectedItem().toString();
						String msg = "select * from vehicle_table where model='"+s1+"'";

						try {
							stmt = conrefvar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			             
						try {
							virtbl = stmt.executeQuery(msg);
							virtbl.last();
							String r="rented";
							virtbl.updateString(5, r);
							

			    			virtbl.updateRow();
			    			 
						} catch (SQLException e1) {
							
							// TODO Auto-generated catch block
							e1.printStackTrace();
							return;
						}
						try{
						s2 =driverid_cmb.getSelectedItem().toString();
						}
						catch(Exception e5){
							System.out.println("ok");
						}
						 try {
							PreparedStatement msg2=conrefvar.prepareStatement( "update driver_table set status ='unavailable' where driver_id='"+s2+"'");
							msg2.executeUpdate();
						
						 } catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
				/*		try {
							stmt = conrefvar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			             
						try {
							virtbl = stmt.executeQuery(msg);
							virtbl.last();
							String r="not available";
							virtbl.updateString(9, r);
							

			    			virtbl.updateRow();
			    			 
						} catch (SQLException e1) {
							
							// TODO Auto-generated catch block
							e1.printStackTrace();
							return;
						}
*/
						
						 
                        
						
						
						
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
		cntr.add(driverid_cmb_label);
		cntr.add(driverid_cmb);
		//cntr.add(output);
		cntr.add(add_ta);
		cntr.add(carcompany_label);
		cntr.add(cmb);
		cntr.add(model_label);
		cntr.add(cmb2);
		cntr.add(rent_btn);
		cntr.add(rent_tf);
		cntr.add(noofdays_label);
		cntr.add(noofdays_tf);
		cntr.add(totalrent_button);
		cntr.add(totalrent_tf);		
        cntr.add(submit);
        //cntr.add(invisible);
        cntr.add(customernumber);
		//frame.setVisible(true);
		
		
        //frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);

        frame.setBounds(x,y,980,600);
      //  frame.setVisible(true);
        desktop.add(frame);
        frame.setVisible(true);

        
        //x += 50;
        //y += 50;
        try{frame.setSelected(true);}catch(Exception e){e.printStackTrace();}
      }
    });
    
   
    
       mipresentcust.addActionListener(new ActionListener()
    		{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					if(frame !=null) {//make sure its not null                      declaring jinternalframe global allows us to use "frame" before its initialisation as an object
				          frame.dispose();}//close the previous internalframe	
					System.out.println("ok");
				    frame= new JFrame("PRESENT RECORDS");
					
					
					 try {
							Class.forName("com.mysql.jdbc.Driver");
						} catch (ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

					   
					   try{
					       con = DriverManager.getConnection("jdbc:mysql://localhost/vehicle_renting_db","root","root");
					       st = con.createStatement();
					       s = "select * from customer_table";
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
					       
					        JPanel panel = new JPanel();
					        JTable table = new JTable(data,column);
					        JScrollPane jsp = new JScrollPane(table);
					        //panel.setLayout(new BorderLayout());
					        panel.setLayout(new BorderLayout());
					        panel.add(jsp,BorderLayout.CENTER);
					        //panel.add(jsp);
					        frame.add(panel);
					        frame.setBounds(100,100,1200,400);
					        frame.setVisible(true);
					        
					       
					        //frame.setVisible(true);       //to make my internal jframe visible i have to add "frame"
					        //to desktop also(for better look nd visibility) and to mainwindow also....//cool
					        
					        
					      
					               
					               
					   }catch(Exception e4){
						   e4.printStackTrace();
					     //  JOptionPane.showMessageDialog(null, "ERROR");
					   }/*finally{
					       try{
					       st.close();
					       rs.close();
					       con.close();
					       }catch(Exception e5){
					           JOptionPane.showMessageDialog(null, "ERROR CLOSE");
					       }
					   }	*/
					
					
					
					
					
					
					
				
				
				
				
				
				
				} 
  
    		});  
       
       
       
       mipreviouscust.addActionListener(new ActionListener()
    		   {

				@Override
				public void actionPerformed(ActionEvent e) {
				
					
  				if(frame2 !=null) {//make sure its not null                      //very cool//declaring jinternalframe global allows us to use "frame" before its initialisation as an object
			           frame2.dispose();}//close the previous internalframe	
				System.out.println("ok");
			    frame2= new JFrame("PREVIOUS RECORDS");
				
				
				 try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				   
				   try{
				       con = DriverManager.getConnection("jdbc:mysql://localhost/vehicle_renting_db","root","root");
				       st = con.createStatement();
				       s = "select * from previousrecord_table";
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
				       
				        JPanel panel = new JPanel();
				        JTable table = new JTable(data,column);
				        JScrollPane jsp = new JScrollPane(table);
				        //panel.setLayout(new BorderLayout());
				        panel.setLayout(new BorderLayout());
				        panel.add(jsp,BorderLayout.CENTER);
				        //panel.add(jsp);
				        frame2.setContentPane(panel);
				        frame2.setBounds(140,140,1200,400);
				        frame2.setVisible(true);
				       // f.add(desktop);                //not using internal frame rytnow
				      //  desktop.add(frame);            //not using internal frame ryt now
				       // f.add(frame);                    //same as above comment
				        frame2.setVisible(true);
				       
				        //frame.setVisible(true);       //to make my internal jframe visible i have to add "frame"
				        //to desktop also(for better look nd visibility) and to mainwindow also....//cool
				        
				      //  try{frame.setSelected(true);}catch(Exception e3){e3.printStackTrace();}
				       // f.add(frame);
				      
				               
				               
				   }catch(Exception e4){
					   e4.printStackTrace();
				       JOptionPane.showMessageDialog(null, "ERROR");
				   }
				
    		   
    		   
				}
    		   });
    		
    		}
   
}

  public class Customer_Entry_Page{
  public static void main(String[] args)
  {
   
        new tt().buildGUI();
      }
   
 
}

