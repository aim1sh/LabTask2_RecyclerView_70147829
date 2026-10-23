package pk.edu.uol.coursecatalogue;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

/**
 * CourseDetailActivity displays comprehensive information about a single tapped course.
 * Scenario 2, Steps 2, 3, 4 (CLO-4)
 */
public class CourseDetailActivity extends AppCompatActivity {

    // Public static final Intent extra keys for deterministic parameter passing
    public static final String EXTRA_CODE = "pk.edu.uol.coursecatalogue.EXTRA_CODE";
    public static final String EXTRA_TITLE = "pk.edu.uol.coursecatalogue.EXTRA_TITLE";
    public static final String EXTRA_CREDIT_HOURS = "pk.edu.uol.coursecatalogue.EXTRA_CREDIT_HOURS";
    public static final String EXTRA_INSTRUCTOR = "pk.edu.uol.coursecatalogue.EXTRA_INSTRUCTOR";
    public static final String EXTRA_EMAIL = "pk.edu.uol.coursecatalogue.EXTRA_EMAIL";
    public static final String EXTRA_DESCRIPTION = "pk.edu.uol.coursecatalogue.EXTRA_DESCRIPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        // Scenario 2, Step 3: Read values using getStringExtra() and getIntExtra()
        Intent intent = getIntent();
        final String code = intent.getStringExtra(EXTRA_CODE);
        final String title = intent.getStringExtra(EXTRA_TITLE);
        int creditHours = intent.getIntExtra(EXTRA_CREDIT_HOURS, 0);
        String instructor = intent.getStringExtra(EXTRA_INSTRUCTOR);
        final String email = intent.getStringExtra(EXTRA_EMAIL);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);

        // Sets the screen title to the course code with setTitle()
        if (code != null && !code.trim().isEmpty()) {
            setTitle(code);
        } else {
            setTitle("Course Details");
        }

        // Enable Up back arrow in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Find view references
        TextView tvDetailCode = findViewById(R.id.tvDetailCode);
        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDetailCreditHours = findViewById(R.id.tvDetailCreditHours);
        TextView tvDetailInstructor = findViewById(R.id.tvDetailInstructor);
        TextView tvDetailEmail = findViewById(R.id.tvDetailEmail);
        TextView tvDetailDescription = findViewById(R.id.tvDetailDescription);
        MaterialButton btnEmailInstructor = findViewById(R.id.btnEmailInstructor);

        // Bind data to views
        if (code != null) tvDetailCode.setText(code);
        if (title != null) tvDetailTitle.setText(title);
        tvDetailCreditHours.setText(creditHours + " Credit Hours");
        if (instructor != null) tvDetailInstructor.setText(instructor);
        if (email != null) tvDetailEmail.setText(email);
        if (description != null) tvDetailDescription.setText(description);

        // Scenario 2, Step 4: "Email Instructor" button with ACTION_SENDTO mailto: inside try-catch
        btnEmailInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email == null || email.trim().isEmpty()) {
                    Toast.makeText(CourseDetailActivity.this, "Instructor email address not available.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Construct implicit intent with ACTION_SENDTO and mailto: URI scheme
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + email));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query regarding course: " + (code != null ? code : "") + " - " + (title != null ? title : ""));

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    // Show user-friendly Toast when no email client is available
                    Toast.makeText(CourseDetailActivity.this, getString(R.string.no_email_app), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
