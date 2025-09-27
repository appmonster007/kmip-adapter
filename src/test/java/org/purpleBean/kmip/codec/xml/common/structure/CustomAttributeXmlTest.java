package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.CustomAttribute;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CustomAttribute XML Serialization Tests")
class CustomAttributeXmlTest extends AbstractXmlSerializationSuite<CustomAttribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<CustomAttribute> type() {
        return CustomAttribute.class;
    }

    @Override
    protected CustomAttribute createDefault() {
        State state = new State(State.Standard.ACTIVE);
        return CustomAttribute.of("x-custom-state", state);
    }

    @Override
    protected CustomAttribute createVariant() {
        State state = new State(State.Standard.ACTIVE);
        return CustomAttribute.of("x-custom-date", FIXED_TIME);
    }
}
