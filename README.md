Ky aplikacion është një sistem për menaxhimin e rezervimeve të biletave për evente. Ai përfshin një bazë te dhënash backup, që siguron funksionimin edhe në rast 
dështimi të bazës së të dhënave kryesore. 

**Kërkesat e sistemit**
Duhet që të keni të instaluara në kompjuter:
1. Java Development Kit (JDK): Versioni 17 ose më i ri.
2. Apache Maven: Për të menaxhuar ndërtimin e projektit.
3. MySQL Database: Për të ruajtur të dhënat.
4. Git: Për të klonuar projektin.
5. IDE (Integrated Development Environment): Si IntelliJ IDEA.

**Klonimi i projektit**
Hapni terminalin ose Command Prompt.
Ekzekutoni komandën:
git clone <https://github.com/erisa14/Booking>

**Konfigurimi i bazës së të dhënave**
Hapni aplikacionin MySQL Workbench.Krijoni një bazë të dhënash:
CREATE DATABASE booking;
Hapni dosjen src/main/resources dhe gjeni skedarin application.properties. Modifikoni këto rreshta me informacionin tuaj:
properties
spring.datasource.url=jdbc:mysql://localhost:3306/booking?serverTimezone=UTC
spring.datasource.username=<emri_juaj>
spring.datasource.password=<fjalekalimi_juaj>
Ruani ndryshimet.

**Ndërtimi dhe ekzekutimi i aplikacionit**
Për të ndërtuar projektin:
mvn clean install
Për të ekzekutuar aplikacionin:
mvn spring-boot:run
Hapni në browser këtë URL: http://localhost:8080/events

**Testimi i aplikacionit**
Për të parë listën e eventeve, klikoni në "Events".
Për të krijuar një rezervim, klikoni te "Create Booking".
Në rast problemi me bazën kryesore të të dhënave, sistemi do të përdorë automatikisht tabelën rezervë.

**Informacione shtesë**
Në rast të ndonjë problemi, sigurohuni që:
1.Porta 8080 është e lirë në kompjuterin tuaj.
2.Bazat e të dhënave MySQL janë të startuara dhe të qasshme.
3.Të dhënat e konfigurimit në skedarin application.properties janë korrekte.
