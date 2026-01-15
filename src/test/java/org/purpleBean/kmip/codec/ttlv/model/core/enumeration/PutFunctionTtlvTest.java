package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PutFunction TTLV Serialization")
class PutFunctionTtlvTest extends AbstractTtlvSerializationTestSuite<PutFunction> {
    @Override
    protected Class<PutFunction> type() {
        return PutFunction.class;
    }

    @Override
    protected PutFunction createDefault() {
        return PutFunction.Standard.NEW.inst();
    }

    @Override
    protected PutFunction createVariant() {
        return PutFunction.Standard.REPLACE.inst();
    }
}
