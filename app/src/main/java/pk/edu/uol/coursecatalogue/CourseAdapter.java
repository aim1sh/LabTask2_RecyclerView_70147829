package pk.edu.uol.coursecatalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter responsible for binding Course data models to item_course CardViews.
 * Implements Scenario 1 (Step 3) and Scenario 2 (Step 1).
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    /**
     * Interface to communicate card click events back to MainActivity.
     * Scenario 2, Step 1 (CLO-4)
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final Context context;
    private final List<Course> courseList;
    private final OnItemClickListener listener;

    /**
     * Adapter constructor.
     *
     * @param context    Calling activity context
     * @param courseList Data list of courses
     * @param listener   Click listener implementation
     */
    public CourseAdapter(Context context, List<Course> courseList, OnItemClickListener listener) {
        this.context = context;
        this.courseList = courseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.tvCourseCode.setText(course.getCode());
        holder.tvCourseTitle.setText(course.getTitle());
        holder.tvCreditHours.setText(course.getCreditHours() + " Credit Hours");
    }

    @Override
    public int getItemCount() {
        return courseList != null ? courseList.size() : 0;
    }

    /**
     * Static inner ViewHolder caching the three TextView references.
     * Attaches item click listener reporting getBindingAdapterPosition().
     */
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvCourseCode;
        public final TextView tvCourseTitle;
        public final TextView tvCreditHours;

        public CourseViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tvCourseCode);
            tvCourseTitle = itemView.findViewById(R.id.tvCourseTitle);
            tvCreditHours = itemView.findViewById(R.id.tvCreditHours);

            // Attach click listener to the entire item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        // Validate that the clicked position is valid and not pending animation/removal
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
