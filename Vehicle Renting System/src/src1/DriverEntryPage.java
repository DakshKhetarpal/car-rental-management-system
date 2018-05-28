package src1;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class one extends JPanel


{
	
	
	
	JDesktopPane desktop ;
	JFrame mainwindow;
	JMenuBar menubar;

	JMenu filemenu;

	JMenuItem miadd, mimodify;	
	
	JPanel pnlcrud;
	JTextField txt1;
	JTextField txt2;
	JTextField txt3;
	JTextField txt4;
	JTextField txt5;
	JTextField txt6;
	JTextArea txt7;
	JTextField txt8;
	
	
	
	
	
	Connection conrefvar;
	 Statement stmt,stmt2;
	 ResultSet virtbl,virtbl2;
	 PreparedStatement pstmt,pstmt2;
	
   JTextField name_tf;
	 JTextField mobile_tf;
	 JTextField driv_id;
	 JComboBox day;
	 JComboBox month;
	 JComboBox year;
	 JTextField invisible;
	 JTextField age_tf;
	 JRadioButton male;
	 JRadioButton female;
	 String  gender_variable;
	 JTextArea add_ta;
    JButton addimage;
    JPanel pm;
    JButton add_driver;
    JButton searchbtn;
    JButton deletebtn;
    
	
	 JComboBox qualification_cmb;
	
	public void CreateInterface()
	{
		
	desktop=new JDesktopPane();
	mainwindow = new JFrame();
	mainwindow.add(desktop);
	mainwindow.setTitle("Driver Page");
	mainwindow.setResizable(false);
	mainwindow.setSize(1000,710);
	mainwindow.setLocationRelativeTo(null);
    mainwindow.add(desktop);
    
    
  
	
	menubar = new JMenuBar();

	filemenu = new JMenu("File");
	

	miadd = new JMenuItem("Add Driver");
	mimodify = new JMenuItem("Modify");

	filemenu.add(miadd);
	filemenu.add(mimodify);

	menubar.add(filemenu);


	mainwindow.setJMenuBar(menubar);
	
				
	miadd.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			add_driver obj3=new add_driver();
			obj3.CreateInterface();

			
			
		}			
	});
	
	mimodify.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			JInternalFrame frame1= new JInternalFrame("MODIFY DRIVER INFO",true,true,true,true);
			
			Container cntr=frame1.getContentPane();
			cntr.setLayout(null);
			
			
			JLabel name_label=new JLabel("Name");
			name_label.setBounds(50,50,60,30);
			
			name_tf=new JTextField();
			name_tf.setBounds(150, 50, 150, 25);  //50+60=110, so keep txt filed distance frm left side as 150
			
			JLabel mobile_label=new JLabel("Mobile");
			mobile_label.setBounds(50,100,60,30);
			
		    mobile_tf=new JTextField();
			mobile_tf.setBounds(150, 100, 150, 25);
			
			JLabel driv_label=new JLabel("Driver Id");
			driv_label.setBounds(50,150,70,30);
			
			driv_id=new JTextField();
			driv_id.setBounds(150,150,150,25);
			
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
			
			day.insertItemAt("", 0);
			month.insertItemAt("", 0);
			year.insertItemAt("", 0);
			
			
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
	gender.add(female);                            //iske object ko cntr.add nhi krte
	
	
	JLabel address_label=new JLabel("Address");
	address_label.setBounds(50,350,60,30);
	
    add_ta=new JTextArea();
	add_ta.setBounds(150,350,230,50); 
	
	JLabel qualification_label=new JLabel("Qualification");
     qualification_label.setBounds(50, 450, 90, 30);
	
	String[] qual_array={"10th pass","12th pass","graduation","post graduation"}; //array 'new' use krke v bna skte h aur bina new use kre v

	qualification_cmb=new JComboBox();
	
	qualification_cmb=new JComboBox(qual_array);
	qualification_cmb.insertItemAt("", 0);
	qualification_cmb.setBounds(150,450,120,25);
	
	pnlcrud = new JPanel();
	// pnlcrud.setBorder(BorderFactory.createDashedBorder(Color.RED));
	pnlcrud.setBorder(BorderFactory.createTitledBorder("Search Result"));

	pnlcrud.setBounds(700, 40, 200, 430);
	
	
	
	txt1 = new JTextField();
	txt1.setBounds(710,70 , 170, 25);                   

	txt2 = new JTextField();
	txt2.setBounds(710,110 , 170, 25);
	
	txt3 = new JTextField();
	txt3.setBounds(710,150 , 170, 25);
	
	txt4 = new JTextField();
	txt4.setBounds(710,190, 170, 25);
	
	txt5 = new JTextField();
	txt5.setBounds(710,230, 170, 25);
	
	txt6 = new JTextField();
	txt6.setBounds(710,280, 170, 25);
	
	txt7 = new JTextArea();
	txt7.setBounds(710,320, 170, 60);
	
	
	txt8 = new JTextField();
	txt8.setBounds(710,400, 170, 25);

	
	// pnl add txts
	pnlcrud.add(txt1);
	pnlcrud.add(txt2);
	pnlcrud.add(txt3);
	pnlcrud.add(txt4);
	pnlcrud.add(txt5);
	pnlcrud.add(txt6);
	pnlcrud.add(txt7);
	pnlcrud.add(txt8);

	
	
	
	
	
	searchbtn=new JButton("SEARCH");
	searchbtn.setBounds(500,80,120,30);
	
	searchbtn.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (driv_id.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(mainwindow, "Driver ID is compulsory for search");
			} else {

				try {
					
					Database();
					virtbl.close();

					String id = driv_id.getText().trim();

					//String msg = "select name,mobile,driver_id,dob,age,gender,address,qualification,status from driver_table where driver_id = ?";
					String msg = "select * from driver_table where driver_id = ?";

					pstmt = conrefvar.prepareStatement(msg);

					pstmt.setString(1, id);          //here number is number of parameters and not the column number

					virtbl = pstmt.executeQuery();

					if (virtbl.next()) {
						FillTextBoxes();
					} else {
						JOptionPane.showMessageDialog(mainwindow, id + " is not in database");
					}
					
					//Database();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			}
			
			
			
			
		}
		});
	
	
	
	
	deletebtn=new JButton("DELETE");
	deletebtn.setBounds(500,140,120,30);
	
	
	deletebtn.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					
					
						
						
						
						try {
							String id = driv_id.getText().trim();
							 PreparedStatement msg2=conrefvar.prepareStatement("delete from driver_table where driver_id=?");
							 msg2.setString(1, id); 
							//virtbl.deleteRow();
							 msg2.executeUpdate(); 
							JOptionPane.showMessageDialog(null, "Choosen record got deleted");

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					
					
					
					
					
					
					
					
					
					
					
					
					
				}
		});
	
	
	add_driver=new JButton("MODIFY");
	add_driver.setBounds(500,200,120,30);
	
	
	add_driver.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			//Database();
			
			String namee = (name_tf.getText()).trim();
			Integer mobilee = Integer.parseInt(mobile_tf.getText().trim());
			String driveridd =(driv_id.getText().trim());
			
			
			
			
			
			String dobb=invisible.getText();
			Integer agee=Integer.parseInt(age_tf.getText());
			String genderr=gender_variable;
			String addresss=add_ta.getText();
			String qualificationn=((String)qualification_cmb.getSelectedItem());
			 try {
				
				// conrefvar.setReadOnly(false);
				 
				 String id = driv_id.getText().trim();
				 PreparedStatement msg3=conrefvar.prepareStatement("UPDATE driver_table SET name=?,mobile=?,driver_id=?,dob=?,age=?,gender=?,address=?,qualification=? where driver_id=?");
				 
				 msg3.setString(1, namee);
				 msg3.setInt(2, mobilee);
				 msg3.setString(3, driveridd);
				 msg3.setString(4, dobb);
				 msg3.setInt(5, agee);
				 msg3.setString(6, genderr);
				 msg3.setString(7, addresss);
				 msg3.setString(8, qualificationn);
				 msg3.setString(9, id);
				 
				//virtbl.deleteRow();
				 msg3.executeUpdate(); 
				JOptionPane.showMessageDialog(null, "Record updated successfully.");

				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}         
			
			
			
			
		}

	}	
	);
			
			cntr.add(name_label);
			cntr.add(name_tf);
			cntr.add(mobile_label);
			cntr.add(mobile_tf);
			cntr.add(driv_id);
			cntr.add(driv_label);
			cntr.add(dob_label);
			cntr.add(day);
			cntr.add(month);
			cntr.add(year);
			cntr.add(invisible);
			cntr.add(age_label);
			cntr.add(age_tf);
			cntr.add(gender_label);
			cntr.add(male);
			cntr.add(female);
			cntr.add(address_label);
			cntr.add(add_ta);
			cntr.add(qualification_label);
			cntr.add(qualification_cmb);
			
			cntr.add(txt1);
			cntr.add(txt2);
			cntr.add(txt3);
			cntr.add(txt4);
			cntr.add(txt5);
			cntr.add(txt6);
			cntr.add(txt7);
			cntr.add(txt8);
			
			
            cntr.add(pnlcrud);
			cntr.add(add_driver);
			cntr.add(searchbtn);
			cntr.add(deletebtn);
			//frame.add(pm);			
			
			frame1.setBounds(0,0,980,600);
		      //  frame.setVisible(true);
		        desktop.add(frame1);
		      

			
			
			
			frame1.setVisible(true);
			
	
	
	
		}
		
			
	});

	
	
	
	
	
	mainwindow.setVisible(true);
	
	
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

							
							 //Step 3 : Create a message
							String msg = "select * from driver_table ";

							stmt = conrefvar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
							
				             
							virtbl = stmt.executeQuery(msg);
							
							//virtbl.close();
							//stmt.close();
							
							
							System.out.println("concurrency = "+virtbl.getConcurrency());

	                        
							 

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

		 }//databaseops ends
	
	    public void FillTextBoxes() {

			try {

				
				txt1.setText(virtbl.getString(1));
				txt2.setText(Integer.toString(virtbl.getInt(2)));
				txt3.setText(virtbl.getString(3));
				txt4.setText(virtbl.getString(4));
				txt5.setText(Integer.toString(virtbl.getInt(5)));
				txt6.setText(virtbl.getString(6));
				txt7.setText(virtbl.getString(7));
				txt8.setText(virtbl.getString(8));
				
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
	
	

}//class ends

public class DriverEntryPage {

	public static void main(String[] args) {
		
		one obj=new one();
		
		obj.CreateInterface();
		
		
	}
}