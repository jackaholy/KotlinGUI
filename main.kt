package com.zetcode

import java.awt.EventQueue
import javax.swing.JFrame

class SimpleEx(title: String) : JFrame() {

    init {
        createUI(title)
    }

    private fun createUI(title: String) {

        setTitle(title)

        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 300)
        setLocationRelativeTo(null)
    }
}

private fun createAndShowGUI() {

    val frame = SimpleEx("Kotlin GUI Program")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}