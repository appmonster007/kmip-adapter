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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerSt;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerSt Json Serialization Tests")
class CertificateIssuerStJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerSt> {

    @Override
    public Class<CertificateIssuerSt> type() {
        return CertificateIssuerSt.class;
    }

    @Override
    public CertificateIssuerSt createDefault() {
        return CertificateIssuerSt.of("default-string");
    }

    @Override
    public CertificateIssuerSt createVariant() {
        return CertificateIssuerSt.of("variant-string");
    }
}