package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.BatchOrderOption;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("BatchOrderOption TTLV Serialization Tests")
class BatchOrderOptionTtlvTest extends AbstractTtlvSerializationSuite<BatchOrderOption> {

    @Override
    protected Class<BatchOrderOption> type() {
        return BatchOrderOption.class;
    }

    @Override
    protected BatchOrderOption createDefault() {
        return BatchOrderOption.builder().value(true).build();
    }

    @Override
    protected BatchOrderOption createVariant() {
        return BatchOrderOption.builder().value(false).build();
    }
}