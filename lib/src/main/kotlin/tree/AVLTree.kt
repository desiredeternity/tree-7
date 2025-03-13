package tree

#import nodes.AVLTReeNode
import java.util.*

class AVLTree<K : Comparable<K>, V> : Iterable<Pair<K, V>> {
    var root: AVLTreeNode<K, V>? = null
    var size = 0

    fun insert(key: K, value: V) {
        root = insertRecursive(root, key, value)
        size++
    }

    fun remove(key: K) {
        root = removeRecursive(root, key)
        if (size > 0) size--
    }

    fun search(key: K): V? {
        var current = root
        while (current != null) {
            current = when {
                key < current.key -> current.left
                key > current.key -> current.right
                else -> return current.value
            }
        }
        return null
    }

    override fun iterator() = inOrderIterator()
    fun inOrderIterator() = InOrderIterator(root)
    fun levelOrderIterator() = LevelOrderIterator(root)

    fun updateHeight(node: AVLTreeNode<K, V>) {
        node.height = 1 + maxOf(
            node.left?.height ?: 0, 
            node.right?.height ?: 0
        )
    }

    fun balanceFactor(node: AVLTreeNode<K, V>) = 
        (node.left?.height ?: 0) - (node.right?.height ?: 0)

    fun rotateRight(y: AVLTreeNode<K, V>): AVLTreeNode<K, V> {
        val x = y.left ?: return y
        val T2 = x.right
        
        x.right = y
        y.left = T2
        
        updateHeight(y)
        updateHeight(x)
        return x
    }

    fun rotateLeft(x: AVLTreeNode<K, V>): AVLTreeNode<K, V> {
        val y = x.right ?: return x
        val T2 = y.left
        
        y.left = x
        x.right = T2
        
        updateHeight(x)
        updateHeight(y)
        return y
    }

    fun rebalance(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {
        updateHeight(node)
        val balance = balanceFactor(node)

        return when {
            balance > 1 -> {
                node.left?.let { if (balanceFactor(it) < 0) node.left = rotateLeft(it) }
                rotateRight(node)
            }
            balance < -1 -> {
                node.right?.let { if (balanceFactor(it) > 0) node.right = rotateRight(it) }
                rotateLeft(node)
            }
            else -> node
        }
    }

    fun insertRecursive(node: AVLTreeNode<K, V>?, key: K, value: V): AVLTreeNode<K, V> {
        if (node == null) return AVLTreeNode(key, value)
        
        when {
            key < node.key -> node.left = insertRecursive(node.left, key, value)
            key > node.key -> node.right = insertRecursive(node.right, key, value)
            else -> node.value = value
        }
        return rebalance(node)
    }

    fun removeRecursive(node: AVLTreeNode<K, V>?, key: K): AVLTreeNode<K, V>? {
        node ?: return null
        
        when {
            key < node.key -> node.left = removeRecursive(node.left, key)
            key > node.key -> node.right = removeRecursive(node.right, key)
            else -> {
                if (node.left == null || node.right == null) return node.left ?: node.right
                val successor = findMin(node.right ?: return node)
                node.key = successor.key
                node.value = successor.value
                node.right = removeRecursive(node.right, successor.key)
            }
        }
        return rebalance(node)
    }

    fun findMin(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {
        var current = node
        while (current.left != null) current = current.left ?: break
        return current
    }

    data class AVLTreeNode<K : Comparable<K>, V>(
        var key: K,
        var value: V,
        var height: Int = 1,
        var left: AVLTreeNode<K, V>? = null,
        var right: AVLTreeNode<K, V>? = null
    )

    inner class InOrderIterator(root: AVLTreeNode<K, V>?) : Iterator<Pair<K, V>> {
        private val stack = ArrayDeque<AVLTreeNode<K, V>>()
        private var current = root

        override fun hasNext() = current != null || stack.isNotEmpty()
        
        override fun next(): Pair<K, V> {
            while (current != null) {
                stack.push(current)
                current = current?.left
            }
            val node = stack.pop()
            current = node.right
            return node.key to node.value
        }
    }

    inner class LevelOrderIterator(root: AVLTreeNode<K, V>?) : Iterator<Pair<K, V>> {
        private val queue: Queue<AVLTreeNode<K, V>> = LinkedList()
        
        init { root?.let { queue.add(it) } }

        override fun hasNext() = queue.isNotEmpty()
        
        override fun next(): Pair<K, V> {
            val node = queue.poll()
            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
            return node.key to node.value
        }
    }
}
