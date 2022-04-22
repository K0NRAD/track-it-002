# Dokumentation 

## Agenda

### 1. Projekt Überblick, Technologien und Frameworks
#### 1.1 Projekt Überblick
#### 1.2 Technologien und Frameworks
#### 1.3 Set up
### 2. DB Dokumentation
### 3. Rest Endpoints Dokumentation
### 4. Backend Dokumentation
#### 4.1 UML
#### 4.2 Models
#### 4.3 Repositories
#### 4.4 Services
#### 4.5 Controllers
#### 4.6 Unit testing
### 5. Frontend Dokumentation


---

# 1. Projekt Überblick, Technologien und Frameworks

## 1.1 Projekt Überblick

![Kundenanforderungen Screenshot 1](/documentation/Kundenanforderungen/Kundenanforderungen1.png)
![Kundenanforderungen Screenshot 2](/documentation/Kundenanforderungen/Kundenanforderungen2.png)
![Kundenanforderungen Screenshot 3](/documentation/Kundenanforderungen/Kundenanforderungen3.png)

## 1.2 Technologien und Frameworks

Wir haben Spring Boot für die Backend-Rest-Api verwendet und einen Docker-Container für die Postgresql-Datenbank gebaut. Auf dem Frontend haben wir das Vue-Framework verwendet, um eine Single Page Application (SPA) zu erstellen. Das Backend wurde mit JUnit, AssertJ und Test Containers getestet.

Die Informationen über den Docker-Container findet man in der Datei ```/docker-compose.yml```, die für die Postgresql-Datenbank erstellt wurde.

Technologien und Frameworks Überblick:

* Backend:
    * **Spring Boot**
    * **Unit tests mit JUnit und Test Containers**
    * **Docker container für postgresql DB**
* Frontend:
    * **Vue**

#### 1.3 Set up

Um die App einzurichten, muss der Docker-Container gestartet werden. Dazu führen wir ```docker-compose up``` im root-Ordner aus, in dem sich die Datei ```/docker-compose.yml``` befindet.

Danach verwenden wir ```docker ps```, um alle verfügbaren Docker-Container zu sehen:

```BASH
$ docker ps

CONTAINER ID   IMAGE           COMMAND                  CREATED         STATUS         PORTS                    NAMES
ea269c4c9cc2   postgres:14.1   "docker-entrypoint.s…"   x minutes ago   Up x minutes   0.0.0.0:5435->5432/tcp   trackitPostgresContainer
```

Wir nehmen die Container-ID des Postgres-Containers und verbinden uns dann mit dem Container:

```BASH
$ docker exec -it ea269c4c9cc2 bash
```

Wir verbinden uns anschließend mit ```psql``` mit der Postgres-Datenbank und erstellen folgenden DBs mit den folgenden und Privilegien ( ```localhost``` ist in diesem Fall nur ein Beispiel ):

```BASH
$ psql -h localhost -p 5432 -U postgres
```

```psql
postgres=# CREATE DATABASE trackit;
postgres=# CREATE DATABASE testcontainers;
postgres=# GRANT ALL PRIVILEGES ON DATABASE trackit TO postgres;
postgres=# GRANT ALL PRIVILEGES ON DATABASE testcontainers TO postgres;
```

Jetztz haben wir die DB trackit für die App und die DB testcontainers für die TestContainers.

---

# 2. DB Dokumentation

ERM Modell:

![ERM Model](/documentation/DB_ERM/ermmodel.png)

Die ```Employee```-Tabelle enthält einen Primärschlüssel ```employeeId```, einen eindeutigen Benutzernamen (```username```), ein Passwort (```password```) und eine Personalnummer (```personnelNumber```) sowie einen Vornamen(```firstName```) und einen Nachnamen (```lastName```). Der Benutzername und das Passwort werden innerhalb der Datenbank mit dem Algorithmus sha256 verschlüsselt. Die im Backend für die Verschlüsselung verwendete Abhängigkeit war [Guava von Google](https://mvnrepository.com/artifact/com.google.guava/guava).

Die Tabelle ```DailyWorkingHours``` enthält Daten über jeden einzelnen Arbeitstag eines Benutzers. Sie hat eine ID als Primärschlüssel (```dailyWorkingHoursId```) und als Fremdschlüssel die ```employeeId```. Ein Mitarbeiter kann mehrere Einträge in dieser Tabelle haben. Jede Zeile stellt einen Arbeitstag für einen bestimmten Mitarbeiter dar. Dieser Arbeitstag enthält das Datum (```date```), die Gesamtzahl der Arbeitsstunden (```totalDayWorkTime```), die Gesamtzahl der Pausenstunden (```totalBreakTime```), den Zeitpunkt des Arbeitsbeginns (```checkIn```) und des Arbeitsendes des Mitarbeiters (```checkOut```).

Die Tabelle ```DailyBreakTimes``` enthält Daten über jede Pause, die an einem bestimmten Tag gemacht wurde. Sie enthält eine ID als Primärschlüssel (```dayBreakTimesId```) und als Fremdschlüssel die ```dailyWorkingHoursId```, da diese Pause an einem bestimmten Arbeitstag für einen bestimmten Mitarbeiter gemacht wurde. Jeder Eintrag enthält Daten darüber, wann die Pause begann (```breakCheckIn```) und wann sie endete (```breakCheckOut```). Diese Daten werden auch verwendet, um die Gesamtpausenzeit und die Gesamtarbeitszeit in der Tabelle ```DailyWorkingHours``` zu berechnen.

---

# 3. Rest Endpoints Dokumentation

Die Rest-Endpoints sind im Dokumentationsordner (```/documentation/rest.txt```).
Wir haben Rest-Endpoints für jede Tabelle erstellt:

* ```Employee```
    * ```api/employee/loginEmployee(username, password)```
        * gibt den Benutzer zurück, der den angegebenen Benutzernamen und das Passwort hat
        * gibt ```null``` zurück, wenn der Benutzername und das Passwort falsch sind
    * ```api/employee/registerEmployee(username, password, personnelNumber, firstName, lastName)```
        * ```true``` zurückgeben, wenn die Registrierung erfolgreich war
        * wenn nicht, ```false``` zurückgeben
    * ```api/employee/getEmployeeDataByEmployeeId(employeeId)```
        * gibt den Benutzer zurück, der die angegebene ```employeeId``` hat
        * gibt ```null``` zurück, wenn die ```employeeId``` falsch ist
* ```DailyWorkingHours```
    * ```api/dailyworkinghours/setCheckIn(employeeId, checkInTime)```
        * ```true``` zurückgeben, wenn es funktioniert hat
        * ```false``` zurückgeben, wenn es nicht funktioniert hat
    * ```api/dailyworkinghours/getDailyWorkingHoursByEmployeeIdAndDate(employeeId, date)```
        * ```DailyWorkingHours``` Objekt zurückgeben, wenn ```employeeId``` und ```date``` richtig sind
        * ```null``` zurückgeben, wenn ```employeeId``` und ```date``` falsch sind
    * ```api/dailyworkinghours/setCheckOut(dailyWorkingsHoursId, employeeId, checkOutTime)```
        * ```true``` zurückgeben, wenn es funktioniert hat
        * ```false``` zurückgeben, wenn es nicht funktioniert hat
* ```DailyBreakTimes```
    * ```api/dailybreaktimes/breakCheckIn(dailyWorkingsHoursId, breakCheckInTime)```
        * ```true``` zurückgeben, wenn es funktioniert hat
        * ```false``` zurückgeben, wenn es nicht funktioniert hat
    * ```api/dailybreaktimes/breakCheckOut(dayBreakTimeId, dailyWorkingsHoursId, breakCheckOutTime)```
        * ```true``` zurückgeben, wenn es funktioniert hat
        * ```false``` zurückgeben, wenn es nicht funktioniert hat

Das Datum eines Rest-Endpunktes wird immer als normale Zeichenkette angenommen.
Beispiel:

```
api/dailybreaktimes/breakCheckIn?dailyWorkingsHoursId=1&breakCheckInTime=12:00:00
```

---

# 4. Backend Dokumentation

## 4.1 UML

Das Drawio-Diagramm befindet sich im Dokumentationsordner (```/documentation/UML/BackendUML.drawio```). 

![Full Backend UML Diagram](/documentation/UML/BackendUML.png)

## 4.2 Models

![Models UML](/documentation/UML/ModelsUML.png)

Die Modelle wurden auf der Grundlage der im obigen Entity-Relationship-Diagramm dargestellten Tabellen erstellt. Wir haben ```Lombok``` für Getters, Setters und Konstruktoren verwendet, aber auch ein paar benutzerdefinierte Konstruktoren für jede Entität erstellt. Alles andere ist genau wie in der Datenbankdokumentation beschrieben.

Alle Models haben die Stereotypen ```Entity``` und ```Table```. Das Modell ```DailyBreakTimes``` hat eine ```Composition-Relationship``` mit dem Modell ```DailyWorkingHours``` und eine ```<<use>>```-Dependency. Das Modell ```DailyWorkingHours``` hat eine ```Composition-Relationship``` mit dem Modell ```Employee``` und eine ```<<use>>```-Dependency.

## 4.3 Repositories

![Repositories UML](/documentation/UML/RepositoriesUML.png)

Jedes Repository hat die Stereotypen ```Interface``` und ```JpaRepository```. Jedes Repository hat eine ```Composition-Relationship``` und eine ```<<use>>```-Dependency zu seiner entsprechenden Entität.

* ```EmployeeRepository```
    * ```+ getUserByUsernameAndPassoword(username: String, password: String) : Employee```
        * Diese Methode gibt einen Benutzer basierend auf dem gegebenen ```username``` und ```password``` zurück
* ```DailyWorkingHoursRepository```
    * ```+ updateCheckOutTotalBreakTimeTotalWorkTime(checkOutTime: LocalTime, totalBreakTime: LocalTime, totalDayWorkTime: LocalTime, dailyWorkingHoursId: Long { unique } ) : None```
        * Diese Methode aktualisiert die ```checkOutTime```, die ```totalBreakTime``` und die ```totalDayWorkTime``` anhand der angegebenen ```dailyWorkingHoursId```
    * ```+ getDailyWorkingHoursByEmployeeIdAndDate(employeeId: Long, date: LocalDate) : DailyWorkingHours```
        * Diese Methode liefert die Entität ```DailyWorkingHours``` anhand der angegebenen ```employeeId``` und des Datums ( ```date``` )
* ```DailyBreakTimesRepository```
    * ```+ getAllBreaksByDailyWorkingHoursIdAndDate(dailyWorkingHoursId: Long {unique}, date: LocalDate): DailyBreakTimes[0..*]{seq}```
        * Diese Methode gibt eine Liste von ```DailyBreakTimes```-Entitäten ( ```List<DailyBreakTimes>``` ) anhand der angegebenen ```dailyWorkingHoursId``` und des angegebenen Datums ( ```date``` ) zurück
    * ```+ breakCheckOut(dayBreakTimesId: Long {unique}, breakCheckOutTime: LocalTime): None```
        * Diese Methode setzt den CheckOutTime (```breakCheckOutTime```) für ein bestimmtest DayBreakTime (```dayBreakTimesId```)
    * ```+ getDailyBreakTimeByDailyWorkingHoursIdAndBreakCheckIn(dailyWorkingHoursId: Long {unique}, breakCheckIn: LocalTime): DailyBreakTimes[..*]{seq}```
        * Diese Methode liefert eine Liste von ```DailyBreakTime``` Entitäten anhand der angegebenen ```dailyWorkingsHoursId``` und ```breakCheckIn```


## 4.4 Services

![Services UML](/documentation/UML/ServicesUML.png)

Der ```EmployeeService``` hat eine Kompositionsbeziehung mit dem ```HashingStaticService```. Jeder Service hat eine ```<<use>>```-Dependency von den Repositories, die er benötigt, um richtig zu funktionieren und ein ```<<Service>>```-Stereotyp.


* ```EmployeeService```
    * ```+ getEmployeeDataByEmployeeId(employeeId: Long { unique }) : Employee```
        * Gibt ein ```Employee``` anhand der angegebenen ```employeeId``` zurück
    * ```+ getEmployeeByUsernameAndPassword(username: String, password: String): Employee```
        * Gibt ein ```Employee``` anhand der angegebenen ```username``` und ```password``` zurück
    * ```+ registerNewEmployee(username: String, password: String, personnelNumber: String, firstName: String, lastName: String) : boolean```
        * erstellt einen neuen ```Employee``` in der Datenbank mit den angegebenen Daten
* ```HashingStaticService```
    * ```+ hashString(stringToHash: String) : String```
        * Das ist eine statische Methode, die die angegebene Zeichenkette mit sha256 verschlüsselt
* ```DailyWorkingHoursService```
    * ```+ setCheckIn(employeeId: Long { unique }, checkInTime: LocalTime) : boolean```
        * Diese Methode erstellt eine neue Entität mit der angegebenen ```employeeId``` und ```checkInTime``` und speichert sie in der Datenbank. ( Die Entität wird mit dem aktuellen Datum gespeichert )
    * ```+ setCheckOut(dailyWorkingHoursId: Long { unique }, checkOutTime: LocalTime): boolean```
        * Diese Methode wird verwendet, wenn ein Mitarbeiter mit seinem Tag fertig ist. Die Methode setzt die ```checkOutTime``` für die gegebene ```dailyWorkingHoursId``` und darüber hinaus auch automatisch die Gesamtarbeitszeit ( ```totalDayWorkTime``` ) und die Gesamtpausenzeit ( ```totalDayBreakTime``` )
    * ``` + getDailyWorkingHoursByEmployeeIdAndDate(employeeId: Long { unique }, searchDate: LocalDate): DailyWorkingHours```
        * Diese Methode gibt eine DailyWorkingHours-Entität mit der angegebenen ```employeeId``` und dem angegebenen Datum ( ```searchDate``` ) zurück

## 4.5 Controllers

![Controllers UML](/documentation/UML/ControllersUML.png)

Jeder Controller hat ein ```<<RestController>>```-Stereotyp und eine ```<<use>>```-Dependency von dem entsprechenden Service. Einige Controller, die mit Datum oder Uhrzeit arbeiten, verfügen über Datumsformatierer ( ```DateTimeFormatter``` ), sodass sie die von den Benutzern angegebenen Daten in ```LocalDate``` oder ```LocalTime``` formatieren können.

Der Endbenutzer verwendet die Rest Controllers, um die Datenbank abzurufen oder zu aktualisieren. Die übrigen Controllers nutzen die Services. Die Services verwenden die Models und die Repositories, um mit der Datenbank zu kommunizieren und Daten an den Benutzer zurückzugeben. Die Methoden innerhalb der Controller arbeiten direkt mit den Services. Die Controller Methoden prüfen nur, ob die Benutzereingaben korrekt formatiert sind und verwenden dann die Services für die restliche Funktionalität.

## 4.6 Unit testing

![Testing Hierarchy](/documentation/DocumentationScreenshots/TestingHierarchy.png)

Wir haben JUnit, AssertJ und TestContainers für Unit-Tests verwendet.

Wir haben nur die Repositories und Services getestet. Da die Controllers direkt mit den Services arbeiten, haben wir sie nicht mehr getestet.

Die Konfiguration für TestContainers ist in ```/test/resources/application.properties```. Es verwendet denselben Docker-Postgres-Container, aber eine andere Datenbank ( ```testcontainers``` ). Es verwendet den gleichen Benutzernamen und das gleiche Passwort.

---

# 5. Frontend Dokumentation

---