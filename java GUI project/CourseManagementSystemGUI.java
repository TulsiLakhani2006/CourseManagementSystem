import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CourseManagementSystemGUI extends Frame implements ActionListener {

    TextArea resultArea;
    TextField courseField, studentNameField, studentAgeField, studentEmailField, searchEmailField;
    Choice courseListComboBox;

    // Fixed-size arrays and index to track the number of elements
    String[] courses = new String[10];
    String[] studentNames = new String[10];
    String[] studentAges = new String[10];
    String[] studentEmails = new String[10];
    String[] studentCourses = new String[10];
    int courseCount = 0; // Tracks number of courses
    int studentCount = 0; // Tracks number of students

    CourseManagementSystemGUI() {
        setSize(800, 600);
        setTitle("Course Management System");
        setLayout(new BorderLayout(10, 10)); // Added gaps between components
        setBackground(new Color(240, 245, 249)); // Light blue background

        // Create a main panel with padding
        Panel mainPanel = new Panel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 245, 249));
        
        // Input Panel with improved styling
        Panel inputPanel = new Panel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBackground(new Color(240, 245, 249));
        courseField = new TextField();
        studentNameField = new TextField();
        studentAgeField = new TextField();
        studentEmailField = new TextField();
        searchEmailField = new TextField();
        courseListComboBox = new Choice();
        
        // Style text fields
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        courseField.setFont(fieldFont);
        studentNameField.setFont(fieldFont);
        studentAgeField.setFont(fieldFont);
        studentEmailField.setFont(fieldFont);
        searchEmailField.setFont(fieldFont);
        courseListComboBox.setFont(fieldFont);
        
        // Style labels
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(50, 80, 120);
        
        inputPanel.add(createStyledLabel("Course Name:", labelFont, labelColor));
        inputPanel.add(courseField);
        inputPanel.add(createStyledLabel("Student Name:", labelFont, labelColor));
        inputPanel.add(studentNameField);
        inputPanel.add(createStyledLabel("Age:", labelFont, labelColor));
        inputPanel.add(studentAgeField);
        inputPanel.add(createStyledLabel("Email:", labelFont, labelColor));
        inputPanel.add(studentEmailField);
        inputPanel.add(createStyledLabel("Courses:", labelFont, labelColor));
        inputPanel.add(courseListComboBox);
        inputPanel.add(createStyledLabel("Search by Email:", labelFont, labelColor));
        inputPanel.add(searchEmailField);

        // Button Panel with improved styling
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(220, 230, 240));
        
        // Create buttons with consistent styling
        Button addCourseButton = createStyledButton("Add Course", new Color(70, 130, 180));
        Button removeCourseButton = createStyledButton("Remove Course", new Color(220, 80, 80));
        Button registerStudentButton = createStyledButton("Register Student", new Color(60, 160, 60));
        Button removeStudentButton = createStyledButton("Remove Student", new Color(220, 80, 80));
        Button viewStudentsButton = createStyledButton("View Registered Students", new Color(90, 150, 200));
        Button searchStudentButton = createStyledButton("Search Student", new Color(90, 150, 200));
        Button viewEnrollmentButton = createStyledButton("View Enrollment Count", new Color(90, 150, 200));
        Button saveDataButton = createStyledButton("Save Data", new Color(150, 100, 180));
        Button loadDataButton = createStyledButton("Load Data", new Color(150, 100, 180));

        // Add Action Listeners
        addCourseButton.addActionListener(this);
        removeCourseButton.addActionListener(this);
        registerStudentButton.addActionListener(this);
        removeStudentButton.addActionListener(this);
        viewStudentsButton.addActionListener(this);
        searchStudentButton.addActionListener(this);
        viewEnrollmentButton.addActionListener(this);
        saveDataButton.addActionListener(this);
        loadDataButton.addActionListener(this);

        // Add buttons to button panel
        buttonPanel.add(addCourseButton);
        buttonPanel.add(removeCourseButton);
        buttonPanel.add(registerStudentButton);
        buttonPanel.add(removeStudentButton);
        buttonPanel.add(viewStudentsButton);
        buttonPanel.add(searchStudentButton);
        buttonPanel.add(viewEnrollmentButton);
        buttonPanel.add(saveDataButton);
        buttonPanel.add(loadDataButton);

        // Result Area with improved styling
        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBackground(new Color(253, 253, 253));
        resultArea.setForeground(new Color(50, 50, 50));
        
        // Create a titled border for the result area
        Panel resultPanel = new Panel(new BorderLayout());
        resultPanel.setBackground(new Color(240, 245, 249));
        resultPanel.add(new Label("Results:", Label.CENTER), BorderLayout.NORTH);
        resultPanel.add(resultArea, BorderLayout.CENTER);
        
        // Style the results label
        ((Label)resultPanel.getComponent(0)).setFont(new Font("Arial", Font.BOLD, 16));
        ((Label)resultPanel.getComponent(0)).setForeground(new Color(50, 80, 120));
        
        // Add panels to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame with padding
        add(mainPanel, BorderLayout.CENTER);
        
        // Add a title label at the top
        Label titleLabel = new Label("Course Management System", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(30, 60, 100));
        titleLabel.setBackground(new Color(200, 220, 240));
        titleLabel.setPreferredSize(new Dimension(800, 40));
        add(titleLabel, BorderLayout.NORTH);

        setVisible(true);
        setLocationRelativeTo(null); // Center the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }
    
    // Helper method to create styled labels
    private Label createStyledLabel(String text, Font font, Color color) {
        Label label = new Label(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }
    
    // Helper method to create styled buttons
    private Button createStyledButton(String text, Color bgColor) {
        Button button = new Button(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(180, 30));
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Add Course":
                addCourse();
                break;
            case "Remove Course":
                removeCourse();
                break;
            case "Register Student":
                registerStudent();
                break;
            case "Remove Student":
                removeStudent();
                break;
            case "View Registered Students":
                viewRegisteredStudents();
                break;
            case "Search Student":
                searchStudentByEmail();
                break;
            case "View Enrollment Count":
                viewEnrollmentCount();
                break;
            case "Save Data":
                saveData();
                break;
            case "Load Data":
                loadData();
                break;
        }
    }

    void addCourse() {
        String courseName = courseField.getText().trim();
        if (!courseName.isEmpty()) {
            if (courseCount < courses.length) { // Check array bounds
                courses[courseCount] = courseName;
                courseListComboBox.add(courseName);
                resultArea.append("Course added: " + courseName + "\n");
                courseField.setText("");
                courseCount++; // Increment course count
            } else {
                resultArea.append("Course list is full. Cannot add more courses.\n");
            }
        } else {
            resultArea.append("Course name cannot be empty.\n");
        }
    }

   void removeCourse() {
        String selectedCourse = courseListComboBox.getSelectedItem();
        if (selectedCourse != null) {
            for (int i = 0; i < courseCount; i++) {
                if (courses[i].equals(selectedCourse)) {
                    resultArea.append("Course removed: " + courses[i] + "\n");
                    courses[i] = courses[courseCount - 1]; // Shift last course to the current index
                    courseCount--; // Decrement course count
                    courseListComboBox.remove(selectedCourse);

                    // Remove students registered in the deleted course
                    for (int j = studentCount - 1; j >= 0; j--) {
                        if (studentCourses[j].equals(selectedCourse)) {
                            studentNames[j] = studentNames[studentCount - 1];
                            studentAges[j] = studentAges[studentCount - 1];
                            studentEmails[j] = studentEmails[studentCount - 1];
                            studentCourses[j] = studentCourses[studentCount - 1];
                            studentCount--;
                        }
                    }
                    break;
                }
            }
        } else {
            resultArea.append("No course selected!\n");
        }
    }

    void registerStudent() {
        String studentName = studentNameField.getText().trim();
        String age = studentAgeField.getText().trim();
        String email = studentEmailField.getText().trim();
        String selectedCourse = courseListComboBox.getSelectedItem();

        if (selectedCourse != null && !studentName.isEmpty() && !age.isEmpty() && !email.isEmpty()) {
            if (studentCount < studentNames.length) { // Check array bounds
                studentNames[studentCount] = studentName;
                studentAges[studentCount] = age;
                studentEmails[studentCount] = email;
                studentCourses[studentCount] = selectedCourse;
                resultArea.append("Registered " + studentName + " to " + selectedCourse + "\n");
                studentNameField.setText("");
                studentAgeField.setText("");
                studentEmailField.setText("");
                studentCount++; // Increment student count
            } else {
                resultArea.append("Student list is full. Cannot register more students.\n");
            }
        } else {
            resultArea.append("All fields must be filled!\n");
        }
    }

    void removeStudent() {
        String email = studentEmailField.getText().trim();
        if (!email.isEmpty()) {
            boolean found = false;
            for (int i = 0; i < studentCount; i++) {
                if (studentEmails[i].equals(email)) {
                    resultArea.append("Removed student: " + studentNames[i] + " from " + studentCourses[i] + "\n");
                    studentNames[i] = studentNames[studentCount - 1];
                    studentAges[i] = studentAges[studentCount - 1];
                    studentEmails[i] = studentEmails[studentCount - 1];
                    studentCourses[i] = studentCourses[studentCount - 1];
                    studentCount--; // Decrement student count
                    found = true;
                    break;
                }
            }
            if (!found) {
                resultArea.append("Student with email " + email + " not found.\n");
            }
        } else {
            resultArea.append("Please enter the student's email.\n");
        }
    }

    void viewRegisteredStudents() {
        String selectedCourse = courseListComboBox.getSelectedItem();
        if (selectedCourse != null) {
            resultArea.append("Registered Students for " + selectedCourse + ":\n");
            for (int i = 0; i < studentCount; i++) {
                if (studentCourses[i].equals(selectedCourse)) {
                    resultArea.append(" - " + studentNames[i] + "\n");
                }
            }
        } else {
            resultArea.append("No course selected!\n");
        }
    }

    void searchStudentByEmail() {
        String email = searchEmailField.getText().trim();
        if (!email.isEmpty()) {
            boolean found = false;
            for (int i = 0; i < studentCount; i++) {
                if (studentEmails[i].equals(email)) {
                    resultArea.append("Student found: " + studentNames[i] + ", Age: " + studentAges[i] + ", Course: " + studentCourses[i] + "\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                resultArea.append("Student with email " + email + " not found.\n");
            }
        } else {
            resultArea.append("Please enter an email to search.\n");
        }
    }

    void viewEnrollmentCount() {
        for (int i = 0; i < courseCount; i++) {
            int count = 0;
            for (int j = 0; j < studentCount; j++) {
                if (studentCourses[j].equals(courses[i])) {
                    count++;
                }
            }
            resultArea.append("Enrollment count for " + courses[i] + ": " + count + "\n");
        }
    }

    void saveData() {
        try (FileWriter writer = new FileWriter("course_data.txt")) {
            for (int i = 0; i < courseCount; i++) {
                writer.write("Course: " + courses[i] + "\n");
                for (int j = 0; j < studentCount; j++) {
                    if (studentCourses[j].equals(courses[i])) {
                        writer.write(" - " + studentNames[j] + ", " + studentAges[j] + ", " + studentEmails[j] + "\n");
                    }
                }
            }
            resultArea.append("Data saved successfully.\n");
        } catch (IOException ex) {
            resultArea.append("Error saving data.\n");
        }
    }

    void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("course_data.txt"))) {
            String line;
            courseCount = 0;
            studentCount = 0;
            courseListComboBox.removeAll();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Course: ")) {
                    courses[courseCount] = line.substring(8);
                    courseListComboBox.add(courses[courseCount]);
                    courseCount++;
                } else if (line.startsWith(" - ")) {
                    String[] studentData = line.substring(3).split(", ");
                    studentNames[studentCount] = studentData[0];
                    studentAges[studentCount] = studentData[1];
                    studentEmails[studentCount] = studentData[2];
                    studentCourses[studentCount] = courses[courseCount - 1]; // Last course added
                    studentCount++;
                }
            }
            resultArea.append("Data loaded successfully.\n");
        } catch (IOException ex) {
            resultArea.append("Error loading data.\n");
        }
    }

    public static void main(String[] args) {
        new CourseManagementSystemGUI();
    }
}