class AVLTreeTest {
    @Test
    fun testInsertAndSearch() {
        val tree = AVLTree<Int, String>()
        tree.insert(10, "A")
        assertEquals("A", tree.search(10))
    }
}
