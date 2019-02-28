package gui;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import functions.Save;
import lock.Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Popup {

	public  JFrame frame;
	private JTextField P_cookies;
	private JTextField P_name;
	private JTextField P_username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Popup window = new Popup();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Popup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JFrame parent = new JFrame();
       parent=frame;
       frame.getContentPane().setLayout(null);
       
       P_cookies = new JTextField();
       P_cookies.setColumns(10);
       P_cookies.setBounds(179, 34, 146, 26);
       frame.getContentPane().add(P_cookies);
       
       P_name = new JTextField();
       P_name.setColumns(10);
       P_name.setBounds(179, 89, 146, 26);
       frame.getContentPane().add(P_name);
       
       P_username = new JTextField();
       P_username.setColumns(10);
       P_username.setBounds(179, 145, 146, 26);
       frame.getContentPane().add(P_username);
       
       JLabel lblCookies = new JLabel("cookies:");
       lblCookies.setBounds(56, 37, 69, 20);
       frame.getContentPane().add(lblCookies);
       
       JLabel lblName = new JLabel("name:");
       lblName.setBounds(56, 92, 69, 20);
       frame.getContentPane().add(lblName);
       
       JLabel lblUsername = new JLabel("username:");
       lblUsername.setBounds(56, 148, 107, 20);
       frame.getContentPane().add(lblUsername);
       
       JButton btnOk = new JButton("ok!");
       btnOk.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       	}
       });
       btnOk.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		String source=P_cookies.getText();
       		String manager=P_username.getText();
       		Main.source= new File(source);
       		Main.manager=manager;
       		System.out.println("this is manager:"+manager);
       		Save s=new Save();
       		try {
				s.saveobj_byname(source, "source");
				s.saveobj_byname(manager, "m");
				frame.setVisible(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	}
       });
       btnOk.setBounds(151, 199, 115, 29);
       frame.getContentPane().add(btnOk);
 
	}

}
