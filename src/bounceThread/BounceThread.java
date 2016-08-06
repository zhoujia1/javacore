package bounceThread;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bounce.Ball;
import bounce.BallComponent;

public class BounceThread {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame frame = new BounceFrame();
				frame.setTitle("BounceThread");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

//���������߳�
class BallRunnable implements Runnable{
	private Ball ball;
	private Component component;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	
	public BallRunnable(Ball aBall, Component aComponent){
		ball = aBall;
		component = aComponent;
	}
	
	@Override
	public void run() {
		try {
			for(int i = 1; i <= STEPS; i++){
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//������
class BounceFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private BallComponent comp;
	
	//������
	public BounceFrame(){
		comp = new BallComponent();
		add(comp,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		
		//��ӿ�ʼ��ť
		addButton(buttonPanel, "Start", new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addBall();
			}
		});
		
		//��ӹرհ�ť
		addButton(buttonPanel, "Close", new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	
	//����������Ӱ�ť
	public void addButton(Container c, String title, ActionListener listener){
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	//����������һ������ʹ������1000��
	public void addBall(){
		Ball ball = new Ball();
		comp.add(ball);
		Runnable r = new BallRunnable(ball, comp);
		Thread t = new Thread(r);
		t.start();
	}
}


