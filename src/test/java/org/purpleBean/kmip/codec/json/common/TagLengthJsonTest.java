package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.TagLength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("TagLength JSON Serialization Tests")
class TagLengthJsonTest extends AbstractJsonSerializationSuite<TagLength> {

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