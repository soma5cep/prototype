package com.example.jsonparser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button button1;
	public String URL = "http://0.0.0.0:8080/MyServer/JsonServer.jsp";
	private String[][] parsedData;
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.activity_main);
		Log.i("check","제대로 된다");
		
		//button1 = (Button)findViewById(R.id.button1);
		
	//	button1.setOnClickListener(new OnClickListener() {
	//		public void onClick(View v) {
	//			makeMessage(-1,-1,-1);
	//			//String result = SendByHttp(URL + makeMessage(-1,-1,-1));
	//			String result = "{\"List\":[{\"id\":4,\"name\":\"전일거래량 1,000%돌파\",\"price\":3155,\"time\":\"09.15 15:01\",\"volume\":1409937}]}";
	//			parsedData = jsonParserList(result);
	//		}
	//	});
	}
	
	public String[][] messageGet(int limit, int before, int after){
		makeMessage(limit, before, after);
		String result = SendByHttp(URL + makeMessage(-1,-1,-1));
		parsedData = jsonParserList(result);
		return parsedData;
	}
	
	protected String makeMessage(long limit, long before, long after){
		String message = "";
		boolean flag = false;
		if(limit!=-1){
			message += "limit=" + limit;
			flag = true;
		}
		if(before!=-1){
			if(flag==true) message += "&";
			message += "before="+ before;
		}
		if(after!=-1){
			if(flag==true) message += "&";
			message += "after" + after;
		}
		return message;
	}

	protected String[][] jsonParserList(String pRecvServerPage) {
		Log.i("서버에서 받은 전체 내용 : " , pRecvServerPage);
		
		try{
			JSONObject json = new JSONObject(pRecvServerPage);
			JSONArray jArr = json.getJSONArray("List");   // 여기에 뭐가 들어가야하는겆...?
			Log.i("check","되었다");
			String[] jsonName = {"price", "item_name", "name", "date", "volume", "entered", "id", "market"};
			String[][] parseredData = new String[jArr.length()][jsonName.length];
			for(int i=0;i<jArr.length();i++){
				json = jArr.getJSONObject(i);
				if(json != null){
					for(int j=0;j<jsonName.length;j++){
						parseredData[i][j] = json.getString(jsonName[j]);
					}
				}
			}
			for(int i=0;i<parseredData.length;i++){
				Log.i("JSON을 분석한 데이터 " + i +" : ", parseredData[i][0]);
				Log.i("JSON을 분석한 데이터 " + i +" : ", parseredData[i][1]);
				Log.i("JSON을 분석한 데이터 " + i +" : ", parseredData[i][2]);
			}
			return parseredData;
		} catch(JSONException e){
			e.printStackTrace();
		}
		return null;
	}

	protected String SendByHttp(String msg) {
		/*if(msg==null) msg = "";
		HttpClient client = new DefaultHttpClient();
		try{
			
			//msg를 서버로 전송 
			HttpPost post = new HttpPost(msg);
			//지연시간 최대 3초 
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params,3000);
			HttpConnectionParams.setSoTimeout(params, 3000);
			//서버에서 데이터를 다시 받아오는 과정 
			HttpResponse response = client.execute(post);
			BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"utf-8"));
			String line = null;
			String result = "";
			
			while((line=bufreader.readLine())!=null){
				result += line;
			}
			return result;
		} catch(Exception e){
			e.printStackTrace();
			client.getConnectionManager().shutdown(); // 에러 발생시 연결 지연 종료
		}*/
		return "";
	}
}
