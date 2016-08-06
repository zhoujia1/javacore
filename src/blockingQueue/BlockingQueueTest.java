package blockingQueue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

	public static void main(String[] args) {
		
	}
	
}

class FileEnumerationTask implements Runnable {

	public static File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startingDirectory;
	
	public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory){
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void enumerate(File directory){
		
	}
	
}
