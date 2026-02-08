package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.InteropFunction;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InteropFunction XML Serialization")
class InteropFunctionXmlTest extends AbstractXmlSerializationTestSuite<InteropFunction> {
    @Override
    public Class<InteropFunction> type() {
        return InteropFunction.class;
    }

    @Override
    public InteropFunction createDefault() {
        return InteropFunction.Standard.BEGIN.inst();
    }

    @Override
    public InteropFunction createVariant() {
        return InteropFunction.Standard.END.inst();
    }
}
