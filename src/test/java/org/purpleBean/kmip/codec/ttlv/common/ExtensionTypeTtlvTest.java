package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ExtensionType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ExtensionType TTLV Serialization Tests")
class ExtensionTypeTtlvTest extends AbstractTtlvSerializationTestSuite<ExtensionType> {

    @Override
    protected Class<ExtensionType> type() {
        return ExtensionType.class;
    }

    @Override
    protected ExtensionType createDefault() {
        return ExtensionType.builder().value(1).build();
    }

    @Override
    protected ExtensionType createVariant() {
        return ExtensionType.builder().value(2).build();
    }
}