package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ExtensionName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ExtensionName TTLV Serialization Tests")
class ExtensionNameTtlvTest extends AbstractTtlvSerializationSuite<ExtensionName> {

    @Override
    protected Class<ExtensionName> type() {
        return ExtensionName.class;
    }

    @Override
    protected ExtensionName createDefault() {
        return ExtensionName.builder().value("test-extension").build();
    }

    @Override
    protected ExtensionName createVariant() {
        return ExtensionName.builder().value("another-extension").build();
    }
}