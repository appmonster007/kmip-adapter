package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DestroyAction;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("DestroyAction TTLV Serialization")
class DestroyActionTtlvTest extends AbstractTtlvSerializationSuite<DestroyAction> {
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
