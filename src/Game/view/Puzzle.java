package Game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Game.database.Util;
import Game.fileserver.wFile;
import Game.sound.SoundEffect;
import Game.view.Control;
import Game.view.PuzzleAction;

public class Puzzle extends JFrame implements ItemListener {
	
	Color ColorBac = new Color(208, 225, 249);
	Color ColorButton = new Color(40, 54, 85);
	Color ColorText = new Color(248, 248, 255);
	
	Font FontText = new Font("Arial", Font.PLAIN, 18);
	Font FontTitle = new Font("MV Boli", Font.BOLD, 25);
	SoundEffect Sound = new SoundEffect();
	JLabel title = new JLabel("PUZZLE GAME SIMPLE");
	JLabel timeLabel = new JLabel("00:00:00");
	JPanel jPanel1 = new JPanel();//khu vực các ô
	JPanel s2Panel = new JPanel();// chứa 2 button newgame và outgame
	JPanel SPanel = new JPanel();// khu vực chứa s1Panel, s2Panel và sPanel
	JPanel mPanel = new JPanel();// panel chính chứa tất cả các panel khác
	JPanel sPanel = new JPanel();// khu vực chứa dòng time
	JPanel s1Panel = new JPanel(); // khu vực chứa level và moves
	JLabel JTextCount = new JLabel();// khu vực chứa giá trị lượt di chuyểnS
	JButton ButtonOut = new JButton("OutGame");
	JButton ButtonNew = new JButton("NewGame");
	JComboBox<String> ComboBox = new JComboBox<>();
	JTextField field1 = new JTextField();
	Control c;
	Util U;
	
	private static final long serialVersionUID = 1L;

	public Puzzle() {
		this.setTitle("Puzzle Game");
		this.setSize(530, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("Pic/logo.png").getImage());

		JLabel fJLabel1 = new JLabel("Moves");
		JLabel fJLabel2 = new JLabel("Level");
		JLabel fJLabel3 = new JLabel("Time:");
		JTextCount = new JLabel("0");
		
		/*=========== Set Font =============*/
		title.setFont(FontTitle);
		timeLabel.setFont(FontText);
		JTextCount.setFont(FontText);
		fJLabel1.setFont(FontText);
		fJLabel2.setFont(FontText);
		fJLabel3.setFont(FontText);
		ButtonNew.setFont(FontText);
		ButtonOut.setFont(FontText);
		jPanel1.setBorder(null);

		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		// flowLayout.
		flowLayout.setHgap(30);
		flowLayout.setVgap(10);
		sPanel.setLayout(flowLayout);
		sPanel.add(fJLabel3);
		sPanel.add(timeLabel);
		/* S1 */
		s1Panel.setLayout(flowLayout);
		s1Panel.add(fJLabel2);
		s1Panel.add(ComboBox);
		s1Panel.add(fJLabel1);
		s1Panel.add(JTextCount);
		/* S2 */
		s2Panel.setLayout(flowLayout);
		s2Panel.add(ButtonNew);
		s2Panel.add(ButtonOut);
		BoxLayout boxlayout = new BoxLayout(SPanel, BoxLayout.Y_AXIS);
		SPanel.setLayout(boxlayout);
		
		// Tạo Border cho panel
		SPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
		
		SPanel.add(sPanel);
		SPanel.add(s1Panel);
		SPanel.add(s2Panel);
		
		title.setHorizontalAlignment(JTextField.CENTER);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		// Tạo Border cho panel
		SPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
		title.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

		/*========== Set Color ================*/
		sPanel.setBackground(ColorBac);
		s1Panel.setBackground(ColorBac);
		s2Panel.setBackground(ColorBac);
		SPanel.setBackground(ColorBac);
		mPanel.setBackground(ColorBac);
		
		this.ButtonNew.setBackground(ColorButton);
		this.ButtonOut.setBackground(ColorButton);
		this.ComboBox.setBackground(ColorButton);

		this.ButtonNew.setForeground(ColorText);
		this.ButtonOut.setForeground(ColorText);
		this.title.setForeground(ColorButton);
		this.ComboBox.setForeground(ColorText);

		ButtonNew.setFocusPainted(false);
		ButtonOut.setFocusPainted(false);
		ButtonNew.setBorderPainted(false);
		/* ========= Đọc dữ liệu từ SQL Server.
		 ArrayList<String> s = Util.loadDataToCombobox();
		for (String string : s) {
			ComboBox.addItem(string);
		}
		 */
		
		/* =========== Đọc dữu liệu từ File txt*/
		ArrayList<String> s = wFile.ReadFile();
		for (String string : s) {
			ComboBox.addItem(string);
		}

		jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		mPanel.setLayout(new BorderLayout());
		mPanel.add(title, BorderLayout.NORTH);
		mPanel.add(jPanel1, BorderLayout.CENTER);
		mPanel.add(SPanel, BorderLayout.SOUTH);
		mPanel.setBorder(null);

		this.setContentPane(mPanel);
		c = new Control(this);
		
		PuzzleAction p = new PuzzleAction(this);
		ButtonNew.addActionListener(p);
		ButtonOut.addActionListener(p);
		ComboBox.addItemListener(this);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		c.ResetGame();

	}
	
}
