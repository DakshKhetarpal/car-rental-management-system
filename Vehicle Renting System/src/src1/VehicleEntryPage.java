package src1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

//import com.mysql.jdbc.ResultSetMetaData;
import java.sql.ResultSetMetaData;

public class VehicleEntryPage implements ActionListener {
	
	
	
	 JDesktopPane desktop ;
	 
	 Connection con = null;
	 Statement st = null;
	 ResultSet rs = null;
	 String s;

	
	Connection conrefvar;
	Statement stmt;
	ResultSet virtbl;

	PreparedStatement pstmt;

	JFrame mainwindow3;
	JLabel company_label;	
	JLabel carmodel_label;
	JLabel number_label;
	JLabel rent_label;
	
	JTextField company_txt;
	JTextField carmodel_txt;
	JTextField number_txt;
	JTextField rent_txt;
	
	JButton add_button;
	JButton update_button;
	JButton delete_button;
	JButton search_button;
	JButton clear_button;
	
	JButton next_button;
	JButton prev_button;
	JButton first_button;
	JButton last_button;

	JPanel pnlcrud, pnlnavig;
	
	JButton showall_button;
	
	JInternalFrame frame;
	
	public void CreateInterface()
	{
		JFrame mainwindow3=new JFrame("CAR PAGE");
		mainwindow3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainwindow3.setResizable(false);
		mainwindow3.setBounds(180, 40, 1000, 650); 
		mainwindow3.setLayout(null);
		
		company_label=new JLabel("Company");
		company_label.setBounds(100,50,60,30);
		
		company_txt=new JTextField();
		company_txt.setBounds(170,50,100,25);
		
		carmodel_label=new JLabel("Car Model");
		carmodel_label.setBounds(330,50,60,30);
		
		carmodel_txt=new JTextField();
		carmodel_txt.setBounds(400,50,100,25);
		
		number_label=new JLabel("Number");
		number_label.setBounds(530,50,60,30);
		
		number_txt=new JTextField();
		number_txt.setBounds(600, 50, 100, 25);
		
		rent_label=new JLabel("Rent(Rs)");
		rent_label.setBounds(730,50,60,30);
		
		rent_txt=new JTextField();
		rent_txt.setBounds(800,50,100,25);
		
		//crud support begans
		pnlcrud = new JPanel();
		pnlcrud.setBounds(90, 110, 420, 60);         //specify its dimension after adding all the buttons in mainframe ,otherwise u will get dimensions  wrong //iski dimension se button ki dimension khd hi change ho jati h 
		// pnlcrud.setBorder(BorderFactory.createDashedBorder(Color.RED));
		pnlcrud.setBorder(BorderFactory.createTitledBorder("CRUD Support"));
		
		add_button = new JButton("Add Car");
		add_button.setBounds(100,140,80,30);
		add_button.addActionListener(this);

		update_button = new JButton("Update");
		update_button.setBounds(190,140,80,30);
		update_button.addActionListener(this);

		delete_button= new JButton("Delete");
		delete_button.setBounds(280,140,80,30);
		delete_button.addActionListener(this);

		search_button= new JButton("Search");
		search_button.setBounds(370,140,80,30);
		search_button.addActionListener(this);

		clear_button= new JButton("Clear");
		clear_button.setBounds(460,140,80,30);
		clear_button.addActionListener(this);
		
		// pnlcrud add controls
	    pnlcrud.add(add_button);
		pnlcrud.add(update_button);
		pnlcrud.add(delete_button);
		pnlcrud.add(search_button);
		pnlcrud.add(clear_button);

		// Add panel into frame
		mainwindow3.add(pnlcrud);
		
		//navigation panel starts
		pnlnavig = new JPanel();
		pnlnavig.setBounds(550, 110, 340, 60);   //iske setbound sse button ke setbound alter ho jaate h khud hi;;;
		// pnlcrud.setBorder(BorderFactory.createDashedBorder(Color.RED));
		pnlnavig.setBorder(BorderFactory.createTitledBorder("Navigation Support"));

		// navigation controls
		first_button = new JButton("<<");
		first_button.setBounds(530,140,60,30);
		
		first_button.addActionListener(this);

		prev_button = new JButton("<");
		prev_button.setBounds(590,140,60,30);
	    prev_button.addActionListener(this);

		next_button = new JButton(">");
		next_button.setBounds(680,140,60,30);
		next_button.addActionListener(this);

		last_button= new JButton(">>");
		last_button.setBounds(770,140,60,30);
		last_button.addActionListener(this);
		
		// pnlnavig add controls
		pnlnavig.add(first_button);
	    pnlnavig.add(prev_button);
		pnlnavig.add(next_button);
		pnlnavig.add(last_button);

		// Add panel into frame
		mainwindow3.add(pnlnavig);

        //show all vehicle button
		showall_button=new JButton("Show All Cars");
		showall_button.setBounds(90,200,150,35);

		mainwindow3.add(company_label);
		mainwindow3.add(company_txt);
		mainwindow3.add(carmodel_label);
		mainwindow3.add(carmodel_txt);
		mainwindow3.add(number_label);
		mainwindow3.add(number_txt);
		mainwindow3.add(rent_label);
		mainwindow3.add(rent_txt);
		
		mainwindow3.add(showall_button);
		
		mainwindow3.setVisible(true);
		
		//work for Jinternal frame starts
	    desktop = new JDesktopPane();
	 //   mainwindow3.add(desktop);
	    
	    showall_button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(frame !=null) {//make sure its not null                      //very cool//declaring jinternalframe global allows us to use "frame" before its initialisation as an object
			          frame.dispose();}//close the previous internalframe	
				System.out.println("ok");
			    frame= new JInternalFrame("ALL CARS",false,true,false,false);
				
				 try {
					Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
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
				       
				        JPanel panel = new JPanel();
				        JTable table = new JTable(data,column);
				        JScrollPane jsp = new JScrollPane(table);
				        //panel.setLayout(new BorderLayout());
				        panel.setLayout(new BorderLayout());
				        panel.add(jsp,BorderLayout.CENTER);
				        //panel.add(jsp);
				        frame.setContentPane(panel);
				        frame.setBounds(90,250,800,300);
				        frame.setVisible(true);
				       mainwindow3.add(desktop);
				        desktop.add(frame);
				        frame.setVisible(true);
				        //frame.setVisible(true);       //to make my internal jframe visible i have to add "frame"
				        //to desktop also(for better look nd visibility) and to mainwindow also....//cool
				        
				        try{frame.setSelected(true);}catch(Exception e3){e3.printStackTrace();}
				        mainwindow3.add(frame);
				      
				               
				               
				   }catch(Exception e4){
				       JOptionPane.showMessageDialog(null, "ERROR");
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
			String msg = "select * from vehicle_table";

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
	

		

	
	
	@Override
	public void actionPerformed(ActionEvent btnref) {

		if (btnref.getActionCommand() == "Add Car") {  //jo v buttonref aayi h kya wo insert ki h--meaning of this line
			rcrinsert();

		} else if (btnref.getActionCommand() == "Update") {
			rcrupdate();

		} else if (btnref.getActionCommand() == "Delete") {
			rcrdelete();

		} else if (btnref.getActionCommand() == "Search") {
			rcrsearch();

		} else if (btnref.getActionCommand() == "Clear") {
			txtclear();

		} else if (btnref.getActionCommand() == "<<") {
			rcrfirst();

		} else if (btnref.getActionCommand() == "<") {
			rcrprev();

		} else if (btnref.getActionCommand() == ">") {
			rcrnext();

		} else if (btnref.getActionCommand() == ">>") {
			rcrlast();

		}

	}
	
	
	public void FillTextBoxes() {
		 
		try {

			company_txt.setText(virtbl.getString(1)); //1,2,3 -column number,,,,,column name will alsso work
			carmodel_txt.setText(virtbl.getString(2));
			number_txt.setText(virtbl.getString(3));
			rent_txt.setText(Double.toString(virtbl.getDouble(4)));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void rcrupdate() {
		// TODO Auto-generated method stub
		
		String company = company_txt.getText();
		String carmodel = carmodel_txt.getText();
		String number = number_txt.getText().trim();
		double rent = Double.parseDouble(rent_txt.getText());

		try {

			/*virtbl.updateString(1, company);           //1,2,3 represents columns
			virtbl.updateString(2, carmodel);
			virtbl.updateString(3, number);
			virtbl.updateDouble(4, rent);

			virtbl.updateRow();
*/
			
			
			 PreparedStatement msg3=conrefvar.prepareStatement("UPDATE vehicle_table SET company=?,model=?,number=?,rent=? where number=?");
			 
			 msg3.setString(1, company);
			 msg3.setString(2, carmodel);
			 msg3.setString(3, number);
			 msg3.setDouble(4, rent);
			 msg3.setString(5, number);
			 			 
			//virtbl.deleteRow();
			 msg3.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "Record updated successfully.");

			 DatabaseOps();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void rcrdelete() {
		// TODO Auto-generated method stub
		
		
		try {
			/*virtbl.deleteRow();
			JOptionPane.showMessageDialog(mainwindow3, "Highlighted record got deleted");*/
			
			String number = number_txt.getText().trim();
			 PreparedStatement msg2=conrefvar.prepareStatement("delete from vehicle_table where number=?");
			 msg2.setString(1,number); 
			//virtbl.deleteRow();
			 msg2.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "Choosen record got deleted");
			DatabaseOps();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void rcrsearch() {
		// TODO Auto-generated method stub

		if (number_txt.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(mainwindow3, "Car number is compusory for search");
		} else {

			try {
				virtbl.close();

				String number =number_txt.getText().trim();

				String msg = "select * from vehicle_table where number = ?";//whenever we r using prepared statement a new virtual table is formed

				pstmt = conrefvar.prepareStatement(msg);

				pstmt.setString(1, number);      //here 1 is no. of parameters that is only one -number

				virtbl = pstmt.executeQuery();

				if (virtbl.next()) {
					FillTextBoxes();
				} else {
					JOptionPane.showMessageDialog(mainwindow3, number + " is not in database");
				}
				
				//DatabaseOps();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*try 
			{ 
				virtbl.close();
			String number=number_txt.getText().trim();      //get text will assign entered value to variable 'user'
			
			String msg="select * vehicle_table where number='"+number+"'";
			
			
				stmt = conrefvar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				virtbl=stmt.executeQuery(msg);
				if (virtbl.next()) {
					FillTextBoxes();
				} else {
					JOptionPane.showMessageDialog(mainwindow3, number + " is not in database");
				}								//creating a new virtual table based on our query
			} catch (SQLException e1) {         //and doing directly  because we don't need to do any changes in real table for now
				// TODO Auto-generated catch block
				e1.printStackTrace();

		}*/
			}

	}

	private void txtclear() {
		// TODO Auto-generated method stub

		company_txt.setText(" ");
		carmodel_txt.setText(" ");
		number_txt.setText(" ");
		rent_txt.setText(" ");

	}

	private void rcrfirst() {
		// TODO Auto-generated method stub

		try {
			if (virtbl.first()) {
				FillTextBoxes();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void rcrprev() {
		// TODO Auto-generated method stub

		try {
			if (virtbl.previous()) {
				FillTextBoxes();
			} else {
				JOptionPane.showMessageDialog(mainwindow3, "You are at first record");
				virtbl.next();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void rcrnext() {
		// TODO Auto-generated method stub

		try {
			if (virtbl.next()) {
				FillTextBoxes();
			} else {
				JOptionPane.showMessageDialog(mainwindow3, "You are at last record");
				virtbl.previous();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void rcrlast() {
		// TODO Auto-generated method stub

		try {
			if (virtbl.last()) {
				FillTextBoxes();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void rcrinsert() {
		// TODO Auto-generated method stub

		try {

			

			String company = company_txt.getText();
			String carmodel = carmodel_txt.getText();
			String number = number_txt.getText();
			double rent = Double.parseDouble(rent_txt.getText());
			
			
			PreparedStatement msg3=conrefvar.prepareStatement("insert into vehicle_table values (?,?,?,?,'unrented')");
			 
			 msg3.setString(1, company);
			 msg3.setString(2, carmodel);
			 msg3.setString(3, number);
			 msg3.setDouble(4, rent);
			 			 
			//virtbl.deleteRow();
			 msg3.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "Record Inserted successfully.");

			 DatabaseOps();

			JOptionPane.showMessageDialog(mainwindow3, "Record got inserted");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args)
	{
	
		VehicleEntryPage obj=new VehicleEntryPage();
		obj.CreateInterface();
		obj.DatabaseOps();

		
		
		
	}

}
