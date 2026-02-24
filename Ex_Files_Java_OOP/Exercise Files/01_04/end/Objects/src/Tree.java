import java.awt.*;

//STATIC VS INSTANE ATTRIBUTES AND METHODS

//STATIC ATTRIBUTES AND METHODS BELONG TO THE CLASS ITSELF
//INSTANCE ATTRIBUTES AND METHODS BELONG TO OBJECTS CREATED FROM THE CLASS

//WHEN TO USE WHICH 

//IF THE ATTRIBUTE OR METHOD SHOULD BE SHARED AMONG ALL OBJECTS OF THE CLASS, MAKE IT STATIC
//IF THE ATTRIBUTE OR METHOD SHOULD BE UNIQUE TO EACH OBJECT, MAKE IT AN INSTANCE ATTRIBUTE OR


public class Tree {
    double heightFt;
    double trunkDiameterInches;
    TreeType treeType;
    static Color TRUNK_COLOR = new Color(102, 51, 0);

    Tree(double heightFt, double trunkDiameterInches,
         TreeType treeType) {
        this.heightFt = heightFt;
        this.trunkDiameterInches = trunkDiameterInches;
        this.treeType = treeType;
    }

    void grow() {
        this.heightFt = this.heightFt + 10;
        this.trunkDiameterInches = this.trunkDiameterInches + 1;
    }

    static void announceTree() {
        System.out.println("Look out for that " +
                TRUNK_COLOR + "tree!");
    }

    void announceTallTree() {
        if (this.heightFt > 100) {
            System.out.println("That's a tall " +
                    this.treeType + " tree!");
        }
    }
}

//METHODS CAN ALSO BE STATIC OR INSTANCE METHODS
//STATIC METHODS CAN ONLY ACCESS STATIC ATTRIBUTES AND CALL STATIC METHODS
//INSTANCE METHODS CAN ACCESS BOTH INSTANCE AND STATIC ATTRIBUTES AND METHODS
//USE STATIC METHODS FOR BEHAVIOURS THAT ARE RELEVANT TO THE CLASS AS A WHOLE
//USE INSTANCE METHODS FOR BEHAVIOURS THAT ARE RELEVANT TO INDIVIDUAL OBJECTS
//OF THE CLASS
//FOR EXAMPLE, ANNOUNCING THE COLOR OF A TREE TRUNK IS RELEVANT TO ALL TREES
//SO IT MAKES SENSE FOR IT TO BE A STATIC METHOD
//ANNOUNCING IF A TREE IS TALLER THAN 100 FT IS RELEVANT TO INDIVIDUAL TREES
//SO IT MAKES SENSE FOR IT TO BE AN INSTANCE METHOD
//WHEN CALLING A STATIC METHOD, USE THE CLASS NAME
//WHEN CALLING AN INSTANCE METHOD, USE THE OBJECT NAME
//OR JUST CALL IT DIRECTLY FROM WITHIN ANOTHER INSTANCE METHOD


/*

1. Static Attributes and Methods

Static attributes and methods belong to the class itself, not to individual objects.
They are shared among all objects created from the class.

Only one copy exists in memory.

Can be accessed using the class name.

Static methods can access only static attributes directly.

Use case:
When data or behavior is common for all objects of the class.

2. Instance Attributes and Methods

Instance attributes and methods belong to individual objects of a class.
Each object has its own separate copy.

Created when an object is instantiated.

Accessed using an object reference.

Instance methods can access both instance and static attributes.

Use case:
When data or behavior should be unique for each object.

*/
