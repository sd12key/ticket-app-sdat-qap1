# Theatre Seat Reservation System

This project is a **command-line seat reservation system** for a movie theatre. The user can define the size of the theatre at the beginning by entering the number of rows and seats per row (with defaults and limits). Once initialized, the system presents a simple menu-driven interface that allows the user to:

- Reserve a seat  
- Cancel a reservation  
- Check seat availability  
- Exit the program

---

## Design Overview

The program uses a clean object-oriented design. The core data model (`Seat`, `Theatre`) handles seat state and layout, while `TheatreService` manages the business logic for reservations and availability.

User interaction is handled separately by `MainMenu`, which collects and validates input and displays output using centralized message constants. A `Utils` class abstracts repeated logic like user input validation.

---

## Smart Seat Suggestions

If the user checks availability and a seat is already reserved, the system suggests nearby available seats using a search algorithm within a configurable distance.
