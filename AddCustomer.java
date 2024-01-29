package Hotalmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener {

	JComboBox comboid;
	JTextField tfnumber, tf1name, tf2country, tf3deposit;
	JRadioButton rmale, rfemale;
	Choice croom;
	JLabel checkintime;
	JButton add, back;

	AddCustomer() {

		getContentPane().setBackground(Color.white);
		setLayout(null);

		JLabel text = new JLabel("NEW CUSTOMER FORM");
		text.setBounds(100, 20, 300, 30);
		text.setFont(new Font("Raleway", Font.PLAIN, 20));
		add(text);

		JLabel lblid = new JLabel("ID");
		lblid.setBounds(35, 80, 100, 20);
		lblid.setFont(new Font("Raleway", Font.PLAIN, 20));
		add(lblid);

		String options[] = { "AAdhar Card", "Passport", "Driving License", "Voter-id card", "Ration card" };
		comboid = new JComboBox(options);
		comboid.setBounds(200, 80, 150, 25);
		comboid.setBackground(Color.white);
		add(comboid);

		JLabel lblnumber = new JLabel("Number");
		lblnumber.setBounds(35, 120, 100, 20);
		lblnumber.setFont(new Font("Raleway", Font.PLAIN, 20));
		add(lblnumber);

		tfnumber = new JTextField();
		tfnumber.setBounds(200, 120, 150, 25);
		add(tfnumber);

		JLabel lblname = new JLabel("Name");
		lblname.setBounds(35, 160, 100, 20);
		lblname.setFont(new Font("Raleway", Font.PLAIN, 20));
		add(lblname);

		tf1name = new JTextField();
		tf1name.setBounds(200, 160, 150, 25);
		add(tf1name);

		JLabel lblgender = new JLabel("Gender");
		lblgender.setBounds(35, 200, 100, 20);
		lblgender.setFont(new Font("Raleway", Font.PLAIN, 20));
		add(lblgender);

		rmale = new JRadioButton("Male");
		rmale.setBackground(Color.white);
		rmale.setBounds(200, 200, 60, 25);
		add(rmale);

		rfemale = new JRadioButton("Female");
		rfemale.setBackground(Color.white);
		rfemale.setBounds(270, 200, 100, 25);
		add(rfemale);

		JLabel lblcountry = new JLabel("Country");
		lblcountry.setBounds(35, 240, 100, 20);
		lblcountry.setFont(new Font("Raleway", Font.PLAIN, 20));
		add(lblcountry);

		tf2country = new JTextField();
		tf2country.setBounds(200, 240, 150, 25);
		add(tf2country);

		JLabel lblroom = new JLabel("Room Number");
		lblroom.setBounds(35, 280, 150, 20);
		lblroom.setFont(new Font("Raleway", Font.PLAIN, 20));
		add(lblroom);

		croom = new Choice();

		try {
			Conn conn = new Conn();
			String query = "select * from room where availbility = 'Available'";
			ResultSet rs = conn.s.executeQuery(query);
			while (rs.next()) {
				croom.add(rs.getString("roomnumber"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		croom.setBounds(200, 280, 150, 25);
		add(croom);

		JLabel lbltime = new JLabel("Checkin Time");
		lbltime.setBounds(35, 320, 150, 20);
		lbltime.setFont(new Font("Raleway", Font.PLAIN, 20));
		add(lbltime);

		Date date = new Date();

		JLabel checkintime = new JLabel("" + date);
		checkintime.setBounds(200, 320, 150, 25);
		checkintime.setFont(new Font("Raleway", Font.PLAIN, 15));
		add(checkintime);

		JLabel lbldeposit = new JLabel("Deposit");
		lbldeposit.setBounds(35, 360, 100, 20);
		lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 20));
		add(lbldeposit);

		tf3deposit = new JTextField();
		tf3deposit.setBounds(200, 360, 150, 25);
		add(tf3deposit);

		add = new JButton("Add");
		add.setBackground(Color.black);
		add.setForeground(Color.white);
		add.addActionListener(this);
		add.setBounds(50, 410, 120, 30);
		add(add);

		back = new JButton("Back");
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.addActionListener(this);
		back.setBounds(200, 410, 120, 30);
		add(back);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("fifth.png"));
		Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(400, 50, 300, 400);
		add(image);

		setBounds(350, 200, 800, 550);
		setVisible(true);

	}

	public static void main(String[] args) {
		new AddCustomer();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == add) {
			String id = (String) comboid.getSelectedItem();
			String number = tfnumber.getText();
			String name = tf1name.getText();
			String gender = null;

			if (rmale.isSelected()) {
				gender = "Male";
			} else {
				gender = "Female";
			}
			String country = tf2country.getText();
			String room = croom.getSelectedItem();
			String time = checkintime.getText();
			String deposit = tf3deposit.getText();

			try {
				String query = "insert into customer values('" + id + "','" + number + "','" + name + "','" + gender
						+ "','" + country + "','" + room + "','" + time + "','" + deposit + "')";

				String query2 = "update room set availbility = 'Occupied' where roomnumber = '"+room+"'";
				
				Conn conn = new Conn();
				conn.s.executeUpdate(query2);
				
				JOptionPane.showMessageDialog(null, "New Coustomer Added Successfully");
				
				setVisible(false);
				new Reception();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (ae.getSource() == back) {
			setVisible(false);
			new Reception();
			
		}

	}

}
