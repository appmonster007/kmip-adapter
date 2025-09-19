package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("SampleStructure XML Serialization Tests")
class SampleStructureXmlTest extends AbstractXmlSerializationSuite<SampleStructure> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<SampleStructure> type() {
        return SampleStructure.class;
    }

    @Override
    protected SampleStructure createDefault() {
        // TODO: Update with actual default values for your structure
        ActivationDateAttribute activationDate = ActivationDateAttribute.builder().dateTime(FIXED_TIME).build();
        State state = new State(State.Standard.ACTIVE);
        return SampleStructure.builder()
                .activationDate(activationDate)
                .state(state)
                .build();
    }

    @Override
    protected SampleStructure createVariant() {
        // TODO: Update with different values to test variations
        ActivationDateAttribute activationDate = ActivationDateAttribute.builder().dateTime(FIXED_TIME.plusDays(1)).build();
        State state = new State(State.Standard.DEACTIVATED);
        return SampleStructure.builder()
                .activationDate(activationDate)
                .state(state)
                .build();
    }
}
