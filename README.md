
# Products Management System

## Krótki opis
Products Management System to REST API do zarządzania produktami, magazynami, zamówieniami oraz fakturami. Projekt centralizuje procesy logistyczne i sprzedażowe, eliminując potrzebę używania wielu niespójnych narzędzi.

System opiera się na Spring Boot + Hibernate (MySQL) i implementuje kompletny model domenowy odwzorowany w załączonym diagramie UML.

## Stack technologiczny
- Java 17+
- Spring Boot (Web, Security, Data JPA)
- Hibernate ORM
- MySQL 8+
- Maven 3.8+

## Model danych (na podstawie diagramu UML)
### Główne encje:
- **Product** – name, sku, price, vat, description, category
- **Category** – name, parent category
- **Warehouse** – name, location
- **Inventory** – relacja Product ⇄ Warehouse, pole quantity
- **Customer** – dane klienta (email, address itd.)
- **Order** – customer, status, order date
- **OrderItem** – produkt, quantity, unitPrice
- **Invoice** – order, issueDate, dueDate, total
- **InvoiceItem** – powiązane z Invoice, odzwierciedla OrderItem

Relacje:
- Product 1..* OrderItem
- Product 1..* Inventory
- Warehouse 1..* Inventory
- Customer 1..* Order
- Order 1..* OrderItem
- Order 1..1 Invoice
- Invoice 1..* InvoiceItem

## Struktura plików
```
project-root/
 ├── src/main/java/...        # Warstwa kontrolerów, serwisów i encji
 ├── src/main/resources/      # Konfiguracje aplikacji
 ├── docs/                    # Dokumentacja, diagram UML
 ├── pom.xml
 └── README.md
```

## Instalacja / Uruchomienie
### 1. Wymagania
- Java 17+
- MySQL 
- Maven 

### 2. Konfiguracja MySQL
Utwórz bazę danych:
```sql
CREATE DATABASE products_management;
```
Zaktualizuj `application.properties`:
```
spring.datasource.url=jdbc:mysql://localhost:3306/products_management
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 2. Klonowanie repozytorium
```bash
git clone https://github.com/ryba24/projekt-products-management.git
cd products-management
```

### 3. Uruchomienie aplikacji
```
mvn spring-boot:run
```
Aplikacja wystartuje pod adresem:
```
http://localhost:8080
```

### 4. Główne moduły API
- **/products** – zarządzanie produktami
- **/warehouses** – magazyny i stany magazynowe
- **/orders** – obsługa zamówień
- **/invoices** – generowanie i pobieranie faktur

## Endpoints (wysoki poziom)
- `/products` – CRUD produktów
- `/categories` – zarządzanie kategoriami
- `/warehouses` – magazyny
- `/inventory` – stany magazynowe
- `/orders` – zamówienia
- `/invoices` – faktury

## Opis procesu (zgodnie z UML)
1. **Klient** składa zamówienie → tworzy się encja `Order`.
2. System tworzy `OrderItem` na podstawie koszyka.
3. Po potwierdzeniu zamówienia generowana jest `Invoice`.
4. Stany magazynowe (`Inventory`) są aktualizowane zgodnie z OrderItem.
5. Każda faktura ma `InvoiceItem`, będące odwzorowaniem pozycji zamówienia.

## Statusy
- Order: Pending → Confirmed → Picking → Shipped → Delivered / Cancelled
- Invoice: Unpaid / Partial / Paid

