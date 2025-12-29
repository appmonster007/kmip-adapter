package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("ExtensionTag Domain Tests")
class ExtensionTagTest extends AbstractKmipDataTypeSuite<ExtensionTag> {

    @Override
    protected Class<ExtensionTag> type() {
        return ExtensionTag.class;
    }

    @Override
    protected ExtensionTag createDefault() {
        return ExtensionTag.builder().value(1).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}