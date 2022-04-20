package unieuroop.controller.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.person.Client;
import unieuroop.model.person.ClientImpl;
import unieuroop.model.shop.Shop;

public final class ControllerClientImpl implements ControllerClient {

    private static final int ADULT = 18;
    private static final int MAXDATE = 1900;
    private final LocalDate maxBirthday = LocalDate.of(LocalDate.now().getYear() - ADULT, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
    private final LocalDate minBirthday = LocalDate.of(MAXDATE, 1, 1);
    private final Shop shop;

    public ControllerClientImpl(final Shop shop) {
        this.shop = shop;
    }

    @Override
    public void addClient(final String name, final String surname, final LocalDate birthday) {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday)) {
            throw new IllegalArgumentException("Impossible because one of the parameters are null");
        }
        final var date = LocalDateTime.now();
        final ZonedDateTime zdt = date.atZone(ZoneId.systemDefault());
        final int code = (zdt.toInstant().getEpochSecond() + name + surname).hashCode();
        this.shop.registerClient(new ClientImpl(name, surname, birthday, Math.abs(code)));
        serializationClient();
    }

    @Override
    public void editClient(final String name, final String surname, final LocalDate birthday, final Client client) {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday)) {
            throw new IllegalArgumentException("Impossible because one of the parameters are null");
        }
        client.getPerson().setPersonName(name);
        client.getPerson().setPersonSurname(surname);
        client.getPerson().setPersonBirthday(birthday);
        serializationClient();
    }

    @Override
    public void deleteClient(final Client client) {
        if (!Objects.isNull(client)) {
            this.shop.removeClient(client);
            serializationClient();
        }
    }

    @Override
    public Set<Client> getRegisteredClients() {
        return this.shop.getRegisteredClients();
    }

    private void serializationClient() {
        try {
            Serialization.<Set<Client>>serialize(Files.CLIENTS.getPath(), this.shop.getRegisteredClients());
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
