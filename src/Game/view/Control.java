package Game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Game.database.Util;
import Game.fileserver.wFile;
import Game.point.Point2D;
import Game.sound.SoundEffect;
import Game.view.Puzzle;

public class Control {

	Color ColorButton = new Color(77, 100, 141);

	SoundEffect Sound = new SoundEffect();
	Puzzle pz;
	public int SIZE = 3;
	private JButton[][] matrix;
	private int move1 = 0;
	
	Util U;
	public boolean started = false;
	boolean isStartGame = true;
	int isStartGame1 = 0;
	int elapsedTime = 0; //thời gian trôi qua
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	Timer time;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);	
	String soundMove = "move.wav";

	public Control(Puzzle pz) {
		this.pz = pz;
		add();
	}
	
	public void StartGame() {

		time = new javax.swing.Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				elapsedTime = elapsedTime + 1000;
				hours = (elapsedTime / 3600000);
				minutes = (elapsedTime / 60000) % 60;
				seconds = (elapsedTime / 1000) % 60;
				seconds_string = String.format("%02d", seconds);
				minutes_string = String.format("%02d", minutes);
				hours_string = String.format("%02d", hours);
				pz.timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
			}
		});
		if (started == false) {
			started = true;
			pz.ButtonNew.setText("Reset");
			start();
		} else {
			started = false;
			// pz.ButtonNew.setText("Start");
			// reset();

		}
		this.isStartGame1 = 1;
		isStartGame = true;
		move1 = 0;
		pz.JTextCount.setText("0");
	}
	// reset game
	public void ResetGame() {
		add();
		reset();
		move1 = 0;
		pz.JTextCount.setText("0");
		pz.ButtonNew.setText("NewGame");
		this.isStartGame1 = 0;
		started = false;
	}
	//game mới
	public void NewGame() {
		add();
		mixButton();
		// move1 = 0;
		// pz.JTextCount.setText("0");
		// pz.ButtonNew.setText("Start");
		pz.ButtonNew.setText("Reset");
		// reset();
		this.isStartGame1 = 0;
	}

	void start() {
		time.start();
	}

	void stop() {
		if (elapsedTime != 0) {
			time.stop();

		}
	}

	void reset() {
		stop();
		elapsedTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		pz.timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
	}

	public void add() {
		String s = (String) pz.ComboBox.getSelectedItem(); // lay du lieu trong combobox													
		System.out.println(s);
		String[] Out = s.split("x"); // tach chuoi
		SIZE = Integer.parseInt(Out[0]);// chuyen tu string sang int
		//System.out.println(SIZE - 1);
		pz.jPanel1.removeAll();//xoá các thông tin trước nếu có
		pz.jPanel1.setLayout(new GridLayout(SIZE, SIZE, 1, 1));// tạo layout kích ỡ size*size
		pz.jPanel1.setPreferredSize(new Dimension(SIZE * 60, SIZE * 60));

		matrix = new JButton[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				JButton btn = new JButton(i * SIZE + j + 1 + "");// gán các giá trị cho từng button
				btn.setFont(new Font("MV Boli", Font.BOLD, 30));//cài phông chữ cho giá trị
				btn.setBackground(ColorButton);//cài màu sắc cho button
				btn.setFocusPainted(false);
				btn.setForeground(new Color(248, 248, 255));// đặt màu cho chữ/số
				matrix[i][j] = btn;//cho vào mảng 2 chiều
				pz.jPanel1.add(btn);// gọi đến giao diện ô để thêm các ô vào panel
				btn.addActionListener(new ActionListener() { //thêm sự kiện cho các ô 

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (isStartGame) {
							if (isStartGame1 != 0) {
								if (checkMove(btn)) {
									moveButton(btn);
									if (checkWin()) {
										// started = flase;
										stop();
										JOptionPane.showMessageDialog(null, "You Won");
										isStartGame = false;

										System.out.println(pz.timeLabel.getText());//in thời gian trên console
										System.out.println(pz.JTextCount.getText());//in số bước trên console
										//extracted();
										WirteFile();

									}
								}
							}
						}
						// else {
						// JOptionPane.showMessageDialog(null, "Press NewGame to
						// play");
						// }
					}
					/* ====== Đưa dữ liệu chiến thắng lên SQL sever*/
//					private void extracted() {
//						Util.insert(pz.timeLabel.getText(), pz.JTextCount.getText());
//					}
					/* =========== Đưa dữ liệu chiến thắng gồm thời gian và số lần clicks vào file txt*/
					private void WirteFile() {
						wFile.writeFile(pz.timeLabel.getText(), pz.JTextCount.getText());
					}
				});
			}
		}
		matrix[SIZE - 1][SIZE - 1].setText("");
		// matrix[0][0].setText("");
	}
	// lấy toạ độ ô trống
	public Point2D getEmptyPos() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (matrix[i][j].getText().equals("")) {
					return new Point2D(i, j);
				}
			}
		}
		return null;
	}
	// trộn button
	public void mixButton() {
		for (int k = 0; k < 500; k++) {
			Point2D p = getEmptyPos();
			Random r = new Random();
			int choice = r.nextInt(4);
			switch (choice) {
			case 0:
				if (p.getX() > 0) {
					matrix[p.getX()][p.getY()].setText(matrix[p.getX() - 1][p.getY()].getText());
					matrix[p.getX() - 1][p.getY()].setText("");
				}
				break;
			case 1:
				if (p.getY() < SIZE - 1) {
					matrix[p.getX()][p.getY()].setText(matrix[p.getX()][p.getY() + 1].getText());
					matrix[p.getX()][p.getY() + 1].setText("");
				}
				break;
			case 2:
				if (p.getX() < SIZE - 1) {
					matrix[p.getX()][p.getY()].setText(matrix[p.getX() + 1][p.getY()].getText());
					matrix[p.getX() + 1][p.getY()].setText("");
				}
				break;
			case 3:
				if (p.getY() > 0) {
					matrix[p.getX()][p.getY()].setText(matrix[p.getX()][p.getY() - 1].getText());
					matrix[p.getX()][p.getY() - 1].setText("");
				}
				break;
			}
		}
	}
	// check chiến thắng
	
	public boolean checkWin() {
		if (matrix[SIZE - 1][SIZE - 1].getText().equals("")) {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (i == SIZE - 1 && j == SIZE - 1) {
						return true;
					}
					if (!matrix[i][j].getText().equals(i * 3 + j + 1 + "")) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	// di chuyển button
	public void moveButton(JButton button) {
		Point2D p = getEmptyPos();
		matrix[p.getX()][p.getY()].setText(button.getText());
		button.setText("");
		move1++;
		pz.JTextCount.setText(move1 + "");
		Sound.setFile(soundMove);
		Sound.play();
	}
	// check buuton có thể di chuyển được không
	public boolean checkMove(JButton button) {
		if (button.getText().equals("")) {
			return false;
		}
		Point2D p = getEmptyPos();
		Point2D clickedPoint = null;

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (matrix[i][j].getText().equals(button.getText())) {
					clickedPoint = new Point2D(i, j);
				}
			}
		}

		if (p.getX() == clickedPoint.getX() && Math.abs(p.getY() - clickedPoint.getY()) == 1) {
			return true;
		}

		if (p.getY() == clickedPoint.getY() && Math.abs(p.getX() - clickedPoint.getX()) == 1) {
			return true;
		}
		return false;
	}

}
