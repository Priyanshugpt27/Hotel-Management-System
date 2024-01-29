package Hotalmanagement;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hotalmanagement extends JFrame implements ActionListener {
	Hotalmanagement() {
		setSize(1366, 565);
		setLocation(100, 100);
		setLayout(null);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("first.jpg"));
		JLabel image = new JLabel(i1);
		image.setBounds(0, 0, 1366, 565); // locationX , locationY, length , breadth
		add(image);

		JLabel text = new JLabel("HOTEL MANAGEMENT SYSTEM");
		text.setBounds(20, 430, 1000, 90);
		text.setForeground(Color.white);
		text.setFont(new Font("serit", Font.PLAIN, 50));
		image.add(text);

		JButton next = new JButton("Next");
		next.setBounds(1150, 450, 150, 50);
		next.setBackground(Color.white);
		next.setForeground(Color.MAGENTA);
		next.addActionListener(this);
		next.setFont(new Font("serif", Font.PLAIN, 24));
		image.add(next);

		setVisible(true);

		while (true) {
			text.setVisible(false);
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			text.setVisible(true);
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Hotalmanagement();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		setVisible(false);
		new Login();
	}

}
