package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.PutFunction;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PutFunction JSON Serialization")
class PutFunctionJsonTest extends AbstractJsonSerializationTestSuite<PutFunction> {
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
