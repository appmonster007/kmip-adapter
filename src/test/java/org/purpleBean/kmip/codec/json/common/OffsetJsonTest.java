package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Offset;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("Offset JSON Serialization Tests")
class OffsetJsonTest extends AbstractJsonSerializationSuite<Offset> {

    @Override
    protected Class<Offset> type() {
        return Offset.class;
    }

    @Override
    protected Offset createDefault() {
        return Offset.builder().value(10).build();
    }

    @Override
    protected Offset createVariant() {
        return Offset.builder().value(20).build();
    }
}