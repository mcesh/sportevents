# Sports Events Microservice

This project is a **Java-based Spring Boot microservice** that simulates real-time sports score updates and streams them to a Kafka topic. It periodically polls a mocked external REST API, transforms the results, and publishes them to a Kafka topic (`live-event-scores`).

---

## ✅ Features
- **REST Endpoint** to toggle event status (`LIVE` / `NOT_LIVE`).
- **Scheduled Tasks** that poll an external API every 10 seconds for active events.
- **Score Mocking Logic** that simulates progressive score updates.
- **Kafka Producer** that publishes updates to a topic.
- **Kafka Consumer** for real-time message logging.
- Configurable via `application.yml`, runs on **port 8881**.

---

## 🚀 How to Run Locally

### 1. Start Kafka using Docker Compose
Make sure Docker is installed. Run:
```bash
docker-compose up
```
This starts Kafka and Zookeeper containers.

### 2. Start the Spring Boot Application
Run the app from your IDE or using Maven:
```bash
./mvnw spring-boot:run
```

### 3. Test the System
Send a `POST` request to mark an event as live:
```bash
curl -X POST http://localhost:8881/events/status \
  -H "Content-Type: application/json" \
  -d '{ "eventId": "match-101", "status": "LIVE" }'
```
You should see periodic score updates published to Kafka and logged by the consumer.

---

## 📦 Project Architecture

This application is structured as a **single microservice** to:
- Keep the codebase simple and compact.
- Make development, testing, and deployment fast.
- Fulfill the assignment's 90-minute implementation scope.

### 📌 Why Not Split into Multiple Microservices?
While this design bundles everything into one service, a **modular architecture** could separate concerns:

| Responsibility         | Potential Service          |
|------------------------|----------------------------|
| Event State Management | `event-service`            |
| Scheduling Engine      | `scheduler-service`        |
| External API Polling   | `poller-service`           |
| Kafka Publishing       | `stream-service`           |
| Notification/Consumer  | `consumer-service`         |

These services could communicate via events (Kafka) or internal REST, and would support scaling and isolation.

---

## 🧠 Mocked Score Logic
The `ExternalApiServiceImpl` class simulates score updates by:
- Persisting current score for each event ID in memory.
- Randomly increasing **only one** team’s score by 0 or 1 on each poll.
- Preventing backward score shifts to maintain realistic progression.

Example progression:
- `0:0` → `1:0` → `1:1` → `2:1` → `3:1`

---

## 🛠️ Future Enhancements
- ⏱️ Add WebSocket support for real-time frontend dashboards.
- 📊 Expose `/metrics` via Prometheus/Grafana.
- 💾 Use Redis or a database for persistence.
- 📡 Integrate a real sports score API instead of mock logic.
- 🔐 Add authentication & rate limiting.
- 🧪 Add more test coverage and contract testing.

---

## 🧪 Testing
- Unit tests are recommended for:
    - `SchedulerService`
    - `ExternalApiService`
    - `KafkaPublisherService`
- Integration tests can validate end-to-end logic from API call to Kafka publish.

---

## 📞 Contact
This project is a backend-focused technical demo built with Spring Boot, Kafka, and Docker. Reach out if you'd like to expand or productionize it.
