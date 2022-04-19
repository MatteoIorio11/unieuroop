package unieuroop.controller.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
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

    public void addClient(final String name, final String surname, final LocalDate birthday) {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday)) {
            throw new IllegalArgumentException("Impossible because one of the parameters are null");
        }
        final var date = LocalDateTime.now();
        final ZonedDateTime zdt = date.atZone(ZoneId.systemDefault());
        final int code = (zdt.toInstant().getEpochSecond() + name + surname).hashCode();
        this.shop.registerClient(new Client(name, surname, birthday, Math.abs(code)));
        serializationClient();
    }

    public void editClient(final String name, final String surname, final LocalDate birthday, final Client client) {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday)) {
            throw new IllegalArgumentException("Impossible because one of the parameters are null");
        }
        client.setPersonName(name);
        client.setPersonSurname(surname);
        client.setPersonBirthday(birthday);
        serializationClient();
    }

    public void deleteClient(final Client client) {
        if (!Objects.isNull(client)) {
            this.shop.removeClient(client);
            serializationClient();
        }
    }

    public Set<Client> getRegisteredClients() {
        return this.shop.getRegisteredClients();
    }

    private void serializationClient() {
        try {
            Serialization.<Set<Client>>serialize(Files.CLIENTS.getPath(), this.shop.getRegisteredClients());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
