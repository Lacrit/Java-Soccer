package zad2;

import java.util.HashMap;
import java.util.Map;

public class PriceList {

	private static PriceList instance = null;
	private Map<String, Double> m = new HashMap<>();

	// getters, const
	public Map<String, Double> getM() {
		return m;
	}

	public Double getPrice(String string) {
		return m.get(string);
	}

	private PriceList() {
	}

	// ...
	public static PriceList getInstance() {
		if (instance == null) {
			instance = new PriceList();
		}
		return instance;
	}

	public void put(String string, double d) {
		m.put(string, d);
	}

}