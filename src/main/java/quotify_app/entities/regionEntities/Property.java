package quotify_app.entities.regionEntities;

import java.util.Objects;

/**
 * The representation of a property in our program.
 */
public class Property {

    private final Identifier identifier;
    private final Address address;
    private final Summary summary;

    public Property(Identifier identifier, Address address, Summary summary) {
        this.identifier = Objects.requireNonNull(identifier, "Identifier cannot be null");
        this.address = Objects.requireNonNull(address, "Address cannot be null");
        this.summary = Objects.requireNonNull(summary, "Summary cannot be null");
    }

    public Property(Address address) {
        this.identifier = null;
        this.address = address;
        this.summary = null;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Address getAddress() {
        return address;
    }

    public Summary getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "Property{"
                + "identifier=" + identifier + ", address=" + address + ", summary=" + summary + '}';
    }
}
