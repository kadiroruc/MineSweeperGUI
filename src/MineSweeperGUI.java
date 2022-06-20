import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MineSweeperGUI extends JFrame implements MouseListener{
	Buttons[][] buttons=new Buttons[10][10];
	int openButton=0;
	
	public MineSweeperGUI() {
		this.setSize(700,700);
		this.setTitle("Mine Sweeper");
		this.setLayout(new GridLayout(10,10));
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		
		for(int i=0;i<buttons.length;i++) {
			for(int j=0;j<buttons[0].length;j++) {
				Buttons b=new Buttons(i, j);
				b.setBackground(Color.black);
				b.setBorderPainted(true);
				this.add(b);
				b.addMouseListener(this);
				buttons[i][j]=b;
				
				
			}
		}
		
		
		
		
		generateMine();
		count();
		
		
		
		this.setVisible(true);
		

	}
	
	public void generateMine() {
		int i=0;
		Random random=new Random();
		while(i<15) {
			int randomRow=random.nextInt(10);
			int randomCol=random.nextInt(10);
			
			if(buttons[randomRow][randomCol].isMine()) {
				continue;
			}
			else {
				buttons[randomRow][randomCol].setMine(true);
			}
			i++;
			
		}
		
	}
	
	public void count() {
		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[0].length; col++) {
				if (buttons[row][col].isMine()) {
					counting(row, col);
				}
			}
		}
		
	}
	
	public void counting(int row,int col) {
		for(int i=row-1;i<=row+1;i++) {
			for(int j=col-1;j<=col+1;j++) {
				try {
					int temp=buttons[i][j].getCount();
					buttons[i][j].setCount(temp+1);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
	}
	
	public void print() {
		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[0].length; col++) {
				if (buttons[row][col].isMine()) {
					buttons[row][col].setIcon(new ImageIcon("mine.png"));
					buttons[row][col].setBackground(Color.white);
				} else {
					buttons[row][col].setText(buttons[row][col].getCount() + "");
					buttons[row][col].setEnabled(false);
					buttons[row][col].setBackground(Color.white);
				}
			}
		}
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		Buttons b = (Buttons) e.getComponent();
		if(e.getButton()==1) {
			if(b.isMine()) {
				print();
				JOptionPane.showMessageDialog(this,"GAME OVER!");
//				for(int i=0;i<buttons.length;i++) {
//					for(int j=0;j<buttons[0].length;j++) {
//						buttons[i][j].addMouseListener(null);
//					}
//				}
//				
				for(int i=0;i<buttons.length;i++) {
					for(int j=0;j<buttons[0].length;j++) {
						if(buttons[i][j].isMine()) {
							buttons[i][j].removeMouseListener(this);
						}
					}
				}
				

			}
			else {
				show(b.getRow(),b.getCol());
				openButton++;
				if (openButton == (buttons.length * buttons[0].length) - 10) {
					JOptionPane.showMessageDialog(this, "CONGRATULATIONS!");
					print();
				}
				
				
				
			}
			
		}
		else if(e.getButton()==3) {
			
			b.setFlag(!b.isFlag());
			if(b.isEnabled()) {
				if(b.isFlag()) {
					b.setIcon(new ImageIcon("flag.png"));
				}
				else {
					b.setIcon(null);
				}
			}
			
		}
		
	}
	
	public void show(int r, int c) {
		
		if(r<0||r>=buttons.length||c<0||c>=buttons.length||buttons[r][c].getText().length()>0||buttons[r][c].isEnabled()==false) {
			return;	
		}
		else if(buttons[r][c].getCount()!=0) {
			//String number=String.valueOf(buttons[r][c].getCount());
			buttons[r][c].setText(String.valueOf(buttons[r][c].getCount()));
			buttons[r][c].setEnabled(false);
			buttons[r][c].setBackground(Color.white);
			
		}
		else {
			buttons[r][c].setEnabled(false);
			buttons[r][c].setBackground(Color.white);
			
			show(r-1,c);
			show(r+1,c);
			show(r,c-1);
			show(r-1,c+1);
		}
		
		
	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
