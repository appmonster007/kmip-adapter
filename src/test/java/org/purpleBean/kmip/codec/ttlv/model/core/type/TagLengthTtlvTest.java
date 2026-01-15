package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.TagLength;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("TagLength TTLV Serialization Tests")
class TagLengthTtlvTest extends AbstractTtlvSerializationTestSuite<TagLength> {

    @Override
    protected Class<TagLength> type() {
        return TagLength.class;
    }

    @Override
    protected TagLength createDefault() {
        return TagLength.of(128);
    }

    @Override
    protected TagLength createVariant() {
        return TagLength.of(256);
    }
}