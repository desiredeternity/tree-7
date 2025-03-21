package node

class TreeNode<K: Comparable<K>, V>(
    var key: K,
    var value: V,
    var height: Int = 1,
    var left: AVLNode<K, V>? = null,
    var right: AVLNode<K, V>? = null,
    var parent: TreeNode<K, V>? = null
)
