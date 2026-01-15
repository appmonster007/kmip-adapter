package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;
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
