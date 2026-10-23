package pk.edu.uol.coursecatalogue;

/**
 * Model class representing a course in the BS Software Engineering curriculum.
 * Scenario 1, Step 1 (CLO-4)
 */
public class Course {
    private String code;
    private String title;
    private int creditHours;
    private String instructor;
    private String email;
    private String description;

    /**
     * Constructor initializing every field of the Course object.
     *
     * @param code        Unique course identifier (e.g. "CS02310")
     * @param title       Formal course title (e.g. "Mobile Application Development")
     * @param creditHours Academic credit hours (e.g. 3)
     * @param instructor  Faculty member assigned to teach the course
     * @param email       Instructor official academic email address
     * @param description Overview of syllabus, learning outcomes, and prerequisites
     */
    public Course(String code, String title, int creditHours, String instructor, String email, String description) {
        this.code = code;
        this.title = title;
        this.creditHours = creditHours;
        this.instructor = instructor;
        this.email = email;
        this.description = description;
    }

    // Public getters for every field
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }
}
