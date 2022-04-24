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
     * Domestic Appliance category.
     * @param name
     */
    DOMESTIC_APPLIANCE("Domestic Appliance"),
    /**
     * computer category.
     * @param name
     */
    COMPUTER("Computer"),
    /**
     * Pc Desktop category.
     * @param name
     */
    PC_DESKTOP("Pc Desktop"),
    /**
     * Monitor category.
     * @param name
     */
    MONITOR("Monitor"),
    /**
     * Keyboard category.
     * @param name
     */
    KEYBOARD("Keyboard"),
    /**
     * Mouse category.
     * @param name
     */
    MOUSE("Mouse"),
    /**
     * Scanner category.
     * @param name
     */
    SCANNER("Scanner"),
    /**
     * Printer category.
     * @param name
     */
    PRINTER("Printer"),
    /**
     * NAS category.
     * @param name
     */
    NAS("NAS"),
    /**
     * Hard disk and storage category.
     * @param name
     */
    HARD_DISK_AND_STORAGE("Hard disk and storage"),
    /**
     * Networking category.
     * @param name
     */
    NETWORKING("Networking"),
    /**
     * Video Surveillance category.
     * @param name
     */
    VIDEO_SURVEILLANCE("Video Surveillance"),
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

    public void setName(final String name) {
        this.name = name;
    }
}
