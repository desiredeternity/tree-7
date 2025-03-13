class BinarySearchTreeTest {
    @Test
    fun testInsertAndSearch() {
        val tree = BinarySearchTree<Int, String>()
        tree.insert(10, "A")
        assertEquals("A", tree.search(10))
    }
}
