# i2i Academy - Apache Ignite (IMDG) - Homework 8

This project demonstrates the fundamentals of In-Memory Data Grids (IMDG) and Distributed Databases by setting up a single-node **Apache Ignite 3** cluster via Docker. It includes a modern Java Thin Client implementation to perform data initialization, insertion, and simulation of telecommunication usage updates.

---

## 🧠 Theoretical Knowledge

### 1. What is Apache Ignite, and how does its IMDG architecture differ from a pure key-value store like Redis?

Apache Ignite is an advanced In-Memory Data Grid (IMDG) that natively provides distributed SQL querying, ACID transactions, and complex compute capabilities directly in memory. Unlike a pure key-value store such as Redis, Ignite's architecture is specifically designed to handle complex relational data operations and computations across a distributed cluster.

### 2. What is the architectural difference between a Thick Client and a Thin Client?

Architecturally, a Thick Client joins the grid topology as a full node to store data and execute compute tasks locally. In contrast, a Thin Client is a lightweight external application that simply connects to the cluster via a standard socket without joining the grid, making it much easier to deploy and scale for standard data operations.

---

## 🛠️ Tech Stack & Prerequisites

- **Java:** Version 26
- **Build Tool:** Maven
- **Database:** Apache Ignite 3 (via Docker)
- **Client:** Ignite Thin Client (ignite-core 2.16.0)

---

## 🐳 Environment Setup (Docker Compose)

The local Ignite node is containerized. Run the following command to spin up the cluster:

```bash
docker-compose up -d
```

- Thin Client Port: 10800

## 🚀 Execution & Verification

Due to the strict modular encapsulation in modern Java (Java 16+), the application requires specific JVM arguments (--add-opens) to allow Ignite's internal memory management (sun.misc.Unsafe) to access direct RAM buffers. These are pre-configured in the pom.xml using the exec-maven-plugin.

To compile the project and run the simulation:

```bash
mvn clean compile
mvn exec:exec
```

### The application will automatically:

\*Connect to the local Ignite node.

\*Initialize and truncate the Subscriber cache/table.

\*Insert 5 dummy subscribers with zeroed usage.

\*Simulate random data (GB), SMS, and call minute updates.

\*Fetch and display the final state of all subscribers.
