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
import org.purpleBean.kmip.model.v2_1.type.Pkcs11InputParameters;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs11InputParameters Ttlv Serialization Tests")
class Pkcs11InputParametersTtlvTest extends AbstractTtlvSerializationTestSuite<Pkcs11InputParameters> {

    @Override
    public Class<Pkcs11InputParameters> type() {
        return Pkcs11InputParameters.class;
    }

    @Override
    public Pkcs11InputParameters createDefault() {
        return Pkcs11InputParameters.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public Pkcs11InputParameters createVariant() {
        return Pkcs11InputParameters.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}