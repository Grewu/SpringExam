## Spring Exam Application App

Приложение для парсинга тестов из Udemy (HTML) и экспорта в Moodle (XML) с сохранением в H2.

## 🔥 Возможности
- **Парсинг тестов из HTML-файлов Udemy**
- **Сохранение тестов в базу данных (H2)**
- **Экспорт в тестов формат Moodle XML**
- **Экспорт в тестов формат JSON**
- **Очистка данных в бд**


## 🛠 Технологии
- **Java 21** 
- **Spring Boot 3** 
- **Gradle**
- **Docker Compose**
- **Liquibase** 
- **H2**

### Интсрукция по запуску
1. **Git clone**
   ```sh
   git clone https://github.com/Grewu/SpingExam.git
   cd SpingExam
   ```
2. **Build and Start the Application:**
   ```sh
   docker compose up --build
   ```

3. **Accessing the Application:**
   - The application will be available on [http://localhost:9011](http://localhost:9011).
   
4. **Use Swagger**
   - The application will be available on [http://localhost:9011/swagger-ui/index.html](http://localhost:9011/swagger-ui/index.html).
   
5. **Accessing the H2 Console**
   - The application will be available on [http://localhost:9011/h2-console](http://localhost:9011/h2-console).





