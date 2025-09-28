package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.structure.Name;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("Name XML Serialization Tests")
class NameXmlTest extends AbstractXmlSerializationSuite<Name> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<Name> type() {
        return Name.class;
    }

    @Override
    protected Name createDefault() {
        // TODO: Update with actual default values for your structure
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME).build();
        State state = new State(State.Standard.ACTIVE);
        return Name.builder()
            .activationDate(activationDate)
            .state(state)
            .build();
    }

    @Override
    protected Name createVariant() {
        // TODO: Update with different values to test variations
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME.plusDays(1)).build();
        State state = new State(State.Standard.DEACTIVATED);
        return Name.builder()
            .activationDate(activationDate)
            .state(state)
            .build();
    }
}
