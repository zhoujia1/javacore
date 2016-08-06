package unsynch;

public class TransferRunnable implements Runnable {

	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 0;
	
	public TransferRunnable(Bank b, int from, double max){
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}
	
	//线程任务--转账
	public void run(){
		try {
			while(true){
				int toAccount = (int)(bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
