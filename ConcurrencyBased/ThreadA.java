package ex1;


public class ThreadA extends Thread {

	
	public  Synchronize syn;
	public ThreadA(Synchronize syn){
		this.syn = syn;
	}


	@Override
	public void run() {
		syn.read();	
		//System.out.println(syn.isFlag());
	}	
	}

// worse implementation 



//	private Good good;
//	private List<Good> list = new ArrayList<>();
//	
//	public List<Good> getList() {
//		return list;
//	}
//
//	@Override
//	public synchronized void run() {
//
//		FileReader fileReader = null;
//		try {
//			fileReader = new FileReader("../Goods.txt");
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		BufferedReader bufReader = new BufferedReader(fileReader);
//		String currentLine = "";
//		try {
//			while ((currentLine = bufReader.readLine()) != null) {
//				String[] words = currentLine.split("\\s");
//				for (int i = 0; i < words.length - 1; i += 2) {
//					good = new Good(Integer.parseInt(words[i]), Integer.parseInt(words[i + 1]));
//					list.add(good);
//					if (i+1 == 200) {
//						System.out.println("created " + i+1 + " objects");
//						this.notifyAll();
//						wait();
//					}
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//

