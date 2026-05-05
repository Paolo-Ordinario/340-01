**Project Name:** G-Market Campus Marketplace

**Version:** Final

**Date:** May 5, 2026

**Purpose:** Define the functional use cases and non-functional test scenarios for the G-Market campus marketplace web application, covering all interactions between Customers, Sellers, and the System.

## Actors

- Customer C: A registered UNCG student who browses listings, purchases items, sends messages, leaves reviews, and saves listings for later.

- Seller S: A registered UNCG student who creates and manages product listings, uploads images, responds to reviews, and communicates with buyers.

- System SYS: The G-Market backend powered by Spring Boot and PostgreSQL. Handles authentication, data persistence, image storage, and API responses.

## Use Cases

#### 1. Customer: US-CUST-001 — Register & Manage Profile

1. Customer C1 navigates to the Sign Up page.
2. C1 enters full name, username, email, and password.
3. System validates that the email and username are not already taken.
4. System creates the account and redirects C1 to the login page.
5. C1 logs in with their credentials.
6. System authenticates the user and stores their session in localStorage.
7. C1 navigates to their Profile page.
8. C1 clicks 'Edit Profile' to open the settings modal.
9. C1 updates their display name, username, or password and saves.
10. System updates the record in the database and reflects changes immediately.

#### 2. Customer: US-CUST-002 — Upload & Update Profile Picture

1. Customer C1 navigates to their Profile page.
2. C1 clicks the 'Change Photo' button below their profile picture.
3. System opens a file picker dialog.
4. C1 selects an image file from their device.
5. System uploads the image to the backend via PUT /users/{id}/profile-picture.
6. Backend stores the image as BYTEA in the PostgreSQL database.
7. Profile picture is immediately updated on the page without a refresh.

#### 3. Customer: US-CUST-003 — Browse & Search Listings

1. Customer C1 navigates to the Market page.
2. System fetches all active (unsold) listings from the backend.
3. System displays listings in a two-column card grid with title, price, seller, and image.
4. C1 types a search query in the search bar.
5. System filters listings in real time by title or seller username.
6. C1 clicks a listing card to navigate to the seller's profile.

#### 4. Customer: US-CUST-004 — Save & Unsave Listings

1. Customer C1 browses the Market page.
2. C1 clicks the star (☆) button on a listing card.
3. System sends POST /saved/{userId}/{listingId} to the backend.
4. Backend creates a record in the saved_listings table.
5. Star icon fills (★) to indicate the listing is saved.
6. C1 navigates to the Saved page.
7. System fetches all saved listings for C1 and displays them.
8. C1 clicks the filled star (★) to unsave a listing.
9. System sends DELETE /saved/{userId}/{listingId} and removes the record.

#### 5. Customer: US-CUST-005 — Message a Seller

1. Customer C1 navigates to a seller's profile page.
2. C1 clicks the 'Message Seller' button.
3. System opens a message modal.
4. C1 types their message and clicks Send.
5. System posts the message via POST /messages to the backend.
6. Message is stored with senderId and receiverId in the database.
7. C1 navigates to the Inbox page to view their conversation.
8. C1 can reply directly from the inbox thread view.

#### 6. Customer: US-CUST-006 — Leave a Review

1. Customer C1 navigates to a seller's profile page.
2. C1 scrolls to the 'Leave a Review' section.
3. C1 enters their name, review content, and selects a star rating (1-5).
4. C1 clicks 'Submit Review'.
5. System posts the review via POST /reviews to the backend.
6. Review is stored in the database and immediately displayed in the review slider.
7. System recalculates and updates the seller's average rating and review count.

#### 7. Seller: US-SELL-001 — Create a Listing

1. Seller S1 navigates to their Profile page.
2. S1 clicks the '+ Add Product' button.
3. System opens the Add Product modal.
4. S1 enters the product title, price, description, and selects an image file.
5. S1 clicks 'Add Product'.
6. System posts listing data via POST /listings to the backend.
7. Backend saves the listing and returns the new listing ID.
8. System uploads the image via PUT /listings/{id}/image using the returned ID.
9. Backend stores the image as BYTEA in the database.
10. New listing appears immediately in S1's listings grid and on the market.

#### 8. Seller: US-SELL-002 — Edit & Manage Listings

1. Seller S1 navigates to their Profile page.
2. S1 hovers over a listing card to reveal the action overlay.
3. S1 clicks 'Edit' to open the Edit Listing modal.
4. S1 updates title, price, description, or uploads a new image.
5. S1 clicks 'Save Changes'.
6. System fetches existing listing data to preserve isSold and other fields.
7. System sends PUT /listings/{id} with merged data to the backend.
8. If a new image was selected, System uploads it via PUT /listings/{id}/image.
9. Listing is updated and reflected immediately on the profile page.

#### 9. Seller: US-SELL-003 — Mark Listing as Sold

1. Seller S1 hovers over a listing card to reveal the action overlay.
2. S1 clicks 'Mark as Sold'.
3. System sends PUT /listings/{id}/sold to the backend.
4. Listing is greyed out and shows a SOLD badge.
5. Listing is removed from the active market view.
6. S1 can click 'Mark as Available' to reverse the sold status.

#### 10. Seller: US-SELL-004 — Reply to a Review

1. Seller S1 navigates to their Profile page.
2. S1 views a review in the review slider.
3. S1 clicks the 'Reply' button on a review.
4. System opens the Reply modal.
5. S1 types their reply and clicks 'Post Reply'.
6. System sends PUT /reviews/{id}/reply to the backend.
7. Reply is stored and displayed under the review in green text.

#### 11. Seller: US-SELL-005 — View Inbox & Respond to Messages

1. Seller S1 navigates to the Inbox page.
2. System loads all conversations where S1 is sender or receiver.
3. Conversations are listed in the left panel with the last message previewed.
4. S1 clicks a conversation to open the message thread.
5. System displays all messages in chronological order with sent/received styling.
6. S1 types a reply in the input box and clicks Send or presses Enter.
7. System posts the message via POST /messages and refreshes the thread.

## CROSS-CUTTING TEST SCENARIOS (Non-Functional Requirements)

### Performance Requirements

**Scenario P1: Market page load time under typical conditions**

- **Setup:** Spring Boot running locally, Neon PostgreSQL connected, 10+ active listings in the database.

- **Steps:**

  1. Load prototype.html in the browser.
  2. Measure time from page open to listings rendering in the grid.
  3. Repeat 10 times.

- **Expected Outcome:** 95% of page loads display listings within 2 seconds.

**Scenario P2: Image upload response time**

- **Setup:** Spring Boot running, a listing exists in the database.

- **Steps:**

  1. Open the Edit modal on a listing.
  2. Select a standard image file under 2MB.
  3. Click Save and measure time until the image appears on the listing card.

- **Expected Outcome:** Image upload and display completes within 3 seconds.

### Security & Privacy Requirements

**Scenario S1: Unauthenticated access prevention**

- **Setup:** User is not logged in (no localStorage entry).

- **Steps:**

  1. Attempt to navigate directly to ProfilePrototpye.html.
  2. Attempt to click a star button on a listing in prototype.html.

- **Expected Outcome:** System redirects to login.html in both cases without exposing any user data.

**Scenario S2: User data isolation**

- **Setup:** Two users are registered — User A and User B.

- **Steps:**

  1. Log in as User A.
  2. Manually modify the user ID in localStorage to User B's ID.
  3. Attempt to edit or delete User B's listing.

- **Expected Outcome:** System should only display and allow editing of listings belonging to the authenticated user's ID.

### Usability Requirements

**Scenario U1: New user onboarding**

- **Setup:** A new user with no prior account visits the app.

- **Steps:**

  1. Navigate to Signup.html.
  2. Complete the signup form and submit.
  3. Log in and navigate to the market page.

- **Expected Outcome:** User can complete registration and browse listings within 2 minutes without any instructions.

**Scenario U2: Listing creation flow**

- **Setup:** A logged-in seller is on their profile page.

- **Steps:**

  1. Click '+ Add Product'.
  2. Fill in title, price, description, and select an image.
  3. Click 'Add Product'.

- **Expected Outcome:** Listing appears in the seller's grid and on the market page immediately with no page refresh required.
