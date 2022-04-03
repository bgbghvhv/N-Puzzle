package Game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Game.sound.SoundEffect;
import Game.view.Puzzle;

public class main extends JFrame implements ActionListener {

	/**
	 * 
	 */
	
	JButton startButton = new JButton("PLAY GAME");
	JButton outButton = new JButton("EXIT");
	private static final long serialVersionUID = 1L;
	Puzzle pz;
	SoundEffect Sound = new SoundEffect();
	String click;
	public main(){
		
		this.setTitle("Puzzle Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100, 100);
		//this.setLayout(new BorderLayout());
		this.setSize(530, 700);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
		/* ============ StartButton =====================*/
		startButton.setFont(new Font("Aril", Font.BOLD, 20));
		startButton.setBackground(new Color(49,169,184));
		startButton.setForeground(new Color(248, 248, 255));
		startButton.setFocusPainted(false);
		startButton.setBorder(null);
		startButton.setBorderPainted(false);
		startButton.setBounds(181, 560, 150, 50);
		
		JLabel background=new JLabel(new ImageIcon("anh2.png"));
		background.setSize(530, 700);
		this.add(background);
		background.add(startButton);
		//background.add(outButton);
		//this.setVisible(true);
		this.setIconImage(new ImageIcon("Pic/logo.png").getImage());
		startButton.addActionListener(this);
		click = "soundgame.wav";
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new menuPz();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == startButton){
			pz = new Puzzle();
			this.setVisible(false);
			//pz.playMusic();
			Sound.setFile(click);
			Sound.play();
			Sound.loop();
		} 
		
	}
	
}
