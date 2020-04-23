package pl.edu.lab2.i256991;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_TASK = "pl.edu.lab2.i256991.extra.TASK";
    public static final int RESULT_OK = 1;
    public static Task mTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Spinner spinner = findViewById(R.id.type_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

    }

    public void onClickAddTask(View view) {

        String title = ((TextView) findViewById(R.id.title)).getText().toString();
        String type = ((Spinner) findViewById(R.id.type_spinner)).getSelectedItem().toString();
        String desc = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        String date = ((TextView) findViewById(R.id.task_date)).getText().toString();
        if ((title.length()|type.length()|desc.length()|date.length()) == 0) {
            return;
        }
        Intent taskIntent = new Intent();
        mTask = new Task(title,type, desc, date);

        taskIntent.putExtra(EXTRA_TASK, mTask);
        setResult(RESULT_OK,taskIntent);
        finish();
    }
        public void onItemSelected(AdapterView<?> adapterView, View view, int
        i, long l) {
            String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //to avoid resetting the main activity content
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

