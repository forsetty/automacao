package util;

import java.util.LinkedHashMap;

public class TestDataExcel implements TestData {
	private LinkedHashMap<String, String> hm;

	public TestDataExcel(String[][] items) {
		hm = new LinkedHashMap<String, String>();

		for (int i = 0; i < items[0].length; i++) {
			hm.put(items[0][i], items[1][i]);
		}
	}

	@Override
	public String get(String key) {
		String value = hm.get(key);
		return value;
	}

	@Override
	public void set(String key, String value) {
		hm.put(key, value);
	}

	@Override
	public boolean contains(String key) {
		return hm.containsKey(key);
	}

	@Override
	public String toString() {
		return hm.toString();
	}

}