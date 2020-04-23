package pl.edu.lab2.i256991;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    //TODO: substituir data inicial (placeholder apenas words)
    private final LinkedList<String> mWordList = new LinkedList<>();

    private LinkedList<Task> mTaskList;

    //variables adapter + recycleView
    private RecyclerView mRecyclerView;
    private TaskListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Task mIntentTask;
    public static final int TASK_REQUEST = 1;
    public static final int RESULT_OK = 1;
    public static final String EXTRA_TASK = "pl.edu.lab2.i256991.extra.TASK";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mTaskList = new LinkedList<>();

        //TODO: Remover esta parte que enche o placeholder com lixo--------------
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TaskListAdapter(this, mTaskList);
        mRecyclerView.setAdapter(mAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = findViewById(R.id.fab);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        Log.d("req", String.valueOf(requestCode));
        Log.d("res", String.valueOf(resultCode));

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TASK_REQUEST) {
            if (resultCode == RESULT_OK) {

                Log.d("aaand","got in");
                Task task = (Task) data.getSerializableExtra(EXTRA_TASK);
                Log.d("please","got in 2");
               Log.d("idk",task.title);
               mTaskList.add(task);

               mAdapter.notifyItemInserted(mTaskList.size());

            }
        }
    }
}
