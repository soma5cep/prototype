package exam.namhoon;

//RTS  = RealTime Signal
public class RTSBox {
	private String signal;
	private String inout;
	private String stock_name;
	private String market_type;
	private String time;
	private String price_diff_percent;
	private String price_diff;
	private String trading_volume;
	private String stock_price;
	
	
	//기본 생성자 호출하면 null로 초기화
	//아마 초기화 안해도 자동으로 null로 초기화 될테지만 명확히 하기위해 추가
	public RTSBox() {
		
		signal = null;
		inout = null;
		stock_name = null;
		market_type = null;
		time = null;
		price_diff_percent = null;
		price_diff = null;
		trading_volume = null;
		stock_price = null;
	}
	
	
	//자동으로 생성시킨 getter setter by eclipse
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getInout() {
		return inout;
	}
	public void setInout(String inout) {
		this.inout = inout;
	}
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public String getMarket_type() {
		return market_type;
	}
	public void setMarket_type(String market_type) {
		this.market_type = market_type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPrice_diff_percent() {
		return price_diff_percent;
	}
	public void setPrice_diff_percent(String price_diff_percent) {
		this.price_diff_percent = price_diff_percent;
	}
	public String getPrice_diff() {
		return price_diff;
	}
	public void setPrice_diff(String price_diff) {
		this.price_diff = price_diff;
	}
	public String getTrading_volume() {
		return trading_volume;
	}
	public void setTrading_volume(String trading_volume) {
		this.trading_volume = trading_volume;
	}
	public String getStock_price() {
		return stock_price;
	}
	public void setStock_price(String stock_price) {
		this.stock_price = stock_price;
	}


}
