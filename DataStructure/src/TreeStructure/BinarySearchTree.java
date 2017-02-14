package TreeStructure;

import com.sun.org.apache.bcel.internal.generic.IMPDEP1;

/**
 * Created by MikeChen on 14/02/2017.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> mRoot; //root
    public class Node<T extends Comparable<T>>{
        T key;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        public Node(T key, Node left, Node right, Node parent){
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
    /*
        first part is about traversal
        preOrder(), inOrder() and postOrder()
    */
    private void preOrder(Node<T> tree){
        if(tree != null){
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }
    public void preOrder(){
        preOrder(mRoot);
        System.out.println();
    }

    private void inOrder(Node<T> tree){
        if(tree != null){
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }
    public void inOrder(){
        inOrder(mRoot);
        System.out.println();
    }

    private void postOrder(Node<T> tree){
        if(tree != null){
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }
    public void postOrder(){
        postOrder(mRoot);
        System.out.println();
    }

    private Node<T> search(T key, Node<T> x){
        if (x == null)
            return null;
        if(key.compareTo(x.key) > 0){
            return search(key, x.right);
        }else if(key.compareTo(x.key) < 0){
            return search(key, x.left);
        }else {
            return x;
        }
    }

    public Node<T> search(T key){
        return searchIterative(key, mRoot);
    }

    private Node<T> searchIterative(T key, Node<T> x){
        while(x!=null){
            if(key.compareTo(x.key) > 0){
                x = x.right;
            }else if(key.compareTo(x.key) < 0){
                x = x.left;
            }else{
                return x;
            }
        }
        return null;
    }

    private Node<T> maximum(Node<T> tree){
        if(tree == null) return null;
        while(tree.right!=null){
            tree = tree.right;
        }
        return tree;
    }
    public T maximum(){
        Node<T> node = maximum(mRoot);
        if(node != null){
            return node.key;
        }
        return null;
    }

    private Node<T> minimum(Node<T> tree){
        if(tree == null) return null;
        while(tree.left!=null){
            tree = tree.left;
        }
        return tree;
    }
    public T minimum(){
        Node<T> node = minimum(mRoot);
        if(node !=null){
            return node.key;
        }
        return null;
    }

    /*
    When we are writing predecessor and successor, we need to pay attention to all the circumstances.
    For example, when we write successor, we want to find the "smallest" node from the "nodes which are bigger than current node"
    If the node has right children, all right, we find the minimum of its right children.
    What if the node is the rightest in the small tree structure? but from a bigger view it is not the smallest?
    What if the node has no right children, it is essentially a left child?
     */
    public Node<T> predecessor(Node<T> x){
        if(x == null) return null;
        if(x == minimum()){
            System.out.println("The node has no predecessor!");
            return null;
        }
        if(x.left != null){
             return maximum(x.left);
        }
        Node<T> y = x.parent;
        while((y != null) && (x == y.left)){
            x = y;
            y = y.parent;
        }
        return y;
    }

    public Node<T> successor(Node<T> x){
        if(x == null) return null;
        if(x == maximum()){
            System.out.println("The node has no successor");
            return null;
        }
        if(x.right != null){
            return minimum(x.right);
        }
        Node<T> y = x.parent;
        while((y != null) && (x == y.right)){
            x = y;
            y = y.parent;
        }
        return y;
    }

    public void insert(T key){
        Node<T> z = new Node<T>(key, null, null, null);
        Node<T> y = null; //very very important variable to store the a node value.
        if(this.mRoot == null){
            mRoot = z;
        }else {
            Node<T> temp;
            if((temp = search(key))!=null){
                temp.key = z.key;
                System.out.println("The key is already there, we replace it");
                return;
            }else {
                temp = mRoot;
                while (temp!=null){
                    y = temp;
                    if(key.compareTo(temp.key) < 0){
                        temp = temp.left;
                    }else {
                        temp = temp.right;
                    }
                }

                z.parent = y;
                if(z.key.compareTo(y.key) > 0){
                    y.right = z;
                }else {
                    y.left = z;
                }
            }
        }
    }

    /*
    When we write remove, we need to consider three/ four cases?
    What if the removed element has one child, or two?
     */
    private void remove( Node<T> node){
        if(node == null) return;
        if(node.left == null && node.right == null){
            Node<T> parent = node.parent;
            if(node == parent.left){
                parent.left = null;
            }else {
                parent.right = null;
            }
            return;
        }
        if(node.left == null && node.right != null){
            Node<T> parent = node.parent;
            Node<T> right = node.right;
            right.parent = parent;
            if(node == parent.left){
                parent.left = right;
            }else {
                parent.right = right;
            }
            return;
        }
        if(node.left != null && node.right == null){
            Node<T> parent = node.parent;
            Node<T> left = node.left;
            left.parent = parent;
            if(node == parent.left){
                parent.left = left;
            }else{
                parent.right = left;
            }
            return;
        }
        //if the node has both two child
        Node<T> succ = successor(node);
        remove(succ);
        node.key = succ.key;
    }
    public void remove(T key){
        Node<T> result;
        if((result = search(key))!=null){
            remove(result);
        }else{
            System.out.println("There is no key value "+ key+ " in this BST");
        }

    }


    /*
        direction == 0 if it is root
                  == -1 if it is left child
                  == 1 if it is right child
     */
    private void print(Node<T> tree, T key, int direction){
        if(tree!= null){
            if(direction == 0){
                System.out.println(tree.key + " is the root");
            }else if(direction == -1){
                System.out.println(tree.key + " is " + key+"'s left child");
            }else{
                System.out.println(tree.key + " is " + key+"'s right child");
            }
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }
    public void print(){
        if(mRoot!=null){
            print(mRoot, mRoot.key, 0);
        }
    }
}
