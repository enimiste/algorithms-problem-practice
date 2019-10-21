package com.nouni.upwork.sjaak.prob2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree implements Set {
	protected Node root;
	protected Comparator<String> comparator;

	static class Node {
		String data;
		Node left;
		Node right;
	}

	/**
	 * 
	 * @param cmp
	 */
	public BinarySearchTree(Comparator<String> cmp) {
		if (cmp == null)
			throw new IllegalArgumentException("You Should provide a non-null comparator.");
		root = null;
		this.comparator = cmp;
	}

	@Override
	public Iterator<String> ascendingIterator() {
		List<String> res = new ArrayList<>();
		if (root != null)
			fillFromLeftToRight(root, res);
		return res.iterator();
	}

	@Override
	public Iterator<String> descendingIterator() {
		List<String> res = new ArrayList<>();
		if (root != null)
			fillFromRightToLeft(root, res);
		return res.iterator();
	}

	/**
	 * Non-increasing order Right > Data > Left
	 * 
	 * @param node
	 * @param res
	 */
	private void fillFromRightToLeft(Node node, List<String> res) {
		if (node.right != null)
			fillFromRightToLeft(node.right, res);
		res.add(node.data);
		if (node.left != null)
			fillFromRightToLeft(node.left, res);
	}

	/**
	 * Non-decreasing order Left > Data > Right
	 * 
	 * @param node
	 * @param res
	 */
	private void fillFromLeftToRight(Node node, List<String> res) {
		if (node.left != null)
			fillFromLeftToRight(node.left, res);
		res.add(node.data);
		if (node.right != null)
			fillFromLeftToRight(node.right, res);
	}

	@Override
	public boolean add(String e) {
		if (root == null) {
			root = new Node();
			root.data = e;
		} else {
			int cmp = this.comparator.compare(e, root.data);
			if (cmp == 0) {
				if (root.left == null && root.right == null)
					root = null;
				else
					root = merge(root.left, root.right);
				return false;
			} else if (cmp < 0) {
				return addLeft(root, e);
			} else {
				return addRight(root, e);
			}
		}
		return true;
	}

	/**
	 * @precondition left and right are subtrees of the same tree
	 * @param left
	 * @param right
	 * @return
	 */
	private Node merge(Node left, Node right) {
		if (left == null)
			return right;
		else if (right == null)
			return left;
		else {
			// Two choices : Inject Left into the right or Right tree into the left one
			// I will use the first choice here
			return add(right, left);
		}
	}

	/**
	 * Insert a node tree into another one
	 * 
	 * @precondition node is an sub tree of the root one
	 * @param root
	 * @param node
	 * @return
	 */
	private Node add(Node root, Node node) {
		String e = node.data;
		int cmp = this.comparator.compare(e, root.data);
		if (cmp == 0) {
			// We will not handle this case here; it is out of the scope where this method
			// is used
			throw new IllegalStateException(
					"Adding node with the same value into another tree with root having the same value is not supported.");
		} else if (cmp < 0) {
			if (root.left == null)
				root.left = node;
			else
				root.left = add(root.left, node);
			return root;
		} else {
			if (root.right == null)
				root.right = node;
			else
				root.right = add(root.right, node);
			return root;
		}
	}

	/**
	 * Add the e at the right of the root node
	 * 
	 * @param root
	 * @param e
	 * @return
	 */
	private boolean addRight(Node root, String e) {
		Node right = root.right;
		if (right == null) {
			right = new Node();
			right.data = e;
			root.right = right;
			return true;
		}
		int cmp = this.comparator.compare(e, right.data);
		if (cmp == 0) {
			root.right = merge(right.left, right.right);
			return false;
		} else if (cmp < 0) {
			return addLeft(right, e);
		} else {
			return addRight(right, e);
		}
	}

	/**
	 * Add the e at the left of the root node
	 * 
	 * @param root
	 * @param e
	 * @return
	 */
	private boolean addLeft(Node root, String e) {
		Node left = root.left;
		if (left == null) {
			left = new Node();
			left.data = e;
			root.left = left;
			return true;
		}
		int cmp = this.comparator.compare(e, left.data);
		if (cmp == 0) {
			root.left = merge(left.left, left.right);
			return false;
		} else if (cmp < 0) {
			return addLeft(left, e);
		} else {
			return addRight(left, e);
		}
	}

}
