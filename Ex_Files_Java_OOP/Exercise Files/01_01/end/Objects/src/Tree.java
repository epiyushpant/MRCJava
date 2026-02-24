public class Tree {
    //attributes of a tree 
    double heightFt;
    double trunkDiameterInches;
    TreeType treeType;


    //behaviours of a tree
    void grow() {
        this.heightFt = this.heightFt + 10;
        this.trunkDiameterInches = this.trunkDiameterInches + 1;
    }
}
