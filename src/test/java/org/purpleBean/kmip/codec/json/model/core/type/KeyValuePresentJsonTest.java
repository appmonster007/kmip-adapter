package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.KeyValuePresent;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyValuePresent Json Serialization Tests")
class KeyValuePresentJsonTest extends AbstractJsonSerializationTestSuite<KeyValuePresent> {

    @Override
    protected Class<KeyValuePresent> type() {
        return KeyValuePresent.class;
    }

    @Override
    protected KeyValuePresent createDefault() {
        return KeyValuePresent.of(Boolean.FALSE);
    }

    @Override
    protected KeyValuePresent createVariant() {
        return KeyValuePresent.of(Boolean.TRUE);
    }
}