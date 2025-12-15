//class Student{
//    String name;
//    int age;
//
//    Student(){
//        name = "Nabaraj";
//        age = 23;
//   System.out.println("Default Constructor");
//                  }
//}
//public class main {
//    public static void main(String[] args){
//        Student s1 = new Student();
//        System.out.println("Name: " + s1.name+ ", Age: "+s1.age);
//    }
//}

//class Student{
//    String name;
//    int age;
//
//    Student(String name, int age){
//        this.name = "John";
//        this.age = 30;
//        System.out.println("Parameterized Constructor: ");
//            }
//}
//public class main {
//    public static void main(String[] args){
//        Student s1 = new Student("Alice", 20);
//        System.out.println("Name: " + s1.name+ ", Age: "+s1.age);
//    }
//}

//class Student{
//    String name;
//    int age;
//
//    Student(String name, int age){
//        this.name = name;
//        this.age = age;
//        System.out.println("Copy Constructor: ");
//    }
//    Student (Student other){
//        this.name=other.name;
//        this.age=other.age;
//    }
//    void display(){
//        System.out.println("Name: " +name+ ", Age:" +age);
//    }
//}
//public class main {
//    public static void main(String[] args){
//        Student s1 = new Student("Ram", 25);
//        Student s2 = new Student(s1);
//        s2.display();
//    }
//}

