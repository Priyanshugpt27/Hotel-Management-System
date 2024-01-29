package Hotalmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UpdateCheck extends JFrame implements ActionListener {
	
	Choice ccustomer;
	JTextField tfroom,tfname,tfpaid,tfcheckin,tfpending;
	JButton check, update, back;
	
	
	UpdateCheck() {
		
		getContentPane().setBackground(Color.white);
		setLayout(null);
	    
		JLabel text = new JLabel ("Update Status");
		text.setBounds(90,20,200,30);
		text.setFont(new Font("Tahoma",Font.PLAIN,20));
		text.setForeground(Color.blue);
		add(text);
		
		JLabel lblid = new JLabel("Customer Id");
		lblid.setBounds(30, 80, 100, 20);
		add(lblid);

		ccustomer = new Choice();
		ccustomer.setBounds(200,80,150,25);
		add(ccustomer);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while(rs.next()) {
				ccustomer.add(rs.getString("number"));
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		JLabel lblroom = new JLabel("Room Number");
		lblroom.setBounds(30, 120, 100, 20);
		add(lblroom);
		
		tfroom = new JTextField();
		tfroom.setBounds(200,120,150,25);
		add(tfroom);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(30, 160, 100, 20);
		add(lblname);
		
		tfname = new JTextField();
		tfname.setBounds(200,160,150,25);
		add(tfname);
		
		JLabel lblcheckin = new JLabel("Checkin Time");
		lblcheckin.setBounds(30, 200, 100, 20);
		add(lblcheckin);
		
		tfcheckin = new JTextField();
		tfcheckin.setBounds(200,200,150,25);
		add(tfcheckin);
		
		JLabel lblpaid = new JLabel("Amount Paid");
		lblpaid.setBounds(30, 240, 100, 20);
		add(lblpaid);
		
		tfpaid = new JTextField();
		tfpaid.setBounds(200,240,150,25);
		add(tfpaid);
		
		JLabel lblpending = new JLabel("Pending Amount");
		lblpending.setBounds(30, 280, 100, 20);
		add(lblpending);
		
		tfpending = new JTextField();
		tfpending.setBounds(200,280,150,25);
		add(tfpending);
		
		check = new JButton("Check");
		check.setBackground(Color.black);
		check.setForeground(Color.white);
		check.addActionListener(this);
		check.setBounds(30, 340, 100, 30);
		add(check);
		
		update= new JButton("Update");
		update.setBackground(Color.black);
		update.setForeground(Color.white);
		update.addActionListener(this);
		update.setBounds(150, 340, 100, 30);
		add(update);

		back = new JButton("Back");
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.addActionListener(this);
		back.setBounds(270, 340, 100, 30);
		add(back);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("nine.jpg"));
		JLabel image = new JLabel(i1);
		image.setBounds(400,30,500,300);
		add(image);
		

		setBounds(300, 200, 980, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new UpdateCheck();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == check) {
			String id = ccustomer.getSelectedItem();
			String query = "select * from customer where number = '"+id+"'";
			try {
				Conn c = new Conn();
				ResultSet rs = c.s.executeQuery(query);
				while(rs.next()) {
					tfroom.setText(rs.getString("room"));
					tfname.setText(rs.getString("name"));
					tfcheckin.setText(rs.getString("checkintime"));
					tfpaid.setText(rs.getString("deposit"));
				}
				
				ResultSet rs2 = c.s.executeQuery("select * from room where roomnumber = '"+tfroom.getText()+"'");
				while(rs2.next()) {
					String price = rs2.getString("price");
					int amountPaid = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText());
					tfpending.setText("" + amountPaid);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if (ae.getSource() == update) {
			String number = ccustomer.getSelectedItem();
			String room = tfroom.getText();
			String name = tfname.getText();
			String checkin = tfcheckin.getText();
			String deposit = tfpaid.getText();
			
			try {
				Conn c = new Conn();
				c.s.executeUpdate("update customer set room = '"+room+"',name = '"+name+"',checkintime = '"+checkin+"',deposit = '"+deposit+"' where number = '"+number+"'");
				
				JOptionPane.showMessageDialog(null, "Data updated Successfully");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			setVisible(false);
			new Reception();
		}
	}

}