package TreeStructure;

/**
 * Created by MikeChen on 15/02/2017.
 */
public class AVLTree<T extends Comparable> {
    private Node<T> mRoot;
    private class Node<T extends Comparable>{
        T key;
        int height;
        Node<T> left;
        Node<T> right;
        public Node(T key, Node<T> left, Node<T> right){
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    private int max(int height1, int height2){
        return (height1 > height2)?height1:height2;
    }
    private int height(Node<T> tree){
        if(tree != null){
            return tree.height;
        }
        return 0;
    }
    public int height(){
        return height(mRoot);
    }

    private Node<T> leftLeftRotation(Node<T> k1){
        Node<T> k2;
        k2 = k1.left;
        k1.left = k2.right;
        k2.right = k1;
        k1.height = max(height(k1.left), height(k1.right))+1;
        k2.height = max(height(k2.left), k1.height)+1;
        return k2;
    }

    private Node<T> rightRightRotation(Node<T> k1){
        Node<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right))+1;
        k2.height = max(k1.height, height(k2.right))+1;
        return k2;
    }

    private Node<T> leftRightRotation(Node<T> k1){
        /*
        Node<T> k2 = k1.left;
        Node<T> k3 = k2.right;
        k2.right = k3.left;
        k3.left = k2;
        leftLeftRotation(k1);
        For this twice rotation, there is no need to be that complex
         */
        k1.left = rightRightRotation(k1.left);
        return leftLeftRotation(k1);
    }

    private Node<T> rightLeftRotation(Node<T> k1){
        k1.right = leftLeftRotation(k1.right);
        return rightRightRotation(k1);
    }

    private Node<T> insert(Node<T> tree, T key){
        if(tree == null){
            tree = new Node<T>(key, null, null);
            if(tree == null){
                System.out.println("Fail to create a new tree!");
                return null;
            }
        }else {
            if (tree.key.compareTo(key) > 0 ){      //the new key should insert to the left subtree
                tree.left = insert(tree.left, key);
                if(height(tree.left) - height(tree.right) == 2){
                    if(key.compareTo(tree.left.key) < 0){
                        tree = leftLeftRotation(tree);
                    }else {
                        tree = leftRightRotation(tree);
                    }
                }
            }else if(tree.key.compareTo(key) < 0){
                tree.right = insert(tree.right, key);
                if(height(tree.right) - height(tree.left) == 2){
                    if(key.compareTo(tree.right.key) < 0){
                        tree = rightLeftRotation(tree);
                    }else {
                        tree = rightRightRotation(tree);
                    }
                }
            }else {
                //if key value is equal to tree value
                System.out.println("Failed: it is not allowed to add the same key");
            }
        }
        tree.height = max(height(tree.left), height(tree.right))+1;
        return tree;
    }

    public void insert(T key){
        mRoot = insert(mRoot,key);
    }

    private Node<T> search(Node<T> tree, T key){
        if(tree == null) return null;
        if(key.compareTo(tree.key) < 0){
            return search(tree.left, key);
        }else if(key.compareTo(tree.key) > 0){
            return search(tree.right, key);
        }else{
            return tree;
        }
    }
    public Node<T> search(T key){
        return search(mRoot, key);
    }

    private Node<T> maximum(Node<T> node){
        if(node == null) return null;
        while(node.right != null){
            node = node.right;
        }
        return node;
    }

    private Node<T> minimum(Node<T> node){
        if(node == null) return null;
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    /*
    Remove the node and return the root node

    @param: the root node of the tree
            the node to be removed z

    @return: the root node
     */
    private Node<T> remove(Node<T> tree, Node<T> z){
        if(z == null){
            return null;
        }
        if(tree == null){
            return null;
        }
        if(z.key.compareTo(tree.key) < 0){
            tree.left = remove(tree.left, z);
            if(height(tree.right) - height(tree.left) == 2){
                Node<T> r = tree.right;
                if(height(r.left) > height(r.right)){
                    tree = rightLeftRotation(tree);
                }else {
                    tree = rightRightRotation(tree);
                }
            }
        }else if(z.key.compareTo(tree.key) > 0){
            tree.right = remove(tree.right, z);
            if(height(tree.left) - height(tree.right) == 2){
                Node<T> l = tree.left;
                if(height(l.left) > height(l.right)){
                    tree = leftLeftRotation(tree);
                }else {
                    tree = leftRightRotation(tree);
                }
            }
        }else{
            if(tree.left!=null && tree.right!=null){
                if(height(tree.left) > height(tree.right)){
                    Node<T> max = maximum(tree.left);
                    tree.key = max.key;
                    tree.left = remove(tree.left, max);
                }else {
                    Node<T> min = minimum(tree.right);
                    tree.key = min.key;
                    tree.right = remove(tree.right, min);
                }
            }else {
                Node<T> temp = tree;
                if(tree.left != null){
                    tree = tree.left;
                }else {             //here we let the external node also equals its right, which is a null
                    tree = tree.right;
                }
                temp = null;
            }
        }
        return tree;
    }
    public void remove(T key){
        Node<T> z;
        if((z = search(mRoot,key)) != null){
            mRoot = remove(mRoot, z);
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
