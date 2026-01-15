package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.InteropFunction;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("InteropFunction JSON Serialization")
class InteropFunctionJsonTest extends AbstractJsonSerializationTestSuite<InteropFunction> {
    @Override
    protected Class<InteropFunction> type() {
        return InteropFunction.class;
    }

    @Override
    protected InteropFunction createDefault() {
        return InteropFunction.Standard.BEGIN.inst();
    }

    @Override
    protected InteropFunction createVariant() {
        return InteropFunction.Standard.END.inst();
    }
}
