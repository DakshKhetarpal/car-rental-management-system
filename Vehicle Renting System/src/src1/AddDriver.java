package src1;

import java.awt.BorderLayout;
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
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



class add_driver extends JPanel
{
	Connection conrefvar,conrefvar2;
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
	
	 JComboBox qualification_cmb;
	 
	 String path;
	 
	  //private static final int MASK =
		//        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		    private JFileChooser chooser = new JFileChooser();
		    		    private BufferedImage image;
	 
	 public void CreateInterface()
		
		{
			JFrame frame=new JFrame("ADD DRIVER");
			 																			
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
			
			String yyyy=(String) year.getSelectedItem();
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
	
	
    
    
    	
	
	
	addimage=new JButton("ADD IMAGE");
	addimage.setBounds(590,280,100,30);
	
	addimage.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int returnVal = chooser.showOpenDialog(null);
		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		                File f = chooser.getSelectedFile();
		                try {
		                	
		                	 path = f.getAbsolutePath();
		                    image = ImageIO.read(f);
		                    image.flush();
		                    
		                    
		                    revalidate();
		                    repaint();                   
		                } catch (IOException ex) {
		                    ex.printStackTrace(System.err);
		                }
		            }
					
					
					
					
					
				}
		
			}	
			);
	
	pm=new JPanel();
    pm.setBounds(550, 45, 180, 220);
    pm.setBorder(BorderFactory.createTitledBorder(""));
    pm.add(new JScrollPane(this),BorderLayout.CENTER);
    
	
	
	
	add_driver=new JButton("ADD DRIVER");
	add_driver.setBounds(580,400,130,70);
	
	
	add_driver.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			Database();
			
			String name = (name_tf.getText());
			Integer mobile = Integer.parseInt(mobile_tf.getText());
			String driverid =(driv_id.getText());
			
			String dob=invisible.getText();
			Integer age=Integer.parseInt(age_tf.getText());
			String gender=gender_variable;
			String address=add_ta.getText();
			String qualification=((String)qualification_cmb.getSelectedItem());
			 try {
				 //below is another way of inserting in table values
				PreparedStatement ps = conrefvar.prepareStatement("insert into driver_table(name,mobile,driver_id,dob,age,gender,address,qualification,status,image) values(?,?,?,?,?,?,?,?,?,?)");
				InputStream is = new FileInputStream(new File(path));
				 ps.setString(1, name);
	               ps.setInt(2, mobile);
	               ps.setString(3, driverid);
	               ps.setString(4,dob);
	               ps.setInt(5,age);
	               ps.setString(6,gender);
	               ps.setString(7,address);
	               ps.setString(8,qualification);
	               
	               ps.setString(9, "available");
	               ps.setBlob(10,is);
	               ps.executeUpdate();
	               
	               JOptionPane.showMessageDialog(null, "Data Inserted");
				
				
				
				
			} catch (Exception e) {
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
			cntr.add(addimage);
			cntr.add(pm);
			cntr.add(add_driver);
			//frame.add(pm);			
			
			
			
			//frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setBounds(220, 60, 1000, 650);
			
			
			frame.setVisible(true);
			
		}
	 
	 @Override
	    public Dimension getPreferredSize() {           //dimension is class ,its object is return type here
	        if (image == null) {
	            return new Dimension();
	        } else {
	            return new Dimension(image.getWidth(),image.getHeight());
	        }
	    }

	    @Override
	    public void paintComponent(Graphics g) {
	       super.paintComponent(g);    
	        g.drawImage(image, 0, 0, null); //x coordiante, y coordiante,width,height    
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

							
	

	
							 

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

		 }

	 
	 
}





public class AddDriver {
	
	public static void main(String[] args)
	{
		add_driver obj3=new add_driver();
		obj3.CreateInterface();
	}

}
