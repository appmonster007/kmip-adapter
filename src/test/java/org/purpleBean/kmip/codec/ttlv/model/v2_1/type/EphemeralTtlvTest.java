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
import org.purpleBean.kmip.model.v2_1.type.Ephemeral;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Ephemeral Ttlv Serialization Tests")
class EphemeralTtlvTest extends AbstractTtlvSerializationTestSuite<Ephemeral> {

    @Override
    public Class<Ephemeral> type() {
        return Ephemeral.class;
    }

    @Override
    public Ephemeral createDefault() {
        return Ephemeral.of(true);
    }

    @Override
    public Ephemeral createVariant() {
        return Ephemeral.of(false);
    }
}