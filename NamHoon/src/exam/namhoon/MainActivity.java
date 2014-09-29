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
		
		/*
		 * 다른 thread에서 돌리는 법 Loader, AsyncTask, AsyncQueryHandler, HandlerThread 등을 사용하면 된다.
		 * volley 사용 고려
		 */
		ListView lv = (ListView)findViewById(R.id.mlist);	
		View footer = View.inflate(this, R.layout.list_footer, null);
		lv.addFooterView(footer);
		
		lv.setAdapter(adapter = new ListViewAdapter(this, R.layout.relative_format, items));
		final AddItem mAddItem = new AddItem(items, adapter);
		
		//리무브 왜하는지 테스트하기
		//lv.removeFooterView(footer);
		
		// 기본 가장 최신 10개를 불러온다.
		mAddItem.additem(AddItem.DEFAULT);
		
		SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
		swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				mAddItem.additem(AddItem.AFTER);
			}	
		});
		//swipeLayout 칼라 지정하는 코드가 있는데 사용고려

		lv.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				//Do nothing
			}		
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				int count = totalItemCount - visibleItemCount;	
				// 예제코드에서는 락이 잡혀있는지에 대한 코드도 있다.
				// 딜레이를 걸어놓지 않았기 때문에 겁나 그냥 빨리 뜸. footer를 볼 수 없을 정도
				if(firstVisibleItem >= count && totalItemCount != 0) {
					mAddItem.additem(AddItem.BEFORE);
				}
			}
		});
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
