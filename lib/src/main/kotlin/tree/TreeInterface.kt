interface Tree<K : Comparable<K>, V> {
    /**
     * Поиск значения по ключу.
     * @param key Ключ для поиска.
     * @return Значение, связанное с ключом, или null, если ключ не найден.
     */
    fun search(key: K): V?

    /**
     * Вставка ключа и значения в дерево.
     * @param key Ключ для вставки.
     * @param value Значение, связанное с ключом.
     */
    fun insert(key: K, value: V)

    /**
     * Удаление ключа из дерева.
     * @param key Ключ для удаления.
     */
    fun delete(key: K)

    /**
     * Итератор для обхода дерева.
     * @param order Порядок обхода (например, IN_ORDER, PRE_ORDER, POST_ORDER, LEVEL_ORDER).
     * @return Итератор по ключам и значениям.
     */
    fun iterator(order: TreeTraversalOrder = TreeTraversalOrder.IN_ORDER): Iterator<Pair<K, V>>
}
