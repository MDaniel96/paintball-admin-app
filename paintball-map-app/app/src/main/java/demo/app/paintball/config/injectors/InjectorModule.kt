package demo.app.paintball.config.injectors

import dagger.Module
import dagger.Provides
import demo.app.paintball.data.ble.BleService
import demo.app.paintball.data.ble.BleServiceImpl
import demo.app.paintball.data.mqtt.MqttService
import demo.app.paintball.data.mqtt.MqttServiceImpl
import demo.app.paintball.data.rest.RestService
import demo.app.paintball.data.rest.RestServiceImpl
import demo.app.paintball.presenters.DashboardPresenter
import demo.app.paintball.presenters.JoinGamePresenter
import javax.inject.Singleton

@Module
class InjectorModule {

    @Provides
    @Singleton
    fun restService(): RestService = RestServiceImpl()

    @Provides
    @Singleton
    fun mqttService(): MqttService = MqttServiceImpl()

    @Provides
    @Singleton
    fun bleService(): BleService = BleServiceImpl()

    @Provides
    @Singleton
    fun dashboardPresenter() = DashboardPresenter()

    @Provides
    @Singleton
    fun joinGamePresenter() = JoinGamePresenter()
}