package unieuroop.controller.client;

import java.time.LocalDate;
import java.util.Set;
import unieuroop.model.person.Client;
import unieuroop.model.shop.Shop;

public final class ControllerClientImpl {
    
    private final int adult = 18;
    private final int maxDate = 1900;
    private final String emptyString = "";
    private final String illegalArgumentString = "illegal argument for client";
    private final LocalDate maxBirthday = LocalDate.of(LocalDate.now().getYear() - adult, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
    private final LocalDate minBirthday = LocalDate.of(maxDate, 1, 1);
    private final Shop shop;
    public ControllerClientImpl(final Shop shop) {
        this.shop = shop;
    }

    /**
     * 
     * @param name
     * @param surname
     * @param birthday
     */
    public void addClient(final String name, final String surname, final LocalDate birthday) {
        if (name.equals(emptyString) || surname.equals(emptyString) || birthday.isBefore(maxBirthday) && birthday.isAfter(minBirthday)) {
            throw new IllegalArgumentException(illegalArgumentString);
        }
        final int code = 0;
        this.shop.registerClient(new Client(name, surname, birthday, code));
    }

    public void editClient() {

    }

    public void deleteClient() {

    }

    public Set<Client> getRegisteredClients() {
        return this.shop.getRegisteredClients();
    }
}
