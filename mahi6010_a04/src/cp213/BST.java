package cp213;

import java.util.ArrayList;

/**
 * Implements a Binary Search Tree.
 *
 * @author Roshni Mahindru
 * @author David Brown
 * @version 2022-11-20
 */
public class BST<T extends Comparable<T>> {
    protected int comparisons = 0; // Count of comparisons performed by tree.

    // Attributes.
    protected TreeNode<T> root = null; // Root node of the tree.
    protected int size = 0; // Number of elements in the tree.

    /**
     * Auxiliary method for {@code equals}. Determines whether two subtrees are
     * identical in items and height.
     *
     * @param source Node of this BST.
     * @param target Node of that BST.
     * @return true if source and target are identical in items and height.
     */
    protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {
    
    boolean equal = true;
    if (source == null && target != null || source != null && target == null) {
    	equal = false;
    }
    if (equal && source.getCs().compareTo(target.getCs()) == 0) {
    	equal = equalsAux(source.getLeft(), target.getLeft());
    	if (equal) {
    		equal = equalsAux(source.getRight(), target.getRight());
    	}
    }
    else {
    	equal = false;
    }
	return equal;
    }

    /**
     * Auxiliary method for {@code insert}. Inserts data into this BST.
     *
     * @param node the current node (TreeNode)
     * @param data data to be inserted into the node
     * @return the inserted node.
     */
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedStore<T> data) {

	if (node == null) { //Add a new node containing data
		TreeNode<T> new_node = new TreeNode<T>(data);
		this.size++;
		new_node.getCs().incrementCount();
		return new_node;
	}
	else if (data.compareTo(node.getCs()) > 0) {
		node.setRight(insertAux(node.getRight(), data));
		node.updateHeight();
	}
	else if (data.compareTo(node.getCs()) < 0) {
		node.setLeft(insertAux(node.getLeft(), data));
		node.updateHeight();
	}
	else {
		node.getCs().incrementCount();
	}
	return node;
    }

    /**
     * Auxiliary method for {@code valid}. Determines if a subtree based on node is
     * a valid subtree.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
    	//Base case
    	if (node == null) {
    		return true;
    	}
    	else if (minNode != null && minNode.getCs().compareTo(node.getCs()) > 0 ) {
    		return false;
    	}
    	else if  (maxNode != null && maxNode.getCs().compareTo(node.getCs()) < 0 ) {
    		return false;
    	}
    	return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);

    }

    /**
     * Returns the height of a given TreeNode.
     *
     * @param node The TreeNode to determine the height of.
     * @return The item of the height attribute of node, 0 if node is null.
     */
    protected int nodeHeight(final TreeNode<T> node) {
	int height = 0;

	if (node != null) {
	    height = node.getHeight();
	}
	return height;
    }

    /**
     * Determines if this BST contains key.
     *
     * @param key The key to search for.
     * @return true if this BST contains key, false otherwise.
     */
    public boolean contains(final CountedStore<T> key) {
    	boolean found = false;
    	if (this.root == null) {
    		return false;
    	}
    	else {
    		found = containsAux(key, this.root);
    	}
	return found;
    }
    
    private boolean containsAux(final CountedStore<T> key, final TreeNode<T> node) {
    	boolean found = false;
    	if (node != null) {
    		if (node.getCs().compareTo(key) == 0) {
    			found = true;
    		}
    		else {
    			found = containsAux(key, node.getLeft());
    			if (found == false) {
    				found = containsAux(key, node.getRight());
    			}
    		}
    	}
    	return found;
    }

    /**
     * Determines whether two BSTs are identical.
     *
     * @param target The BST to compare this BST against.
     * @return true if this BST and that BST contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final BST<T> target) {
	boolean isEqual = false;

	if (this.size == target.size) {
	    isEqual = this.equalsAux(this.root, target.root);
	}
	return isEqual;
    }

    /**
     * Get number of comparisons executed by the {@code retrieve} method.
     *
     * @return comparisons
     */
    public int getComparisons() {
	return this.comparisons;
    }

    /**
     * Returns the height of the root node of this BST.
     *
     * @return height of root node, 0 if the root node is null.
     */
    public int getHeight() {
	int height = 0;

	if (this.root != null) {
	    height = this.root.getHeight();
	}
	return height;
    }

    /**
     * Returns the number of nodes in the BST.
     *
     * @return number of node in this BST.
     */
    public int getSize() {
	return this.size;
    }

    /**
     * Returns an array of copies of CountedStore objects in a linked data
     * structure. The array contents are in data order from smallest to largest.
     *
     * Not thread safe as it assumes contents of data structure are not changed by
     * an external thread during the copy loop. If data elements are added or
     * removed by an external thread while the data is being copied to the array,
     * then the declared array size may no longer be valid.
     *
     * @return this tree data as an array of data.
     */
    public ArrayList<CountedStore<T>> inOrder() {
	return this.root.inOrder();
    }

    /**
     * Inserts data into this BST.
     *
     * @param data Data to store.
     */
    public void insert(final CountedStore<T> data) {
    
	this.root = this.insertAux(this.root, data);
	this.root.updateHeight();
	return;
    }

    /**
     * Determines if this BST is empty.
     *
     * @return true if this BST is empty, false otherwise.
     */
    public boolean isEmpty() {
	return this.root == null;
    }

    /**
     * Determines if this BST is a valid BST; i.e. a node's left child data is
     * smaller than its data, and its right child data is greater than its data, and
     * a node's height is equal to the maximum of the heights of its two children
     * (empty child nodes have a height of 0), plus 1.
     *
     * @return true if this BST is a valid BST, false otherwise.
     */
    public boolean isValid() {
	return this.isValidAux(this.root, null, null);
    }

    /**
     * Returns an array of copies of CountedStore objects int a linked data
     * structure. The array contents are in level order starting from the root
     * (this) node. Helps determine the structure of the tree.
     *
     * Not thread safe as it assumes contents of data structure are not changed by
     * an external thread during the copy loop. If data elements are added or
     * removed by an external thread while the data is being copied to the array,
     * then the declared array size may no longer be valid.
     *
     * @return this tree data as an array of data.
     */
    public ArrayList<CountedStore<T>> levelOrder() {
	return this.root.levelOrder();
    }

    /**
     * Resets the comparison count to 0.
     */
    public void resetComparisons() {
	this.comparisons = 0;
	return;
    }

    /**
     * Retrieves a copy of data matching key (key should have item
     * count of 0). Returning a complete CountedStore gives access to the
     * item and count.
     *
     * @param key The key to look for.
     * @return data The complete CountedStore that matches key, null otherwise.
     */
    public CountedStore<T> retrieve(final CountedStore<T> key) {
    	CountedStore<T> value = null;
    	TreeNode<T> node = this.root;
    	
    	while (node != null && value == null) {
    		this.comparisons++;
    		if (node.getCs().compareTo(key) > 0) {
    			node = node.getLeft();
    		}
    		else if (node.getCs().compareTo(key) < 0) {
    			node = node.getRight();
    		}
    		else if (node.getCs().compareTo(key) == 0) {
    			value = node.getCs();
    		}
    	}
	return value;
    }
}