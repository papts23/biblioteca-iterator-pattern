package collection;

import iterator.Aggregate;
import iterator.Iterator;
import iterator.DepthFirstIterator;

public class LibraryTree<T> implements Aggregate<T> {
    private TreeNode<T> root;

    public LibraryTree(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    @Override
    public Iterator<T> createIterator() {
        return new DepthFirstIterator<>(root);
    }
}