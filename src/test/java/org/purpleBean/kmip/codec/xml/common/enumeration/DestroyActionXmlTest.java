package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DestroyAction;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("DestroyAction XML Serialization")
class DestroyActionXmlTest extends AbstractXmlSerializationSuite<DestroyAction> {
    @Override
    protected Class<DestroyAction> type() {
        return DestroyAction.class;
    }

    @Override
    protected DestroyAction createDefault() {
        return new DestroyAction(DestroyAction.Standard.UNSPECIFIED);
    }

    @Override
    protected DestroyAction createVariant() {
        return new DestroyAction(DestroyAction.Standard.KEY_MATERIAL_DELETED);
    }
}
