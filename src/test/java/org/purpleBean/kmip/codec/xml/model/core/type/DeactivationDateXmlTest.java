package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DeactivationDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("DeactivationDate XML Serialization Tests")
class DeactivationDateXmlTest extends AbstractXmlSerializationTestSuite<DeactivationDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<DeactivationDate> type() {
        return DeactivationDate.class;
    }

    @Override
    protected DeactivationDate createDefault() {
        return DeactivationDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected DeactivationDate createVariant() {
        return DeactivationDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}