
# Requirements – G-Market

**Project Name:** G-Market\
**Team:** Paolo Ordinario - Sellers; Brian Ramirez - Customer\
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-02-13\
**Purpose:** This SRS consolidates the scope and user‑facing requirements of G-Market and enumerates all user stories that guide development and testing.

---

## 1. Overview
**Vision.** We want G-Market to be buying and trading place for UNCG students. We want students to be able to create profiles from their UNCG emails so they can trade with other UNCG students.

**Glossary**
- **Seller Items:** Products sellers are wishing to sell, from clothes to, books, to tech.
- **Engagement:** Like Specific products, dm sellers, follow sellers, and leave and reply to reviews. 

**Primary Users / Roles.**
- **Customer** — ; View products being sold, Direct message sellers, like items for later, review items bought.
- **Seller** — publish items to sell and description of it; manage how much and what they are selling; respond to reviews; view engagement.
- **SysAdmin (None)** — manage user access; moderate listings & reviews; monitor usage/delivery health.

**Scope (this semester).**
- Browse items being sold with filtering/sorting/searching. 
- Like and follow Items and sellers; manage Follows and likes. 
- Create and account; create/edit account and manage what you are selling; update descriptions of items and activity. 
- Reviews (customer write; Sellers reply) with basic moderation.
- Customers can directly message sellers for extra information on items or set up meet ups to buy.
- Admin access management and moderation workflows. 

**Out of scope (deferred).**
- Online payments and refund processing. 
- Online delievery/delievery System. 
- Multi‑language UI beyond English.

---

## 2. Functional Requirements

### 2.1 Customer Stories
- **US‑CUST‑001 — Register & manage profile**  
  _Story:_ As a customer, I want to create or update my profile so that I receive relevant offerings/products.  
  _Acceptance:_
  ```gherkin
  Scenario: Register with valid details
    Given I am not registered
    When I sign up with required fields
    Then my customer profile is created and visible in my account
  ```

- **US‑CUST‑002 — Browse seller profiles**  
  _Story:_ As a customer, I want to browse other profiles  see what people are selling and whether I want to follow the seller
  _Acceptance:_
  ```gherkin
  Scenario: Find Sellers
    Given seller names
    Then items appear ordered from the closest match
  ```

- **US‑CUST‑003 — Discover produce boxes (filter & sort)**  
  _Story:_ As a customer, I want to filter and sort available boxes by season, farm, contents, price, and pickup/delivery so that I can find options that fit my needs and budget.  
  _Acceptance:_
  ```gherkin
  Scenario: Sort items by description/details
    Given description of the item
    When I filter it
    Then I see only items which match the description/details the closest
  ```

- **US‑CUST‑004 — View produce box details**  
  _Story:_ As a customer, I want to open an item and see descriptions of the item, price, product name, and others related.  
  _Acceptance:_
  ```gherkin
  Scenario: View item details
    Given a published product exists
    When I open the product, information on item and seller information pop up.
    Then I see contents, price, and other related details of the item and seller.
  ```

- **US‑CUST‑005 — Follow Sellers**  
  _Story:_ As a customer, I want to follow sellers so that I receive their items on my feed.  
  _Acceptance:_
  ```gherkin
  Scenario: Follow a seller
    Given the seller is real
    When I choose to follow
    Then I am able to see their products/ new products on my feed
  ```

- **US‑CUST‑006 — Write a review**  
  _Story:_ As a customer, I want to rate and write reviews on the sellers profile about the item bought and the buying interaction to the benefit of other costumers.
  _Acceptance:_
  ```gherkin
  Scenario: Eligible review within policy window
    Given I received a item from seller
    When I submit a rating and a comment
    Then the review is recorded and linked to the sellers profile
      And it is published or queued per moderation policy
  ```

- **US‑CUST‑007 — Read reviews**  
  _Story:_ As a customer, I want to read previous reviews on a seller profile so that I can make an informed decision.  
  _Acceptance:_
  ```gherkin
  Scenario: List reviews on seller profiles
    Given reviews exist for a box
    When I open Reviews
    Then I see the reviews with rating and comment
  ```

### 2.2 Seller Stories
- **US‑SELL‑001 — Register & manage profile**  
  _Story:_ As a Seller, I want to create/update my profile so that customers understand who we are.  
  _Acceptance:_
  ```gherkin
  Scenario: Update seller profile
    Given I am a verified provider
    When I add or update profile details
    Then the profile is saved and visible on the seller page
  ```

- **US‑SELL‑002 — Products to sell**  
  _Story:_ As a seller, I want to publish an item to sell so costumers know what is being sold
  _Acceptance:_
  ```gherkin
  Scenario: Policy-compliant listing
    Given my = profile is active
    When I enter required fields and confirm
    Then the box is created in "Published" (or "Pending") state
      And it becomes discoverable in search within 60 seconds
  ```

- **US‑SELL‑003 — Update Products being sold**  
  _Story:_ As a seller, I want to adjust prices, description and availability of listings to let costumers know updated information.  
  _Acceptance:_
  ```gherkin
  Scenario: I want to adjust published item description
    Given a change in item description
    Then the listing reflects the new change
  ```

- **US‑SELL‑004 — Manage Products (sold out)**  
  _Story:_ As a seller, I want to mark a prodcut as sold when item is sold so costumers dont direct message me about item.
  _Acceptance:_
  ```gherkin
  Scenario: Show sold out
    Given item is sold
    When a customer views the box
    Then the system shows Sold and disables direct message for item
  ```

- **US‑SELL‑005 — View customer engagement metrics**  
  _Story:_ As a seller, I want to view active follows, average rating, and feedback so that I can improve as a seller.  
  _Acceptance:_
  ```gherkin
  Scenario: View how many people are following and who
    Given I open the engagement dashboard
    When I select a date range and a box
    Then I see active followers and average rating as a seller
  ```

- **US‑SELL‑006 — Reply to customer reviews**  
  _Story:_ As a Seller, I want to post a public reply to a customer review so that I can acknowledge feedback and share context.  
  _Acceptance:_
  ```gherkin
  Scenario: Reply to a review
    Given a published review exists for my profile
    When I submit a reply
    Then the reply appears publicly beneath the review (subject to moderation)
  ```

  - **US‑SELL‑007 — Reply to customer Direct messages**  
  _Story:_ As a Seller, I want to reply to direct messages of sellers
  _Acceptance:_
  ```gherkin
  Scenario: Reply to a message
    Given a customer messages a seller
    When I submit a reply
    Then the reply appears for the costumer in the chat
  ```

## 3. Non‑Functional Requirements
- **Performance:** 95% of discovery responses ≤ **1.5s**; 99% of box detail pages ≤ **1.0s** under typical load. 
- **Availability/Reliability:** ≥ **99.5%** monthly uptime (maintenance excluded); basic retries for transient failures. 
- **Security/Privacy:** Hashed & salted passwords; role‑based access checks.
- **Usability:** New users complete first subscription in ≤ **3 minutes** in hallway tests.

---

## 4. Assumptions, Constraints, and Policies
- Modern browsers (latest Chrome/Firefox/Edge/Safari); stable connectivity. 
- Course timeline & campus infrastructure constraints apply. 
- Policies: review allowed if item was sold to them; content guidelines (no PII/harassment); cancellation rules per box terms; capacity rules prevent overbooking.

---

## 5. Milestones (course‑aligned)
- **M2 Requirements** — this file + stories opened as issues. 
- **M3 High‑fidelity prototype** — core customer/provider flows clickable. 
- **M4 Design** — architecture, schema, API outline. 
- **M5 Backend API** — key endpoints + tests. 
- **M6 Increment** — ≥2 use cases end‑to‑end. 
- **M7 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.