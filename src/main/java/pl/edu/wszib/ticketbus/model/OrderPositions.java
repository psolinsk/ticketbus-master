package pl.edu.wszib.ticketbus.model;

import javax.persistence.*;

@Entity(name = "orderPositionTable")
public class OrderPositions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pieces;
    @ManyToOne(fetch = FetchType.EAGER)
    private Bus bus; // TODO
    @ManyToOne( fetch = FetchType.EAGER)
    private Order order;

    public OrderPositions() {
    }

    public OrderPositions(int id, int pieces, Bus bus, Order order) {
        this.id = id;
        this.pieces = pieces;
        this.bus = bus;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) { // TODO
        this.pieces = pieces;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderPositions{" +
                "id=" + id +
                ", pieces=" + pieces +
                ", bus=" + bus +
                ", order=" + order +
                '}';
    }
}
