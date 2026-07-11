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
import org.purpleBean.kmip.model.core.type.InteropIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InteropIdentifier Ttlv Serialization Tests")
class InteropIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<InteropIdentifier> {

    @Override
    public Class<InteropIdentifier> type() {
        return InteropIdentifier.class;
    }

    @Override
    public InteropIdentifier createDefault() {
        return InteropIdentifier.of("default-string");
    }

    @Override
    public InteropIdentifier createVariant() {
        return InteropIdentifier.of("variant-string");
    }
}