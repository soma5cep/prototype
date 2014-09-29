package exam.namhoon;

import java.util.*;

import android.content.*;
import android.graphics.*;
import android.text.*;
import android.text.style.*;
import android.view.*;
import android.widget.*;

public class ListViewAdapter extends ArrayAdapter<RTSBox> {
	private ArrayList<RTSBox> items;
	private Context context;
	private int resource;
	
	public ListViewAdapter(Context context, int viewResourceId, ArrayList<RTSBox> items) {
		super(context, viewResourceId, items);
		this.items = items;
		this.context = context;
		resource = viewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout v = (RelativeLayout)convertView;
		ViewHolder vh;
		if (v == null) {
			v = (RelativeLayout)View.inflate(context, resource, null);
			vh = new ViewHolder();
			
			vh.signal = (TextView)v.findViewById(R.id.signal);	
			vh.inout = (TextView)v.findViewById(R.id.inout);
			vh.stock_name = (TextView)v.findViewById(R.id.stock_name);
			vh.market_type = (TextView)v.findViewById(R.id.market_type);
			vh.time = (TextView)v.findViewById(R.id.time);
			vh.price_diff_percent = (TextView)v.findViewById(R.id.price_diff_percent);
			vh.price_diff = (TextView)v.findViewById(R.id.price_diff);
			vh.trading_volume = (TextView)v.findViewById(R.id.trading_volume);
			vh.stock_price = (TextView)v.findViewById(R.id.stock_price);
			
			v.setTag(vh);
		}
		else {
			vh = (ViewHolder)v.getTag();
		}
		RTSBox b = items.get(position);
		if(b != null) {
			ConvertRTSBox.convertBoxToRel(b, vh);
		}
		return v;
	}
	
	public static class ViewHolder {		
		TextView signal;
		TextView inout;
		TextView stock_name;
		TextView market_type;
		TextView time;
		TextView price_diff_percent;
		TextView price_diff;
		TextView trading_volume;
		TextView stock_price;		
	}
	
	
	public static class ConvertRTSBox {
		static int count = 0;
		static void setSignal(String str, TextView textView) {
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
			
			
			//TextView text = (TextView)rel.findViewById(R.id.signal);
			textView.setText(sp);
			
		}
		static void setInout(String str, TextView textView) {
			if(str == null) {
				return;
			}
			//TextView text = (TextView)rel.findViewById(R.id.inout);
			textView.setText(" "+str);
			if(str.equals("진입")) {
				textView.setTextColor(Color.RED);
			}
			else if(str.equals("이탈")) {
				textView.setTextColor(Color.BLUE);
			}
		}
		static void setStock_name(String str, TextView textView) {
			if(str == null) {
				return;
			}
			//TextView text = (TextView)rel.findViewById(R.id.stock_name);
			textView.setText(str);
		}
		static void setMarket_type(String str, TextView textView) {
			if(str == null) {
				return;
			}
			//TextView text = (TextView)rel.findViewById(R.id.market_type);
			textView.setText(" "+str+" ");
		}
		static void setTime(String str, TextView textView) {
			if(str == null) {
				return;
			}
			//TextView text = (TextView)rel.findViewById(R.id.time);
			textView.setText(str);
		}
		static void setPrice_diff_percent(String str, TextView textView) {
			if(str == null) {
				return;
			}
			
			//TextView text = (TextView)rel.findViewById(R.id.price_diff_percent);
			textView.setText(" ("+str+")");
			if(str.contains("+")) {
				textView.setTextColor(Color.RED);
			}
			else if(str.contains("-")) {
				textView.setTextColor(Color.BLUE);
			}
		}
		static void setPrice_diff(String str, TextView textView) {
			if(str == null) {
				return;
			}
			//TextView text = (TextView)rel.findViewById(R.id.price_diff);
			textView.setText(" "+str+" ");
			if(str.contains("+")) {
				textView.setTextColor(Color.RED);
			}
			else if(str.contains("-")) {
				textView.setTextColor(Color.BLUE);
			}
		}
		static void setTrading_volume(String str, TextView textView) {
			if(str == null) {
				return;
			}
			//TextView text = (TextView)rel.findViewById(R.id.trading_volume);
			textView.setText(" <"+str+"> ");
		}
		static void setStock_price(String str, TextView textView) {
			if(str == null) {
				return;
			}
			//TextView text = (TextView)rel.findViewById(R.id.stock_price);
			textView.setText(" "+str+" ");
		}
		
		public static void convertBoxToRel(RTSBox box, ViewHolder vh) {
			setSignal(box.getSignal(), vh.signal);
			setInout(box.getInout() + box.getId(), vh.inout);
			setStock_name(box.getStock_name(), vh.stock_name);
			setMarket_type(box.getMarket_type(), vh.market_type);
			setTime(box.getTime(), vh.time);
			setPrice_diff_percent(box.getPrice_diff_percent(), vh.price_diff_percent);
			setPrice_diff(box.getPrice_diff(), vh.price_diff);
			setTrading_volume(box.getTrading_volume(), vh.trading_volume);
			setStock_price(box.getStock_price(), vh.stock_price);
			
			/*아래 코드는 디자인적인 부분. 배경을 설정한다. */
			/*
			if((++count)%2 == 0) {
				//패딩을 재설정하는데 setBackground를 호출시 버그로 인해 XML에서 지정한 설정이 무시되기 때문이다.
				//layer-list를 runtime에서 background로 설정시에 일어나는 문제인 듯 하다.
				int left = rel.getPaddingLeft();
				int top = rel.getPaddingTop();
				int right =  rel.getPaddingRight();
				int bottom = rel.getPaddingBottom();
				rel.setBackgroundResource(R.drawable.view_underline_bkcolored);
				rel.setPadding(left, top, right, bottom);
			}
			else {
				//패딩을 재설정하는데 setBackground를 호출시 버그로 인해 XML에서 지정한 설정이 무시되기 때문이다.
				//layer-list를 runtime에서 background로 설정시에 일어나는 문제인 듯 하다.
				int left = rel.getPaddingLeft();
				int top = rel.getPaddingTop();
				int right =  rel.getPaddingRight();
				int bottom = rel.getPaddingBottom();
				rel.setBackgroundResource(R.drawable.view_underline);
				rel.setPadding(left, top, right, bottom);
			}
			*/
			
		}
	}
}