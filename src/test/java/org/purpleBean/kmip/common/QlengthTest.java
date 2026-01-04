package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("Qlength Domain Tests")
class QlengthTest extends AbstractKmipDataTypeSuite<Qlength> {

    @Override
    protected Class<Qlength> type() {
        return Qlength.class;
    }

    @Override
    protected Qlength createDefault() {
        return Qlength.builder().value(128).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}