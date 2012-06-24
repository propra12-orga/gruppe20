package game.bomberman;

import java.awt.Toolkit;

/**
 * 
 * @author timozjx
 */

public class ChooseMenu extends javax.swing.JFrame {

	public ChooseMenu() {
		initComponents();
		this.setSize(480, 550);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 480) / 2);
		this.setResizable(false);
	}

	private ClickAction ck;

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	// initComponents

	private void initComponents() {

		online = new java.awt.Button();

		single = new java.awt.Button();

		multi = new java.awt.Button();

		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		openMenuItem = new javax.swing.JMenuItem();
		newServer = new javax.swing.JMenuItem();

		enterGame = new javax.swing.JMenuItem();
		enterOnline = new javax.swing.JMenuItem();
		Exit = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();
		cutMenuItem = new javax.swing.JMenuItem();
		copyMenuItem = new javax.swing.JMenuItem();
		pasteMenuItem = new javax.swing.JMenuItem();
		deleteMenuItem = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		contentsMenuItem = new javax.swing.JMenuItem();
		aboutMenuItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(
				new org.netbeans.lib.awtextra.AbsoluteLayout());

		online.setActionCommand("online");
		online.setLabel("online");
		getContentPane().add(
				online,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 197,
						100, 30));

		single.setActionCommand("single");
		single.setLabel("single");
		single.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				singleActionPerformed(evt);
			}
		});
		single.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				singleKeyPressed(evt);
			}
		});
		getContentPane().add(
				single,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 70,
						30));

		multi.setActionCommand("multi");
		multi.setLabel("multi");
		getContentPane().add(
				multi,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 70,
						30));

		fileMenu.setText("File");

		openMenuItem.setText("Open");
		fileMenu.add(openMenuItem);

		newServer.setText("New server");
		newServer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newServerActionPerformed(evt);
			}
		});
		fileMenu.add(newServer);

		enterGame.setText("Enter game");
		fileMenu.add(enterGame);

		enterOnline.setText("Enter Online");
		enterOnline.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				enterOnlineActionPerformed(evt);
			}
		});
		enterOnline.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				enterOnlineKeyPressed(evt);
			}
		});
		fileMenu.add(enterOnline);

		Exit.setText("Exit");
		fileMenu.add(Exit);

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

		aboutMenuItem.setText("About");
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		pack();
	}

	private void newServerActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void singleKeyPressed(java.awt.event.KeyEvent evt) {

	}

	private void enterOnlineKeyPressed(java.awt.event.KeyEvent evt) {

	}

	private void singleActionPerformed(java.awt.event.ActionEvent evt) {

	}

	void enterOnlineActionPerformed(java.awt.event.ActionEvent evt) {

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ChooseMenu().setVisible(true);
			}
		});
	}

	private javax.swing.JMenuItem Exit;
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JMenuItem contentsMenuItem;
	private javax.swing.JMenuItem copyMenuItem;
	private javax.swing.JMenuItem cutMenuItem;
	private javax.swing.JMenuItem deleteMenuItem;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenuItem enterGame;
	private javax.swing.JMenuItem enterOnline;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JMenuBar menuBar;
	private java.awt.Button multi;
	private javax.swing.JMenuItem newServer;
	private java.awt.Button online;
	private javax.swing.JMenuItem openMenuItem;
	private javax.swing.JMenuItem pasteMenuItem;
	private java.awt.Button single;

}