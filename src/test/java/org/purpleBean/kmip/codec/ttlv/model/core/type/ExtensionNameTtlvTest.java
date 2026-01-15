package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ExtensionName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ExtensionName TTLV Serialization Tests")
class ExtensionNameTtlvTest extends AbstractTtlvSerializationTestSuite<ExtensionName> {

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