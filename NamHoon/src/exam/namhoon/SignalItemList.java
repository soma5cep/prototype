package exam.namhoon;

import java.util.*;

public class SignalItemList {
	List<SignalItem> signals;
	public List<SignalItem> getSignals() {
		return signals;
	}
	public void setSignals(List<SignalItem> signals) {
		this.signals = signals;
	}
	
	public SignalItemList(List<SignalItem> signals) {
		super();
		this.signals = signals;
	}
}
