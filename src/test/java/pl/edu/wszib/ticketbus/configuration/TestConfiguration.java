package pl.edu.wszib.ticketbus.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.ticketbus.controllers",
        "pl.edu.wszib.ticketbus.services.impl",
        "pl.edu.wszib.ticketbus.session"
})
public class TestConfiguration {
}
