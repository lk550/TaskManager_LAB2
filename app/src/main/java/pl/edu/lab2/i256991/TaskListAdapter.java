package pl.edu.lab2.i256991;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

//This adapter is responsible for creating and binding viewholders, that have the task info for the recyclerview
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {


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

    public void onBindViewHolder(TaskListAdapter.TaskViewHolder holder, int position) {
        // Retrieve the data for that position.
         Task task = mTaskList.get(position);
         holder.setTitle(task.getTitle());
         holder.setIcon(task.getType());
         holder.setDate(task.getDate());
         holder.setStatus(task.getStatus());
    }


    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


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


        public void setIcon(String type) {
            Context context= itemView.getContext();
            switch(type){
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
