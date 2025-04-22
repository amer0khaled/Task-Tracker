# 📝 Task Tracker

A simple task management RESTful API built with **Spring Boot**.  
This project allows users to manage task lists and tasks with support for CRUD operations, priority, status, and progress tracking.

---

## 🚀 Features

- Create, read, update, and delete **Task Lists**
- Create, read, update, and delete **Tasks** under specific Task Lists
- Track task status (`OPEN`, `IN_PROGRESS`, `CLOSED`)
- Calculate progress of each task list based on closed tasks

---

## 📦 Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 / PostgreSQL (configurable)
- Maven

---

## 📂 Project Structure

```
com.amerkhaled.tasktracker
💼— controllers        # REST API endpoints
💼— services           # Business logic
💼— repositories       # JPA repositories
💼— domain             # Entities and DTOs
💼— mappers            # Entity <-> DTO conversion
💼— config             # Configuration classes (if any)
```

---

## 🔧 Setup & Run

```bash
# Clone the repo
git clone https://github.com/your-username/task-tracker.git
cd task-tracker

# Run the app
./mvnw spring-boot:run
```

Visit: `http://localhost:8080`

---

## 🔌 Example Endpoints

- `GET /task-lists` – List all task lists
- `POST /task-lists` – Create a new task list
- `GET /task-lists/{task_list_id}/tasks` – List tasks in a task list
- `POST /task-lists/{task_list_id}/tasks` – Create a new task



---

## 📃 License

[MIT License](LICENSE)

