package unsynch;

public class Bank {

	private final double[] accounts;
	
	//初始化账户数量及账户余额
	public Bank(int n, double initialBalance){
		accounts = new double[n];
		
		for(int i = 0; i < n; i++){
			accounts[i] = initialBalance;
		}
	}
	
	//转账
	public void transfer(int from, int to, double amount){
		//账户余额不足
		if(accounts[from] < amount)
			return;
		
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total Balance:%10.2f%n", amount, from, to);
	}
	
	//获取账户资金总额
	public double getTotalBalance(){
		double sum = 0;
		
		for(double a:accounts){
			sum += a;
		}
		
		return sum;
	}
	
	//账户数量
	public int size(){
		return accounts.length;
	}
	
}
