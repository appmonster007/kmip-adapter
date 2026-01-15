package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IterationCount;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("IterationCount TTLV Serialization Tests")
class IterationCountTtlvTest extends AbstractTtlvSerializationTestSuite<IterationCount> {

    @Override
    protected Class<IterationCount> type() {
        return IterationCount.class;
    }

    @Override
    protected IterationCount createDefault() {
        return IterationCount.builder().value(1000).build();
    }

    @Override
    protected IterationCount createVariant() {
        return IterationCount.builder().value(2000).build();
    }
}