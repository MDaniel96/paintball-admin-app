package demo.app.paintball.data

import dagger.Component
import demo.app.paintball.data.ble.BleServiceImpl
import demo.app.paintball.data.mqtt.MqttServiceImpl
import demo.app.paintball.data.rest.RestServiceImpl
import javax.inject.Singleton

@Singleton
@Component
interface DataServiceInjector {

    fun rest(): RestServiceImpl

    fun mqtt(): MqttServiceImpl

    fun ble(): BleServiceImpl
}