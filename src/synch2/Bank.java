package synch2;

public class Bank {

	private final double[] accounts;
	
	//初始化账户数量及账户余额、对象锁、锁条件
	public Bank(int n, double initialBalance){
		accounts = new double[n];
		
		for(int i = 0; i < accounts.length; i++){
			accounts[i] = initialBalance;
		}
	}
	
	//转账
	public synchronized void transfer(int from, int to, double amount) throws InterruptedException{
		//账户余额不足
		while(accounts[from] < amount){
			wait(); //线程等待，进入阻塞状态
		}
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total Balance:%10.2f%n", getTotalBalance());
		
		//账户余额明细
		/*for(int i = 0; i < accounts.length; i++){
			System.out.println("账户["+(i+1)+"]的余额为:"+accounts[i]);
		}*/
		
		notifyAll(); //解除所有在该对象上调用wait方法的阻塞状态
	}
	
	//获取账户资金总额
	public synchronized double getTotalBalance(){
		double sum = 0;
		for (double a : accounts) {
			sum += a;
		}
		return sum;
	}
	
	//账户数量
	public int size(){
		return accounts.length;
	}
	
}
