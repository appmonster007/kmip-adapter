package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Operation XML Serialization")
class OperationXmlTest extends AbstractXmlSerializationTestSuite<Operation> {
    @Override
    protected Class<Operation> type() {
        return Operation.class;
    }

    @Override
    protected Operation createDefault() {
        return Operation.Standard.CREATE.inst();
    }

    @Override
    protected Operation createVariant() {
        return Operation.Standard.CREATE_KEY_PAIR.inst();
    }
}
