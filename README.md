# E-Commerce System

An enterprise-grade, multi-tier distributed e-commerce application. The system's architecture leverages RESTful web services combined with asynchronous Java Message Service (JMS) messaging for decoupled communication between independent subsystems.

---

## 📌 System Architecture Overview

The system consists of the following components:

1. **Client Application (Java SE):** User interface for authentication, sending requests, and displaying responses.
2. **Central Server (REST API):** Handles incoming REST requests, user validation, authorization checks, and routes messages to the appropriate subsystem.
3. **JMS Broker:** Facilitates asynchronous and reliable messaging between the Central Server and subsystems.
4. **Subsystem 1 (Users & Cities):** Manages database records and business logic for cities, users, roles, and user account balances.
5. **Subsystem 2 (Catalog, Cart & Wishlist):** Manages categories, subcategories, items, user shopping carts, and wishlists.

---

## 🛠️ Tech Stack

* **Language:** Java (Java SE / Jakarta EE)
* **Database:** MySQL Server
* **Communication:** RESTful Web Services (JAX-RS / Jersey), JMS (Java Message Service)
* **ORM / Persistence:** JPA / Hibernate / EclipseLink

---

## 🌐 Endpoint & Functionality Mapping

| ID | Functionality | Subsystem | Access Level |
| :---: | :--- | :---: | :---: |
| 1 | Validate user credentials | Subsystem 1 | All Users |
| 2–5 | Manage cities, users, deposit funds, update address | Subsystem 1 | **Administrator** |
| 6–13 | Manage categories, items, cart, and wishlist | Subsystem 2 | Registered Users |
| 15–16 | Fetch all cities and users | Subsystem 1 | **Administrator** |
| 17–20 | Fetch categories, items, cart, and wishlist | Subsystem 2 | Registered Users |

---

## 🚀 Deployment & Execution Guide

### 1. Database Setup
1. Start your **MySQL Server**.
2. Execute the provided SQL scripts for Subsystem 1 and Subsystem 2 to initialize tables and initial data.

### 2. JMS Configuration
1. Launch an application server supporting JMS (GlassFish).
2. Configure the required JMS message queues: `Q1`, `Q2`, and `QCentral`.

### 3. Execution Order
1. Start **Subsystem 1** and **Subsystem 2** (listening for incoming JMS messages).
2. Start the **Central Server** (REST API).
3. Run the **Client Application**.
