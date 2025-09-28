package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@DisplayName("Name Domain Tests")
class NameTest extends AbstractKmipStructureSuite<Name> {

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
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        // TODO: Update with the expected minimum number of components
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // Add assertions for components if desired
        // TODO: Add validation for each component
        // Example:
        // assertThat(values.get(0).getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
        // assertThat(values.get(1).getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
    }
}
