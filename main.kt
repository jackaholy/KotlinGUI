// Jack Holy
// This is a kotlin program is intentionally terrible and allows you to change the
// volume on your computer in a completely unconventional way, being randomly. Simply
// run the application and press the button on the GUI window to change the volume on
// your computer.
import java.awt.EventQueue
import java.awt.FlowLayout
import java.awt.Label
import javax.swing.JFrame
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.swing.JButton
import kotlin.random.Random

class TerribleVolumeAdjustment(title: String) : JFrame() {

    init {
        createUI(title)
    }

    /**
     * This function generates a GUI window with a button and some labels.
     * @param title - The title of the whole window of the application.
     */
    private fun createUI(title: String) {

        setTitle(title)
        // Create labels to exist in the window
        val randomBtn = JButton("Change Volume")
        val volumeLbl = Label("Current Volume: ")
        val currentVolumeLbl = Label()

        // When the button is pressed change and display the volume.
        randomBtn.addActionListener {
            val volume = randomNumber()
            adjustVolume(volume)
            val strVolume = volume.toString()
            // Print the current volume level
            currentVolumeLbl.text = "$strVolume%"
            // Reset the volume string
            currentVolumeLbl.revalidate()
        }
        // Add components to the frame.
        layout = FlowLayout()
        add(randomBtn)
        add(volumeLbl)
        add(currentVolumeLbl)

        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 100)
        setLocationRelativeTo(null)
    }
}

/**
 * Generates a random number between 1 and 100.
 * @return a random integer
 */
fun randomNumber(): Int {
    return Random.nextInt(1, 101)
}

/**
 * Adjusts the volume on the computer in which the program is running.
 * @param volume - The volume the computer is going to be set to.
 */
fun adjustVolume(volume: Int) {
    // Shell command to set the volume. Got these 3 lines with help from chat.openai.com
    val volumeProcess = Runtime.getRuntime().exec(arrayOf("/usr/bin/osascript", "-e", "set volume output volume $volume"))
    val volumeReader = BufferedReader(InputStreamReader(volumeProcess.inputStream))
    var vol: String?
    // Read command output. Got this line with help from chat.openai.com
    while (volumeReader.readLine().also { vol = it } != null) {
        println(vol)
    }
    // Wait for the command to complete
    volumeProcess.waitFor()

    println("Volume set to $volume")
}

fun main() {
    // Create an instance of the GUI window
    EventQueue.invokeLater {
        val frame = TerribleVolumeAdjustment("Terrible Volume Adjustment")
        frame.isVisible = true
    }
}