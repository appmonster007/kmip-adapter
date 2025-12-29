package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("BatchCount Domain Tests")
class BatchCountTest extends AbstractKmipDataTypeSuite<BatchCount> {

    @Override
    protected Class<BatchCount> type() {
        return BatchCount.class;
    }

    @Override
    protected BatchCount createDefault() {
        return BatchCount.builder().value(5).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}