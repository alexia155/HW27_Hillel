import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDao {
    private static final String INSERT_LESSON_SQL = "INSERT INTO Lesson (name, homework_id) VALUES (?, ?)";
    private static final String DELETE_LESSON_SQL = "DELETE FROM Lesson WHERE id=?";
    private static final String SELECT_ALL_LESSONS_SQL = "SELECT * FROM Lesson";
    private static final String SELECT_LESSON_BY_ID_SQL = "SELECT * FROM Lesson WHERE id=?";
    private static final String SELECT_HOMEWORK_BY_ID_SQL = "SELECT * FROM Homework WHERE id=?";
        public void addLesson(Lesson lesson) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LESSON_SQL)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setInt(2, lesson.getHomework().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLesson(int lessonId) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LESSON_SQL)) {
            preparedStatement.setInt(1, lessonId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LESSONS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int homeworkId = resultSet.getInt("homework_id");
                Homework homework = getHomeworkById(homeworkId);
                Lesson lesson = new Lesson(id, name, homework);
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public Homework getHomeworkById(int homeworkId) {
        Homework homework = null;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOMEWORK_BY_ID_SQL)) {
            preparedStatement.setInt(1, homeworkId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                homework = new Homework(id, name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homework;
    }

    public Lesson getLessonById(int lessonId) {
        Lesson lesson = null;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LESSON_BY_ID_SQL)) {
            preparedStatement.setInt(1, lessonId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int homeworkId = resultSet.getInt("homework_id");
                Homework homework = getHomeworkById(homeworkId);
                lesson = new Lesson(id, name, homework);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }
}