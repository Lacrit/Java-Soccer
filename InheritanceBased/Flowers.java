
public abstract class Flowers {

	private int amount;
	
	public int getAmount() {
		return amount;
	}	
	public abstract String getName(); 
	public abstract String getColor();
	
	public Flowers(int amount) {
		super();
		this.amount = amount;
	}	
	@Override
	public abstract String toString();
	
}
