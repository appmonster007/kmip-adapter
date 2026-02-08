package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ActivationDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ActivationDate XML Serialization Tests")
class ActivationDateXmlTest extends AbstractXmlSerializationTestSuite<ActivationDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<ActivationDate> type() {
        return ActivationDate.class;
    }

    @Override
    public ActivationDate createDefault() {
        return ActivationDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    public ActivationDate createVariant() {
        return ActivationDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}
