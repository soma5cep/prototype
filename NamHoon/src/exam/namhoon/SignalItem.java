package exam.namhoon;

public class SignalItem {
	int price;
	String item_name;
	String name;
	String date;
	int volume;
	int entered;
	int id;
	int market;
	
	public SignalItem() {
		super();
	}
	
	public SignalItem(int price, String item_name, String name, String date,
			int volume, int entered, int id, int market) {
		super();
		this.price = price;
		this.item_name = item_name;
		this.name = name;
		this.date = date;
		this.volume = volume;
		this.entered = entered;
		this.id = id;
		this.market = market;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getEntered() {
		return entered;
	}
	public void setEntered(int entered) {
		this.entered = entered;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMarket() {
		return market;
	}
	public void setMarket(int market) {
		this.market = market;
	}
}
