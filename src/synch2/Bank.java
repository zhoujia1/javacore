package synch2;

public class Bank {

	private final double[] accounts;
	
	//��ʼ���˻��������˻�����������������
	public Bank(int n, double initialBalance){
		accounts = new double[n];
		
		for(int i = 0; i < accounts.length; i++){
			accounts[i] = initialBalance;
		}
	}
	
	//ת��
	public synchronized void transfer(int from, int to, double amount) throws InterruptedException{
		//�˻�����
		while(accounts[from] < amount){
			wait(); //�̵߳ȴ�����������״̬
		}
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total Balance:%10.2f%n", getTotalBalance());
		
		//�˻������ϸ
		/*for(int i = 0; i < accounts.length; i++){
			System.out.println("�˻�["+(i+1)+"]�����Ϊ:"+accounts[i]);
		}*/
		
		notifyAll(); //��������ڸö����ϵ���wait����������״̬
	}
	
	//��ȡ�˻��ʽ��ܶ�
	public synchronized double getTotalBalance(){
		double sum = 0;
		for (double a : accounts) {
			sum += a;
		}
		return sum;
	}
	
	//�˻�����
	public int size(){
		return accounts.length;
	}
	
}
