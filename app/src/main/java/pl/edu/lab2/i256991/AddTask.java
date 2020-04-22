package pl.edu.lab2.i256991;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;


public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Declare a member variable to keep track of a task's selected mPriority
    //TODO: Meter variaveis novas
    private int mPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Initialize to highest mPriority by default (mPriority = 1)
        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;

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
        String input = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        if (input.length() == 0) {
            return;
        }

        // Insert new task data via a ContentResolver
        // Create new empty ContentValues object
   /* ContentValues contentValues = new ContentValues();
    // Put the task description and selected mPriority into the ContentValues
        contentValues.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, input);
        contentValues.put(TaskContract.TaskEntry.COLUMN_PRIORITY, mPriority);
    // Insert the content values via a ContentResolver
    Uri uri = getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI, contentValues);

    // Display the URI that's returned with a Toast
    // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if(uri != null) {
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }*/

        // Finish activity (this returns back to MainActivity)
        finish();
    }
        /**
         * onPrioritySelected is called whenever a priority button is clicked.
         * It changes the value of mPriority based on the selected button.
         */
        public void onPrioritySelected (View view){
            if (((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
                mPriority = 1;
            } else if (((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
                mPriority = 2;
            } else if (((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
                mPriority = 3;
            }
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

