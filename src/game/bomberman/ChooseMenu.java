package game.bomberman;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 * 
 * @author timozjx
 */

public class ChooseMenu extends javax.swing.JFrame implements Runnable {

	public ChooseMenu() {
		initComponents();
		this.setSize(480, 550);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 480) / 2);
		this.setResizable(false);
	}

	private ClickAction ck;

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		ck = new ClickAction();
		jButton3.addActionListener(ck);
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		openMenuItem = new javax.swing.JMenuItem();
		saveMenuItem = new javax.swing.JMenuItem();
		saveAsMenuItem = new javax.swing.JMenuItem();

		exitMenuItem = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();
		cutMenuItem = new javax.swing.JMenuItem();
		copyMenuItem = new javax.swing.JMenuItem();
		pasteMenuItem = new javax.swing.JMenuItem();
		deleteMenuItem = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		contentsMenuItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButton1.setText("double game");
		jButton1.addActionListener(new ClickAction());
		jButton2.setText("single game");
		jButton2.addActionListener(new ClickAction());
		jButton3.setText("online game");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(197, 197, 197)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jButton3)
														.addComponent(jButton1)
														.addComponent(jButton2))
										.addContainerGap(215, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addGap(184, 184, 184)
						.addComponent(jButton2).addGap(18, 18, 18)
						.addComponent(jButton1).addGap(18, 18, 18)
						.addComponent(jButton3)
						.addContainerGap(156, Short.MAX_VALUE)));

		fileMenu.setText("start menu");
		JMenuItem open = new JMenuItem("load game");
		open.addActionListener(new ClickAction());
		fileMenu.add(open);
		openMenuItem.setText("new Server");
		openMenuItem.addActionListener(new ClickAction());
		fileMenu.add(openMenuItem);

		saveMenuItem.setText("enter the game");
		saveMenuItem.setEnabled(false);
		saveMenuItem.addActionListener(new ClickAction());
		fileMenu.add(saveMenuItem);
		saveAsMenuItem.setText("start online game");
		saveAsMenuItem.addActionListener(new ClickAction());
		saveAsMenuItem.setEnabled(false);
		fileMenu.add(saveAsMenuItem);
		JMenuItem save = new JMenuItem("save");
		fileMenu.add(save);

		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		editMenu.setText("Edit");

		cutMenuItem.setText("Cut");
		editMenu.add(cutMenuItem);

		copyMenuItem.setText("Copy");
		editMenu.add(copyMenuItem);

		pasteMenuItem.setText("Paste");
		editMenu.add(pasteMenuItem);

		deleteMenuItem.setText("Delete");
		editMenu.add(deleteMenuItem);

		menuBar.add(editMenu);

		helpMenu.setText("Help");

		contentsMenuItem.setText("Contents");
		helpMenu.add(contentsMenuItem);

		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		pack();
	}

	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitMenuItemActionPerformed
		System.exit(0);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				ChooseMenu m = new ChooseMenu();
				ObjectContainer.cm = m;
				ChooseMenu.main = m;
				m.setLocationRelativeTo(null);
				m.setVisible(true);
			}
		});
	}

	private javax.swing.JMenuItem contentsMenuItem;
	private javax.swing.JMenuItem copyMenuItem;
	private javax.swing.JMenuItem cutMenuItem;
	private javax.swing.JMenuItem deleteMenuItem;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem openMenuItem;
	private javax.swing.JMenuItem pasteMenuItem;
	private javax.swing.JMenuItem saveAsMenuItem;
	private javax.swing.JMenuItem saveMenuItem;
	private static ChooseMenu main;
	private JTextField text;
	private boolean find;
	private boolean bar = false;
	private boolean wait = false;
	public OnlineClient olc;
	public OnlineServer olserver;
	public static NewMyFrame newmf;

	class ClickAction implements ActionListener {
		Dialog dia = null;

		@Override
		public void actionPerformed(ActionEvent e) {
			Graphics g = main.jPanel1.getGraphics();
			if (e.getActionCommand().equals("load game")) {

			} else if (e.getActionCommand().equals("double game")) {
				System.out.println("double game");
				main.setVisible(false);
				main = null;
				NewMyFrame doublegame = new NewMyFrame();
				doublegame.jPanel1.doublePlayer = true;

			} else if (e.getActionCommand().equals("single game")) {
				main.setVisible(false);
				main = null;
				new NewMyFrame();
			} else if (e.getActionCommand().equals("online game")) {

				main.jButton1.setVisible(false);
				main.jButton2.setVisible(false);
				main.jButton3.setVisible(false);
				dia = new inputDialog();
				dia.setVisible(true);

			} else if (e.getActionCommand().equals("confirm")) {
				dia.setVisible(false);
				olc = new OnlineClient();
				ObjectContainer.olclient = olc;
				bar = true;
				new Thread(main).start();
				find = olc.connect(text.getText());
				new Thread(olc).start();
				wait = false;
				System.out.println(find);
				if (find)
					saveMenuItem.setEnabled(true);
			} else if (e.getActionCommand().equals("new Server")) {
				main.setTitle("I am Server");
				olserver = new OnlineServer();
				ObjectContainer.olserver = olserver;
				new Thread(olserver).start();
				main.jButton1.setVisible(false);
				main.jButton2.setVisible(false);
				main.jButton3.setVisible(false);
				wait = true;

				new Thread(main).start();
			} else if (e.getActionCommand().equals("enter the game")) {
				saveMenuItem.setEnabled(false);
				g.setColor(Color.red);
				g.drawString("wait for the server to start game...", 170, 260);
				// join for :send the message to server to ask for a new game
				olc.send("join");
				System.out.println("ask for the server to start a new game");
			} else if (e.getActionCommand().equals("start online game")) {
				olserver.send("start");
				main.setVisible(false);

				newmf = new NewMyFrame();
				ObjectContainer.newmf = newmf;
				newmf.jPanel1.netGame();
				// die Methoden in Server aufzurufen
				olserver.process = ObjectContainer.process;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	class inputDialog extends Dialog {
		public JTextField t;

		public inputDialog() {
			super(ChooseMenu.this, true);
			this.setLayout(new FlowLayout());
			JTextField t = new JTextField(20);
			text = t;
			this.add(new JLabel("Server IP:"));
			this.add(t);
			// this.setSize(200, 100);
			JButton b = new JButton("confirm");
			b.addActionListener(ck);
			this.add(b);
			this.pack();
			this.setLocationRelativeTo(main);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					setVisible(false);

				}
			});

		}
	}

	@Override
	public void run() {

		Graphics g = main.jPanel1.getGraphics();
		while (bar) {

			Font font = new Font("", Font.BOLD, 15);
			g.setFont(font);
			String str = "waiting for the server...";
			g.drawString(str, 180, 100);
			g.setColor(Color.black);
			g.fillRect(140, 120, 200, 15);
			g.setColor(Color.green);
			for (int i = 0; i < 100; i++) {
				if (8 * i >= 200)
					break;
				g.fillRect(140 + 8 * i, 120, 6, 15);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
				}
			}
			g.setColor(Color.black);
			if (find) {
				g.drawString("Server£º" + text.getText(), 160, 200);
			} else
				g.drawString("can't find the Server", 180, 200);
			bar = false;
		}
		while (wait) {
			g.setColor(Color.red);
			g.drawString("server is starting,wait for other player...", 150,
					150);
			// System.out.println("olserver.join " + olserver.join);
			if (olserver.join) {
				g.setColor(Color.black);
				g.drawString(
						"player enter the online game,please choose the menu start online game...",
						130, 180);
				main.saveAsMenuItem.setEnabled(true);

			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}