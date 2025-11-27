# 📋 Task Tracker Application

A full-stack task management application built with Spring Boot and React, demonstrating backend API development, frontend design, containerization, and database management.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.8-brightgreen?style=flat-square&logo=spring)
![React](https://img.shields.io/badge/React-18.3-blue?style=flat-square&logo=react)
![TypeScript](https://img.shields.io/badge/TypeScript-5.5-blue?style=flat-square&logo=typescript)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-blue?style=flat-square&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?style=flat-square&logo=docker)

## 🎯 Project Overview

A task management system that allows users to organize their work through task lists and individual tasks. Features a minimal React and NextUI frontend, with a Spring Boot REST API backend and PostgreSQL database.

## ✨ Key Features

### 📱 Frontend
- Built with React 18.3, TypeScript, and Vite
- NextUI component library with Tailwind CSS styling
- React Router for multi-page navigation
- Progress bars showing task completion percentages
- Task management features: 
  - Status toggling with checkboxes
  - Edit and delete functionality
  - Priority and due date display
- Custom AppProvider for application state management

### 🔧 Backend
- RESTful API with Spring Boot 3.5.8 and Java 21
- Layered architecture:
  - **Controllers**: HTTP request handling
  - **Services**: Business logic with interface-based design
  - **Repositories**: Data access using Spring Data JPA
  - **Mappers**: DTO pattern for data transfer
- JPA entities with relationships (One-to-Many, Many-to-One)
- Global exception handling with custom error responses
- UUID-based identifiers

### 🗄️ Database
- PostgreSQL database
- Hibernate for schema management (DDL auto-update)
- Entity relationships: 
  - TaskList to Tasks (One-to-Many with cascade operations)
  - Foreign key constraints with lazy loading
- Data model includes:
  - Tasks with title, description, due dates, priority, and status
  - Automatic timestamp tracking (created/updated)
  - Task lists with calculated progress and task count

### 🐳 Deployment
- Docker Compose setup with three containers:
  - PostgreSQL database with health checks
  - Spring Boot backend
  - React frontend (Vite dev server)
- Service dependencies and networking configured
- Externalized environment configuration
- Persistent database storage with Docker volumes

## 🏗️ Technical Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                       Frontend Layer                         │
│  React + TypeScript + NextUI + Tailwind CSS + Vite         │
│              (Port 5173 - Docker Container)                  │
└────────────────────┬────────────────────────────────────────┘
                     │ HTTP/REST API
                     │
┌────────────────────▼────────────────────────────────────────┐
│                      Backend Layer                           │
│           Spring Boot 3.5.8 + Java 21                       │
│     Controllers → Services → Repositories                    │
│              (Port 8080 - Docker Container)                  │
└────────────────────┬────────────────────────────────────────┘
                     │ JPA/Hibernate
                     │
┌────────────────────▼────────────────────────────────────────┐
│                    Database Layer                            │
│              PostgreSQL (Latest)                             │
│            (Port 15432 - Docker Container)                   │
└─────────────────────────────────────────────────────────────┘
```

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.5.8
- **Language**: Java 21
- **ORM**: Spring Data JPA with Hibernate
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Additional Libraries**: Lombok for reducing boilerplate

### Frontend
- **Framework**: React 18.3
- **Language**: TypeScript 5.5
- **Build Tool**: Vite
- **UI Library**: NextUI 2.4.8
- **Styling**: Tailwind CSS 3.4
- **Routing**: React Router DOM 6.27
- **HTTP Client**: Axios
- **Icons**: Lucide React
- **Animations**: Framer Motion

### Infrastructure
- **Containerization**: Docker & Docker Compose
- **Database**: PostgreSQL (containerized)
- **Development**: Hot reload enabled for both frontend and backend

## 📋 API Endpoints

### Task Lists
```
GET    /task-lists              - Retrieve all task lists
POST   /task-lists              - Create a new task list
GET    /task-lists/{id}         - Get specific task list
PUT    /task-lists/{id}         - Update task list
DELETE /task-lists/{id}         - Delete task list
```

### Tasks
```
GET    /task-lists/{listId}/tasks            - Get all tasks in a list
POST   /task-lists/{listId}/tasks            - Create new task
GET    /task-lists/{listId}/tasks/{taskId}   - Get specific task
PUT    /task-lists/{listId}/tasks/{taskId}   - Update task
DELETE /task-lists/{listId}/tasks/{taskId}   - Delete task
```

## 🚀 Getting Started

### Prerequisites
- Docker & Docker Compose installed
- Ports 5173, 8080, and 15432 available

### Quick Start
1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd task-tracker-app
   ```

2. **Launch the application**
   ```bash
   docker-compose up --build
   ```

3. **Access the application**
   - Frontend: http://localhost:5173
   - Backend API: http://localhost:8080
   - Database: localhost:15432

### Development Setup

#### Backend Development
```bash
cd task-tracker-backend
./mvnw spring-boot:run
```

#### Frontend Development
```bash
cd task-tracker-frontend
npm install
npm run dev
```

## 📊 Data Models

### Task Entity
- **id**: UUID (Primary Key)
- **title**: String (Required)
- **description**: Text
- **dueDate**: DateTime (Required)
- **priority**: Enum (LOW, MEDIUM, HIGH)
- **status**: Enum (OPEN, IN_PROGRESS, CLOSED)
- **created**: DateTime (Auto-managed)
- **updated**: DateTime (Auto-managed)
- **taskList**: Many-to-One relationship

### TaskList Entity
- **id**: UUID (Primary Key)
- **title**: String (Required)
- **description**: Text
- **tasks**: One-to-Many relationship with cascade operations
- **created**: DateTime (Auto-managed)
- **updated**: DateTime (Auto-managed)
- **count**: Computed field (number of tasks)
- **progress**: Computed field (completion percentage)

## 🎨 UI Features

- Task list dashboard with progress indicators
- Task management view with inline editing
- Forms for creating and editing task lists and tasks
- Progress bars, status indicators, and priority badges
- Responsive design
- ARIA labels for accessibility

## 🧪 Testing

The project includes test infrastructure:
- JUnit test setup for backend
- H2 in-memory database for testing
- Test application properties configuration

## 📦 Project Structure

```
task-tracker-app/
├── docker-compose.yml
├── task-tracker-backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/akashmudgal/tasktrackerapp/
│   │   │   │   ├── controllers/     # REST endpoints
│   │   │   │   ├── services/        # Business logic
│   │   │   │   ├── repositories/    # Data access
│   │   │   │   ├── domain/          # Entities & DTOs
│   │   │   │   └── mappers/         # DTO mappers
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
└── task-tracker-frontend/
    ├── src/
    │   ├── components/          # React components
    │   ├── domain/              # TypeScript interfaces
    │   ├── App.tsx              # Main app component
    │   └── AppProvider.tsx      # State management
    ├── package.json
    └── vite.config.ts
```

## 🔒 Security Notes

- UUID-based resource identifiers (non-sequential)
- Exception handling with custom error responses
- Externalized database credentials
- Note: This is a portfolio project without authentication - not intended for production use without additional security measures

## 🚧 Future Enhancements

Potential features for expansion:
- User authentication & authorization (Spring Security + JWT)
- Task categories and tags
- File attachments for tasks
- Task comments and collaboration
- Email notifications for due dates
- Search and filtering capabilities
- Task templates


---

**Built with ❤️ using Spring Boot, React, and Docker**
