package demo.app.paintball.fragments.buttons

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import demo.app.paintball.PaintballApplication
import demo.app.paintball.PaintballApplication.Companion.player
import demo.app.paintball.PaintballApplication.Companion.services
import demo.app.paintball.R
import demo.app.paintball.config.topics.TopicsConfig.Companion.playerTopics
import demo.app.paintball.data.mqtt.MqttService
import demo.app.paintball.data.mqtt.messages.ChatMessage
import demo.app.paintball.util.*
import demo.app.paintball.util.services.ButtonProgressDisplayService
import demo.app.paintball.util.services.RecordService
import kotlinx.android.synthetic.main.fragment_chat_buttons.*
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class ChatButtonsFragmentImpl : MapButtonsFragment(), MqttService.ChatListener {

    companion object {
        const val RECORDING_TIME = 4_000L
    }

    override lateinit var rootLayout: View

    override lateinit var btnBottom: View
    override lateinit var btnBottomLayout: View
    override lateinit var btnBottomTextView: View

    override lateinit var btnMiddle: View
    override lateinit var btnMiddleLayout: View
    override lateinit var btnMiddleTextView: View

    @Inject
    lateinit var mqttService: MqttService

    private lateinit var recordService: RecordService

    private var recording = false
    private var chatActivated = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mqttService = services.mqtt().apply { chatListener = this@ChatButtonsFragmentImpl }

        return inflater.inflate(R.layout.fragment_chat_buttons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootLayout = chatButtonsLayout
        btnBottom = fabActivateChat
        btnBottomLayout = fabLayoutActivateChat
        btnBottomTextView = fabTextViewActivateChat
        btnMiddle = fabTeamChat
        btnMiddleLayout = fabLayoutTeamChat
        btnMiddleTextView = fabTextViewTeamChat
        initFabTeamChat()
        initFabActivateTeamChat()
    }

    private fun initFabTeamChat() {
        val rootActivity = activity as Activity
        val buttonProgressDisplayService = ButtonProgressDisplayService(fabTeamChat, rootActivity)
        var timer = Timer()
        var timerStarted = 0L
        val recordingStopped = {
            rootActivity.runOnUiThread {
                timer.cancel()
                val recordedBytes = recordService.stop()
                val messageLength = SystemClock.uptimeMillis() - timerStarted
                ChatMessage(message = recordedBytes.toHexString(), length = messageLength).publish(mqttService)
                recording = false
                fabTeamChat.setColor(ContextCompat.getColor(PaintballApplication.context, R.color.primaryLightColor))
                fabTeamChat.setIcon(R.drawable.ic_teamspeak, 0)
                buttonProgressDisplayService.stop()
            }
        }
        fabTeamChat.setOnClickListener {
            if (!recording) {
                recordService = RecordService()
                recordService.start()
                recording = true
                timerStarted = SystemClock.uptimeMillis()
                fabTeamChat.setColor(ContextCompat.getColor(PaintballApplication.context, R.color.lightTransparentGray))
                fabTeamChat.setIcon(R.drawable.ic_stop, 0)
                buttonProgressDisplayService.show(RECORDING_TIME)
                timer = Timer()
                timer.schedule(RECORDING_TIME) { recordingStopped() }
            } else {
                recordingStopped()
            }
        }
    }

    @SuppressLint("NewApi")
    private fun initFabActivateTeamChat() {
        fabActivateChat.setOnClickListener {
            if (chatActivated) {
                fabActivateChat.setBackgroundTint(R.color.lightTransparentGray)
                fabActivateChat.setSrc(R.drawable.ic_volumeoff)
                mqttService.unsubscribe(playerTopics.teamChat)
            } else {
                fabActivateChat.setBackgroundTint(R.color.primaryLightColor)
                fabActivateChat.setSrc(R.drawable.ic_volumeup)
                mqttService.subscribe(playerTopics.teamChat)
            }
            chatActivated = !chatActivated
        }
    }

    override fun chatMessageArrived(message: ChatMessage) {
        if (chatActivated && player.name != message.playerName) {
            val bytes = message.message.fromHexToByteArray()
            bytes.playAudio()
        }
    }
}