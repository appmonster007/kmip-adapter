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
import org.purpleBean.kmip.model.v2_1.type.NeverExtractable;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NeverExtractable Ttlv Serialization Tests")
class NeverExtractableTtlvTest extends AbstractTtlvSerializationTestSuite<NeverExtractable> {

    @Override
    public Class<NeverExtractable> type() {
        return NeverExtractable.class;
    }

    @Override
    public NeverExtractable createDefault() {
        return NeverExtractable.of(true);
    }

    @Override
    public NeverExtractable createVariant() {
        return NeverExtractable.of(false);
    }
}