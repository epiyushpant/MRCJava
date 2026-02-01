class Student {
    // private data member (data hiding)
    private int age;

    // Setter method
    public void setAge(int age) {
        if (age > 0) {          // validation
            this.age = age;
        }
    }

    // Getter method
    public int getAge() {
        return age;
    }
}

