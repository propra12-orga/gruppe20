package game.bomberman;

import java.awt.Toolkit;

public class NewMapEditor extends javax.swing.JFrame {
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				NewMapEditor m = new NewMapEditor();
				// ObjectContainer.cm = m;
				// ChooseMenu.main = m;
				m.setLocationRelativeTo(null);
				m.setVisible(true);
			}
		});
	}

	private ClickAction ca;

	/** Creates new form myFrameTest */
	public NewMapEditor() {
		initComponents();
		this.setVisible(true);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 480) / 2);
		this.setResizable(false);
		this.jPanel1.requestFocus();
	}

	private void initComponents() {

		jPanel1 = new MapEditor();
		// ca = new ClickAction(jPanel1, this);
		// menuBar = new javax.swing.JMenuBar();
		// fileMenu = new javax.swing.JMenu();
		// openMenuItem = new javax.swing.JMenuItem();
		// saveMenuItem = new javax.swing.JMenuItem();
		// saveAsMenuItem = new javax.swing.JMenuItem();
		// exitMenuItem = new javax.swing.JMenuItem();
		// edit = new javax.swing.JMenu();
		// loadGame = new javax.swing.JMenuItem();
		// newGame = new javax.swing.JMenuItem();
		// computerModul = new javax.swing.JMenuItem();
		// helpMenu = new javax.swing.JMenu();
		// contentsMenuItem = new javax.swing.JMenuItem();
		// aboutMenuItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 476,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 499,
				Short.MAX_VALUE));

		fileMenu.setText("File");

		openMenuItem.setText("Open");
		openMenuItem
				.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
					public void menuKeyPressed(
							javax.swing.event.MenuKeyEvent evt) {
						openMenuItemMenuKeyPressed(evt);
					}

					public void menuKeyReleased(
							javax.swing.event.MenuKeyEvent evt) {
					}

					public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
					}
				});
		fileMenu.add(openMenuItem);

		saveMenuItem.setText("Save");

		fileMenu.add(saveMenuItem);

		saveAsMenuItem.setText("Save as...");
		saveAsMenuItem.addActionListener(ca);
		fileMenu.add(saveAsMenuItem);

		exitMenuItem.setText("Exit");
		exitMenuItem.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				exitMenuItemKeyPressed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		edit.setText("Edit");

		loadGame.setText("load Game");
		loadGame.addActionListener(ca);
		edit.add(loadGame);

		newGame.setText("new Game");
		newGame.addActionListener(ca);
		edit.add(newGame);

		computerModul.setText("computer Game");
		computerModul.addActionListener(ca);
		edit.add(computerModul);

		menuBar.add(edit);

		helpMenu.setText("Help");

		contentsMenuItem.setText("Contents");
		helpMenu.add(contentsMenuItem);

		aboutMenuItem.setText("About");
		aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aboutMenuItemActionPerformed(evt);
			}
		});
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addComponent(jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(126, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addComponent(jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		pack();
	}

	private void openMenuItemMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
		// TODO add your handling code here:
	}

	private void exitMenuItemKeyPressed(java.awt.event.KeyEvent evt) {
		System.exit(0);
	}

	private void loadGameActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling co
	}

	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JMenuItem computerModul;
	private javax.swing.JMenuItem contentsMenuItem;
	private javax.swing.JMenu edit;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenu helpMenu;

	private javax.swing.JMenuItem loadGame;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem newGame;
	private javax.swing.JMenuItem openMenuItem;
	private javax.swing.JMenuItem saveAsMenuItem;
	private javax.swing.JMenuItem saveMenuItem;
	MapEditor jPanel1;
}
