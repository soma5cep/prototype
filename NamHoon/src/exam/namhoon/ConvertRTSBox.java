// 이부분의 내용은 ListViewAdapter로 넣었다.

/*
package exam.namhoon;

import android.graphics.*;
import android.text.*;
import android.text.style.*;
import android.widget.*;

public class ConvertRTSBox {
	static int count = 0;
	static void setRelSignal(String str, RelativeLayout rel) {
		//null일때 return 하면 relative는 수정되지 않고 relative_format.xml 파일에 정의된
		//default message로 출력된다.
		if(str == null) {
			return;
		}		
		
		SpannableStringBuilder sp =  new	SpannableStringBuilder(str);
		int index=0;
		//주어진 str 문자열에서 특정 단어를 검색해서 색을 바꾼다. 특정단어가 하나만 있다고
		//가정한 코드이므로 조건이 바뀌면 수정하도록 한다.
		if((index = str.indexOf("상향")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.RED), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("하향")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.BLUE), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("상승")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.RED), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("하락")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.BLUE), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("신고가")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.RED), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("신저가")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.BLUE), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("상한가")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.RED), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("하한가")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.BLUE), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("고가")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.RED), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("저가")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.BLUE), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		
		//퍼센티지는 BOLD 없이
		else if((index = str.indexOf("80%")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.GREEN), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			//sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("100%")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.GREEN), index, index+4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			//sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("200%")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.GREEN), index, index+4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			//sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("500%")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.GREEN), index, index+4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			//sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		else if((index = str.indexOf("1000%")) != -1) {
			sp.setSpan(new ForegroundColorSpan(Color.GREEN), index, index+5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			//sp.setSpan(new StyleSpan(Typeface.BOLD), index, index+5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
		}
		
		
		TextView text = (TextView)rel.findViewById(R.id.signal);
		text.setText(sp);
		
	}
	static void setRelInout(String str, RelativeLayout rel) {
		if(str == null) {
			return;
		}
		TextView text = (TextView)rel.findViewById(R.id.inout);
		text.setText(" "+str);
		if(str.equals("진입")) {
			text.setTextColor(Color.RED);
		}
		else if(str.equals("이탈")) {
			text.setTextColor(Color.BLUE);
		}
	}
	static void setRelStock_name(String str, RelativeLayout rel) {
		if(str == null) {
			return;
		}
		TextView text = (TextView)rel.findViewById(R.id.stock_name);
		text.setText(str);
	}
	static void setRelMarket_type(String str, RelativeLayout rel) {
		if(str == null) {
			return;
		}
		TextView text = (TextView)rel.findViewById(R.id.market_type);
		text.setText(" "+str+" ");
	}
	static void setRelTime(String str, RelativeLayout rel) {
		if(str == null) {
			return;
		}
		TextView text = (TextView)rel.findViewById(R.id.time);
		text.setText(str);
	}
	static void setRelPrice_diff_percent(String str, RelativeLayout rel) {
		if(str == null) {
			return;
		}
		
		TextView text = (TextView)rel.findViewById(R.id.price_diff_percent);
		text.setText(" ("+str+")");
		if(str.contains("+")) {
			text.setTextColor(Color.RED);
		}
		else if(str.contains("-")) {
			text.setTextColor(Color.BLUE);
		}
	}
	static void setRelPrice_diff(String str, RelativeLayout rel) {
		if(str == null) {
			return;
		}
		TextView text = (TextView)rel.findViewById(R.id.price_diff);
		text.setText(" "+str+" ");
		if(str.contains("+")) {
			text.setTextColor(Color.RED);
		}
		else if(str.contains("-")) {
			text.setTextColor(Color.BLUE);
		}
	}
	static void setRelTrading_volume(String str, RelativeLayout rel) {
		if(str == null) {
			return;
		}
		TextView text = (TextView)rel.findViewById(R.id.trading_volume);
		text.setText(" <"+str+"> ");
	}
	static void setRelStock_price(String str, RelativeLayout rel) {
		if(str == null) {
			return;
		}
		TextView text = (TextView)rel.findViewById(R.id.stock_price);
		text.setText(" "+str+" ");
	}
	
	public static void convertBoxToRel(RTSBox box, RelativeLayout rel) {
		setRelSignal(box.getSignal(), rel);
		setRelInout(box.getInout(), rel);
		setRelStock_name(box.getStock_name(), rel);
		setRelMarket_type(box.getMarket_type(), rel);
		setRelTime(box.getTime(), rel);
		setRelPrice_diff_percent(box.getPrice_diff_percent(), rel);
		setRelPrice_diff(box.getPrice_diff(), rel);
		setRelTrading_volume(box.getTrading_volume(), rel);
		setRelStock_price(box.getStock_price(), rel);
//	
//		if((++count)%2 == 0) {
//			//패딩을 재설정하는데 setBackground를 호출시 버그로 인해 XML에서 지정한 설정이 무시되기 때문이다.
//			//layer-list를 runtime에서 background로 설정시에 일어나는 문제인 듯 하다.
//			int left = rel.getPaddingLeft();
//			int top = rel.getPaddingTop();
//			int right =  rel.getPaddingRight();
//			int bottom = rel.getPaddingBottom();
//			rel.setBackgroundResource(R.drawable.view_underline_bkcolored);
//			rel.setPadding(left, top, right, bottom);
//		}
//		else {
//			//패딩을 재설정하는데 setBackground를 호출시 버그로 인해 XML에서 지정한 설정이 무시되기 때문이다.
//			//layer-list를 runtime에서 background로 설정시에 일어나는 문제인 듯 하다.
//			int left = rel.getPaddingLeft();
//			int top = rel.getPaddingTop();
//			int right =  rel.getPaddingRight();
//			int bottom = rel.getPaddingBottom();
//			rel.setBackgroundResource(R.drawable.view_underline);
//			rel.setPadding(left, top, right, bottom);
//		}
		
		
	}
}
*/