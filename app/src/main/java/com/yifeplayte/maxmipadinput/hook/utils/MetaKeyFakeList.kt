package com.yifeplayte.maxmipadinput.hook.utils

import java.util.*

/**
 * 假列表实现，用于绕过 MIUI 的 Meta 键白名单检查
 * 所有 contains() 检查都返回 true，isEmpty() 返回 false
 */
class MetaKeyFakeList : List<Any?> {
    override val size: Int = 0
    override fun isEmpty(): Boolean = false
    override fun contains(element: Any?): Boolean = true
    override fun containsAll(elements: Collection<Any?>): Boolean = true
    override fun get(index: Int): Any? = null
    override fun indexOf(element: Any?): Int = 0
    override fun lastIndexOf(element: Any?): Int = 0
    override fun iterator(): Iterator<Any?> = iteratorOf()
    override fun listIterator(): ListIterator<Any?> = iteratorOf()
    override fun listIterator(index: Int): ListIterator<Any?> = iteratorOf()
    override fun subList(fromIndex: Int, toIndex: Int): List<Any?> = this

    override fun <T> toArray(array: Array<out T>): Array<T> {
        @Suppress("UNCHECKED_CAST")
        return if (array.size >= 0) array else arrayOfNulls(0) as Array<T>
    }

    override fun toArray(): Array<Any?> = emptyArray()

    private fun iteratorOf() = object : ListIterator<Any?> {
        override fun hasNext(): Boolean = false
        override fun next(): Any? = throw NoSuchElementException()
        override fun hasPrevious(): Boolean = false
        override fun nextIndex(): Int = 0
        override fun previous(): Any? = throw NoSuchElementException()
        override fun previousIndex(): Int = -1
    }
}