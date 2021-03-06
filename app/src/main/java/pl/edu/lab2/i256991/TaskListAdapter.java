package pl.edu.lab2.i256991;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {


    public static final String EXTRA_TASKDET = "pl.edu.lab2.i256991.extra.TASKDET";

    //has the data
    private final LinkedList<Task> mTaskList;
    //responsible for inflating the xml with the content
    private final LayoutInflater mInflater;


    public TaskListAdapter(Context context, LinkedList<Task> taskList) {

        mInflater = LayoutInflater.from(context);
        this.mTaskList = taskList;
    }


    public TaskListAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(mItemView, this);
    }

    public void onBindViewHolder(TaskListAdapter.TaskViewHolder holder, int position) {
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

        public LinearLayout backgroundLayer, foregroundLayer;

        final TaskListAdapter mAdapter;
        final TextView taskTitle;
        final ImageView taskIcon;
        final TextView taskDate;
        TextView taskStatus;

        public TaskViewHolder(View itemView, TaskListAdapter adapter) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.task_title);
            taskIcon = itemView.findViewById(R.id.task_icon);
            taskDate = itemView.findViewById(R.id.task_date);
            taskStatus = itemView.findViewById(R.id.task_status);

            backgroundLayer = itemView.findViewById(R.id.item_Background);
            foregroundLayer = itemView.findViewById(R.id.item_Foreground);

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

            int mPosition = getLayoutPosition();
            Task task = mTaskList.get(mPosition);

            Intent taskIntent = new Intent(view.getContext(), TaskDetails.class);
            taskIntent.putExtra(EXTRA_TASKDET, task);

            view.getContext().startActivity(taskIntent);

        }
    }

    public int getItemCount() {
        return mTaskList == null? 0: mTaskList.size();
    }

    public void removeItem(int position) {
        mTaskList.remove(position);
        notifyItemRemoved(position);
    }

}
