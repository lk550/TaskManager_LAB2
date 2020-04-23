package pl.edu.lab2.i256991;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements RecItemTouchHelper.RecyclerItemListener {


    private LinkedList<Task> mTaskList;

    private RecyclerView mRecyclerView;
    private TaskListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public static final int TASK_REQUEST = 1;
    public static final int RESULT_OK = 1;
    public static final String EXTRA_TASK = "pl.edu.lab2.i256991.extra.TASK";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mTaskList = new LinkedList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);


        mAdapter = new TaskListAdapter(this, mTaskList);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecItemTouchHelper(0, ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_add) {
            Intent intent = new Intent(MainActivity.this, AddTask.class);
            startActivityForResult(intent, TASK_REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TASK_REQUEST) {
            if (resultCode == RESULT_OK) {

                Task task = (Task) data.getSerializableExtra(EXTRA_TASK);
               mTaskList.add(task);

               mAdapter.notifyItemInserted(mTaskList.size());

            }
        }
    }


    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof TaskListAdapter.TaskViewHolder) {
            String title = mTaskList.get(viewHolder.getAdapterPosition()).getTitle();

            mAdapter.removeItem(viewHolder.getAdapterPosition());

        }
    }
}
