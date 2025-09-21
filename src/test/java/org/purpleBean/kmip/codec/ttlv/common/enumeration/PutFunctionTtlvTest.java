package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.PutFunction;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("PutFunction TTLV Serialization")
class PutFunctionTtlvTest extends AbstractTtlvSerializationSuite<PutFunction> {
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
