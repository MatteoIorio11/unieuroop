package unieuroop.controller.client;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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
        final String code = UUID.randomUUID().toString();
        this.shop.registerClient(new ClientImpl(name, surname, birthday, code));
        serializationClient();
    }

    @Override
    public void editClient(final String name, final String surname, final LocalDate birthday, final Client client) {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday)) {
            throw new IllegalArgumentException("Impossible because one of the parameters are null");
        }
        final var clientSelected = this.shop.getRegisteredClients().stream().filter(clientStream -> clientStream.equals(client)).findAny();
        if (clientSelected.isPresent()) {
            final var clientInput = clientSelected.get();
            clientInput.getPerson().setPersonName(name);
            clientInput.getPerson().setPersonSurname(surname);
            clientInput.getPerson().setPersonBirthday(birthday);
            serializationClient();
        } else {
            throw new IllegalArgumentException("The selected client does not exist");
        }
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
