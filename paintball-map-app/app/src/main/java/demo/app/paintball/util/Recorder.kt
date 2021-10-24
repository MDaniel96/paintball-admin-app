package demo.app.paintball.util

import android.media.MediaRecorder
import android.os.ParcelFileDescriptor
import java.io.ByteArrayOutputStream

class Recorder {

    private val mediaRecorder: MediaRecorder
    private val inputStream: ParcelFileDescriptor.AutoCloseInputStream
    private lateinit var recordingOutputStream: ByteArrayOutputStream

    init {
        val descriptors = ParcelFileDescriptor.createPipe()
        val parcelRead = ParcelFileDescriptor(descriptors[0])
        val parcelWrite = ParcelFileDescriptor(descriptors[1])

        inputStream = ParcelFileDescriptor.AutoCloseInputStream(parcelRead)

        mediaRecorder = MediaRecorder()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mediaRecorder.setOutputFile(parcelWrite.fileDescriptor)
    }

    private val recordingThread = Thread {
        recordingOutputStream = ByteArrayOutputStream()
        mediaRecorder.prepare()
        mediaRecorder.start()
        var read: Int
        val data = ByteArray(16384)
        while (inputStream.read(data, 0, data.size).also { read = it } != -1) {
            recordingOutputStream.write(data, 0, read)
        }
    }

    fun start() {
        recordingThread.start()
    }

    fun stop(): ByteArray {
        mediaRecorder.stop()
        mediaRecorder.reset()
        mediaRecorder.release()
        return recordingOutputStream.toByteArray()
    }
}