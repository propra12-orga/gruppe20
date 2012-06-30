package game.bomberman;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EndMenu extends JFrame implements ActionListener {

	private boolean yes = false;

	public boolean isYes() {
		return yes;
	}

	public EndMenu() {

		JFrame frame = new JFrame();
		frame.setLayout(null);

		JLabel title = new JLabel("Do you want play again?");

		JButton jButtonYes = new JButton("Yes");
		jButtonYes.addActionListener(this);

		JButton jButtonNo = new JButton("No");
		jButtonNo.addActionListener(this);

		frame.setSize(280, 123);
		title.setBounds(45, 5, 150, 20);
		jButtonYes.setBounds(10, 30, 80, 20);
		jButtonNo.setBounds(100, 30, 80, 20);

		frame.add(title);
		frame.add(jButtonYes);
		frame.add(jButtonNo);
		frame.setVisible(true);
		this.setResizable(false);

		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((width - 480) / 2, (height - 480) / 2);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 响应对话框中的按钮事件
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Yes")) {
			this.yes = true;
			new MyFrame("obstruction.xml");
		}

		if (e.getActionCommand().equals("No")) {
			System.exit(0);
		}

	}
}
