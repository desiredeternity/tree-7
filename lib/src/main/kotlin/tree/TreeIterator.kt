class TreeIterator<K : Comparable<K>, V>(
    private val root: TreeNode<K, V>?,
    private val order: TreeTraversalOrder
) : Iterator<Pair<K, V>> {
    private val queue = LinkedList<TreeNode<K, V>>()

    init {
        when (order) {
            TreeTraversalOrder.IN_ORDER -> inOrder(root)
            TreeTraversalOrder.PRE_ORDER -> preOrder(root)
            TreeTraversalOrder.POST_ORDER -> postOrder(root)
            TreeTraversalOrder.LEVEL_ORDER -> levelOrder(root)
        }
    }

    override fun hasNext(): Boolean = queue.isNotEmpty()

    override fun next(): Pair<K, V> {
        val node = queue.poll()
        return Pair(node.key, node.value)
    }

    private fun inOrder(node: TreeNode<K, V>?) {
        // Реализация обхода in-order
    }

    private fun preOrder(node: TreeNode<K, V>?) {
        // Реализация обхода pre-order
    }

    private fun postOrder(node: TreeNode<K, V>?) {
        // Реализация обхода post-order
    }

    private fun levelOrder(node: TreeNode<K, V>?) {
        // Реализация обхода level-order
    }
}
