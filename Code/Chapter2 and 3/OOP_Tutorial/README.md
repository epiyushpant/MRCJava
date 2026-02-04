# Java OOP Concepts - Teaching Materials

A comprehensive, beginner-friendly guide to Object-Oriented Programming in Java.

---

## 📚 Table of Contents

| # | Concept | File | Description |
|---|---------|------|-------------|
| 1 | **Classes & Objects** | `01_ClassesAndObjects.java` | Creating classes, objects, constructors |
| 2 | **Encapsulation** | `02_Encapsulation.java` | Private fields, getters/setters, data hiding |
| 3 | **Inheritance** | `03_Inheritance.java` | Single inheritance, `extends`, `super` keyword |
| 4 | **Polymorphism** | `04_Polymorphism.java` | Method overloading & overriding |
| 5 | **Abstraction** | `05_Abstraction.java` | Abstract classes and methods |
| 6 | **Interfaces** | `06_Interfaces.java` | Interface implementation, multiple inheritance |
| 7 | **Static vs Instance** | `07_StaticVsInstance.java` | Static and instance members |
| 8 | **Diamond Problem** | `08_DiamondProblem.java` | Multiple inheritance issue & solutions |
| 9 | **Complete Example** | `09_CompleteOOPExample.java` | Bank system using all concepts |

---

## 🎯 The Four Pillars of OOP

```
┌─────────────────────────────────────────────────────────────────────┐
│                        Object-Oriented Programming                  │
├────────────────┬────────────────┬────────────────┬─────────────────┤
│ Encapsulation  │  Inheritance   │ Polymorphism   │  Abstraction    │
│                │                │                │                 │
│ Data hiding    │ Code reuse     │ Many forms     │ Hide complexity │
│ Private fields │ extends, super │ Overloading    │ Abstract class  │
│ Getters/Setters│ Parent/Child   │ Overriding     │ Interface       │
└────────────────┴────────────────┴────────────────┴─────────────────┘
```

---

## 🚀 How to Run

### Compile and Run a Single File:
```bash
javac 01_ClassesAndObjects.java
java ClassesAndObjectsDemo
```

### Or run all examples:
```bash
javac *.java
```

---

## 📖 Quick Reference

### Access Modifiers
| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `default` | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

### Key Differences

| Method Overloading | Method Overriding |
|--------------------|-------------------|
| Compile-time polymorphism | Runtime polymorphism |
| Same class | Parent-child classes |
| Different parameters | Same signature |
| Can change return type | Must have same return type |

---

## 👨‍🏫 Author
Created for MRC Java Teaching - Chapter 2 & 3
