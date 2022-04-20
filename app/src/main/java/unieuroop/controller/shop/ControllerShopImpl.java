package unieuroop.controller.shop;

import unieuroop.model.shop.Shop;

public final class ControllerShopImpl implements ControllerShop {

    private final Shop shop;

    public ControllerShopImpl(final Shop shop) {
        this.shop = shop;
    }

    @Override
    public Shop getShop() {
        return this.shop;
    }
}
