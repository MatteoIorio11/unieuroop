package unieuroop.controller.client;

import java.time.LocalDate;
import java.util.Set;

import com.google.common.base.FinalizablePhantomReference;

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

    public void AddClient(final String name, final String surname, final LocalDate birthday) {
        int code = 0;
        if (name.equals(emptyString) || surname.equals(emptyString) || birthday.isBefore(maxBirthday) && birthday.isAfter(minBirthday)) {
            throw new IllegalArgumentException(illegalArgumentString);
        }
        this.shop.registerClient(new Client(name, surname, birthday, code));
    }

    public void EditClient() {
        
    }

    public void DeleteClient() {

    }

    public Set<Client> getRegisteredClients() {
        return this.shop.getRegisteredClients();
    }
}
