package unieuroop.model.product;

public enum Category {
    /**
     * Smartphone category.
     * @param name
     */
    SMARTPHONE("Smartpone"),
    /**
     * PC category.
     * @param name
     */
    PC("PC"),
    /**
     * Home category.
     * @param name
     */
    HOME("Home"),
    /**
     * Smartwatch category.
     * @param name
     */
    SMARTWATCH("Smartwatch"),
    /**
     * Tablet Category.
     * @param name
     */
    TABLET("Tablet");

    private String name;

    Category(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    void setName(final String name) {
        this.name = name;
    }
}
