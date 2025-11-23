# Restaurant Ordering & Billing System

A comprehensive restaurant management system that enables customers to browse menus, customize meals, place orders, process payments, and generate bills. The system is built using SOLID design principles and implements multiple object-oriented design patterns for flexibility, maintainability, and extensibility.

## 📋 Table of Contents

- [Features](#features)
- [Design Patterns](#design-patterns)
- [System Architecture](#system-architecture)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage Guide](#usage-guide)
- [SOLID Principles](#solid-principles)
- [Class Diagram Overview](#class-diagram-overview)

## ✨ Features

### Core Functionality
- **Multiple Menu Types**: Vegetarian, Non-Vegetarian, and Kids Menu
- **Menu Item Variations**: Different variants within each category (e.g., Italian Pizza, Eastern Pizza, Classic Burger)
- **Customizable Add-ons**: Extra cheese, sauces, and toppings using decorator pattern
- **Order Types**: Dine-in, Delivery, and Takeaway with different tax rates
- **Multiple Payment Methods**: Cash, Credit Card, and Mobile Wallet
- **Category-based Discounts**: Automatic discounts for Pizza, Meat, and Chicken items
- **Order Notifications**: Automatic notifications to Kitchen and Waiter systems
- **Order Cancellation**: Ability to cancel orders with proper notifications

### Tax System
- **Dine-in**: 10% tax
- **Delivery**: 20% tax
- **Takeaway**: No tax

### Discount System
- **Pizza Discount**: Applied to all pizza items
- **Meat Discount**: Applied to meat-based items
- **Chicken Discount**: Applied to chicken-based items

## 🎨 Design Patterns

This project implements the following design patterns:

### 1. **Abstract Factory Pattern**
- **Purpose**: Create families of related menu items without specifying concrete classes
- **Implementation**: `IMenuFactory` interface with concrete factories:
  - `VegetarianFactory`
  - `NonVegetarianFactory`
  - `KidsMenuFactory`
- **Location**: `restaurant.MenuFactory`

### 2. **Decorator Pattern**
- **Purpose**: Dynamically add add-ons (cheese, sauces, toppings) to menu items
- **Implementation**: 
  - `MenuDecorator` (abstract decorator)
  - `ExtraCheese`, `ExtraSauces`, `ExtraToppings` (concrete decorators)
- **Location**: `restaurant.CustomAddOns`

### 3. **Observer Pattern**
- **Purpose**: Notify Kitchen and Waiter systems when orders are created or cancelled
- **Implementation**:
  - `IOrderSubject` and `IOrderObserver` interfaces
  - `OrderNotifier` (Subject)
  - `KitchenNotifier` and `WaiterNotifier` (Observers)
- **Location**: `restaurant.OrderNotification`

### 4. **Strategy Pattern**
- **Purpose**: Encapsulate different algorithms for discounts and payments
- **Implementation**:
  - **Discount Strategy**: `IDiscountStrategy` with `PizzaDiscount`, `MeatDiscount`, `ChickenDiscount`
  - **Payment Strategy**: `PaymentStrategy` with `CashPayment`, `CreditPayment`, `MobileWalletPayment`
- **Location**: `restaurant.strategy.discounts` and `restaurant.strategy.payment`

### 5. **Facade Pattern**
- **Purpose**: Provide a unified interface for managing customer interactions
- **Implementation**: `RestaurantFacade` class that simplifies complex subsystem interactions
- **Location**: `restaurant.WorkflowFacade`

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────┐
│                    Main Application                     │
│                  (User Interface)                       │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│              RestaurantFacade (Facade)                   │
│  - Orchestrates complete ordering workflow               │
│  - Manages menu display, order creation, notifications  │
│  - Handles payment processing                           │
└─────┬───────────────────────────────────────────────────┘
      │
      ├─────────────────┬─────────────────┬──────────────┐
      │                 │                 │              │
      ▼                 ▼                 ▼              ▼
┌──────────┐    ┌──────────────┐   ┌──────────┐  ┌──────────┐
│  Menu    │    │   Order      │   │ Payment  │  │ Observer  │
│ Factory  │    │  Management  │   │ Strategy │  │  Pattern  │
│(Abstract │    │              │   │          │  │           │
│ Factory) │    │              │   │          │  │           │
└──────────┘    └──────────────┘   └──────────┘  └──────────┘
      │                 │                 │              │
      │                 │                 │              │
      ▼                 ▼                 ▼              ▼
┌──────────┐    ┌──────────────┐   ┌──────────┐  ┌──────────┐
│ Decorator│    │   Discount    │   │  Payment │  │ Kitchen/  │
│ Pattern  │    │   Strategy   │   │ Methods  │  │  Waiter   │
└──────────┘    └──────────────┘   └──────────┘  └──────────┘
```

## 📁 Project Structure

```
Advanced_SWE_Assignment/
│
├── src/
│   ├── Main.java
│   │
│   └── restaurant/
│       ├── Core/
│       │   ├── IMenuItem.java          # Interface for menu items
│       │   ├── MenuItem.java           # Concrete menu item
│       │   └── Order.java              # Order management
│       │
│       ├── MenuFactory/
│       │   ├── IMenuFactory.java       # Abstract factory interface
│       │   ├── VegetarianFactory.java
│       │   ├── NonVegetarianFactory.java
│       │   └── KidsMenuFactory.java
│       │
│       ├── CustomAddOns/
│       │   ├── MenuDecorator.java      # Abstract decorator
│       │   ├── ExtraCheese.java
│       │   ├── ExtraSauces.java
│       │   └── ExtraToppings.java
│       │
│       ├── OrderNotification/
│       │   ├── IOrderSubject.java      # Observer pattern interfaces
│       │   ├── IOrderObserver.java
│       │   ├── OrderNotifier.java      # Subject
│       │   ├── KitchenNotifier.java    # Observer
│       │   └── WaiterNotifier.java     # Observer
│       │
│       ├── strategy/
│       │   ├── discounts/
│       │   │   ├── IDiscountStrategy.java
│       │   │   ├── DiscountLogic.java
│       │   │   ├── PizzaDiscount.java
│       │   │   ├── MeatDiscount.java
│       │   │   └── ChickenDiscount.java
│       │   │
│       │   └── payment/
│       │       ├── PaymentStrategy.java
│       │       ├── PaymentLogic.java
│       │       ├── CashPayment.java
│       │       ├── CreditPayment.java
│       │       └── MobileWalletPayment.java
│       │
│       └── WorkflowFacade/
│           └── RestaurantFacade.java   # Main facade class
│
└── README.md
```

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) or command line

### Running the Application

1. **Using IDE**:
   - Open the project in your IDE
   - Navigate to `src/Main.java`
   - Run the `main` method

2. **Using Command Line**:
   ```bash
   # Compile
   javac -d out src/Main.java src/restaurant/**/*.java
   
   # Run
   java -cp out Main
   ```

## 📖 Usage Guide

### Ordering Process

1. **Choose Menu Type**:
   - Select 1 for Vegetarian menu
   - Select 2 for Non-Vegetarian menu
   - Select 3 for Kids menu

2. **Add Items**:
   - Enter item names as they appear in the menu (case-insensitive)
   - Type 'done' when finished adding items
   - For each item, choose add-ons (cheese, sauces, toppings)

3. **Select Order Type**:
   - Enter `DINE_IN`, `DELIVERY`, or `TAKEAWAY`

4. **Confirm or Cancel**:
   - Choose to proceed to payment or cancel the order

5. **Payment**:
   - Select payment method (Cash, Credit Card, or Mobile Wallet)
   - Enter payment details if required

### Example Menu Items

**Vegetarian Menu**:
- Italian Pizza
- Eastern Pizza

**Non-Vegetarian Menu**:
- Chicken Pizza
- Classic Beef Burger
- Classic Chicken Burger

**Kids Menu**:
- Kids Pizza
- Kids Beef Burger
- Kids Chicken Burger

### Sample Output

```
--- MENU ---
Italian Pizza (PIZZA) - 11.0
Eastern Pizza (PIZZA) - 10.5

--- Pricing Details ---
Item: Italian Pizza                  Base Price: $11.00 Discounted Price: $9.90
Subtotal: $9.90
Tax (DINE_IN): $0.99
Total: $10.89

Kitchen notified: New order 100
  - Italian Pizza
Waiter notified: New order 100
  - Italian Pizza
```

## 🎯 SOLID Principles

### Single Responsibility Principle (SRP)
- Each class has a single, well-defined responsibility
- `MenuItem` handles item data
- `Order` manages order state
- `RestaurantFacade` orchestrates workflow
- Payment strategies handle only payment logic

### Open/Closed Principle (OCP)
- System is open for extension, closed for modification
- New menu factories can be added without changing existing code
- New payment methods can be added via Strategy pattern
- New discount types can be added without modifying existing discount logic

### Liskov Substitution Principle (LSP)
- All concrete factories implement `IMenuFactory` and are interchangeable
- All decorators extend `MenuDecorator` and can replace base items
- All payment strategies implement `PaymentStrategy` interface

### Interface Segregation Principle (ISP)
- Interfaces are specific and focused
- `IMenuItem` only contains menu item operations
- `IOrderObserver` only contains notification methods
- Clients only depend on interfaces they use

### Dependency Inversion Principle (DIP)
- High-level modules depend on abstractions, not concretions
- `RestaurantFacade` depends on `IMenuFactory`, not concrete factories
- Payment and discount logic depend on strategy interfaces
- Observer pattern uses interfaces for loose coupling

## 📊 Class Diagram Overview

### Key Relationships

```
RestaurantFacade
    ├── uses → IMenuFactory (Abstract Factory)
    ├── uses → MenuDecorator (Decorator)
    ├── uses → OrderNotifier (Observer Subject)
    ├── uses → IDiscountStrategy (Strategy)
    └── uses → PaymentStrategy (Strategy)

OrderNotifier (Subject)
    ├── notifies → KitchenNotifier (Observer)
    └── notifies → WaiterNotifier (Observer)

MenuItem (Component)
    └── decorated by → ExtraCheese, ExtraSauces, ExtraToppings (Decorators)
```

## 🔧 Extensibility

The system is designed for easy extension:

- **New Menu Types**: Implement `IMenuFactory` interface
- **New Add-ons**: Extend `MenuDecorator` class
- **New Payment Methods**: Implement `PaymentStrategy` interface
- **New Discount Types**: Implement `IDiscountStrategy` interface
- **New Observers**: Implement `IOrderObserver` interface

## 📝 Notes

- Order IDs are auto-generated starting from 100
- Item names are matched case-insensitively and support partial matches
- Discounts are applied per item based on category
- Tax is calculated after discounts are applied
- All observers are notified synchronously when orders are created or cancelled

## 👨‍💻 Author

Advanced Software Engineering Assignment - Cairo University  
Faculty of Computers and Artificial Intelligence  
Course: Advanced Software Engineering 2025

---

**Built with ❤️ using SOLID principles and Design Patterns**

