// Generated by Dagger (https://dagger.dev).
package demo.app.paintball.fragments.buttons;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import demo.app.paintball.data.mqtt.MqttService;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ChatButtonsFragmentImpl_MembersInjector implements MembersInjector<ChatButtonsFragmentImpl> {
  private final Provider<MqttService> mqttServiceProvider;

  public ChatButtonsFragmentImpl_MembersInjector(Provider<MqttService> mqttServiceProvider) {
    this.mqttServiceProvider = mqttServiceProvider;
  }

  public static MembersInjector<ChatButtonsFragmentImpl> create(
      Provider<MqttService> mqttServiceProvider) {
    return new ChatButtonsFragmentImpl_MembersInjector(mqttServiceProvider);
  }

  @Override
  public void injectMembers(ChatButtonsFragmentImpl instance) {
    injectMqttService(instance, mqttServiceProvider.get());
  }

  @InjectedFieldSignature("demo.app.paintball.fragments.buttons.ChatButtonsFragmentImpl.mqttService")
  public static void injectMqttService(ChatButtonsFragmentImpl instance, MqttService mqttService) {
    instance.mqttService = mqttService;
  }
}
