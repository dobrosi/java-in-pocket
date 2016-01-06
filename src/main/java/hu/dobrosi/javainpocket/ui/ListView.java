package hu.dobrosi.javainpocket.ui;

import java.util.ArrayList;
import java.util.List;

import hu.dobrosi.javainpocket.javascript.JQueryBuilder;

public class ListView extends Component {
	public class Item {
		private String value;

		public Item(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	private List<Item> items = new ArrayList<>();

	public ListView() {
		super();
	}

	public Item addItem(String value) {
		JQueryBuilder.call(null, this, "append", "<li>" + value + "</li>");
		JQueryBuilder.call(null, this, "listview", "refresh");
		Item res = new Item(value);
		items.add(res);
		return res;
	}

	@Override
	public void create() {
		JQueryBuilder.call("o", "$('#nullPanel')", "append", "<ul id='" + getId() + "' data-role='listview'></ul>");
	}
}