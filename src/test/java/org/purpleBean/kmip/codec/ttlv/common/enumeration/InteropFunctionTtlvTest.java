package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.InteropFunction;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("InteropFunction TTLV Serialization")
class InteropFunctionTtlvTest extends AbstractTtlvSerializationSuite<InteropFunction> {
    @Override
    protected Class<InteropFunction> type() {
        return InteropFunction.class;
    }

    @Override
    protected InteropFunction createDefault() {
        return new InteropFunction(InteropFunction.Standard.BEGIN);
    }

    @Override
    protected InteropFunction createVariant() {
        return new InteropFunction(InteropFunction.Standard.END);
    }
}
