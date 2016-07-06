package wan.rr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.List;

/**
 * Created by wan on 2016/6/30.
 */
public class MainActivity extends AppCompatActivity {

    ExpandableListView explist;
    ExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        explist = (ExpandableListView)findViewById(R.id.list);
        adapter = new ExpListAdapter(this);
        explist.setAdapter(adapter);
    }
}