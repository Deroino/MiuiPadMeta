package com.yifeplayte.maxmipadinput.hook.utils

import java.util.*

/**
 * 假列表实现，用于绕过 MIUI 的 Meta 键白名单检查
 * 所有 contains() 检查都返回 true，isEmpty() 返回 false
 */
class MetaKeyFakeList : java.util.List<Any?> {
    override val size: Int = 0
    override fun isEmpty(): Boolean = false
    override fun contains(element: Any?): Boolean = true
    override fun containsAll(elements: Collection<Any?>): Boolean = true
    override fun get(index: Int): Any? = null
    override fun indexOf(element: Any?): Int = 0
    override fun lastIndexOf(element: Any?): Int = 0
    override fun iterator(): MutableIterator<Any?> = iteratorOf()
    override fun listIterator(): MutableListIterator<Any?> = iteratorOf()
    override fun listIterator(index: Int): MutableListIterator<Any?> = iteratorOf()
    override fun subList(fromIndex: Int, toIndex: Int): MutableList<Any?> = this

    override fun toArray(): Array<Any?> = emptyArray()

    @Suppress("UNCHECKED_CAST")
    override fun <T> toArray(array: Array<T>): Array<T> {
        return if (array.size >= 0) array else emptyArray<Any?>() as Array<T>
    }

    override fun add(element: Any?): Boolean = true
    override fun add(index: Int, element: Any?) {}
    override fun addAll(index: Int, elements: Collection<Any?>): Boolean = false
    override fun addAll(elements: Collection<Any?>): Boolean = false
    override fun clear() {}
    override fun remove(element: Any?): Boolean = false
    override fun remove(index: Int): Any? = null
    override fun removeAll(elements: Collection<Any?>): Boolean = false
    override fun retainAll(elements: Collection<Any?>): Boolean = false
    override fun set(index: Int, element: Any?): Any? = null

    private fun iteratorOf() = object : MutableListIterator<Any?> {
        override fun hasNext(): Boolean = false
        override fun next(): Any? = throw NoSuchElementException()
        override fun hasPrevious(): Boolean = false
        override fun nextIndex(): Int = 0
        override fun previous(): Any? = throw NoSuchElementException()
        override fun previousIndex(): Int = -1
        override fun add(element: Any?) {}
        override fun remove() {}
        override fun set(element: Any?): Any? = null
    }
}