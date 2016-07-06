package wan.rr;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ExpListAdapter implements ExpandableListAdapter {

    private Context ctx;
    private List<String> group;
    private List<String> child;

    public ExpListAdapter(Context ctx) {
        this.ctx = ctx;

        group = new ArrayList<String>();
        group.add("Group 1");
        group.add("Group 2");
        group.add("Group 3");
        group.add("Group 4");
        group.add("Group 5");

        child = new ArrayList<String>();
        child.add("Child 1");
        child.add("Child 2");
        child.add("Child 3");
        child.add("Child 4");
        child.add("Child 5");
    }

    @Override
    public void registerDataSetObserver(DataSetObserver obs) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver obs) {

    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int pos) {
        return child.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group_title=(String) getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_layout,parent,false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.parent_txt);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(getGroup(groupPosition).toString());
        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child_title=(String) getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_layout,parent,false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.child_txt);
        textView.setText(getChild(groupPosition, childPosition).toString());
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}

