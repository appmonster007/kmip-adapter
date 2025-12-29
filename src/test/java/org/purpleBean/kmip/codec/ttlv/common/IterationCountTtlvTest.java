package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.IterationCount;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("IterationCount TTLV Serialization Tests")
class IterationCountTtlvTest extends AbstractTtlvSerializationSuite<IterationCount> {

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