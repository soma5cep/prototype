package exam.namhoon;

import java.util.*;

import android.content.*;
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
		if (v == null) {
			v = (RelativeLayout)View.inflate(context, resource, null);
		}
		RTSBox b = items.get(position);
		if(b != null) {
			ConvertRTSBox.convertBoxToRel(b, v);
		}
		return v;
	}
}