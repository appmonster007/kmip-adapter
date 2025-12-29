package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("MaximumItems Domain Tests")
class MaximumItemsTest extends AbstractKmipDataTypeSuite<MaximumItems> {

    @Override
    protected Class<MaximumItems> type() {
        return MaximumItems.class;
    }

    @Override
    protected MaximumItems createDefault() {
        return MaximumItems.builder().value(100).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}