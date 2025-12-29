package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SplitKeyParts;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("SplitKeyParts XML Serialization Tests")
class SplitKeyPartsXmlTest extends AbstractXmlSerializationSuite<SplitKeyParts> {

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