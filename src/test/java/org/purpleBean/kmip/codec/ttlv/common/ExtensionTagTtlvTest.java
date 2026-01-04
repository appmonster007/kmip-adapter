package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ExtensionTag;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ExtensionTag TTLV Serialization Tests")
class ExtensionTagTtlvTest extends AbstractTtlvSerializationSuite<ExtensionTag> {

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