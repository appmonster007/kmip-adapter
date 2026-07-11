package org.purpleBean.kmip.codec.ttlv.model.core.type;

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
import org.purpleBean.kmip.model.core.type.Sensitive;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Sensitive Ttlv Serialization Tests")
class SensitiveTtlvTest extends AbstractTtlvSerializationTestSuite<Sensitive> {

    @Override
    public Class<Sensitive> type() {
        return Sensitive.class;
    }

    @Override
    public Sensitive createDefault() {
        return Sensitive.of(true);
    }

    @Override
    public Sensitive createVariant() {
        return Sensitive.of(false);
    }
}