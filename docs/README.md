# Judy Chatbot - User Guide

## 1. Introduction

Welcome to Judy Chatbot, a task management assistant that helps you organize To-Dos, Deadlines, and Events. You can interact with Judy via a Graphical User Interface (GUI) or Command-Line Interface (CLI).

## 2. Installation & Setup

### Prerequisites

- Java 11 or higher installed 

- JavaFX installed (for GUI mode)

- An IDE (IntelliJ IDEA, Eclipse) or command-line terminal

### Installation Steps

💻 Run via Terminal (CLI Mode)
```
git clone https://github.com/your-repo/judy-chatbot.git
cd judy-chatbot
java -jar judy.jar
```

🖥️ Run via IntelliJ (GUI Mode)

1. Open the project in IntelliJ IDEA.
2. Run Launcher.java inside the Judy.ui package.

## 3. Features

✅ Task Management - Add, delete, and list tasks

✅ Three Task Types - To-Do, Deadline, and Event

✅ Natural Date Parsing - Recognizes multiple date formats

✅ Save & Load Tasks - Tasks persist between sessions

✅ GUI Interface - Simple and interactive

## 4. Available Commands

### 📌 Adding Tasks
You can add different types of tasks using the following commands. 

Multiple date formats are supported for deadlines and events.

#### Supported Date Formats:

- d/MM/yyyy HHmm → 12/03/2025 1800
- d/M/yyyy HHmm → 5/3/2025 0930
- yyyy-MM-dd HHmm → 2025-03-12 1800
- yyyy-MM-dd → 2025-03-12
- MMM d yyyy (English month names) → Mar 12 2025
- Weekday names (Monday to Sunday) → deadline submit report /by Friday

| Command                                       | Description | Example |
|-----------------------------------------------|------------|---------|
| `todo <description>`                          | Adds a **To-Do task** | `todo read book` |
| `deadline <description> /by <date>`         | Adds a **Deadline task** | `deadline submit report /by 12/03/2025 1800` |
| `event <description> /from <start> /to <end>` | Adds an **Event task** | `event project meeting /from 15/03/2025 /to 16/03/2025` |

---

### 📌 Listing Tasks

| Command | Description | Example |
|---------|------------|---------|
| `list` | Shows all tasks | `list` |

---

### 📌 Marking and Deleting Tasks

| Command | Description | Example |
|---------|------------|---------|
| `mark <task number>` | Marks a task as done | `mark 1` |
| `unmark <task number>` | Unmarks a completed task | `unmark 1` |
| `delete <task number>` | Deletes a task | `delete 2` |

---

### 📌 Searching Tasks

| Command | Description | Example |
|---------|------------|---------|
| `find <keyword>` | Finds tasks matching keyword | `find meeting` |

---

### 📌 Exiting the Program

| Command | Description | Example |
|---------|------------|---------|
| `bye` | Saves tasks and exits | `bye` |

---

## 5. Troubleshooting

### ⚠️ "Invalid event format"

Ensure you're using **`/from` and `/to`** correctly:

```sh
event <description> /from <start> /to <end>
```
✅ Example:
```sh
event team meeting /from Monday /to Friday
```

### ⚠️ "Cannot invoke String.trim() because dateTime is null"
Ensure the date format is supported.

✅ Supported Formats:

```sh
d/MM/yyyy HHmm
d/M/yyyy HHmm
yyyy-MM-dd HHmm
yyyy-MM-dd
```

❌ Unsupported:

```sh
"tomorrow"
"next week"
```

### ⚠️ "java.lang.reflect.InvocationTargetException"

- This error usually happens inside JavaFX.
- Run MainWindow.java and check logs for the root cause.
- Common issues:
  - Missing MainWindow.fxml
  - Incorrect fx:controller="Judy.ui.MainWindow"

## 6. FAQ

### Q1: How does Judy save tasks?

Judy automatically saves tasks in ./data/judy.txt.

### Q2: How do I delete all tasks?

Manually delete judy.txt or use delete <task number> repeatedly.

### Q3: What happens if I enter an invalid command?

Judy will prompt:

```
Unknown command. Please try again with a valid command. 
```

## 7. Credits

👨‍💻 Developed by Sun Siliang

🔗 [GitHub Pages](https://github.com/ki1r0/ip)
