package programms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Crud extends JFrame {
	
	Connection con;
	ResultSet rs; 
	PreparedStatement st;
	ResultSetMetaData rm;
	DefaultTableModel model;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textid;
	private JTextField textfn;
	private JTextField textln;
	private JTextField textemail;
	private JTextField textmobile;
	private JTable table;
	private final JPanel panel_2 = new JPanel();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) throws Exception  {
	

		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Crud frame = new Crud();
					frame.setVisible(true);
					frame.connection();
					frame.t();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void t() {
		int a=0;
		try {
			st=con.prepareStatement("select * from student");
			rs=st.executeQuery();
			rm=rs.getMetaData();
			a = rm.getColumnCount();
			model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			while(rs.next()) {
				Vector v = new Vector();
				
				for( int i =1; i<=a; i++ ) {
				v.add(rs.getString("ID"));
				v.add(rs.getString("FirstName"));
				v.add(rs.getString("LastName"));
				v.add(rs.getString("Email"));
				v.add(rs.getString("Mobile"));
					
				}
		model.addRow(v);
			}
			
		}catch (Exception r) {
			System.out.println(r);
		}
	}
	public void  connection() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/crud";
		String username="root";
		String password="123456";
		
		con=DriverManager.getConnection(url,username,password);
		if(con.isClosed()) {
			System.out.println("Connection is closed..");
		}else {
			System.out.println("Connection is establessed..");
		}
	}
	

	
	public Crud() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 576);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(55, 103, 96, 33);
		contentPane.add(lblNewLabel);
		
		textid = new JTextField();
		textid.setBounds(184, 105, 164, 33);
		contentPane.add(textid);
		textid.setColumns(10);
		
		JLabel lblFirstName = new JLabel("FIRST NAME :");
		lblFirstName.setForeground(Color.BLACK);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstName.setBounds(55, 171, 132, 33);
		contentPane.add(lblFirstName);
		
		textfn = new JTextField();
		textfn.setColumns(10);
		textfn.setBounds(184, 171, 164, 33);
		contentPane.add(textfn);
		
		JLabel lblLastName = new JLabel("LAST NAME :");
		lblLastName.setForeground(Color.BLACK);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastName.setBounds(55, 238, 132, 33);
		contentPane.add(lblLastName);
		
		textln = new JTextField();
		textln.setColumns(10);
		textln.setBounds(184, 238, 164, 33);
		contentPane.add(textln);
		
		JLabel lblFirstName_1_1 = new JLabel("EMAIL :");
		lblFirstName_1_1.setForeground(Color.BLACK);
		lblFirstName_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstName_1_1.setBounds(55, 307, 132, 33);
		contentPane.add(lblFirstName_1_1);
		
		textemail = new JTextField();
		textemail.setColumns(10);
		textemail.setBounds(184, 307, 164, 33);
		contentPane.add(textemail);
		
		JLabel lblFirstName_1_1_1 = new JLabel("MOBILE :");
		lblFirstName_1_1_1.setForeground(Color.BLACK);
		lblFirstName_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstName_1_1_1.setBounds(55, 370, 132, 33);
		contentPane.add(lblFirstName_1_1_1);
		
		textmobile = new JTextField();
		textmobile.setColumns(10);
		textmobile.setBounds(184, 370, 164, 33);
		contentPane.add(textmobile);
		
		JButton btnNewButton = new JButton("INSERT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qry,firstname,lastname,email;
				int id,mobile;
				id =Integer.parseInt(textid.getText());
				firstname=textfn.getText();
				lastname=textln.getText();
				email=textemail.getText();
				mobile=	Integer.parseInt(textmobile.getText());
				
		
				qry = "insert into student(ID,FirstName,LastName,Email,Mobile)values(?,?,?,?,?) ";
				try {
					st = con.prepareStatement(qry);
					st.setInt(1, id);
					st.setString(2, firstname);
					st.setString(3, lastname);
					st.setString(4, email);
					st.setInt(5, mobile);
					st.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Addedddd...!!!");
					t();
					
					textid.setText("");
					textfn.setText("");
					textln.setText("");
					textemail.setText("");
					textmobile.setText("");
					textid.requestFocus();
				}catch(Exception r) {
					System.out.println(r);
				}

			
			}	
		});
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(0, 255, 255));
		btnNewButton.setBounds(55, 451, 107, 33);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int id;
				id =Integer.parseInt(textid.getText());
				try {
					  String qry="delete from Student where ID=?";
		     		    st=con.prepareStatement(qry);
		     		    st.setInt(1, id);
		     		    st.executeUpdate();
		    			textid.requestFocus();
		     			JOptionPane.showMessageDialog(null,"Record Deleteddd...!!!");
						t();
						
				}catch(Exception r) {
					System.out.println(r);
				}

			}
		});
		btnDelete.setForeground(Color.CYAN);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBackground(Color.BLACK);
		btnDelete.setBounds(575, 451, 107, 33);
		contentPane.add(btnDelete);
		
		JScrollPane table_1 = new JScrollPane();
		table_1.setBounds(408, 114, 612, 289);
		contentPane.add(table_1);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "FisrtName", "LastName", "Gmail", "Mobile"
			}
		));
		table_1.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 0, 128));
		panel_1.setBounds(-12, 0, 1064, 79);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CRUD OPERATION");
		lblNewLabel_1.setBounds(425, 18, 236, 61);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setBackground(new Color(0, 255, 255));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_2.setBackground(new Color(255, 0, 128));
		panel_2.setForeground(new Color(255, 0, 128));
		panel_2.setBounds(0, 509, 1052, 36);
		contentPane.add(panel_2);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qry,firstname,lastname,email;
				int id,mobile;
				id =Integer.parseInt(textid.getText());
				firstname=textfn.getText();
				lastname=textln.getText();
				email=textemail.getText();
				mobile=	Integer.parseInt(textmobile.getText());
				
		
				qry = "update student set FirstName = ?,LastName=?,Email=?,Mobile=? where ID=? ";
				try {
					st = con.prepareStatement(qry);
	
					st.setString(1, firstname);
					st.setString(2, lastname);
					st.setString(3, email);
					st.setInt(4, mobile);
					st.setInt(5, id);
					st.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Updateddd...!!!");
					t();
					
					textid.setText("");
					textfn.setText("");
					textln.setText("");
					textemail.setText("");
					textmobile.setText("");
					textid.requestFocus();
				}catch(Exception r) {
					System.out.println(r);
				}

			}
		});
		btnUpdate.setForeground(Color.CYAN);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setBounds(239, 451, 107, 33);
		contentPane.add(btnUpdate);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(401, 451, 107, 33);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textid.getText();
					
					st=con.prepareStatement("select FirstName,LastName,Email,Mobile from student where id=?");
					st.setString(1, id);
					ResultSet rs = st.executeQuery();
					textid.requestFocus();
					
					if(rs.next() == true) {
						String FirstName  = rs.getString(1);
						String LastName  = rs.getString(2);
						String email=rs.getString(3);
						String mobile = rs.getString(4);
						
						textfn.setText(FirstName);
						textln.setText(LastName);
						textemail.setText(email);
						textmobile.setText(mobile);
						
					}else {
						textfn.setText("");
						textln.setText("");
						textemail.setText("");
						textmobile.setText("");
					}
					
				}catch(Exception o) {
					System.out.println(o);
				
				}
			}
		});
		btnSearch.setForeground(Color.CYAN);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSearch.setBackground(Color.BLACK);
	}
}
