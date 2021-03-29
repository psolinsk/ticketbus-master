package pl.edu.wszib.ticketbus.services;

public interface IBasketService { // TODO
    double calculateTotal();
    void addBusByIdToBasket(int id);
    void deleteBusFromBasket(int id);
}
