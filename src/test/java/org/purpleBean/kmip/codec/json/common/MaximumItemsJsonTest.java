package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MaximumItems;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MaximumItems JSON Serialization Tests")
class MaximumItemsJsonTest extends AbstractJsonSerializationTestSuite<MaximumItems> {

    @Override
    protected Class<MaximumItems> type() {
        return MaximumItems.class;
    }

    @Override
    protected MaximumItems createDefault() {
        return MaximumItems.builder().value(100).build();
    }

    @Override
    protected MaximumItems createVariant() {
        return MaximumItems.builder().value(200).build();
    }
}