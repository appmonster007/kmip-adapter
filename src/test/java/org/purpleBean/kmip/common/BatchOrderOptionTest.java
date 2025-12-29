package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("BatchOrderOption Domain Tests")
class BatchOrderOptionTest extends AbstractKmipDataTypeSuite<BatchOrderOption> {

    @Override
    protected Class<BatchOrderOption> type() {
        return BatchOrderOption.class;
    }

    @Override
    protected BatchOrderOption createDefault() {
        return BatchOrderOption.builder().value(true).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BOOLEAN;
    }
}