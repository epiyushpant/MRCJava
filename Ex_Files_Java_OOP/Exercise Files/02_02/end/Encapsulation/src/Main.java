public class Main {

    public static void main(String[] args) {

        Tree myFavoriteMapleTree = new Tree(90,
                30, TreeType.MAPLE);

    //   System.out.println(myFavoriteMapleTree.treeType);
//        System.out.println(myFavoriteMapleTree.heightFt);
//        System.out.println(myFavoriteMapleTree.trunkDiameterInches);

//using access modifier and etter and setter methods
        myFavoriteMapleTree.announceTallTree();




       // another example 
//using getter and setter methods
        Student s = new Student();
        
        s.setAge(20);                   // setting value
        System.out.println(s.getAge()); // getting value
    }
}
