package quotify_app.data_access;

import java.util.Map;

import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Identifier;
import quotify_app.entities.regionEntities.Property;
import quotify_app.entities.regionEntities.Summary;

/**
 * Temporary dependencies for the comparatorclient.
 */
public class ComparatorDependencies {
    static final Address address1 = new Address(
            "US",
            "NY",
            "Albany",
            "CAMPUS CLUB DR",
            "33",
            "12084"
    );
    static final Identifier identifier1 = new Identifier(
            "4690292",
            Map.of(
                    "ZI", "82052344736ea4a747ae8fd283a29734",
                    "CS", "ca9cb8ddcc92c619d4a64e6bc6125b8d",
                    "ST", "ST36",
                    "CO", "c92b20d9ac51365b22857a5e3e292c25",
                    "N2", "Oaa691ef3be9ab71a648069522e48d57"
            )
    );
    static final Summary summary1 = new Summary(
            "Single Family Residence / Townhouse",
            6,
            3,
            "Good",
            2,
            2625,
            1967
    );
    static final Address address2 = new Address(
            "US",
            "NY",
            "Albany",
            "HIAWATHA DR",
            "8",
            "12084"
    );
    static final Identifier identifier2 = new Identifier(
            "4692100",
            Map.of(
                    "ZI", "82052344736ea4a747ae8fd283a29734",
                    "CS", "ca9cb8ddcc92c619d4a64e6bc6125b8d",
                    "ST", "ST36",
                    "CO", "c92b20d9ac51365b22857a5e3e292c25",
                    "N2", "Oaa691ef3be9ab71a648069522e48d57"
            )
    );
    static final Summary summary2 = new Summary(
            "Single Family Residence / Townhouse",
            5,
            2,
            "Good",
            2,
            2800,
            1977
    );

    static final Address address3 = new Address(
            "US",
            "NY",
            "Albany",
            "CAMPUS CLUB DR",
            "41",
            "12084"
    );
    static final Identifier identifier3 = new Identifier(
            "4693250",
            Map.of(
                    "ZI", "82052344736ea4a747ae8fd283a29734",
                    "CS", "ca9cb8ddcc92c619d4a64e6bc6125b8d",
                    "ST", "ST36",
                    "CO", "c92b20d9ac51365b22857a5e3e292c25",
                    "N2", "Oaa691ef3be9ab71a648069522e48d57"
            )
    );
    static final Summary summary3 = new Summary(
            "Single Family Residence / Townhouse",
            5,
            2,
            "Good",
            2,
            2560,
            1969
    );
    static final Property property1 = new Property(identifier1, address1, summary1);
    static final Property property2 = new Property(identifier2, address2, summary2);
    static final Property property3 = new Property(identifier3, address3, summary3);
}
