package unieuroop.controller.shop;

import unieuroop.model.shop.Shop;

public final class ControllerShopImpl {
    private final Shop shop;

    public ControllerShopImpl(final Shop shop) {
        this.shop = shop;
    }
    public Shop getShop() {
        return this.shop;
    }
}
