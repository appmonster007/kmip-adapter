package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DerivationData;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("DerivationData JSON Serialization Tests")
class DerivationDataJsonTest extends AbstractJsonSerializationSuite<DerivationData> {

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