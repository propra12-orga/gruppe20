package game.bomberman;

import java.awt.Color;
import java.awt.Toolkit;

/**
 * Erzeugt Fenster in dem das Spielfeld erzeugt wird. Ausserdem wird eine
 * Highscore angezeigt und es gibt auswaehlbare Optionen im Menubar.
 * 
 * @author tiomzjx
 */
public class NewMyFrame extends javax.swing.JFrame {

	private ClickAction ca;

	/**
	 * Creates new form myFrameTest
	 * 
	 * @param Level
	 *            -String : Pfad der XML Datei des einzulesenden Levels
	 * */
	public NewMyFrame() {
		initComponents();
		this.setVisible(true);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 480) / 2);
		this.setResizable(false);
		this.jPanel1.requestFocus();
	}

	private void initComponents() {

		if (!Config.netGame && !MyFrame.doublePlayer) {
			side = new SidePanel();
			new Thread(side).start();
			side.setSize(88, 450);
			side.setBackground(new Color(238, 238, 238));
			side.setLocation(490, 35);
			this.add(side);
		}

		jPanel1 = new MyFrame();
		ca = new ClickAction(jPanel1, this);
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		playEditorMapMenuItem = new javax.swing.JMenuItem();
		saveMenuItem = new javax.swing.JMenuItem();
		saveAsMenuItem = new javax.swing.JMenuItem();
		exitMenuItem = new javax.swing.JMenuItem();
		edit = new javax.swing.JMenu();
		loadGame = new javax.swing.JMenuItem();
		newGame = new javax.swing.JMenuItem();
		computerModul = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		contentsMenuItem = new javax.swing.JMenuItem();
		aboutMenuItem = new javax.swing.JMenuItem();

		// setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

		playEditorMapMenuItem.setText("PlayEditorMap");
		playEditorMapMenuItem
				.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
					public void menuKeyPressed(
							javax.swing.event.MenuKeyEvent evt) {
						playEditorMapMenuItemMenuKeyPressed(evt);
					}

					public void menuKeyReleased(
							javax.swing.event.MenuKeyEvent evt) {
					}

					public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
					}
				});
		fileMenu.add(playEditorMapMenuItem);

		saveMenuItem.setText("save");
		saveMenuItem.addActionListener(ca);
		fileMenu.add(saveMenuItem);

		fileMenu.add(saveMenuItem);

		loadGame.setText("load Game");
		loadGame.addActionListener(ca);
		fileMenu.add(loadGame);

		// saveAsMenuItem.setText("Save as...");
		// saveAsMenuItem.addActionListener(ca);
		// fileMenu.add(saveAsMenuItem);

		exitMenuItem.setText("Exit");
		exitMenuItem.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				System.exit(0);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		// edit.setText("Edit");

		// newGame.setText("new Game");
		// newGame.addActionListener(ca);
		// edit.add(newGame);
		//
		// computerModul.setText("monster Game");
		// computerModul.addActionListener(ca);
		// edit.add(computerModul);

		menuBar.add(edit);

		helpMenu.setText("Help");

		contentsMenuItem.setText("Help Contents");
		helpMenu.add(contentsMenuItem);

		aboutMenuItem.setText("About");
		aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aboutMenuItemActionPerformed(evt);
			}
		});
		// helpMenu.add(aboutMenuItem);

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
	}// </editor-fold>
		// GEN-END:initComponents

	private void playEditorMapMenuItemMenuKeyPressed(
			javax.swing.event.MenuKeyEvent evt) {
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
	private javax.swing.JMenuItem playEditorMapMenuItem;
	private javax.swing.JMenuItem saveAsMenuItem;
	private javax.swing.JMenuItem saveMenuItem;
	MyFrame jPanel1;
	SidePanel side;
	// End of variables declaration//GEN-END:variables

}