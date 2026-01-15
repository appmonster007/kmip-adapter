package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DestroyAction JSON Serialization")
class DestroyActionJsonTest extends AbstractJsonSerializationTestSuite<DestroyAction> {
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
