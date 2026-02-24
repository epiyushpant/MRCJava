import java.awt.Color;

public class Tree {

    static Color trunkColor = new Color(102,51,0);

    double heightFt;
    double trunkDiameterInches;
    TreeType treeType;

    Tree(double heightFt, double trunkDiameterInches,
         TreeType treeType) {
        this.heightFt = heightFt;
        this.trunkDiameterInches = trunkDiameterInches;
        this.treeType = treeType;
    }

    void grow() {
        this.heightFt = heightFt + 10;
        this.trunkDiameterInches = trunkDiameterInches + 1;
    }

    void announceTallTree() {
        if (this.heightFt > 100) {
            System.out.println("That's a tall " + this.treeType +
                    " tree!");
        }
    }

}
