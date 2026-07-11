package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CommonAttributes;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.Collections;

@DisplayName("CommonAttributes Xml Serialization Tests")
class CommonAttributesXmlTest extends AbstractXmlSerializationTestSuite<CommonAttributes> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<CommonAttributes> type() {
        return CommonAttributes.class;
    }

    @Override
    public CommonAttributes createDefault() {
        return CommonAttributes.of(Collections.emptyList());
    }

    @Override
    public CommonAttributes createVariant() {
        return CommonAttributes.of(Collections.emptyList());
    }
}
