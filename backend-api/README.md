# G-Market - Backend API Documentation

**Version:** 1.0  
**Last Updated:** March 23, 2026  
**Base URL:** `http://localhost:8080`

---

## Table of Contents

1. [Overview](#1-overview)
2. [User Roles](#2-user-roles)
3. [UML Class Diagram](#3-uml-class-diagram)
4. [API Endpoints](#4-api-endpoints)
   - [User Management](#user-management)
   - [Listing Management](#listing-management)
   - [Review Management](#review-management)
   - [Message Management](#message-management)
5. [Use Case Mapping](#5-use-case-mapping)

---

## 1. Overview

The G-Market Backend API provides a RESTful interface for managing a student marketplace where users can buy and sell items on campus.

- **User Accounts**: Buyers, Sellers, and SysAdmin roles
- **Listings**: Items posted for sale by sellers
- **Reviews**: Buyer feedback on sellers
- **Messages**: Direct communication between buyers and sellers

---

## 2. User Roles

| Role | Description | Primary Responsibilities |
|------|-------------|--------------------------|
| **BUYER** | Student looking to purchase items | Browse listings, message sellers, leave reviews |
| **SELLER** | Student selling items | Create listings, reply to reviews, message buyers |
| **SYS_ADMIN** | Platform administrator | Manage users, moderate listings and reviews |

---

## 3. UML Class Diagram

![UML Class Diagram](../docs/uml-class.png)

---

## 4. API Endpoints

---

### User Management

#### Sign Up
**Endpoint:** `POST /users/signup`  
**Use Case:** US-BUYER-001, US-SELLER-001 (Create Account)  
**Description:** Register a new user account.

```http
POST /users/signup
Content-Type: application/json

{
  "name": "Joe Boe",
  "username": "joeboe",
  "email": "joe@example.com",
  "password": "123456"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Joe Boe",
  "username": "joeboe",
  "email": "joe@example.com"
}
```

**Status Code:** `201 Created`

---

#### Login
**Endpoint:** `POST /users/login`  
**Use Case:** US-BUYER-001, US-SELLER-001 (Login)  
**Description:** Login with email and password.

```http
POST /users/login
Content-Type: application/json

{
  "email": "joe@example.com",
  "password": "123456"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Joe shmoe",
  "username": "joeshmoe",
  "email": "joe@example.com"
}
```

**Status Code:** `200 OK` or `401 Unauthorized`

---

#### Get All Users
**Endpoint:** `GET /users`  
**Use Case:** Admin user management  
**Description:** Retrieve all user accounts.

```http
GET /users
```

**Status Code:** `200 OK`

---

#### Get User By ID
**Endpoint:** `GET /users/{id}`  
**Use Case:** View user profile  
**Description:** Retrieve a specific user by ID.

```http
GET /users/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Update User
**Endpoint:** `PUT /users/{id}`  
**Use Case:** US-BUYER-001, US-SELLER-001 (Update Profile)  
**Description:** Update user profile information.

```http
PUT /users/1
Content-Type: application/json

{
  "name": "Joe Updated",
  "username": "joeboe",
  "email": "joe@example.com",
  "password": "123456"
}
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete User
**Endpoint:** `DELETE /users/{id}`  
**Use Case:** US-ADMIN-001 (Manage User Access)  
**Description:** Delete a user account.

```http
DELETE /users/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

### Listing Management

#### Get All Listings
**Endpoint:** `GET /listings`  
**Use Case:** US-BUYER-002 (Browse Listings)  
**Description:** Retrieve all listings.

```http
GET /listings
```

**Status Code:** `200 OK`

---

#### Get Active Listings
**Endpoint:** `GET /listings/active`  
**Use Case:** US-BUYER-002 (Browse Available Items)  
**Description:** Retrieve all listings that have not been sold.

```http
GET /listings/active
```

**Status Code:** `200 OK`

---

#### Get Listing By ID
**Endpoint:** `GET /listings/{id}`  
**Use Case:** US-BUYER-003 (View Listing Details)  
**Description:** Retrieve a specific listing by ID.

```http
GET /listings/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Get Listings By User
**Endpoint:** `GET /listings/user/{userId}`  
**Use Case:** US-SELLER-003 (View My Listings)  
**Description:** Retrieve all listings posted by a specific user.

```http
GET /listings/user/1
```

**Status Code:** `200 OK`

---

#### Search Listings
**Endpoint:** `GET /listings/search?title={title}`  
**Use Case:** US-BUYER-002 (Search for Items)  
**Description:** Search listings by title keyword.

```http
GET /listings/search?title=watch
```

**Status Code:** `200 OK`

---

#### Create Listing
**Endpoint:** `POST /listings`  
**Use Case:** US-SELLER-002 (Post a Listing)  
**Description:** Create a new item listing.

```http
POST /listings
Content-Type: application/json

{
  "userId": 1,
  "title": "White Watch",
  "description": "Good condition, barely used.",
  "price": 60.00,
  "imageUrl": "https://example.com/watch.jpg"
}
```

**Response:**
```json
{
  "id": 1,
  "userId": 1,
  "title": "White Watch",
  "description": "Good condition, barely used.",
  "price": 60.00,
  "imageUrl": "https://example.com/watch.jpg",
  "isSold": false,
  "createdAt": "2026-03-24T10:30:00"
}
```

**Status Code:** `201 Created`

---

#### Update Listing
**Endpoint:** `PUT /listings/{id}`  
**Use Case:** US-SELLER-002 (Edit Listing)  
**Description:** Update an existing listing.

```http
PUT /listings/1
Content-Type: application/json

{
  "title": "White Watch - Price Drop",
  "price": 50.00
}
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Mark As Sold
**Endpoint:** `PUT /listings/{id}/sold`  
**Use Case:** US-SELLER-004 (Mark Item as Sold)  
**Description:** Mark a listing as sold.

```http
PUT /listings/1/sold
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Listing
**Endpoint:** `DELETE /listings/{id}`  
**Use Case:** US-SELLER-002, US-ADMIN-002 (Remove Listing)  
**Description:** Delete a listing.

```http
DELETE /listings/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

### Review Management

#### Get All Reviews
**Endpoint:** `GET /reviews`  
**Use Case:** Admin moderation  
**Description:** Retrieve all reviews.

```http
GET /reviews
```

**Status Code:** `200 OK`

---

#### Get Reviews By Seller
**Endpoint:** `GET /reviews/seller/{sellerId}`  
**Use Case:** US-BUYER-004 (View Seller Reviews)  
**Description:** Retrieve all reviews for a specific seller.

```http
GET /reviews/seller/1
```

**Status Code:** `200 OK`

---

#### Get Review By ID
**Endpoint:** `GET /reviews/{id}`  
**Use Case:** Review detail view  
**Description:** Retrieve a specific review by ID.

```http
GET /reviews/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Create Review
**Endpoint:** `POST /reviews`  
**Use Case:** US-BUYER-004 (Leave a Review)  
**Description:** Post a new review for a seller.

```http
POST /reviews
Content-Type: application/json

{
  "sellerId": 1,
  "reviewerName": "John Doe",
  "content": "Great seller, fast response!",
  "rating": 5
}
```

**Response:**
```json
{
  "id": 1,
  "sellerId": 1,
  "reviewerName": "John Doe",
  "content": "Great seller, fast response!",
  "rating": 5,
  "reply": null,
  "createdAt": "2026-03-24T10:30:00"
}
```

**Status Code:** `201 Created`

---

#### Reply To Review
**Endpoint:** `PUT /reviews/{id}/reply`  
**Use Case:** US-SELLER-005 (Reply to Review)  
**Description:** Seller replies to a review.

```http
PUT /reviews/1/reply
Content-Type: application/json

"Thanks for the kind words!"
```

**Status Code:** `200 OK` or `404 Not Found`

---

#### Delete Review
**Endpoint:** `DELETE /reviews/{id}`  
**Use Case:** US-ADMIN-003 (Moderate Reviews)  
**Description:** Delete a review.

```http
DELETE /reviews/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

### Message Management

#### Get All Messages
**Endpoint:** `GET /messages`  
**Use Case:** Admin oversight  
**Description:** Retrieve all messages.

```http
GET /messages
```

**Status Code:** `200 OK`

---

#### Get Messages By Sender
**Endpoint:** `GET /messages/sender/{senderId}`  
**Use Case:** US-BUYER-005, US-SELLER-006 (View Sent Messages)  
**Description:** Retrieve all messages sent by a user.

```http
GET /messages/sender/1
```

**Status Code:** `200 OK`

---

#### Get Messages By Receiver
**Endpoint:** `GET /messages/receiver/{receiverId}`  
**Use Case:** US-BUYER-005, US-SELLER-006 (View Received Messages)  
**Description:** Retrieve all messages received by a user.

```http
GET /messages/receiver/2
```

**Status Code:** `200 OK`

---

#### Get Conversation
**Endpoint:** `GET /messages/conversation/{senderId}/{receiverId}`  
**Use Case:** US-BUYER-005, US-SELLER-006 (View Conversation)  
**Description:** Retrieve all messages between two users.

```http
GET /messages/conversation/1/2
```

**Status Code:** `200 OK`

---

#### Send Message
**Endpoint:** `POST /messages`  
**Use Case:** US-BUYER-005, US-SELLER-006 (Send Message)  
**Description:** Send a message to another user.

```http
POST /messages
Content-Type: application/json

{
  "senderId": 1,
  "receiverId": 2,
  "content": "Hey, is the watch still available?"
}
```

**Response:**
```json
{
  "id": 1,
  "senderId": 1,
  "receiverId": 2,
  "content": "Hey, is the watch still available?",
  "createdAt": "2026-03-24T10:30:00"
}
```

**Status Code:** `201 Created`

---

#### Delete Message
**Endpoint:** `DELETE /messages/{id}`  
**Use Case:** Message deletion  
**Description:** Delete a message.

```http
DELETE /messages/1
```

**Status Code:** `200 OK` or `404 Not Found`

---

## 5. Use Case Mapping

### Buyer Use Cases

| Use Case | Description | Related Endpoints |
|----------|-------------|-------------------|
| **US-BUYER-001** | Register & manage profile | `POST /users/signup`, `PUT /users/{id}` |
| **US-BUYER-002** | Browse & search listings | `GET /listings`, `GET /listings/active`, `GET /listings/search` |
| **US-BUYER-003** | View listing details | `GET /listings/{id}` |
| **US-BUYER-004** | Leave & read reviews on sellers | `POST /reviews`, `GET /reviews/seller/{sellerId}` |
| **US-BUYER-005** | Message a seller | `POST /messages`, `GET /messages/conversation/{senderId}/{receiverId}` |

### Seller Use Cases

| Use Case | Description | Related Endpoints |
|----------|-------------|-------------------|
| **US-SELLER-001** | Register & manage profile | `POST /users/signup`, `PUT /users/{id}` |
| **US-SELLER-002** | Create & manage listings | `POST /listings`, `PUT /listings/{id}`, `DELETE /listings/{id}` |
| **US-SELLER-003** | View my listings | `GET /listings/user/{userId}` |
| **US-SELLER-004** | Mark item as sold | `PUT /listings/{id}/sold` |
| **US-SELLER-005** | Reply to reviews | `PUT /reviews/{id}/reply` |
| **US-SELLER-006** | Message a buyer | `POST /messages`, `GET /messages/conversation/{senderId}/{receiverId}` |

### SysAdmin Use Cases

| Use Case | Description | Related Endpoints |
|----------|-------------|-------------------|
| **US-ADMIN-001** | Manage user access | `GET /users`, `PUT /users/{id}`, `DELETE /users/{id}` |
| **US-ADMIN-002** | Moderate listings | `GET /listings`, `DELETE /listings/{id}` |
| **US-ADMIN-003** | Moderate reviews | `GET /reviews`, `DELETE /reviews/{id}` |

---
