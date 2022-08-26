/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
