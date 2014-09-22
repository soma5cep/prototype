package exam.namhoon;

import android.content.res.*;
import android.graphics.drawable.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
