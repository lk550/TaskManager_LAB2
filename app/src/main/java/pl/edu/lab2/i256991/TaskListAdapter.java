package pl.edu.lab2.i256991;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

//This adapter is responsible for creating and binding viewholders, that have the task info for the recyclerview
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    Resources res = Resources.getSystem();
    //has the data
    private final LinkedList<Task> mTaskList;
    //responsible for inflating the xml with the content
    private final LayoutInflater mInflater;

    public TaskListAdapter(Context context, LinkedList<Task> taskList) {
        mInflater = LayoutInflater.from(context);
        this.mTaskList = taskList;
    }


    public TaskListAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(mItemView, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBindViewHolder(TaskListAdapter.TaskViewHolder holder, int position) {
        // Retrieve the data for that position.
         Task task = mTaskList.get(position);
         holder.setTitle(task.getTitle());
         holder.setIcon(task.getType());
         holder.setDate(task.getDate());
         holder.setStatus(task.getStatus());
    }


    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       // private final String TODO = res.getString(R.string.type_todo);
       /* private final String EMAIL = res.getString(R.string.type_email);
        private final String PHONE = res.getString(R.string.type_phone);
        private final String MEETING = res.getString(R.string.type_meeting);
        private final String OTHER = res.getString(R.string.type_other); */

        private final String EMAIL = "Email";
        private final String PHONE = "Phone";
        private final String MEETING = "Meeting";
        private final String OTHER = "Other";

        final TaskListAdapter mAdapter;
        final TextView taskTitle;
        final ImageView taskIcon;
        final TextView taskDate;
        TextView taskStatus;

        public TaskViewHolder(View itemView, TaskListAdapter adapter) {
            super(itemView);
           // taskViewItem = itemView.findViewById(R.id.task_item);
            taskTitle = itemView.findViewById(R.id.task_title);
            taskIcon = itemView.findViewById(R.id.task_icon);
            taskDate = itemView.findViewById(R.id.task_date);
            taskStatus = itemView.findViewById(R.id.task_status);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        public void setTitle(String title) {
            taskTitle.setText(title);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public void setIcon(String type) {
            switch(type){
                case EMAIL:
                    taskIcon.setImageResource(R.drawable.ic_email);
                    taskIcon.setColorFilter(res.getColor(R.color.emailRed,null));
                    break;
                case PHONE:
                    taskIcon.setImageResource(R.drawable.ic_telephone);
                    taskIcon.setColorFilter(res.getColor(R.color.phoneGreen,null));
                    break;
                case MEETING:
                    taskIcon.setImageResource(R.drawable.ic_meeting);
                    taskIcon.setColorFilter(res.getColor(R.color.meetingPurple,null));
                    break;
                case OTHER:
                    taskIcon.setImageResource(R.drawable.ic_other);
                    taskIcon.setColorFilter(res.getColor(R.color.otherGray,null));
                    break;
                default:
                    taskIcon.setImageResource(R.drawable.ic_todo);
                    taskIcon.setColorFilter(res.getColor(R.color.todoOrange,null));
                    break;
            }
        }

        public void setDate(String date) {
            taskDate.setText(date);
        }

        public void setStatus(String status) {
            taskStatus.setText(status);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in mTaskList.
            Task element = mTaskList.get(mPosition);
            // Change the word in the mWordList.
           /* mWordList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.*/
            mAdapter.notifyDataSetChanged();
        }
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mTaskList == null? 0: mTaskList.size();
    }
}
