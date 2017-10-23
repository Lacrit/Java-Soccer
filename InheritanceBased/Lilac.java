package zad2;

public class Lilac extends Flowers {

	public Lilac(int i) {
		super(i);
	}

	@Override
	public String toString() {
		return getName() + " ( " + getColor() + ", " + getAmount() + " ) ";
	}

	@Override
	public String getName() {
		return "lilac";
	}

	@Override
	public String getColor() {
		return "white";
	}

}