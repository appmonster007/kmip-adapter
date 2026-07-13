package org.purpleBean.kmip.codec.json.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerL;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerL Json Serialization Tests")
class CertificateIssuerLJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerL> {

    @Override
    public Class<CertificateIssuerL> type() {
        return CertificateIssuerL.class;
    }

    @Override
    public CertificateIssuerL createDefault() {
        return CertificateIssuerL.of("default-string");
    }

    @Override
    public CertificateIssuerL createVariant() {
        return CertificateIssuerL.of("variant-string");
    }
}