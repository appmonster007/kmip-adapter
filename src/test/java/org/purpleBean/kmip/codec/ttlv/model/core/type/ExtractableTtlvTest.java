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
import org.purpleBean.kmip.model.core.type.Extractable;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Extractable Ttlv Serialization Tests")
class ExtractableTtlvTest extends AbstractTtlvSerializationTestSuite<Extractable> {

    @Override
    public Class<Extractable> type() {
        return Extractable.class;
    }

    @Override
    public Extractable createDefault() {
        return Extractable.of(true);
    }

    @Override
    public Extractable createVariant() {
        return Extractable.of(false);
    }
}