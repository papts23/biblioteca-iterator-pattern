package iterator;

import collection.TreeNode;
import java.util.Stack;
import java.util.List;
import java.util.NoSuchElementException;

public class DepthFirstIterator<T> implements Iterator<T> {

    private Stack<TreeNode<T>> stack = new Stack<>();

    public DepthFirstIterator(TreeNode<T> root) {
        if (root != null) {
            stack.push(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Fin de la biblioteca.");
        }

        TreeNode<T> currentNode = stack.pop();
        List<TreeNode<T>> children = currentNode.getChildren();

        for (int i = children.size() - 1; i >= 0; i--) {
            stack.push(children.get(i));
        }

        return currentNode.getData();
    }
}