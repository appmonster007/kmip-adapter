package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DerivationData;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.nio.ByteBuffer;

@DisplayName("DerivationData TTLV Serialization Tests")
class DerivationDataTtlvTest extends AbstractTtlvSerializationSuite<DerivationData> {

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