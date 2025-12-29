package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ExtensionTag;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("ExtensionTag JSON Serialization Tests")
class ExtensionTagJsonTest extends AbstractJsonSerializationSuite<ExtensionTag> {

    @Override
    protected Class<ExtensionTag> type() {
        return ExtensionTag.class;
    }

    @Override
    protected ExtensionTag createDefault() {
        return ExtensionTag.builder().value(1).build();
    }

    @Override
    protected ExtensionTag createVariant() {
        return ExtensionTag.builder().value(2).build();
    }
}