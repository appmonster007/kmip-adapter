package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ActivationDateAttribute TTLV Serialization Tests")
class ActivationDateAttributeTtlvTest extends AbstractTtlvSerializationSuite<ActivationDateAttribute> {
    
    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    
    @Override 
    protected Class<ActivationDateAttribute> type() { 
        return ActivationDateAttribute.class; 
    }
    
    @Override 
    protected ActivationDateAttribute createDefault() {
        return ActivationDateAttribute.builder()
            .dateTime(FIXED_TIME)
            .build();
    }
    
    @Override
    protected ActivationDateAttribute createVariant() {
        return ActivationDateAttribute.builder()
            .dateTime(FIXED_TIME.plusDays(1))
            .build();
    }
}
