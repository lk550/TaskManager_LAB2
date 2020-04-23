package pl.edu.lab2.i256991;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    public static final String EXTRA_TASKDET = "pl.edu.lab2.i256991.extra.TASKDET";

    private final String EMAIL = "Email";
    private final String PHONE = "Phone";
    private final String MEETING = "Meeting";
    private final String OTHER = "Other";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);


        Intent intent = getIntent();
        Task task = (Task) intent.getSerializableExtra(EXTRA_TASKDET);

        TextView desc = findViewById(R.id.desc);
        TextView date = findViewById(R.id.date);
        TextView status = findViewById(R.id.status);
        TextView type = findViewById(R.id.type);
        ImageView taskIcon = findViewById(R.id.task_icon);

        String title=task.getTitle();
        setTitle(title);
        desc.setText(task.getDescription());
        date.setText(task.getDate());
        status.setText(task.getStatus());
        type.setText(task.getType());

        Context context= TaskDetails.this;
        switch(task.getType()){
            case EMAIL:
                taskIcon.setImageResource(R.drawable.ic_email);
                taskIcon.setColorFilter(ContextCompat.getColor(context,R.color.emailRed));
                break;
            case PHONE:
                taskIcon.setImageResource(R.drawable.ic_telephone);
                taskIcon.setColorFilter(ContextCompat.getColor(context,R.color.phoneGreen));
                break;
            case MEETING:
                taskIcon.setImageResource(R.drawable.ic_meeting);
                taskIcon.setColorFilter(ContextCompat.getColor(context,R.color.meetingPurple));
                break;
            case OTHER:
                taskIcon.setImageResource(R.drawable.ic_other);
                taskIcon.setColorFilter(ContextCompat.getColor(context,R.color.otherGray));
                break;
            default:
                taskIcon.setImageResource(R.drawable.ic_todo);
                taskIcon.setColorFilter(ContextCompat.getColor(context,R.color.todoOrange));
                break;
        }


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
