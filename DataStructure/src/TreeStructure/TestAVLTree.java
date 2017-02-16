package TreeStructure;

/**
 * Created by MikeChen on 16/02/2017.
 */
public class TestAVLTree {
    private static int arr[] = {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        System.out.println("Adding elements...");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+" ");
            tree.insert(arr[i]);
        }
        System.out.println();

        System.out.println(tree.height());
        System.out.println("Details:");
        tree.print();

        System.out.println("Preparing to remove");
        tree.remove(8);

        tree.print();
    }
}
