package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SplitKeyParts;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SplitKeyParts JSON Serialization Tests")
class SplitKeyPartsJsonTest extends AbstractJsonSerializationTestSuite<SplitKeyParts> {

    @Override
    protected Class<SplitKeyParts> type() {
        return SplitKeyParts.class;
    }

    @Override
    protected SplitKeyParts createDefault() {
        return SplitKeyParts.builder().value(2).build();
    }

    @Override
    protected SplitKeyParts createVariant() {
        return SplitKeyParts.builder().value(3).build();
    }
}