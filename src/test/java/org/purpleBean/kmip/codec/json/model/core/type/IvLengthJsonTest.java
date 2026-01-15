package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IvLength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("IvLength JSON Serialization Tests")
class IvLengthJsonTest extends AbstractJsonSerializationTestSuite<IvLength> {

    @Override
    protected Class<IvLength> type() {
        return IvLength.class;
    }

    @Override
    protected IvLength createDefault() {
        return IvLength.of(128);
    }

    @Override
    protected IvLength createVariant() {
        return IvLength.of(256);
    }
}