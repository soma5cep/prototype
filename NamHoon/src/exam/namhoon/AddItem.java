package exam.namhoon;

import java.text.*;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;

import android.os.*;

import com.google.gson.*;

public class AddItem {
	private ArrayList<RTSBox> items;
	private ListViewAdapter adapter;
	final static int LIMIT = 10;
	final static int DEFAULT = 0;
	final static int BEFORE = 1;
	final static int AFTER = 2;
	final static String URL = "http://14.63.165.145:5000/signals?";
	
	private HttpRequest req = null;
	
	public AddItem(ArrayList<RTSBox> items, ListViewAdapter adapter) {
		super();
		this.items = items;
		this.adapter = adapter;
	}
	public void additem(int refresh_flag) {			
		String getURL=URL+"limit="+LIMIT;
		switch(refresh_flag) {
			case DEFAULT :
				break;
			case BEFORE :
				if(items.isEmpty()) break; // 비어있으면 안됨.
				getURL=getURL+"&before="+items.get(items.size()-1).getId(); //마지막 녀석의 Id를 before 값으로 넘겨준다.		
				break;
			case AFTER :
				if(items.isEmpty()) break; //비어있으면 안됨.
				getURL=getURL+"&after="+items.get(0).getId(); //처음 녀석의 Id를 after 값으로 넘겨준다.
				break;
			default :
				break;			
		}
		
		if(req == null) {
			req = new HttpRequest();
		}
		if(req.getStatus() == AsyncTask.Status.RUNNING) {
			return;
		}
		else {
			req = new HttpRequest();
			req.execute(getURL, String.valueOf(refresh_flag));
		}
		
		//new HttpRequest().execute(getURL, String.valueOf(refresh_flag));
	}
	
	private class HttpRequest extends AsyncTask<String, Integer, Long> {
		private String json_string = null;
		private int refresh_flag;
		
		
		@Override
		protected Long doInBackground(String ... strs) {
			refresh_flag = Integer.parseInt(strs[1]); //strs[1] 은 refresh_flag
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(strs[0]); //strs[0] 은 URL
				HttpResponse responseGet = client.execute(get);
				HttpEntity resEntityGet = responseGet.getEntity();
				if(resEntityGet != null) {
					json_string = EntityUtils.toString(resEntityGet);
				}
			} catch (Exception e) {
				/* http 통신 에러 */
				e.printStackTrace();
			}
			return 0L;
		}
		
		@Override
		protected void onPostExecute(Long result) {
			// 서버에서 응답이 없으면 바로 return
			if(json_string == null || json_string == "") return;
			
			Gson gson = new Gson();				
			List<SignalItem> signals = gson.fromJson(json_string, SignalItemList.class).getSignals();
			if(signals.isEmpty()) {
				/* do nothing */
				return;
			}		
			for(int i=0; i<signals.size(); ++i) {
				SignalItem item = signals.get(i);
				RTSBox box = new RTSBox();
			
				box.setSignal(item.getName());
				if(item.getEntered() == 1) {
					box.setInout("진입");
				}
				else if(item.getEntered() == 0) {
					box.setInout("이탈");
				}
				box.setStock_name(item.getItem_name());
				if(item.getMarket() == 1) {
					box.setMarket_type("KOSPI");
				}
				else if(item.getMarket() == 2) {
					box.setMarket_type("KOSDAQ");
				}
				SimpleDateFormat origin_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				SimpleDateFormat new_format = new SimpleDateFormat("M/d HH:mm:ss");
				String new_date="";
				Date origin_date = null;
				
				try {
					origin_date = origin_format.parse(item.getDate());
					new_date = new_format.format(origin_date);
				} catch (Exception e){
					e.printStackTrace();
				}
				
				box.setTime(new_date);
				box.setPrice_diff_percent("-11.5%d");
				box.setPrice_diff("-110d");
				box.setTrading_volume(String.valueOf(item.getVolume()));
				box.setStock_price(String.valueOf(item.getPrice()));
				box.setId(item.getId());
				
				
				switch(refresh_flag) {
					case BEFORE :
						items.add(box);
						break;
					case AFTER :
						items.add(i, box);
						break;
					default :
						items.add(box);
						break;
				}
			}
			adapter.notifyDataSetChanged();
		}
	}
}
