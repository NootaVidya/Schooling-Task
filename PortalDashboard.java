import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class PortalDashboard {
    private static Map<String, User> registeredUsers = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String loggedInUser;
    private static Map<String, String> studentAttendanceRecords = new HashMap<>();
    private static Map<String, String> staffAttendanceRecords = new HashMap<>();
    private static Map<String, Query> queriesAndAnswer = new HashMap<>();
    private static final List<String> notices = new ArrayList<>();
    private static String attendence;

    public static void main(String[] args) {

        System.out.println("Welcome to the Portal Dashboard!");
        while (true) {
            System.out.println("\nSelect User Type:");
            System.out.println("1. Admin");
            System.out.println("2. Staff");
            System.out.println("3. Parent");
            System.out.println("4. Student");
            System.out.println("5. Exit");
            int choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1:
                    adminDashboard();
                    break;
                case 2:
                    staffDashboard();
                    break;
                case 3:
                    parentDashboard();
                    break;
                case 4:
                    studentDashboard();
                    break;
                case 5:
                    System.out.println("Exiting the Portal Dashboard. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }




    private static void adminDashboard() {
        while (true) {
            System.out.println("\nAdmin Dashboard:");
            System.out.println("1. Enter Credentials");
            System.out.println("2. Admin Home Page");
            System.out.println("3. Logout");
            int choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1:
                    enterCredentials();
                    break;
                case 2:
                    adminHomePage();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void enterCredentials() {
        System.out.println("Enter Credentials:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (isValidAdminCredentials(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
    private static boolean isValidAdminCredentials(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }


    private static void adminHomePage() {
        while (true) {
            System.out.println("\nAdmin Home Page:");
            System.out.println("1. View Staff Attendance");
            System.out.println("2. View Student Attendance");
            System.out.println("3. Add Notice");
            System.out.println("4. viewNotices");
            System.out.println("5. Edit Fee Structure");
            System.out.println("6. View Student Queries");
            System.out.println("7. View Staff Queries");
            System.out.println("8. Logout");
            int choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1:
                    viewStaffAttendance();
                    break;
                case 2:
                    viewStudentAttendance();
                    break;
                case 3:
                    addNotice();
                    break;
                case 4:
                    viewNotices();
                    break;
                case 5:
                    editFeeStructure();
                    break;
                case 6:
                    viewStudentQueries();
                    break;
                case 7:
                    viewStaffQueries();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void viewStaffAttendance() {
        System.out.println("\nStaff Attendance:");
        for (Map.Entry<String, User> entry : registeredUsers.entrySet()) {
            if (!entry.getValue().getSubjectRole().equals("student")) {
                System.out.println("Username: " + entry.getKey() + ", Subject Role: " + entry.getValue().getSubjectRole());
            }
        }
    }
    private static void viewStudentAttendance() {
        System.out.println("\nStudent Attendance:");
        for (Map.Entry<String, String> entry : studentAttendanceRecords.entrySet()) {
            String username = entry.getKey();
            if (registeredUsers.containsKey(username) && registeredUsers.get(username).getSubjectRole().equals("student")) {
                System.out.println("Username: " + username + ", Roll Number: " + registeredUsers.get(username).getRollNumber() + ", Attendance: " + entry.getValue());
            }
        }
        viewAttendance();
    }
    private static void addNotice() {
        System.out.println("\nAdd Notice:");
        System.out.print("Enter notice message: ");
        String notice = scanner.nextLine();
        notices.add(notice);
        System.out.println("Notice added successfully!");
    }
    private static void viewNotices() {
        System.out.println("\nNotices:");
        if (notices.isEmpty()) {
            System.out.println("No notices available.");
        } else {
            for (String notice : notices) {
                System.out.println(notice);
            }
        }
    }
    private static Map<String, String> queriesAndAnswers = new HashMap<>();
    private static void viewStudentQueries() {
        System.out.println("\nStudent Queries:");
        for (Map.Entry<String, String> entry : queriesAndAnswers.entrySet()) {
            String queryText = entry.getKey();
            String submitter = entry.getValue();
            System.out.println("Query: " + queryText);
            System.out.println("Submitter: " + submitter);
            System.out.print("Do you want to answer this query? (yes/no): ");
            String answerOption = scanner.nextLine();
            if (answerOption.equalsIgnoreCase("yes")) {
                System.out.print("Enter your answer: ");
                String answer = scanner.nextLine();
                queriesAndAnswers.put(queryText, answer);
                System.out.println("Query answered successfully!");
            }
            System.out.println();
        }
    }
    private static void viewStaffQueries() {
        System.out.println("\nStaff Queries:");
        for (Map.Entry<String, String> entry : queriesAndAnswers.entrySet()) {
            String queryText = entry.getKey();
            String submitter = entry.getValue();
            System.out.println("Query: " + queryText);
            System.out.println("Submitter: " + submitter);
            System.out.print("Do you want to answer this query? (yes/no): ");
            String answerOption = scanner.nextLine();
            if (answerOption.equalsIgnoreCase("yes")) {
                System.out.print("Enter your answer: ");
                String answer = scanner.nextLine();
                queriesAndAnswers.put(queryText, answer);
                System.out.println("Query answered successfully!");
            }
        }
        System.out.println();
    }


    private static void parentDashboard() {


        boolean exit = false;
        while (!exit){
            System.out.println("Select an option:");
            System.out.println("1. About Us");
            System.out.println("2. Contact Us");
            System.out.println("3. Schools");
            System.out.println("4. Collages & University");
            System.out.println("5. Awards & Certifications");
            System.out.println("6. Notice");
            System.out.println("7. Career");
            System.out.println("8. Faculty Details");
            System.out.println("9. Reviews & Ratings");
            System.out.println("10. Support");
            System.out.println("11. logout");

            int choice = getIntInput("Enter your choice:");

            switch (choice) {
                case 1:
                    AboutUs();
                    break;
                case 2:
                    ContactUs();
                    break;
                case 3:
                    Schools();
                    break;
                case 4:
                    CollagesandUniversity();
                    break;
                case 5:
                    AwardsandCertifications();
                    break;
                case 6:
                    Noticee();
                    break;
                case 7:
                    Career();
                    break;
                case 8:
                    FacultyDetails();
                    break;
                case 9:
                    ReviewsandRatings();
                    break;
                case 10:
                    Support();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        }
    }




    private static List<String> queries = new ArrayList<>();
    private static void Support() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Support Page");
            System.out.println("We are here to support you with any queries or issues you may have.");
            System.out.println("Email: support@example.com");
            System.out.println("Phone: +1 (123) 456-7891");
            System.out.println("You can ask your query below:");
            System.out.println("Type 'exit' to go back to the previous menu.");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                exit = true;
            } else {

                queries.add(input);
                System.out.println("Query sent successfully to the support team.");
            }
        }
    }

    private static void ReviewsandRatings() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nReviews and Ratings:");
            System.out.println("1. View Reviews and Ratings");
            System.out.println("2. Add Review and Rating");
            System.out.println("3. Go Back");

            int choice = getIntInput("Enter your choice:");

            switch (choice) {
                case 1:
                    viewReviewsAndRatings();
                    break;
                case 2:
                    addReviewAndRating();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
    private static List<String> reviews = new ArrayList<>();
    private static List<Integer> ratings = new ArrayList<>();

    private static void viewReviewsAndRatings() {
        if (reviews.isEmpty()) {
            System.out.println("No reviews available.");
        } else {
            for (int i = 0; i < reviews.size(); i++) {
                System.out.println("Review " + (i + 1) + ": " + reviews.get(i));
                System.out.println("Rating: " + ratings.get(i) + "/5");
                System.out.println("------------------------");
            }
        }
    }

    private static void addReviewAndRating() {
        System.out.print("Enter your review: ");
        String review = scanner.nextLine();
        int rating;
        do {
            rating = getIntInput("Enter your rating (1-5): ");
            if (rating < 1 || rating > 5) {
                System.out.println("Invalid rating. Please enter a number between 1 and 5.");
            }
        } while (rating < 1 || rating > 5);

        reviews.add(review);
        ratings.add(rating);
        System.out.println("Thank you for your review and rating!");
    }


    private static void AwardsandCertifications() {
        System.out.println("\nAwards and Certifications:");
        System.out.println("1. Best Educational Institution Award 2023");
        System.out.println("2. Excellence in Teaching Award");
        System.out.println("3. Outstanding Student Support Services");
        System.out.println("4. ISO 9001 Certification");
        System.out.println("5. National Accreditation Board Certification");
    }

    private static void CollagesandUniversity() {
        System.out.println("\nColleges and Universities:");
        System.out.println("1. Harvard University");
        System.out.println("2. Stanford University");
        System.out.println("3. MIT");
        System.out.println("4. University of California, Berkeley");
        System.out.println("5. Yale University");
    }

    private static void Schools() {
        System.out.println("\nSchools:");
        System.out.println("1. St. Xavier’s Collegiate School, Kolkata");
        System.out.println("2. La Martiniere For Girls School, Kolkata");
        System.out.println("3. The Doon School, Dehradun");
        System.out.println("4. Shree Swaminarayan Gurukul International School, Hyderabad");
        System.out.println("5. Little Flower High School, Hyderabad");
        System.out.println("6. Mother Teresa High School, Hyderabad");
    }

    private static void Noticee() {
        System.out.println("\nNotices:");
        if (notices.isEmpty()) {
            System.out.println("No notices available.");
        } else {
            for (String notice : notices) {
                System.out.println(notice);
            }
        }
    }


    private static void FacultyDetails() {
        System.out.println("\nSubjects:");
        System.out.println("Regular Subjects Schedule:");
        System.out.println("i) English - Sudhir");
        System.out.println("ii) Maths - Sunil");
        System.out.println("iii) Science - Helini");
        System.out.println("iv) Social - Sijo");

    }

    private static void Career() {
        System.out.println("Student Career Opportunities");
        System.out.println("----------------------------");
        System.out.println("Explore various career opportunities for students:");
        System.out.println("- Internships");
        System.out.println("- Job Placements");
        System.out.println("- Further Education");
        System.out.println("- Career Counseling");
        showImagea();
    }
    private static void showImagea() {
        // Load the image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("D:\\Numetry Internship\\additional features\\src\\C.jpg")); // Adjust the path accordingly
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Scale the image to fit the dialog box
        int dialogWidth = 400; // Set the desired width of the dialog
        int dialogHeight = 300; // Set the desired height of the dialog
        Image scaledImg = img.getScaledInstance(dialogWidth, dialogHeight, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(scaledImg);

        // Display the scaled image in a JOptionPane
        JLabel imageLabel = new JLabel(imageIcon);
        JOptionPane.showMessageDialog(null, imageLabel, "Carrer - Image", JOptionPane.INFORMATION_MESSAGE);
    }



    private static void ContactUs() {
        System.out.println("Contact Us Page");
        System.out.println("We are here to assist you!");
        System.out.println("If you have any questions or need assistance, feel free to contact us using the following information:");
        System.out.println("Email: info@example.com");
        System.out.println("Phone: +1 (123) 456-7890");
        System.out.println("Address: 123 College Street, City, Country");
        System.out.println("Our clg hours are Monday to Friday, 9:00 AM to 5:00 PM.");
        System.out.println("We look forward to hearing from you!");
        showImage();
    }
    private static void showImage() {
        // Load the image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("D:\\Numetry Internship\\additional features\\src\\B.png")); // Adjust the path accordingly
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Scale the image to fit the dialog box
        int dialogWidth = 400; // Set the desired width of the dialog
        int dialogHeight = 300; // Set the desired height of the dialog
        Image scaledImg = img.getScaledInstance(dialogWidth, dialogHeight, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(scaledImg);

        // Display the scaled image in a JOptionPane
        JLabel imageLabel = new JLabel(imageIcon);
        JOptionPane.showMessageDialog(null, imageLabel, "Contact Us - Image", JOptionPane.INFORMATION_MESSAGE);
    }



    private static void AboutUs() {
        System.out.println("Welcome to the Home");
        System.out.println("\nAbout:");
        System.out.println("College Name: ABC College");
        System.out.println("Contact: 1234567890");
        System.out.println("Email: info@abccollege.com");
        showImageb();
    }
    private static void showImageb() {
        // Load the image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("D:\\Numetry Internship\\additional features\\src\\D.png")); // Adjust the path accordingly
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Scale the image to fit the dialog box
        int dialogWidth = 400; // Set the desired width of the dialog
        int dialogHeight = 300; // Set the desired height of the dialog
        Image scaledImg = img.getScaledInstance(dialogWidth, dialogHeight, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(scaledImg);

        // Display the scaled image in a JOptionPane
        JLabel imageLabel = new JLabel(imageIcon);
        JOptionPane.showMessageDialog(null, imageLabel, "Contact Us - Image", JOptionPane.INFORMATION_MESSAGE);
    }




    private static void studentDashboard() {
        while (true) {
            System.out.println("\nStudent Dashboard:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            int choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1:
                    studentRegister();
                    break;
                case 2:
                    studentLogin();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void studentRegister() {
        System.out.println("\nStudent Register:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (registeredUsers.containsKey(username)) {
            System.out.println("Username already exists. Please try again.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Registration failed.");
            return;
        }
        registeredUsers.put(username, new User(username, password, rollNumber, attendence));
        System.out.println("Registration successful!");
    }
    private static void studentLogin() {
        System.out.println("\nStudent Login:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (!registeredUsers.containsKey(username)) {
            System.out.println("User not registered. Please register first.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = registeredUsers.get(username);
        if (!user.getPassword().equals(password)) {
            System.out.println("Invalid password. Please try again.");
            return;
        }

        System.out.println("Login successful!");

        loggedInUser = username;
        studentMenu();
    }
    private static void studentMenu() {
        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. About");
            System.out.println("2. Subjects");
            System.out.println("3. Fee Structure");
            System.out.println("4. Raise Query");
            System.out.println("5. View Answered Queries");
            System.out.println("6. Notice Board");
            System.out.println("7. Mark Attendance");
            System.out.println("8. View Attendance");
            System.out.println("9. Logout");

            int choice = getIntInput("Enter your choice:");

            switch (choice) {
                case 1:
                    about();
                    break;
                case 2:
                    timeTable();
                    break;
                case 3:
                    feeStructure();
                    break;
                case 4:
                    raiseQuery();
                    break;
                case 5:
                    viewAnsweredQueries();
                    break;
                case 6:
                    viewNoticeBoard();
                    break;
                case 7:
                    markAttendance();
                    break;
                case 8:
                    viewAttendance();
                    break;
                case 9:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

        private static void about() {
            System.out.println("\nAbout:");
            System.out.println("College Name: ABC College");
            System.out.println("Contact: 1234567890");
            System.out.println("Email: info@abccollege.com");
        }

    private static void timeTable() {
        System.out.println("\nSubjects:");
        System.out.println("Regular Subjects Schedule:");
        System.out.println("i) English - Sudhir");
        System.out.println("ii) Maths - Sunil");
        System.out.println("iii) Science - Helini");
        System.out.println("iv) Social - Sijo");
    }
    private static double tuitionFee = 25000.0;
    private static double examFee = 800.0;
    private static double busFee = 1100.0;
    private static void updateFeeComponent(String component, double newAmount) {
        switch (component) {
            case "Tuition Fee":
                tuitionFee = newAmount;
                break;
            case "Exam Fee":
                examFee = newAmount;
                break;
            case "Bus Fee":
                busFee = newAmount;
                break;
            default:
                System.out.println("Invalid fee component.");
                return;
        }
        System.out.println(component + " updated to $" + newAmount);
    }
    private static void feeStructure() {
        System.out.println("\nFee Structure:");
        System.out.println("i) Tuition Fee - $" + tuitionFee + " per year");
        System.out.println("ii) Exam Fee - $" + examFee);
        System.out.println("iii) Bus Fee - $" + busFee + " per year");
    }
    private static void editFeeStructure() {
        System.out.println("\nEdit Fee Structure:");
        feeStructure();
        System.out.println("Select a fee component to edit:");
        System.out.println("1. Tuition Fee");
        System.out.println("2. Exam Fee");
        System.out.println("3. Bus Fee");
        int choice = getIntInput("Enter your choice (1-3):");
        double newAmount;

        switch (choice) {
            case 1:
                newAmount = getDoubleInput("Enter the new amount for Tuition Fee:");
                updateFeeComponent("Tuition Fee", newAmount);
                break;
            case 2:
                newAmount = getDoubleInput("Enter the new amount for Exam Fee:");
                updateFeeComponent("Exam Fee", newAmount);
                break;
            case 3:
                newAmount = getDoubleInput("Enter the new amount for Bus Fee:");
                updateFeeComponent("Bus Fee", newAmount);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                editFeeStructure();
                return;
        }
        System.out.println("Updated Fee Structure:");
        feeStructure();
    }
    private static double getDoubleInput(String message) {
        while (true) {
            System.out.print(message + " ");
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    private static class Query {
        private String queryText;
        private String submitter;

        public Query(String queryText, String submitter) {
            this.queryText = queryText;
            this.submitter = submitter;
        }
        public String getQueryText() {
            return queryText;
        }
        public String getSubmitter() {
            return submitter;
        }
    }
    private static void raiseQuery() {
        System.out.println("\nRaise Query:");
        System.out.println("Please enter your query:");
        String queryText = scanner.nextLine();
        Query query = new Query(queryText, loggedInUser);
        queriesAndAnswers.put(queryText, query.getSubmitter());
        System.out.println("Query submitted successfully. Admin will respond shortly.");
    }
    private static void viewAnsweredQueries() {
        System.out.println("\nAnswered Queries:");
        boolean foundAnsweredQueries = false;
        for (Map.Entry<String, String> entry : queriesAndAnswers.entrySet()) {
            String queryText = entry.getKey();
            String answer = entry.getValue();
            if (!answer.equals("NOT_ANSWERED")) {
                foundAnsweredQueries = true;
                System.out.println("Query: " + queryText);
                System.out.println("Answer: " + answer);
                System.out.println();
            }
        }
        if (!foundAnsweredQueries) {
            System.out.println("No answered queries available.");
        }
    }
    private static void viewNoticeBoard() {
        System.out.println("\nNotice Board:");
        if (notices.isEmpty()) {
            System.out.println("No notices available.");
        } else {
            for (String notice : notices) {
                System.out.println(notice);
            }
        }
    }
    private static void markAttendance() {
        System.out.println("\nMark Attendance:");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        if (!registeredUsers.containsKey(username)) {
            System.out.println("User not registered. Please register first.");
            return;
        }
        System.out.print("Enter attendance (presence/absence): ");
        String attendance = scanner.nextLine();
        studentAttendanceRecords.put(username, attendance);
        System.out.println("Attendance recorded successfully!");
    }
    private static void viewAttendance() {
        System.out.println("\nAttendance:");
        for (Map.Entry<String, String> entry :studentAttendanceRecords.entrySet()) {
            System.out.println("user name: " + entry.getKey() + ", Attendance: " + entry.getValue());
        }
    }
    private static void staffDashboard() {
        while (true) {
            System.out.println("\nStaff Dashboard:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            int choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void register() {
        System.out.println("\nRegister:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (registeredUsers.containsKey(username)) {
            System.out.println("Username already exists. Please try again.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();
        System.out.print("Enter subject role: ");
        String subjectRole = scanner.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Registration failed.");
            return;
        }
        registeredUsers.put(username, new User(username, password, subjectRole, attendence));
        System.out.println("Registration successful!");
    }
    private static void login() {
        System.out.println("\nLogin:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (!registeredUsers.containsKey(username)) {
            System.out.println("User not registered. Please register first.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = registeredUsers.get(username);
        if (!user.getPassword().equals(password)) {
            System.out.println("Invalid password. Please try again.");
            return;
        }
        System.out.println("Login successful!");
        loggedInUser = username;
        staffMenu();
    }
    private static void staffMenu() {
        while (true) {
            System.out.println("\nStaff Menu:");
            System.out.println("1. Attendance for staff");
            System.out.println("2. viewStaffAttendance()");
            System.out.println("3. View Events or Notices");
            System.out.println("4. View Student Attendance");
            System.out.println("5. Raise Query");
            System.out.println("6. View Answered Queries");
            System.out.println("7. Logout");
            int choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1:
                    takeAttendance();
                    break;
                case 2:
                    viewStaffAttendance();
                    break;
                case 3:
                    NoticeBoard();
                    break;
                case 4:
                    viewStudentAttendance();
                    break;
                case 5:
                    raiseQuery();
                    break;
                case 6:
                    viewAnsweredQueries();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    loggedInUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void viewAttendanceStudent() {
        System.out.println("\nStudent Attendance:");
        for (Map.Entry<String, String> entry :studentAttendanceRecords.entrySet()) {
            String username = entry.getKey();
            if (registeredUsers.containsKey(username) && registeredUsers.get(username).getSubjectRole().equals("student")) {
                System.out.println("Username: " + username + ", Roll Number: " + registeredUsers.get(username).getRollNumber() + ", Attendance: " + entry.getValue());
            }
        }
        viewAttendance();
    }
    private static void takeAttendance() {
        System.out.println("\nMark Attendance:");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        if (!registeredUsers.containsKey(username)) {
            System.out.println("User not registered. Please register first.");
            return;
        }
        User user = registeredUsers.get(username);
        System.out.print("Enter attendance (present/absent): ");
        String attendance = scanner.nextLine();
        if (user.getSubjectRole().equals("staff")) {
            staffAttendanceRecords.put(username, attendance);
        }
        System.out.println("Attendance recorded successfully!");
    }
    private static void viewAttendanceStaff() {
        System.out.println("\nStaff Attendance:");
        for (Map.Entry<String, String> entry : staffAttendanceRecords.entrySet()) {
            String username = entry.getKey();
            if (registeredUsers.containsKey(username) && registeredUsers.get(username).getSubjectRole().equals("staff")) {
                System.out.println("Username: " + username + ", Attendance: " + entry.getValue());
            }
        }
    }
    private static void AskQuery() {
        System.out.println("\nRaise Query:");
        System.out.println("Please enter your query:");
        String queryText = scanner.nextLine();
        Query query = new Query(queryText, loggedInUser);
        queriesAndAnswers.put(queryText, "NOT_ANSWERED");
        System.out.println("Query submitted successfully. Admin will respond shortly.");
    }
    private static void viewAnswerQueries() {
        System.out.println("\nView Answered Queries:");
        boolean foundAnsweredQueries = false;
        for (Map.Entry<String, String> entry : queriesAndAnswers.entrySet()) {
            String queryText = entry.getKey();
            String submitter = entry.getValue();
            String answer = entry.getValue();
            if (!answer.equals("NOT_ANSWERED")) {
                foundAnsweredQueries = true;
                System.out.println("Query: " + queryText);
                System.out.println("Submitter: " + submitter);
                System.out.println("Answer: " + answer);
                System.out.println();
            }
        }
        if (!foundAnsweredQueries) {
            System.out.println("No queries have been answered yet.");
        }
    }
    private static void NoticeBoard() {
        System.out.println("\nNotice Board:");
        if (notices.isEmpty()) {
            System.out.println("No notices available.");
        } else {
            for (String notice : notices) {
                System.out.println(notice);
            }
        }
    }
    private static int getIntInput(String message) {
        while (true) {
            System.out.print(message + " ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    private static class User {
        private String username;
        private String password;
        private String subjectRole;
        public User(String username, String password, String subjectRole, String attendence) {
            this.username = username;
            this.password = password;
            this.subjectRole = subjectRole;
        }
        public String getPassword() {
            return password;
        }
        public String getSubjectRole() {
            return subjectRole;
        }
        public String getRollNumber() {
            return null;
        }
    }
}