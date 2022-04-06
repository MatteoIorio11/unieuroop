package unieuroop.controller.client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import unieuroop.model.person.Client;
import unieuroop.model.shop.Shop;

public final class ControllerClientImpl {

    private static final int ADULT = 18;
    private static final int MAXDATE = 1900;
    private final LocalDate maxBirthday = LocalDate.of(LocalDate.now().getYear() - ADULT, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
    private final LocalDate minBirthday = LocalDate.of(MAXDATE, 1, 1);
    private final Shop shop;

    public ControllerClientImpl(final Shop shop) {
        this.shop = shop;
    }

    public Client addClient(final String name, final String surname, final LocalDate birthday) {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(maxBirthday) && birthday.isAfter(minBirthday)) {
            throw new IllegalArgumentException("Impossible ");
        }
        final var date = LocalDateTime.now();
        final ZonedDateTime zdt = date.atZone(ZoneId.systemDefault());
        final int code = (zdt.toInstant().getEpochSecond() + name + surname).hashCode();
        this.shop.registerClient(new Client(name, surname, birthday, code));
        return new Client(name, surname, birthday, code);
    }

    public Client editClient(final String name, final String surname, final LocalDate birthday) {
        return addClient(name, surname, birthday);
    }

    public void deleteClient(final Client client) {
        this.shop.removeClient(client);
    }

    public Set<Client> getRegisteredClients() {
        return this.shop.getRegisteredClients();
    }
}
