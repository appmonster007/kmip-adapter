package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.BatchCount;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("BatchCount TTLV Serialization Tests")
class BatchCountTtlvTest extends AbstractTtlvSerializationSuite<BatchCount> {

    @Override
    protected Class<BatchCount> type() {
        return BatchCount.class;
    }

    @Override
    protected BatchCount createDefault() {
        return BatchCount.builder().value(5).build();
    }

    @Override
    protected BatchCount createVariant() {
        return BatchCount.builder().value(10).build();
    }
}