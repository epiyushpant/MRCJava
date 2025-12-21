public class Main {

    public static void main(String[] args) {
        Tree myFavoriteOakTree = new Tree(120,
                12, TreeType.OAK);

        Tree myFavoriteMapleTree = new Tree(90,
                30, TreeType.MAPLE);

        System.out.println(myFavoriteOakTree.heightFt);
        myFavoriteOakTree.announceTallTree();

        myFavoriteMapleTree.announceTallTree();

        System.out.println(Tree.TRUNK_COLOR);
        Tree.announceTree();
    }
}
