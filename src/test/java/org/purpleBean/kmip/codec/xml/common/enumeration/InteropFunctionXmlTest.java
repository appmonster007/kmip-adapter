package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.InteropFunction;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InteropFunction XML Serialization")
class InteropFunctionXmlTest extends AbstractXmlSerializationTestSuite<InteropFunction> {
    @Override
    protected Class<InteropFunction> type() {
        return InteropFunction.class;
    }

    @Override
    protected InteropFunction createDefault() {
        return InteropFunction.Standard.BEGIN.inst();
    }

    @Override
    protected InteropFunction createVariant() {
        return InteropFunction.Standard.END.inst();
    }
}
