# Versenykezelő Webalkalmazás – Projektleírás

## Áttekintés

Ez a webalkalmazás egy sportversenyek nyilvántartására és kezelésére szolgáló rendszer, amely lehetőséget biztosít felhasználók regisztrációjára, bejelentkezésére, valamint versenyzők, mérkőzések és bajnokságok adminisztrálására.  
A projekt célja egy stabil, jól strukturált, Java alapú webes rendszer létrehozása, amely könnyen bővíthető és karbantartható.

## Technológiák és eszközök

- **Backend**  
  - Java 21+  
  - Spring Boot keretrendszer  
  - JDBC az adatbázis műveletekhez (manuálisan írt SQL parancsokkal)  
  - REST API végpontok a frontenddel való kommunikációhoz

- **Frontend**  
  - Thymeleaf template engine  
  - HTML, CSS alapú felhasználói felület

- **Adatbázis**  
  - MySQL relációs adatbázis  
  - Egyéni DAO réteg az adatbázis-műveletekhez (CRUD)

- **Authentikáció és jogosultságkezelés**  
  - Session-alapú autentikáció  
  - Cookie-ban tárolt session ID  
  - Jogosultsági szintek: Admin, Néző

## Főbb funkciók

- Felhasználók regisztrációja és bejelentkezése  
- Versenyzők, mérkőzések és bajnokságok rögzítése, módosítása, listázása  
- Adminisztrációs és nézői jogosultságok elkülönítése  
- Biztonságos SQL alapú adatkezelés

---

## Telepítés és futtatás

### Előfeltételek

- Java JDK 21
- Maven  
- MySQL szerver  
- Fejlesztői környezet (pl. IntelliJ IDEA, Eclipse)

### Lépések

1. **Adatbázis létrehozása**  
   - Hozd létre a szükséges táblákat a mellékelt SQL fájl alapján.
   - Konfiguráld a `application.properties` fájlt:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/egyenisport
     spring.datasource.username=root
     spring.datasource.password=your_password
     ```

2. **Projekt buildelése**
   ```bash
   mvn clean install
   ```

2. **Alkalmazás indítása**
   ```bash
   mvn spring-boot:run
   ```

4. **Webes felület megnyitása**
  - Alapértelmezett URL: http://localhost:8080
