package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SplitKeyParts XML Serialization Tests")
class SplitKeyPartsXmlTest extends AbstractXmlSerializationTestSuite<SplitKeyParts> {

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