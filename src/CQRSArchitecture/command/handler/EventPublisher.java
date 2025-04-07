package CQRSArchitecture.command.handler;

public interface EventPublisher {
    void publish(Object event);
}
