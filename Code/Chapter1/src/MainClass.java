public class MainClass {
    public static void main(String[] args) {
       PrimitiveTypeCasting obj = new PrimitiveTypeCasting();
       obj.demoCasting();


//   PreIncrementDemo.demonstratePreIncrement();
//   PreIncrementDemo.demonstratePostIncrement();

//     BreakExample.ShowBreakExample();
//     BreakExample.ShowContinueExample();
//
//
//        LearnLiterals.GetLiterals();


//        System.out.println(args[0]);
//        System.out.println(args[1]);
       // Assignments.runAssignments();

       // StringManipulationDemo.DemoStringManipulation();
        //StringManipulationDemo.DemoStringManipulation();
       // StringManipulationDemo.comparePerformance();
        // VariableArgs.CallVarArgs();

        // String ch= "name";
        // System.out.println((ch.length()));
    }


}



/*
Step Over	Goes to next line (does NOT enter methods)	F8
Step Into	Enters inside method call	F7
Force Step Into	Enters even if method is not yours	Alt + Shift + F7
Step Out	Finish current method and return	Shift + F8
Resume Program	Continue running until next breakpoint	F9





Java Program
                  ↓
              javac
                  ↓
            Java Bytecode
                  ↓
       ┌──────────┼──────────┐
       ↓          ↓          ↓
   Windows JVM  Linux JVM  macOS JVM
       ↓          ↓          ↓
   Windows OS   Linux OS   macOS






                    JDK
                  │
        ┌─────────┴─────────┐
        │                   │
       JRE             Development Tools
        │                   │
   ┌────┴─────┐        javac, javadoc,
   │          │        jdb, jar, etc.
  JVM    Java Class
         Libraries
         │
   ┌─────┴─────────────┐
   │ java.lang         │
   │ java.util         │
   │ java.io           │
   │ java.net          │
   │ java.time         │
   │ java.sql          │
   └───────────────────┘




                            JAVA DEVELOPMENT KIT (JDK)
                 Tools required to DEVELOP Java programs
┌─────────────────────────────────────────────────────────────────────┐
│                                                                     │
│   Development Tools                                                 │
│   ┌────────────┬────────────┬────────────┬──────────────┐           │
│   │   javac    │  javadoc   │    jar     │     jdb      │           │
│   │  Compiler  │ Documentation│ Packaging │  Debugger    │           │
│   └────────────┴────────────┴────────────┴──────────────┘           │
│                                                                     │
│                           JRE                                       │
│          Java Runtime Environment                                   │
│   ┌─────────────────────────────────────────────────────────────┐   │
│   │                                                             │   │
│   │                  Java Class Libraries                       │   │
│   │                                                             │   │
│   │  java.lang    java.util    java.io    java.nio              │   │
│   │  java.net     java.time    java.sql   java.awt / Swing      │   │
│   │                                                             │   │
│   │                    +                                        │   │
│   │                                                             │   │
│   │                      JVM                                    │   │
│   │        Java Virtual Machine                                  │   │
│   │                                                             │   │
│   │  ┌───────────────────────────────────────────────────────┐  │   │
│   │  │ Class Loader                                          │  │   │
│   │  │ Loads .class files                                   │  │   │
│   │  ├───────────────────────────────────────────────────────┤  │   │
│   │  │ Bytecode Verifier                                    │  │   │
│   │  │ Checks bytecode safety                                │  │   │
│   │  ├───────────────────────────────────────────────────────┤  │   │
│   │  │ Runtime Memory Areas                                 │  │   │
│   │  │ Heap │ Stack │ Method Area │ PC Register             │  │   │
│   │  ├───────────────────────────────────────────────────────┤  │   │
│   │  │ Execution Engine                                     │  │   │
│   │  │ Interpreter + JIT Compiler                           │  │   │
│   │  ├───────────────────────────────────────────────────────┤  │   │
│   │  │ Garbage Collector                                   │  │   │
│   │  └───────────────────────────────────────────────────────┘  │   │
│   │                                                             │   │
│   └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘


       Java Source Code
            .java
              │
              │  javac  ← JDK
              ▼
       Java Bytecode
            .class
              │
              │  Class Loader
              ▼
             JVM
              │
              │  Interpreter / JIT
              ▼
        Machine Code
              │
              ▼
       Operating System



                            JAVA DEVELOPMENT KIT (JDK)
                 Tools required to DEVELOP Java programs
┌─────────────────────────────────────────────────────────────────────┐
│                                                                     │
│   Development Tools                                                 │
│   ┌────────────┬────────────┬────────────┬──────────────┐           │
│   │   javac    │  javadoc   │    jar     │     jdb      │           │
│   │  Compiler  │ Documentation│ Packaging │  Debugger    │           │
│   └────────────┴────────────┴────────────┴──────────────┘           │
│                                                                     │
│                           JRE                                       │
│          Java Runtime Environment                                   │
│   ┌─────────────────────────────────────────────────────────────┐   │
│   │                                                             │   │
│   │                  Java Class Libraries                       │   │
│   │                                                             │   │
│   │  java.lang    java.util    java.io    java.nio              │   │
│   │  java.net     java.time    java.sql   java.awt / Swing      │   │
│   │                                                             │   │
│   │                    +                                        │   │
│   │                                                             │   │
│   │                      JVM                                    │   │
│   │        Java Virtual Machine                                  │   │
│   │                                                             │   │
│   │  ┌───────────────────────────────────────────────────────┐  │   │
│   │  │ Class Loader                                          │  │   │
│   │  │ Loads .class files                                   │  │   │
│   │  ├───────────────────────────────────────────────────────┤  │   │
│   │  │ Bytecode Verifier                                    │  │   │
│   │  │ Checks bytecode safety                                │  │   │
│   │  ├───────────────────────────────────────────────────────┤  │   │
│   │  │ Runtime Memory Areas                                 │  │   │
│   │  │ Heap │ Stack │ Method Area │ PC Register             │  │   │
│   │  ├───────────────────────────────────────────────────────┤  │   │
│   │  │ Execution Engine                                     │  │   │
│   │  │ Interpreter + JIT Compiler                           │  │   │
│   │  ├───────────────────────────────────────────────────────┤  │   │
│   │  │ Garbage Collector                                   │  │   │
│   │  └───────────────────────────────────────────────────────┘  │   │
│   │                                                             │   │
│   └─────────────────────────────────────────────────────────────┘   │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘


       Java Source Code
            .java
              │
              │  javac  ← JDK
              ▼
       Java Bytecode
            .class
              │
              │  Class Loader
              ▼
             JVM
              │
              │  Interpreter / JIT
              ▼
        Machine Code
              │
              ▼
       Operating System


   
 */