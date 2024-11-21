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
        this.identifier = identifier;
        this.address = address;
        this.summary = summary;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Property property = (Property) o;
        return Objects.equals(identifier, property.identifier)
                && Objects.equals(address, property.address)
                && Objects.equals(summary, property.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, address, summary);
    }

    @Override
    public String toString() {
        return "Property{"
                + "identifier=" + identifier + ", address=" + address + ", summary=" + summary + '}';
    }
}
