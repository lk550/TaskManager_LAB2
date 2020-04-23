package pl.edu.lab2.i256991;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;


public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Declare a member variable to keep track of a task's selected mPriority
    //TODO: Meter variaveis novas

    public static final String EXTRA_TASK = "pl.edu.lab2.i256991.extra.TASK";
    public static final int RESULT_OK = 1;
    public static Task mTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Create the spinner.
        Spinner spinner = findViewById(R.id.type_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

    }


    /**
     * onClickAddTask is called when the "ADD" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onClickAddTask(View view) {
        // Check if EditText is empty, if not retrieve input and store it in a ContentValues object
        // If the EditText input is empty -> don't create an entry
        String title = ((TextView) findViewById(R.id.title)).getText().toString();
        String type = ((Spinner) findViewById(R.id.type_spinner)).getSelectedItem().toString();
        String desc = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        String date = ((TextView) findViewById(R.id.task_date)).getText().toString();
        if ((title.length()|type.length()|desc.length()|date.length()) == 0) {
            return;
        }


       /* CharSequence text = "Hello toast! info: "+title+"|"+type+"|"+desc+"|"+date+".";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show(); */

        Intent taskIntent = new Intent();
        mTask = new Task(title,type, desc, date);

        Log.d("test1",mTask.title);
        Log.d("resok?", String.valueOf(RESULT_OK));

        taskIntent.putExtra(EXTRA_TASK, mTask);
        setResult(RESULT_OK,taskIntent);
        finish();
    }


        public void onItemSelected(AdapterView<?> adapterView, View view, int
        i, long l) {
            String spinnerLabel = adapterView.getItemAtPosition(i).toString();
           // displayToast(spinnerLabel);
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

