package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.PutFunction;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PutFunction XML Serialization")
class PutFunctionXmlTest extends AbstractXmlSerializationTestSuite<PutFunction> {
    @Override
    protected Class<PutFunction> type() {
        return PutFunction.class;
    }

    @Override
    protected PutFunction createDefault() {
        return new PutFunction(PutFunction.Standard.NEW);
    }

    @Override
    protected PutFunction createVariant() {
        return new PutFunction(PutFunction.Standard.REPLACE);
    }
}
