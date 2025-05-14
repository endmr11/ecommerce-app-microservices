# Microservice Architecture Diagram

## 1. API Gateway (API GW)
- **Purpose:** All requests from the outside world pass through the API Gateway. This provides a single entry point and eliminates the need for each microservice to be exposed directly to the internet.
- **Usage:** It receives incoming requests and routes them to the correct microservice. For example, a `/customers` request is routed to the Customer service, `/products` to the Product service, and `/orders` to the Order service.

## 2. Customer Service
- **Purpose:** Manages customer information.
- **Usage:** Stores customer data in a MongoDB database. Operations such as creating and updating customer records are handled by this service.

## 3. Product Service
- **Purpose:** Manages product information.
- **Usage:** Stores product data in a MongoDB database. Operations like adding, updating, and listing products are performed through this service.

## 4. Order Service
- **Purpose:** Manages order processes.
- **Usage:** Handles order creation and stores order data. When an order is created, it fetches product information from the Product service and customer information from the Customer service.

## 5. Payment Service
- **Purpose:** Manages payment transactions.
- **Usage:** When an order is placed, this service processes the payment and confirms it. It then sends payment confirmation messages to the Notification service via Kafka.

## 6. Notification Service
- **Purpose:** Sends notifications.
- **Usage:** Listens to asynchronous messages from the Payment and Order services and sends notifications (such as emails) to the customer about the order status.

## 7. Kafka (Message Broker)
- **Purpose:** Enables asynchronous communication between microservices.
- **Usage:** Delivers confirmation messages from the Payment and Order services to the Notification service. This allows services to operate independently without tight coupling.

## 8. Zipkin (Distributed Tracing)
- **Purpose:** Distributed tracing and performance monitoring.
- **Usage:** Tracks requests across services and helps analyze their performance. It shows which services respond and how long they take.

## 9. Eureka Server
- **Purpose:** Service discovery and registration.
- **Usage:** Helps identify where each microservice is running. Each microservice registers itself with this server, and others use it to locate services.

## 10. Config Server
- **Purpose:** Centralized configuration management.
- **Usage:** Manages configuration files for all microservices from a central location. For example, when a database URL changes, it can be updated in one place.

## 11. MongoDB
- **Purpose:** Acts as the database.
- **Usage:** A NoSQL database used by the Customer, Product, Order, and Notification services to store their data.

## 12. Docker
- **Purpose:** Containerization of applications.
- **Usage:** Ensures microservices run the same way across different environments. Each service can be run inside a Docker container.

---

# E-Commerce Site Shopping Process

## 1. Customer Registration
**Scenario:** A user named Joe decides to sign up on the e-commerce website.

### Steps:
1. Joe enters his name, surname, and email to register on the site.
2. A new record is added to the `Customer` table with Joe’s information.
3. Joe also enters his address (e.g., `street: "Atatürk Cad."`, `houseNumber: "10"`, `zipCode: "34560"`).
4. The address is stored in the `Address` table and linked to the `Customer` table.

## 2. Product Search and Selection
**Scenario:** Joe browses products and adds the ones he’s interested in to his cart.

### Steps:
1. Joe browses various categories, e.g., selects the “Electronics” category from the `Category` table.
2. Products in that category are fetched from the `Product` table (e.g., “Laptop”, “Smartphone”).
3. Joe adds a laptop to his cart. Product details like name, description, price, and available quantity come from the `Product` table.

## 3. Placing an Order
**Scenario:** Joe decides to buy the products in his cart.

### Steps:
1. Joe proceeds to checkout. An order is created and stored in the `Order` table (e.g., `orderDate`, `reference`).
2. Each product in the order is recorded in the `OrderLine` table (e.g., `id: 1`, `quantity: 1`).
3. The relationship between the order and its order lines shows how many of each product Joe ordered.

## 4. Payment Processing
**Scenario:** Joe enters his credit card details to pay for the order.

### Steps:
1. Once Joe submits his card details, the payment information is saved in the `Payment` table (e.g., `reference`, `amount`, `status`).
2. This payment is linked to the related order record, showing which order the payment belongs to.

## 5. Order Confirmation and Notification
**Scenario:** Joe’s order is successfully completed and he receives a notification.

### Steps:
1. When the order is completed, the system automatically sends Joe an email notification.
2. This notification is stored in the `Notification` table with details like `content`, `sender`, `recipient`, and `date`.
3. The notification is linked to the relevant order.

## 6. Product Delivery
**Scenario:** Joe’s order is shipped and delivered to the provided address.

### Steps:
1. The order status is updated and shipping information is added.
2. Product stock quantities are updated (`availableQuantity` is reduced).
3. Joe is notified that his order has been delivered, and this is recorded in the `Notification` table.
