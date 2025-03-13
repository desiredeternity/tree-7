class RedBlackTreeTest {
    @Test
    fun testInsertAndSearch() {
        val tree = RedBlackTree<Int, String>()
        tree.insert(10, "A")
        assertEquals("A", tree.search(10))
    }
}
