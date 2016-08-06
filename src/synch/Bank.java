package synch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

	private final double[] accounts;
	private Lock bankLock;
	private Condition sufficientFunds;
	
	//初始化账户数量及账户余额、对象锁、锁条件
	public Bank(int n, double initialBalance){
		accounts = new double[n];
		
		for(int i = 0; i < n; i++){
			accounts[i] = initialBalance;
		}
		
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition(); //返回该锁相关的条件对象
	}
	
	//转账
	public void transfer(int from, int to, double amount) throws InterruptedException{
		
		bankLock.lock();
		
		try {
			//账户余额不足
			while(accounts[from] < amount){
				sufficientFunds.await();
			}
			
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total Balance:%10.2f%n", amount, from, to);
			
			sufficientFunds.signalAll(); //解除等待集中的所有线程的阻塞状态
		} finally {
			bankLock.unlock();
		}
		
	}
	
	//获取账户资金总额
	public double getTotalBalance(){
		bankLock.lock();
		try {
			double sum = 0;
			for (double a : accounts) {
				sum += a;
			}
			return sum;
		} finally {
			bankLock.unlock();
		}
	}
	
	//账户数量
	public int size(){
		return accounts.length;
	}
	
}
