package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("FinalIndicator Ttlv Serialization Tests")
class FinalIndicatorTtlvTest extends AbstractTtlvSerializationTestSuite<FinalIndicator> {

    @Override
    public Class<FinalIndicator> type() {
        return FinalIndicator.class;
    }

    @Override
    public FinalIndicator createDefault() {
        return FinalIndicator.of(true);
    }

    @Override
    public FinalIndicator createVariant() {
        return FinalIndicator.of(false);
    }
}