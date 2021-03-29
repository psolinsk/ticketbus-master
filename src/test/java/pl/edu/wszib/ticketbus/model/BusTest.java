package pl.edu.wszib.ticketbus.model;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wszib.ticketbus.model.Bus;

import java.sql.Date;

public class BusTest {

    @Test
    public void BusCloneTest(){

        Bus bus = new Bus();
        bus.setId(1);
        bus.setMiastoPoczatkowe("Krkaów");
        bus.setMiastoKoncowe("Warszawa");
        bus.setNumerBiletu(1);
        bus.setIloscMiejsc(10);
        bus.setCena(20.00);

        Bus clone = bus.clone();

        Assert.assertEquals(bus.getId(), clone.getId());
        Assert.assertEquals(bus.getMiastoPoczatkowe(), clone.getMiastoPoczatkowe());
        Assert.assertEquals(bus.getMiastoKoncowe(), clone.getMiastoKoncowe());
        Assert.assertEquals(bus.getIloscMiejsc(), clone.getIloscMiejsc());
        Assert.assertEquals(bus.getNumerBiletu(), clone.getNumerBiletu());
        Assert.assertEquals(bus.getCena(), clone.getCena(), 0.001);

        Assert.assertNotSame(bus, clone);

    }
}
