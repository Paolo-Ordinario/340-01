# 340-01
340 Project

Dylan Paolo Ordinario

## Title
> G-Market

## Team Members
> Paolo Ordinario

> Brian Ramirez

## Description 
> This app is for UNCG Students who want to buy, sell, and trade items with other students. This app was made because college is a place to make new friends, share, and learn with other people, what best way to do that than to create a trade market for students. This app became a way for UNCG Students to interact and gain easier access to items that would be farther away than on campus. 
>

## Project Structure
> backend-api/ is where all the backend code is. It handles everything the app needs to run by creating the students accounts, posting listings, sending messages, and leaving reviews. We built it in Java and it runs a server that can talk to the frontend. high-fidelity-prototype/ is the early mockup pages that we made to plan out what the app would look like. Each of files shows a different screen like the login page, profile and listing page. docs/ holds the project documentation. The SRS which outlines all of the parts we planned and the UML diagram that shows how the different parts of the app connect to eachother. mvc-app/ breaks down how the backend is organized by using the MVC pattern and basically showing which files do what and how each feature connects to its code.

## App Functions
1. Customer:
    1. Create/modify Student(customer) profile - Use student email to create account
    2. View available products - Allow .
    3. Write reviews for products bought from students - Allow reviews when items are traded or bought in the students profile.
2. Provider:
    1. Create/modify/remove provider profile - Same as Customer, use student email to create account
    2. Create service - Allow the use of pictures and description to create items being sold
    4. Reply to reviews - Allow Provider to respond to reviews left by other students on their own profile
3. SysAdmin (the user with the admin role if applicable):
    1. Manage user access - .
    2. Moderate services - .
    3. Moderate reviews - .
    4. View usage statistics - .
  
<div class="bruno-fetch-button" 
  data-bruno-collection-url="https://github.com/Paolo-Ordinario/340-01/tree/main/340-01/backend-api/Bruno/G-Market">
</div>
<script src="https://fetch.usebruno.com/button.js"></script>
