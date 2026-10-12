# 🐞 BugRadar – Software Risk Assessment Dashboard

BugRadar is a web-based Software Testing and Quality Assurance (STQA) project developed using Java and Spring Boot. It helps users manage software modules, assess their risk levels, and identify modules that may require additional testing.

The application provides a dashboard for analyzing software risks based on module information and calculated risk scores.

## 🎯 Objectives

- Identify high-risk software modules.
- Calculate risk scores to support testing prioritization.
- Organize software module information in one place.
- Demonstrate software testing and quality assurance concepts through a practical application.
- Provide a simple and user-friendly dashboard for risk assessment.

## ✨ Features

- **Risk Assessment:** Calculates risk scores for software modules.
- **Module Management:** Provides functionality to manage software module information through the application.
- **Risk Prioritization:** Helps identify modules that need more testing attention.
- **Web Dashboard:** Displays information through an HTML, CSS, and JavaScript interface.
- **REST API:** Uses Spring Boot controllers to handle application requests.
- **Data Access Layer:** Uses a repository layer to manage module data.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core application logic |
| Spring Boot | Backend application framework |
| Spring Web | Web endpoints and REST API |
| Spring Data JPA | Data access and persistence, if configured |
| HTML5 | Dashboard structure |
| CSS3 | Dashboard styling |
| JavaScript | Frontend interactions |
| Maven | Project build and dependency management |
| JUnit / Spring Boot Test | Application testing |

## 📂 Project Structure


bugradar/
├── .mvn/

├── src/

│   ├── main/

│   │   ├── java/com/bugradar/

│   │   │   ├── BugradarApplication.java

│   │   │   ├── controller/

│   │   │   │   └── SoftwareModuleController.java

│   │   │   ├── model/

│   │   │   │   └── SoftwareModule.java

│   │   │   ├── repository/

│   │   │   │   └── SoftwareModuleRepository.java

│   │   │   └── service/

│   │   │       └── RiskCalculationService.java

│   │   ├── resources/

│   │   │   ├── application.properties

│   │   │   └── static/

│   │   │       ├── index.html

│   │   │       ├── script.js

│   │   │       └── style.css

│   └── test/

│       └── java/com/bugradar/

│           └── BugradarApplicationTests.java

├── mvnw

├── mvnw.cmd

├── pom.xml

└── README.md


## 🔍 Software Testing and Quality Assurance Applications

BugRadar demonstrates how software risk assessment can support STQA activities, including:

- Risk-based testing
- Test prioritization
- Identification of potentially critical modules
- Organization of software testing information
- Support for quality-focused development decisions

## 🔮 Future Enhancements

- User authentication and role-based access.
- Persistent database integration.
- Detailed risk analytics and visual reports.
- Exportable test and risk assessment reports.
- Test case management and defect tracking.
- Automated testing integration.
- Historical risk trends and module comparisons.


---

*BugRadar aims to make software risk assessment more organized, understandable, and useful for prioritizing testing activities.*


<img width="1887" height="955" alt="image" src="https://github.com/user-attachments/assets/fa7c4cc1-eb37-4829-8671-e089d17fae18" />

