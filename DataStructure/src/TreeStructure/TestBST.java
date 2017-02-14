package TreeStructure;

/**
 * Created by MikeChen on 14/02/2017.
 */
public class TestBST {
    private static final int arr[] = {6,2,1,4,5,9,8};
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        System.out.println("Adding elements");
        for(int i = 0; i< arr.length; i++){
            System.out.print(arr[i] + " ");
            tree.insert(arr[i]);
        }
        System.out.println();
        System.out.println("Preorder: ");
        tree.preOrder();

        System.out.println("InOrder");
        tree.inOrder();

        System.out.println("PostOrder");
        tree.postOrder();

        System.out.println("max: "+ tree.maximum());
        System.out.println("min: "+ tree.minimum());

        System.out.println("Details:");
        tree.print();

        System.out.println("Delete :" + arr[1]);
        tree.remove(arr[1]);
        tree.inOrder();

        tree.print();
    }
}
