
import java.util.List;
import java.util.ArrayList;

public class Customer {

	private String name;
	private double money;
	private ShoppingCart sc = new ShoppingCart();

	// getters, const
	public Customer(String name, int money) {
		this.name = name;
		this.money = money;
	}

	public String getName() {
		return name;
	}
	public void get(Flowers flowers) {
		sc.getList().add(flowers);
	}

	public ShoppingCart getShoppingCart() {
		return sc;
	}

	public double getCash() {
		return money;
	}

	// ...
	public void pack(Box box) {
		for (int i = 0; i < sc.getList().size(); i++) {
			box.getList().add(sc.getList().get(i));
		}
		sc.getList().removeAll(sc.getList());
	}

	public void pay() {
		List<Flowers> ind = new ArrayList<>();
		for (int i = 0; i < sc.getList().size(); i++) {
			if (PriceList.getInstance().getPrice(sc.getList().get(i).getName()) == null) {
				sc.getList().remove(i);
			}
		}
		for (int i = 0; i < sc.getList().size(); i++) {
			if (money - PriceList.getInstance().getPrice(sc.getList().get(i).getName())
					* sc.getList().get(i).getAmount() > 0) {
				money -= PriceList.getInstance().getPrice(sc.getList().get(i).getName())
						* sc.getList().get(i).getAmount();
			} else {
				ind.add(sc.getList().get(i));
			}
		}
		sc.getList().removeAll(ind);
	}
}
