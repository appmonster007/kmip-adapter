package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ActivationDate XML Serialization Tests")
class ActivationDateXmlTest extends AbstractXmlSerializationSuite<ActivationDate> {
    
    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    
    @Override 
    protected Class<ActivationDate> type() {
        return ActivationDate.class;
    }
    
    @Override 
    protected ActivationDate createDefault() {
        return ActivationDate.builder()
            .dateTime(FIXED_TIME)
            .build();
    }
    
    @Override
    protected ActivationDate createVariant() {
        return ActivationDate.builder()
            .dateTime(FIXED_TIME.plusDays(1))
            .build();
    }
}
