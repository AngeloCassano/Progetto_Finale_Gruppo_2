# 📌 Progetto TierList

Applicazione web per la creazione e gestione di **tier list** con **back-end in Java (Spring Boot)** e **database MySQL**.

---

## 🚀 Tecnologie utilizzate
- **Java 17**
- **Spring Boot** (Web, Security, Data JPA)
- **MySQL** (database relazionale)
- **Lombok** (riduzione boilerplate)
- **JWT Authentication** (gestione autenticazione)
- **Swagger/OpenAPI** (documentazione API)

---

## 👥 Team di sviluppo

- **Angelo Cassano** – Service e Repository  
- **Luca Alessio** – Service e Repository  
- **Francesco Mochi** – Security e JWT  
- **Valerio Bellucci** – DTO, Mapper e Controller  
- **Giovanni Dadone** – Front-end e supporto Back-end  

📌 Tutti i membri hanno collaborato alla fase di debug e rifinitura finale del progetto.

---

## 📌 Funzionalità principali

✅ Registrazione e login utenti (JWT)  
✅ Creazione e salvataggio di TierList personalizzate  
✅ Associazione TierList a **Categorie** e **Utenti**  
✅ Gestione Tier con livelli personalizzati (S, A, B, C...)  
✅ API REST sicure e documentate con Swagger  
✅ Connessione con **MySQL** per la persistenza dei dati  

---

## 📂 Struttura del progetto
```plaintext
📦 src
 ┣ 📂 main/java/com/example/TierList
 ┃ ┣ 📂 controller    # Controller REST
 ┃ ┣ 📂 dto           # Data Transfer Objects
 ┃ ┣ 📂 mapper        # Mapper Entity <-> DTO
 ┃ ┣ 📂 model         # Entità JPA
 ┃ ┣ 📂 repository    # Spring Data JPA Repository
 ┃ ┣ 📂 security      # Configurazione JWT e Spring Security
 ┃ ┣ 📂 service       # Business Logic
 ┃ ┗ 📜 TierListApplication.java
 ┗ 📂 main/resources
   ┣ 📜 application.properties
   ┗ 📜 schema.sql (opzionale)


---

## 📌 API Principali
POST /auth/register → Registrazione utente

POST /auth/login → Login e ricezione token JWT

GET /api/tierlists → Recupera tutte le TierList

POST /api/tierlists → Crea una nuova TierList

GET /api/tierlists/by-user/{utenteId} → Recupera TierList di un utente specifico

http://localhost:8080/swagger-ui.html

---

## 🔒 Sicurezza
Autenticazione basata su JWT

Autorizzazioni con Spring Security

Rotte protette e ruoli gestiti

