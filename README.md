# Maksite

A personal website built with **Spring Boot** and **Vaadin**, demonstrating Java web application development patterns including dependency injection with different bean scopes and embedded database connectivity.

---

## Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Views & Navigation](#views--navigation)
- [Bean Scopes](#bean-scopes)
- [Database](#database)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Build](#build)
  - [Run](#run)
- [Configuration](#configuration)
- [Testing](#testing)

---

## Overview

Maksite is a Spring Boot + Vaadin web application that serves as a personal website. It demonstrates:

- **Multi-view navigation** with a persistent top navigation bar
- **Spring dependency injection** with different bean lifecycle scopes (UI scope and Route/View scope)
- **Embedded Apache Derby database** connectivity via JDBC
- **Vaadin Flow** for building server-side rendered Java web UIs

The application runs on **http://localhost:8085** and provides three navigable views accessible from the header.

---

## Technology Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| Java | 25 | Programming language |
| Spring Boot | 4.0.3 | Application framework and auto-configuration |
| Vaadin Flow | 25.0.5 | Server-side Java web UI framework |
| Apache Derby | 10.17.1.0 | Embedded relational database |
| Maven | 3.6+ | Build tool and dependency management |
| JUnit 5 | (via Spring Boot parent) | Testing framework |

---

## Project Structure

```
maksite/
├── pom.xml                                     # Maven build configuration
├── src/
│   ├── main/
│   │   ├── java/com/makrand/
│   │   │   ├── MaksiteApplication.java         # Spring Boot entry point
│   │   │   ├── DBUtils.java                    # Embedded Derby DB utility
│   │   │   ├── services/
│   │   │   │   ├── Greeter.java                # UI-scoped service bean
│   │   │   │   └── ViewGreeter.java            # View/Route-scoped service bean
│   │   │   └── view/
│   │   │       ├── MainLayout.java             # App layout with navigation header
│   │   │       ├── DefaultView.java            # Home page ("/")
│   │   │       ├── UIScopedView.java           # UI scoped view ("/ui")
│   │   │       └── ViewScopedView.java         # View scoped view ("/view")
│   │   └── resources/
│   │       └── application.properties          # Server configuration
│   └── test/
│       └── java/com/makrand/
│           └── MaksiteApplicationTests.java    # Spring context load test
└── codejava/webdb1/                            # Embedded Derby database files
```

---

## Views & Navigation

All views share a common layout (`MainLayout`) that renders a full-width header containing the application title **"Maksite"** and navigation links to each view.

### Default View (`/`)
- **Class:** `DefaultView`
- **Page Title:** Default View
- Displays a simple welcome paragraph: *"This is the default view."*
- Serves as the home page of the application.

### UI Scoped View (`/ui`)
- **Class:** `UIScopedView`
- **Page Title:** UI Scoped View
- Annotated with `@UIScope`, meaning the view instance is tied to the Vaadin UI lifecycle.
- Injects a `Greeter` bean and displays its greeting, including the bean's object identity (memory address), demonstrating that the same bean instance is reused for the lifetime of the UI session.

### View Scoped View (`/view`)
- **Class:** `ViewScopedView`
- **Page Title:** View Scoped View
- Injects both a `ViewGreeter` (route-scoped) and a `Greeter` (UI-scoped) bean.
- Displays three paragraphs: a static message, the view-scoped greeting, and the UI-scoped greeting.
- Demonstrates the difference between view-scoped and UI-scoped beans side by side.

---

## Bean Scopes

The application uses two Vaadin Spring bean scopes to illustrate how Spring manages component lifecycles:

### `Greeter` — UI Scope (`@UIScope`)
```java
@SpringComponent
@UIScope
public class Greeter {
    public String sayHello() {
        return "Hello from bean " + toString();
    }
}
```
- One instance is created per Vaadin UI (browser tab/session).
- The same instance is shared across all views within the same UI.
- Object identity (`toString()`) will remain constant as you navigate between views within the same session.

### `ViewGreeter` — Route Scope (`@RouteScope` + `@RouteScopeOwner`)
```java
@SpringComponent
@RouteScope
@RouteScopeOwner(ViewScopedView.class)
public class ViewGreeter {
    public String sayHello() {
        return "Hello from a view scoped bean " + toString();
    }
}
```
- One instance is created per navigation to `ViewScopedView`.
- The instance is destroyed when the user navigates away from the view.
- Object identity (`toString()`) will change on each new navigation to the view.

---

## Database

`DBUtils` provides a utility class for connecting to the embedded **Apache Derby** database.

```java
String dbURL1 = "jdbc:derby:codejava/webdb1;create=true";
Connection conn1 = DriverManager.getConnection(dbURL1);
```

- **Driver:** `org.apache.derby.jdbc.EmbeddedDriver`
- **Database path:** `codejava/webdb1/` (relative to project root)
- The `create=true` parameter automatically creates the database directory and files if they do not already exist.
- `DBUtils.main()` can be run standalone to verify database connectivity.

---

## Getting Started

### Prerequisites

- **Java 25** or later ([Download](https://jdk.java.net/25/))
- **Maven 3.6+** ([Download](https://maven.apache.org/download.cgi))

Verify your installation:
```bash
java -version
mvn -version
```

### Build

```bash
# Clone the repository
git clone https://github.com/youinmak/maksite.git
cd maksite

# Build (skip tests for a faster build)
mvn clean package -DskipTests
```

### Run

**Option 1 — Using Maven (recommended for development):**
```bash
mvn spring-boot:run
```

**Option 2 — Running the packaged JAR:**
```bash
java -jar target/maksite-0.0.1-SNAPSHOT.jar
```

Once started, open your browser and navigate to:

```
http://localhost:8085
```

Use the navigation links in the header to explore the three views:

| URL | View |
|-----|------|
| `http://localhost:8085/` | Default View |
| `http://localhost:8085/ui` | UI Scoped View |
| `http://localhost:8085/view` | View Scoped View |

---

## Configuration

Application configuration is managed in `src/main/resources/application.properties`:

| Property | Value | Description |
|----------|-------|-------------|
| `server.port` | `8085` | The port the embedded Tomcat server listens on |

To change the port, update `application.properties`:
```properties
server.port=8085
```

---

## Testing

The project uses **JUnit 5** via the Spring Boot Test starter.

**Run all tests:**
```bash
mvn test
```

The existing test (`MaksiteApplicationTests`) verifies that the Spring application context loads successfully without errors.

