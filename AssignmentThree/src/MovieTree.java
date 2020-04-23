import java.util.ArrayList;

class Node<E extends Comparison> {
    private Node<E> parent;
    private Node<E> leftChild;
    private Node<E> rightChild;
    private E data;
    private String key;

    public Node(Node<E> parent, E data) {
        this.setParent(parent);
        this.leftChild = null;
        this.rightChild = null;
        this.setData(data);
        this.setKey(data.valueString());
    }

    public Node<E> parent() {
        return this.parent;
    }

    public Node<E> leftChild() {
        return this.leftChild;
    }

    public Node<E> rightChild() {
        return this.rightChild;
    }

    public E data() {
        return this.data;
    }

    public String key() {
        return this.key;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node<E> parent, E item) {
        this.leftChild = new Node<E>(parent, item);
    }

    public void setRightChild(Node<E> parent, E item) {
        this.rightChild = new Node<E>(parent, item);
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void addChild(E data) {
        if (data.valueString().compareTo(this.key()) < 0) {
            if (this.leftChild != null) {
                this.leftChild.addChild(data);
            } else {
                this.setLeftChild(this, data);
            }
        } else if (data.valueString().compareTo(this.key()) > 0) {
            if (this.rightChild != null) {
                this.rightChild.addChild(data);
            } else {
                this.setRightChild(this, data);
            }
        } else {
            // update this one
            this.setData(data);
        }
    }

    public Node<E> getChild(String key) {
        if (key.compareTo(this.key()) < 0) {
            if (this.leftChild != null) {
                return this.leftChild.getChild(key);
            } else {
                return this.getChild(key);
            }
        } else if (key.compareTo(this.key()) > 0) {
            if (this.rightChild != null) {
                return this.rightChild.getChild(key);
            } else {
                return this.getChild(key);
            }
        } else {
            // return this one
            return this;
        }
    }

    public void print(Node<E> node) {
        if (node != null && node.data() != null) {
            node.print(node.leftChild());
            System.out.println(node.data().valueString());
            node.print(node.rightChild());
        }
    }
}

/**
 * This class implements the Binary Search Tree abstract data type.
 */
public class MovieTree<E extends Comparison> {
    private Node<E> root;

    public MovieTree() {
        this.root = null;
    }

    public void add(E item) {
        if (this.root == null) {
            this.root = new Node<E>(null, item);
        } else {
            root.addChild(item);
        }
    }

    public E get(String key) {
        if (this.root != null) {
            if (key.equals(this.root.key())) {
                return this.root.data();
            } else {
                return this.root.getChild(key).data();
            }
        } else {
            return null;
        }
    }

    public ArrayList<E> subset(String startTitle, String endTitle) {
        return recurseSubSet(new ArrayList<E>(), root, startTitle, endTitle);
    }

    public ArrayList<E> recurseSubSet(ArrayList<E> subset, Node<E> node, String startTitle, String endTitle) {
        if(node.leftChild() != null) recurseSubSet(subset, node.leftChild(), startTitle, endTitle);
        if(node != null && node.key().compareTo(startTitle) >= 0 && node.key().compareTo(endTitle) <= 0){
            subset.add(node.data());
        }
        if(node.rightChild() != null) recurseSubSet(subset, node.rightChild(), startTitle, endTitle);
        return subset;
    }

    public void printTree() {
        this.root.print(root.leftChild());
        System.out.println(this.root.data().valueString());
        this.root.print(root.rightChild());
    }
}
