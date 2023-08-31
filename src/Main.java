import java.util.List;

public class Main {

    public static void main(String[] args) {
        LessonDao lessonDao = new LessonDao();

        Homework homework = new Homework(4, "Homework 4", "Description 4");
        Lesson newLesson = new Lesson(4, "Lesson 4", homework);
        lessonDao.addLesson(newLesson);
        System.out.println("Added Lesson: " + newLesson.getName());

        List<Lesson> allLessons = lessonDao.getAllLessons();
        System.out.println("All Lessons:");
        for (Lesson lesson : allLessons) {
            System.out.println("ID: " + lesson.getId() + ", Name: " + lesson.getName());
        }

        int lessonIdToRetrieve = 1;
        Lesson retrievedLesson = lessonDao.getLessonById(lessonIdToRetrieve);
        if (retrievedLesson != null) {
            System.out.println("Retrieved Lesson by ID: " + retrievedLesson.getName());
        } else {
            System.out.println("Lesson not found for ID: " + lessonIdToRetrieve);
        }

        int lessonIdToDelete = 1;
        lessonDao.deleteLesson(lessonIdToDelete);
        System.out.println("Deleted Lesson with ID: " + lessonIdToDelete);
    }
}



