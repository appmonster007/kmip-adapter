package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CustomAttribute;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CustomAttribute XML Serialization Tests")
class CustomAttributeXmlTest extends AbstractXmlSerializationTestSuite<CustomAttribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CustomAttribute> type() {
        return CustomAttribute.class;
    }

    @Override
    protected CustomAttribute createDefault() {
        return CustomAttribute.of("x-custom-state", AttributeValueInteger.of(1));
    }

    @Override
    protected CustomAttribute createVariant() {
        return CustomAttribute.of("x-custom-date", AttributeValueDateTime.of(FIXED_TIME));
    }
}
