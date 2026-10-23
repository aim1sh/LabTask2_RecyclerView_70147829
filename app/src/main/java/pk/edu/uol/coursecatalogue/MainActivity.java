package pk.edu.uol.coursecatalogue;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * MainActivity: The Course List screen.
 * Displays a scrollable list of BS Software Engineering courses using RecyclerView and CardView.
 * Scenario 1 (Steps 4 & 5) and Scenario 2 (Steps 1 & 2)
 */
public class MainActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener {

    private RecyclerView rvCourses;
    private ArrayList<Course> courses;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Customize ActionBar title for the home catalogue screen
        setTitle("UOL Course Catalogue");

        // Scenario 1, Step 4: Initialize RecyclerView
        rvCourses = findViewById(R.id.rvCourses);

        // Populate curriculum data: at least eight Course objects of the BS-SE programme
        populateCourses();

        // Attach CourseAdapter passing context, dataset, and onItemClickListener (this)
        courseAdapter = new CourseAdapter(this, courses, this);

        // Scenario 1, Step 4: Set LinearLayoutManager on rvCourses
        rvCourses.setLayoutManager(new LinearLayoutManager(this));

        // Attach adapter
        rvCourses.setAdapter(courseAdapter);
    }

    /**
     * Constructs and initializes an ArrayList of BS Software Engineering courses.
     * Scenario 1, Step 4 (CLO-4)
     */
    private void populateCourses() {
        courses = new ArrayList<>();

        courses.add(new Course(
                "CS02310",
                "Mobile Application Development",
                3,
                "Sir Muhammad Arsalan",
                "muhammad.arsalan@uol.edu.pk",
                "Focuses on native Android application development using Java, Android Studio, UI architecture with RecyclerView, explicit and implicit Intents, data passing, life cycles, and responsive layouts."
        ));

        courses.add(new Course(
                "SE03302",
                "Software Design and Architecture",
                3,
                "Engr. Ayesha Tariq",
                "ayesha.tariq@uol.edu.pk",
                "Comprehensive study of software architectural styles (Layered, Microservices, Event-Driven), GoF design patterns (Creational, Structural, Behavioral), and UML component diagrams for scalable software."
        ));

        courses.add(new Course(
                "CS03104",
                "Artificial Intelligence",
                3,
                "Dr. Farhan Ahmed",
                "farhan.ahmed@uol.edu.pk",
                "Explores state-space search heuristics, informed search (A*), adversarial games (Minimax, Alpha-Beta pruning), constraint satisfaction problems, knowledge representation, and probabilistic reasoning."
        ));

        courses.add(new Course(
                "SE03201",
                "Software Quality Engineering",
                3,
                "Ms. Hina Bashir",
                "hina.bashir@uol.edu.pk",
                "In-depth investigation of software quality models, verification and validation strategies, automated unit testing, integration testing, code coverage criteria, and ISO/IEC quality standards."
        ));

        courses.add(new Course(
                "SE04403",
                "Software Project Management",
                3,
                "Dr. Kamran Ali",
                "kamran.ali@uol.edu.pk",
                "Covers software project lifecycle management, Agile/Scrum sprint frameworks, effort estimation models (COCOMO II, Function Points), risk mitigation, scheduling, and project governance."
        ));

        courses.add(new Course(
                "CS02202",
                "Database Systems",
                4,
                "Engr. Bilal Raza",
                "bilal.raza@uol.edu.pk",
                "Relational data model fundamentals, Entity-Relationship (ER) diagrams, database normalization up to BCNF, advanced SQL query design, ACID transactions, indexing, and NoSQL stores."
        ));

        courses.add(new Course(
                "CS02101",
                "Data Structures and Algorithms",
                4,
                "Dr. Zeeshan Haider",
                "zeeshan.haider@uol.edu.pk",
                "Rigorous analysis of asymptotic complexity (Big-O), linear structures (arrays, linked lists, stacks, queues), non-linear trees (BST, AVL), graphs, Dijkstra's algorithm, and dynamic programming."
        ));

        courses.add(new Course(
                "SE04505",
                "Web Engineering",
                3,
                "Mr. Hamza Sheikh",
                "hamza.sheikh@uol.edu.pk",
                "Modern full-stack web application development encompassing responsive semantic UI, asynchronous client interactions, RESTful API design, database connectivity, and secure session management."
        ));

        courses.add(new Course(
                "CS03305",
                "Information Security",
                3,
                "Engr. Sana Malik",
                "sana.malik@uol.edu.pk",
                "Core cybersecurity concepts including symmetric and asymmetric encryption (AES, RSA), hashing mechanisms, authentication protocols, vulnerabilities analysis, and secure coding standards."
        ));

        courses.add(new Course(
                "SE04901",
                "Final Year Project (FYP-I)",
                3,
                "Prof. Dr. Arshad Mehmood",
                "arshad.mehmood@uol.edu.pk",
                "Capstone software engineering project where students formulate requirements, design software architecture, build functional prototypes, and defend industry-standard solutions before an evaluation panel."
        ));
    }

    /**
     * Handles card item click events delivered from CourseAdapter's ViewHolder.
     * Scenario 2, Steps 1 & 2 (CLO-4)
     *
     * @param position The zero-based adapter position of the tapped course card.
     */
    @Override
    public void onItemClick(int position) {
        // Retrieve the exact course object corresponding to the tapped card position
        Course tappedCourse = courses.get(position);

        // Create explicit intent directed to CourseDetailActivity
        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);

        // Pass course fields as extras using public static final constants
        intent.putExtra(CourseDetailActivity.EXTRA_CODE, tappedCourse.getCode());
        intent.putExtra(CourseDetailActivity.EXTRA_TITLE, tappedCourse.getTitle());
        intent.putExtra(CourseDetailActivity.EXTRA_CREDIT_HOURS, tappedCourse.getCreditHours());
        intent.putExtra(CourseDetailActivity.EXTRA_INSTRUCTOR, tappedCourse.getInstructor());
        intent.putExtra(CourseDetailActivity.EXTRA_EMAIL, tappedCourse.getEmail());
        intent.putExtra(CourseDetailActivity.EXTRA_DESCRIPTION, tappedCourse.getDescription());

        // Launch details activity
        startActivity(intent);
    }
}
