# Java OOP Concepts - Teaching Materials

A comprehensive, beginner-friendly guide to Object-Oriented Programming in Java.

---

## 📚 Table of Contents

| # | Concept | File | Description |
|---|---------|------|-------------|
| 1 | **Classes & Objects** | `ClassesAndObjectsDemo.java` | Creating classes, objects, constructors |
| 2 | **Encapsulation** | `EncapsulationDemo.java` | Private fields, getters/setters, data hiding |
| 3 | **Inheritance** | `InheritanceDemo.java` | Single inheritance, `extends`, `super` keyword |
| 4 | **Polymorphism** | `PolymorphismDemo.java` | Method overloading & overriding |
| 5 | **Abstraction** | `AbstractionDemo.java` | Abstract classes and methods |
| 6 | **Interfaces** | `InterfacesDemo.java` | Interface implementation, multiple inheritance |
| 7 | **Static vs Instance** | `StaticVsInstanceDemo.java` | Static and instance members |
| 8 | **Diamond Problem** | `DiamondProblemDemo.java` | Multiple inheritance issue & solutions |
| 9 | **Complete Example** | `CompleteOOPDemo.java` | Bank system using all concepts |

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
javac ClassesAndObjectsDemo.java
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
