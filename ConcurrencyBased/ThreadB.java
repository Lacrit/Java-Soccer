package ex1;



public class ThreadB extends Thread {

	public Synchronize syn;

	public ThreadB(Synchronize syn) {
		this.syn = syn;
	
	}
	
	@Override
	public void run() {
		while(!syn.isFlag()) {	
			syn.sum();
			//System.out.println("2");
			if (syn.ready) {
				break;
			}
		}
		
	}
	
	
	// worse implementation 
	
	
	
	
	
	// private boolean flag;
	// private boolean flag1;
	// private long sum;
	// private List<Good> list = new ArrayList<>();
	//
	// public ThreadB(List<Good> list, boolean flag) {
	// this.list = list;
	// this.flag = flag;
	// }
	//
	// public void start() {
	// flag = true;
	// this.start();
	// }
	//
	// public boolean isFlag() {
	// return flag;
	// }
	//
	// public List<Good> getList() {
	// return list;
	// }
	//
	// @Override
	// public void run() {
	// for ( int i = 0; i < list.size(); i++ ) {
	// sum += list.get(i).weight;
	// if ( i+1 == 100 ) {
	// System.out.println("counted the weight of " + i+1 + " goods");
	// }
	// }
	// flag = false;
	//
	// }

}
