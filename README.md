# UOL Course Catalogue — Mobile Application Development (CS02310)

**The University of Lahore**  
**Faculty of Information Technology | Department of Software Engineering**  
**Course:** Mobile Application Development (CS02310) — Lab Task No. 02 (Lab Assignment No. 02) — Section S  
**Student Name:** Aimish Umar | **SAP ID:** 70147829  
**Semester:** Fall 2026 | 7th Semester | Section S  
**GitHub Repository:** [https://github.com/aim1sh/LabTask2_RecyclerView_70147829](https://github.com/aim1sh/LabTask2_RecyclerView_70147829)  

---

## 📋 Project Overview

**UOL Course Catalogue** is a native Android application built in Java for BS Software Engineering (BS-SE) students at The University of Lahore. The application allows students to browse the BS-SE curriculum in an aesthetically styled, high-performance scrollable list and inspect the details of any single course, with direct one-tap email connectivity to the course instructor.

The project is structured in two linked scenarios:
1. **Scenario 1 — The Course List (5 Marks):**
   - High-performance scrolling list of course cards built using `RecyclerView` and `CardView`.
   - Custom `Course` data model with encapsulation (private fields, constructor, public getters).
   - `CourseAdapter` with static inner `ViewHolder` caching view lookups.
   - `LinearLayoutManager` orchestrating linear vertical card placement.
   - Comprehensive comparative testing with and without `setLayoutManager()`.

2. **Scenario 2 — The Course Details Screen (5 Marks):**
   - Decoupled touch dispatching using an `OnItemClickListener` interface inside `CourseAdapter`.
   - Card clicks reported using `getBindingAdapterPosition()` from the ViewHolder.
   - Deterministic data extraction with `courses.get(position)` in `MainActivity`.
   - Inter-activity navigation using an **Explicit Intent** carrying course parameters through `putExtra()` with `public static final` key constants.
   - `CourseDetailActivity` declared in `AndroidManifest.xml`, updating the screen title to the course code using `setTitle()`.
   - Implicit Intent with `Intent.ACTION_SENDTO` and `mailto:` URI scheme inside `try-catch`, gracefully displaying a `Toast` upon `ActivityNotFoundException`.

---

## 📸 Application Screenshots

| Scenario 1: Course List | Scenario 1: Scrolled List | Scenario 1: Without LayoutManager |
|:---:|:---:|:---:|
| ![Course List](screenshots/01_course_list.png) | ![Scrolled List](screenshots/02_course_list_scrolled.png) | ![Without LayoutManager](screenshots/03_without_layout_manager.png) |

| Scenario 2: Course 1 (CS02310) | Scenario 2: Course 2 (SE03302) | Scenario 2: Course 3 (CS03104) |
|:---:|:---:|:---:|
| ![Detail CS02310](screenshots/04_detail_cs02310.png) | ![Detail SE03302](screenshots/05_detail_se03302.png) | ![Detail CS03104](screenshots/06_detail_cs03104.png) |

| Scenario 2: Email Action | Scenario 2: Toast Exception Handler |
|:---:|:---:|
| ![Email Action](screenshots/07_email_action.png) | ![Email Toast](screenshots/07_email_toast.png) |

---

## 🔬 Core Laboratory Questions & Answers

### 1. Scenario 1, Step 5: Screen without `setLayoutManager()` & The Architectural Reason
> **Observation:** When `rvCourses.setLayoutManager(...)` is omitted, the screen renders completely blank below the header, showing zero course cards despite the adapter being correctly populated with 10 `Course` objects.  
> **Reason:** The `RecyclerView` architecture enforces strict separation of concerns between data adaptation and layout positioning. The `CourseAdapter` creates ViewHolders and binds course data, but has zero knowledge of spatial positioning or coordinates. The `LayoutManager` is solely responsible for measuring, positioning, and rendering item views within the parent viewport. Without a `LayoutManager`, the `RecyclerView` cannot execute its layout pass (`onLayout()`), resulting in zero views being drawn. Once restored, `LinearLayoutManager` stacks each `CardView` sequentially in vertical order.

### 2. Scenario 2, Step 5: Why `position` passed to `onItemClick()` is used to fetch the data
> The `position` passed to `onItemClick(int position)` specifies the exact zero-based index of the tapped card in the adapter's backing dataset. Calling `courses.get(position)` enables `MainActivity` to access the exact `Course` object clicked by the student, ensuring the explicit Intent carries only that course's parameters to `CourseDetailActivity`.

---

## 🛠️ Project Structure

```text
UOLCourseCatalogue/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/pk/edu/uol/coursecatalogue/
│   │   │   │   ├── Course.java                 # Model class (Encapsulated curriculum entity)
│   │   │   │   ├── CourseAdapter.java          # Adapter, static ViewHolder, OnItemClickListener
│   │   │   │   ├── MainActivity.java           # List activity (Dataset, LinearLayoutManager, Intent dispatch)
│   │   │   │   └── CourseDetailActivity.java   # Detail activity (Extras unpacking, setTitle, email implicit intent)
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml       # Main catalogue layout (UOL branding + RecyclerView)
│   │   │   │   │   ├── item_course.xml         # CardView item layout (Code, Title, Credit Hours)
│   │   │   │   │   └── activity_course_detail.xml # Scrollable detail screen with faculty card & email button
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml              # Official UOL Emerald Green & Gold brand palette
│   │   │   │   │   ├── strings.xml             # Resource strings
│   │   │   │   │   └── themes.xml              # MaterialComponents DarkActionBar theme
│   │   │   │   └── drawable/                   # Vector drawables (uol_logo, badges, icons)
│   │   │   └── AndroidManifest.xml             # Declares activities & queries for ACTION_SENDTO
│   │   └── build.gradle.kts                    # App build config (compileSdk 34, recyclerview, cardview)
│   └── proguard-rules.pro
├── screenshots/                                # High-resolution execution screenshots
├── LabTask_Report.md                           # Detailed Laboratory Task Report (Markdown)
├── LabTask_Report.html                         # Publication-quality HTML Report
├── LabTask_Report.pdf                          # Submission-ready PDF Report
├── build.gradle.kts                            # Root build configuration
├── settings.gradle.kts                         # Dependency repository & project definition
└── README.md                                   # Project documentation
```

---

## ⚙️ Build and Installation

1. **Prerequisites:**
   - Android Studio Iguana / Jellyfish / Ladybug or newer
   - JDK 17 (OpenJDK 17 recommended)
   - Android SDK Platform 34
   - Android Emulator or physical device running Android 7.0+ (API 24+)

2. **Clone and Run:**
   ```bash
   git clone https://github.com/aim1sh/LabTask2_RecyclerView_70147829.git
   cd LabTask2_RecyclerView_70147829
   ./gradlew assembleDebug
   ```

3. **Install via ADB:**
   ```bash
   adb install -r app/build/outputs/apk/debug/app-debug.apk
   adb shell am start -n pk.edu.uol.coursecatalogue/.MainActivity
   ```
