package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.InteropFunction;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("InteropFunction JSON Serialization")
class InteropFunctionJsonTest extends AbstractJsonSerializationTestSuite<InteropFunction> {
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
