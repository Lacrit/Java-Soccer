
public class Rose extends Flowers {

	public Rose(int i) {
		super(i);
	}

	@Override
	public String toString() {
		return getName() + " ( " + getColor() + ", " + getAmount() + " ) ";
	}

	@Override
	public String getName() {		
		return "rose";
	}

	@Override
	public String getColor() {
		return "red";
	}

}
