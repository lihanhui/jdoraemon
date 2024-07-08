package io.doraemon.daemon;

public abstract class AbstractDoraemonServer {
    public void start(){
    	while(true){
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
