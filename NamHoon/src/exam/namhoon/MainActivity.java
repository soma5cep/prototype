package exam.namhoon;

import java.util.*;

import android.os.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import android.widget.AbsListView.OnScrollListener;

public class MainActivity extends ActionBarActivity {
	
	
	private ArrayList<RTSBox> items;
	private ListViewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		items = new ArrayList<RTSBox>();
		for(int i=0; i<10; ++i) {
			RTSBox box = new RTSBox();
			box.setSignal("10일 이평선 상향 돌파");
			box.setInout("진입");
			box.setStock_name("소마전자");
			box.setMarket_type("KOSPI");
			box.setTime("9/22 12:19");
			box.setPrice_diff_percent("-11.5%");
			box.setPrice_diff("-110");
			box.setTrading_volume("6660");
			box.setStock_price("882660");
			items.add(box);
		}
		SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
		swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				//서버로부터 정보를 받아와서 listview에 추가하는 코드 작성
				int limit=1, after=-1, before=-1;
				RTSBox[] box = new RTSBox[500];
				
				ConnectServer connect = new ConnectServer();
				String[][] data = connect.messageGet(limit, after, before);
				
				for(int i=0;i<limit;i++){
					box[i].setSignal(data[i][1]);
					box[i].setInout("진입");
					box[i].setStock_name(data[i][0]);
					box[i].setMarket_type("KOSPI	");
					box[i].setTime(data[i][3]);
					box[i].setPrice_diff_percent("-11.5%");
					box[i].setPrice_diff("-110");
					box[i].setTrading_volume(data[i][4]);
					box[i].setStock_price(data[i][2]);
					items.add(0, box[i]);
					adapter.notifyDataSetChanged();
				}
			}	
		});
		//swipeLayout 칼라 지정하는 코드가 있는데 사용고려
		
		
		ListView lv = (ListView)findViewById(R.id.mlist);	
		View footer = View.inflate(this, R.layout.list_footer, null);
		lv.addFooterView(footer);
		
		lv.setAdapter(adapter = new ListViewAdapter(this, R.layout.relative_format, items));
		
		
		//리무브 왜하는지 테스트하기
		//lv.removeFooterView(footer);
		
		lv.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				//Do nothing
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				int count = totalItemCount - visibleItemCount;
				
				// 예제코드에서는 락이 잡혀있는지에 대한 코드도 있다.
				// 락 여부와 threading 하는 것을 차후 고려
				// 딜레이를 걸어놓지 않았기 때문에 겁나 그냥 빨리 뜸. footer를 볼 수 없을 정도
				if(firstVisibleItem >= count && totalItemCount != 0) {
					RTSBox box = new RTSBox();
					box.setSignal("5일 이평선 상향 돌파");
					box.setInout("진입");
					box.setStock_name("소마전자");
					box.setMarket_type("KOSPI");
					box.setTime("9/22 12:19");
					box.setPrice_diff_percent("-11.5%");
					box.setPrice_diff("-110");
					box.setTrading_volume("6660");
					box.setStock_price("882660");
					items.add(box);
					adapter.notifyDataSetChanged();
				}
			}
		});
		
		/*
		LinearLayout linear = (LinearLayout)findViewById(R.id.linear);
		for(int i=0; i<5; ++i) {
			RelativeLayout rel = (RelativeLayout)View.inflate(this, R.layout.relative_format, null);
			RTSBox box = new RTSBox();
			if(i==0) {
				box.setSignal("20일 이평선 상향 돌파");
				box.setInout("진입");
				box.setStock_name("소마전자");
				box.setMarket_type("주");
				box.setTime("9/22 12:19");
				box.setPrice_diff_percent("-11.5%");
				box.setPrice_diff("-110");
				box.setTrading_volume("6660");
				box.setStock_price("882660");				
			}
			
			ConvertRTSBox.convertBoxToRel(box, rel);
			
			if(i%2 == 0) {
				//패딩을 재설정하는데 setBackground를 호출시 버그로 인해 XML에서 지정한 설정이 무시되기 때문이다.
				//layer-list를 runtime에서 background로 설정시에 일어나는 문제인 듯 하다.
				int left = rel.getPaddingLeft();
				int top = rel.getPaddingTop();
				int right =  rel.getPaddingRight();
				int bottom = rel.getPaddingBottom();
				rel.setBackgroundResource(R.drawable.view_underline_bkcolored);
				rel.setPadding(left, top, right, bottom);
			}
			
			linear.addView(rel, 0);
		}
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
