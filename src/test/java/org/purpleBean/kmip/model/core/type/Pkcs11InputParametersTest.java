package org.purpleBean.kmip.model.core.type;

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
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("Pkcs11InputParameters Domain Tests")
class Pkcs11InputParametersTest extends AbstractKmipDataTypeTestSuite<Pkcs11InputParameters> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<Pkcs11InputParameters> type() {
        return Pkcs11InputParameters.class;
    }

    @Override
    protected Pkcs11InputParameters createDefault() {
        return Pkcs11InputParameters.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}