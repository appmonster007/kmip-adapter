package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DestroyAction XML Serialization")
class DestroyActionXmlTest extends AbstractXmlSerializationTestSuite<DestroyAction> {
    @Override
    public Class<DestroyAction> type() {
        return DestroyAction.class;
    }

    @Override
    public DestroyAction createDefault() {
        return DestroyAction.Standard.UNSPECIFIED.inst();
    }

    @Override
    public DestroyAction createVariant() {
        return DestroyAction.Standard.KEY_MATERIAL_DELETED.inst();
    }
}
