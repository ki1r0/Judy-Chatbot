package Judy;

import Judy.task.*;
import Judy.util.JudyException;
import Judy.util.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskListTest {
    private TaskList taskList;
    private Storage mockStorage;

    @BeforeEach
    void setUp() {
        mockStorage = mock(Storage.class);
        taskList = new TaskList(new ArrayList<>(), mockStorage);
    }

    @Test
    void testAddTask() throws JudyException {
        String[] description = {"read book"};
        taskList.addTask(description, TaskType.TODO, null, null, null);

        assertEquals(1, taskList.getTasks().size(), "Task list should contain one task");
        assertTrue(taskList.getTasks().get(0) instanceof Todo);
        assertEquals("read book", taskList.getTasks().get(0).getDescription());

        verify(mockStorage).saveTasks(anyList());
    }

    @Test
    void testDeleteTask_validIndex() throws JudyException {
        taskList.addTask(new String[]{"read book"}, TaskType.TODO, null, null, null);
        assertEquals(1, taskList.getTasks().size());

        taskList.deleteTask(1);
        assertEquals(0, taskList.getTasks().size(), "Task list should be empty after deletion");

        verify(mockStorage, atLeastOnce()).saveTasks(anyList());
    }
}