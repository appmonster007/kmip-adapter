package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DestroyAction TTLV Serialization")
class DestroyActionTtlvTest extends AbstractTtlvSerializationTestSuite<DestroyAction> {
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
