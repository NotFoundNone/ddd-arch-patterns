package CQRSArchitecture.command.model;

public enum OrderStatus {
    CREATED,   // Заказ создан
    COOKING,   // Заказ в процессе приготовления
    READY,     // Заказ готов (при необходимости)
    COMPLETED  // Заказ завершён
}
