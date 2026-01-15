package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DestroyAction;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DestroyAction XML Serialization")
class DestroyActionXmlTest extends AbstractXmlSerializationTestSuite<DestroyAction> {
    @Override
    protected Class<DestroyAction> type() {
        return DestroyAction.class;
    }

    @Override
    protected DestroyAction createDefault() {
        return DestroyAction.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected DestroyAction createVariant() {
        return DestroyAction.Standard.KEY_MATERIAL_DELETED.inst();
    }
}
