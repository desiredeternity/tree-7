class AVLTree<K : Comparable<K>, V> : Tree<K, V> {
    private var root: AVLTreeNode<K, V>? = null

    override fun search(key: K): V? {
        // Реализация поиска
    }

    override fun insert(key: K, value: V) {
        // Реализация вставки
    }

    override fun delete(key: K) {
        // Реализация удаления
    }

    override fun iterator(order: TreeTraversalOrder): Iterator<Pair<K, V>> {
        return TreeIterator(root, order)
    }
}
