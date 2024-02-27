package com.zetcode

import java.awt.EventQueue
import javax.swing.JFrame
import java.io.BufferedReader
import java.io.InputStreamReader

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
    // Create an instance of the GUI window
    EventQueue.invokeLater(::createAndShowGUI)
    // Desired volume level (0-100)
    val volume = 40
    // Shell command to set the volume
    val volumeProcess =
        Runtime.getRuntime().exec(arrayOf("/usr/bin/osascript", "-e", "set volume output volume $volume"))
    val volumeReader = BufferedReader(InputStreamReader(volumeProcess.inputStream))
    var vol: String?
    // Read command output
    while (volumeReader.readLine().also { vol = it } != null) {
        println(vol)
    }
    // Wait for the command to complete
    volumeProcess.waitFor()

    println("Volume set to $volume")
}