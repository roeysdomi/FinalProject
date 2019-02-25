package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;

import com.sun.org.apache.xpath.internal.functions.Function;

import communication.Textnow;
import functions.Functions;
import functions.Save;
import keyboard_func.SetUp;
import keyboard_func.Start;
import lc.kra.system.keyboard.example.KeyboardFunc;
import lock.Main;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class Gui extends MouseAdapter{

	public static JFrame frame;
	public static JButton Startbut;
	public static JLabel lblNewLabel;
	private JTextField SU_name;
	private JTextField SU_textuser;
	//public static JTextArea  txtme;
	private Point mouseDownCompCoords = null;
	public static JTextArea textArea;
	private JTextField DA_textuser;
	private JTextField DA_password;
	////-------------------
	public static JButton quickrec;
	public static JButton preventrec;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
		 this.frame = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public Gui(JFrame frame) {
       
    }
	 public void mouseReleased(MouseEvent e) {
         mouseDownCompCoords = null;
     }

     public void mousePressed(MouseEvent e) {
         mouseDownCompCoords = e.getPoint();
     }

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 643, 615);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		 FrameDragListener frameDragListener = new FrameDragListener(frame);
         frame.addMouseListener(frameDragListener);
         frame.addMouseMotionListener(frameDragListener);
		
		///-------------------
	    if(!SystemTray.isSupported()){
	        System.out.println("System tray is not supported !!! ");
	        return ;
	    }
	    SystemTray systemTray = SystemTray.getSystemTray();
	    Image image = Toolkit.getDefaultToolkit().getImage("images\\EXIT.png");
	    PopupMenu trayPopupMenu = new PopupMenu();
	    TrayIcon trayIcon = new TrayIcon(image, "BioType", trayPopupMenu);
	    trayIcon.setImageAutoSize(true);
	    trayIcon.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	frame.setVisible(true);          
	        }
	    });	
	    try{
	        systemTray.add(trayIcon);
	        
	    }catch(AWTException awtException){
	        awtException.printStackTrace();
	    }
		////-------------------
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(0, 0, 288, 615);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		 lblNewLabel = new JLabel("");
		lblNewLabel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
			}
		});
		lblNewLabel.setBounds(41, 284, 185, 249);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("images\\Final project3.png"));
		
		 Startbut = new JButton("START");
		Startbut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Startbut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Startbut.getText().equals("START"))
				{
					Startbut.setText("STOP");
					textArea.append(System.lineSeparator());
					
					textArea.append("\n START PROGRAM ");
					Start s=new Start();
				    Thread t=new Thread(s);
				     t.start();
					
					lblNewLabel.setIcon(new ImageIcon("images\\Final project2.png"));
				}
				else
				{
					Start.run=false;
					Textnow txt=new Textnow();
					try {
						txt.sendmess(Main.manager, "someone  shutdown the program");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					textArea.append("\n Stop PROGRAM ");
					Startbut.setText("START");
					lblNewLabel.setIcon(new ImageIcon("images\\Final project3.png"));
				}
				
			}
		});
		Startbut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		Startbut.setBackground(new Color(250, 128, 114));
		Startbut.setForeground(new Color(245, 255, 250));
		Startbut.setBounds(41, 27, 185, 50);
		Startbut.setFocusPainted(false);
		panel.add(Startbut);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		textArea.setForeground(new Color(255, 204, 102));
		textArea.setBackground(new Color(47, 79, 79));
	
		textArea.setBounds(41, 93, 185, 155);
		textArea.setAutoscrolls(true);
		JScrollPane js=new JScrollPane(textArea);
		js.setViewportBorder(null);
		js.setBounds(15, 93, 244, 155);
		js.setBorder(new EmptyBorder(0, 0, 0, 0));
		js.setOpaque(false);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		textArea.setEditable(false);
		textArea.setBorder(null);
		//textArea.setOpaque(false);
		
		
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		textArea.setCaretPosition(textArea.getDocument().getLength());
		panel.add(js);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(255, 255, 255));
		separator_2.setForeground(new Color(255, 255, 255));
		separator_2.setBounds(28, 260, 221, 62);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBackground(Color.WHITE);
		separator_3.setBounds(51, 549, 175, 50);
		panel.add(separator_3);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		separator_6.setBackground(Color.WHITE);
		separator_6.setBounds(83, 565, 106, 50);
		panel.add(separator_6);
		
		JPanel panel2 = new JPanel();
		panel2.setForeground(new Color(245, 255, 250));
		panel2.setBackground(new Color(205, 92, 92));
		panel2.setBounds(287, 0, 625, 626);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(0, 0, 625, 46);
		panel2.add(panel3);
		panel3.setBackground(new Color(255, 196, 0));
		panel3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Set Up:");
		lblNewLabel_1.setBounds(15, 0, 154, 46);
		panel3.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		
		JButton button_1 = new JButton("");
		button_1.setBorder(null);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				frame.setVisible(false);
			}
		});
		button_1.setBounds(310, 0, 46, 46);
		panel3.add(button_1);
		button_1.setIcon(new ImageIcon("C:\\Users\\Roey\\eclipse-workspace\\system\\images\\EXIT.jpg"));
		
		SU_name = new JTextField();
		SU_name.setHorizontalAlignment(SwingConstants.CENTER);
		SU_name.setForeground(new Color(255, 255, 255));
		SU_name.setFont(new Font("Century Gothic", Font.BOLD, 16));
		SU_name.setBounds(27, 83, 126, 26);
		SU_name.setBorder(null);
		SU_name.setOpaque(false);
		
		panel2.add(SU_name);
		SU_name.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(245, 255, 250));
		separator.setBounds(22, 112, 131, 19);
		panel2.add(separator);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(53, 62, 69, 20);
		panel2.add(lblName);
		
		SU_textuser = new JTextField();
		SU_textuser.setHorizontalAlignment(SwingConstants.CENTER);
		SU_textuser.setOpaque(false);
		SU_textuser.setForeground(Color.WHITE);
		SU_textuser.setFont(new Font("Century Gothic", Font.BOLD, 16));
		SU_textuser.setColumns(10);
		SU_textuser.setBorder(null);
		SU_textuser.setBounds(210, 83, 126, 26);
		panel2.add(SU_textuser);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(245, 255, 250));
		separator_1.setBounds(208, 112, 131, 19);
		panel2.add(separator_1);
		
		JLabel lblTextUser = new JLabel("Text user:");
		lblTextUser.setForeground(Color.WHITE);
		lblTextUser.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblTextUser.setBounds(239, 62, 69, 20);
		panel2.add(lblTextUser);
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBackground(new Color(255, 216, 86));
		panel4.setBounds(0, 147, 625, 46);
		panel2.add(panel4);
		
		JLabel lblQuickAccess = new JLabel("Quick Access:");
		lblQuickAccess.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblQuickAccess.setBounds(15, 0, 154, 46);
		panel4.add(lblQuickAccess);
		
		JPanel panel5 = new JPanel();
		panel5.setLayout(null);
		panel5.setBackground(new Color(255, 226, 133));
		panel5.setBounds(0, 275, 625, 46);
		panel2.add(panel5);
		
		JLabel lblPreventAccess = new JLabel("Prevent Access:");
		lblPreventAccess.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblPreventAccess.setBounds(15, 0, 191, 46);
		panel5.add(lblPreventAccess);
		
		JLabel label = new JLabel("");
		label.setBounds(95, 16, 329, 341);
		panel5.add(label);
		label.setIcon(new ImageIcon("C:\\Users\\Roey\\eclipse-workspace\\system\\images\\icon-blk-tech.png"));
		
		JButton quickbut = new JButton("Start recording!");
		quickrec=quickbut;
		quickbut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		quickbut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(quickbut.getText().equals("Start recording!"))
				{
					quickbut.setText("stop recording!");
					SetUp ro=new SetUp();
					System.out.println(SU_name.getText().length());
					
					if(SU_name.getText().length()==0)
					{
						textArea.append("insert values first");
						quickbut.setText("Start recording!");
						return;
					}
					Thread t=new Thread(ro);
					ro.type=1;
					ro.name=SU_name.getText();
					ro.user=SU_textuser.getText();
					t.start();
				   // ro.reckeyboard();
				    
				    return;
				   // quickbut.setText("Start recording!");
				    
				}
				else
				{
					quickbut.setText("Start recording!");
				}
			}
		});
		quickbut.setForeground(new Color(245, 255, 250));
		quickbut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		quickbut.setFocusPainted(false);
		quickbut.setBackground(new Color(255, 160, 122));
		quickbut.setBounds(85, 209, 179, 50);
		quickbut.setBorder(null);
		panel2.add(quickbut);
		
		JButton preventbut = new JButton("Start recording!");
		preventrec=preventbut;
		preventbut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		preventbut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(preventbut.getText().equals("Start recording!"))
				{
					preventbut.setText("stop recording!");
					if(SU_name.getText().length()==0)
					{
						textArea.append("insert values first");
						preventbut.setText("Start recording!");
						return;
					}
					SetUp ro=new SetUp();
					ro.type=2;
					ro.name=SU_name.getText();
					ro.user=SU_textuser.getText();
				    ro.reckeyboard();
				    Save s=new Save();
					try {
						s.saveobj(Main.hmap);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					preventbut.setText("Start recording!");

				}
				else
				{
					preventbut.setText("Start recording!");
				}
			}
		});
		preventbut.setForeground(new Color(245, 255, 250));
		preventbut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		preventbut.setFocusPainted(false);
		preventbut.setBorder(null);
		preventbut.setBackground(new Color(255, 160, 122));
		preventbut.setBounds(85, 337, 179, 50);
		panel2.add(preventbut);
		
		JLabel lblTextUser_1 = new JLabel("Text user:");
		lblTextUser_1.setForeground(Color.WHITE);
		lblTextUser_1.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblTextUser_1.setBounds(53, 530, 95, 20);
		panel2.add(lblTextUser_1);
		
		DA_textuser = new JTextField();
		DA_textuser.setOpaque(false);
		DA_textuser.setHorizontalAlignment(SwingConstants.CENTER);
		DA_textuser.setForeground(Color.WHITE);
		DA_textuser.setFont(new Font("Century Gothic", Font.BOLD, 16));
		DA_textuser.setColumns(10);
		DA_textuser.setBorder(null);
		DA_textuser.setBounds(22, 551, 126, 26);
		panel2.add(DA_textuser);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(245, 255, 250));
		separator_4.setBounds(20, 580, 131, 19);
		panel2.add(separator_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 242, 197));
		panel_1.setBounds(0, 401, 625, 46);
		panel2.add(panel_1);
		
		JLabel lblDeleteAccount = new JLabel("Delete Account :");
		lblDeleteAccount.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblDeleteAccount.setBounds(15, 0, 191, 46);
		panel_1.add(lblDeleteAccount);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblPassword.setBounds(232, 530, 102, 20);
		panel2.add(lblPassword);
		
		DA_password = new JTextField();
		DA_password.setOpaque(false);
		DA_password.setHorizontalAlignment(SwingConstants.CENTER);
		DA_password.setForeground(Color.WHITE);
		DA_password.setFont(new Font("Century Gothic", Font.BOLD, 16));
		DA_password.setColumns(10);
		DA_password.setBorder(null);
		DA_password.setBounds(208, 551, 126, 26);
		panel2.add(DA_password);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(new Color(245, 255, 250));
		separator_5.setBounds(206, 580, 128, 19);
		panel2.add(separator_5);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user=DA_textuser.getText();
				String passw=DA_password.getText();
				if(user.isEmpty()||passw.isEmpty()) 
				{
					textArea.append("enter details first \n");return;
				}
				Functions.deleteuser(user, passw);
				
			}
		});
		btnDelete.setForeground(new Color(245, 255, 250));
		btnDelete.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnDelete.setFocusPainted(false);
		btnDelete.setBorder(null);
		btnDelete.setBackground(new Color(255, 160, 122));
		btnDelete.setBounds(85, 463, 179, 50);
		panel2.add(btnDelete);
		frame.dispose();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Color getTextAreaBackground() {
		return textArea.getBackground();
	}
	public void setTextAreaBackground(Color background) {
		textArea.setBackground(background);
	}
	////-------------------------------------------------------------
	public static class FrameDragListener extends MouseAdapter {

	    private final JFrame frame;
	    private Point mouseDownCompCoords = null;

	    public FrameDragListener(JFrame frame) {
	        this.frame = frame;
	    }

	    public void mouseReleased(MouseEvent e) {
	        mouseDownCompCoords = null;
	    }

	    public void mousePressed(MouseEvent e) {
	        mouseDownCompCoords = e.getPoint();
	    }

	    public void mouseDragged(MouseEvent e) {
	        Point currCoords = e.getLocationOnScreen();
	        frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
	    }
	}

}
