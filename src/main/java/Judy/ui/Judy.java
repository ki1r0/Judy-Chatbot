package Judy.ui;

import Judy.command.Command;
import Judy.task.TaskList;
import Judy.util.JudyException;
import Judy.util.Storage;

/**
 * The main class for the Judy chatbot application.
 * Manages user interactions, task processing, and storage.
 */
public class Judy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Judy chatbot instance with the specified file path for storage.
     *
     * @param filePath The file path used for loading and saving tasks.
     */
    public Judy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks(), storage);
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            storage.saveTasks(tasks.getTasks());
            return response;
        } catch (JudyException e) {
            return "Error: " + e.getMessage();
        }
    }
}


