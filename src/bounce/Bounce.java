package bounce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bounce {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame frame = new BounceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class BounceFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	
	//跳球窗体
	public BounceFrame(){
		setTitle("Bounce");
		
		comp = new BallComponent();
		add(comp,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		
		//添加开始按钮
		addButton(buttonPanel, "Start", new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addBall();
			}
		});
		
		//添加关闭按钮
		addButton(buttonPanel, "Close", new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
		
	}
	
	//向容器中添加按钮
	public void addButton(Container c, String title, ActionListener listener){
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	//向面板中添加一个跳球，使其跳动1000次
	public void addBall(){
		try {
			Ball ball = new Ball();
			comp.add(ball);
			
			for(int i = 1; i <= STEPS; i++){
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
