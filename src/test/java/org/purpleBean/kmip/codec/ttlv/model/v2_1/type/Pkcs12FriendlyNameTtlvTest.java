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
import org.purpleBean.kmip.model.v2_1.type.Pkcs12FriendlyName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs12FriendlyName Ttlv Serialization Tests")
class Pkcs12FriendlyNameTtlvTest extends AbstractTtlvSerializationTestSuite<Pkcs12FriendlyName> {

    @Override
    public Class<Pkcs12FriendlyName> type() {
        return Pkcs12FriendlyName.class;
    }

    @Override
    public Pkcs12FriendlyName createDefault() {
        return Pkcs12FriendlyName.of("default-string");
    }

    @Override
    public Pkcs12FriendlyName createVariant() {
        return Pkcs12FriendlyName.of("variant-string");
    }
}