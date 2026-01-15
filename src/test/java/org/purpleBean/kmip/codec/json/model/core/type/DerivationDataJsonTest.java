package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DerivationData JSON Serialization Tests")
class DerivationDataJsonTest extends AbstractJsonSerializationTestSuite<DerivationData> {

    @Override
    protected Class<DerivationData> type() {
        return DerivationData.class;
    }

    @Override
    protected DerivationData createDefault() {
        return DerivationData.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected DerivationData createVariant() {
        return DerivationData.of(new byte[]{0x04, 0x05, 0x06});
    }
}