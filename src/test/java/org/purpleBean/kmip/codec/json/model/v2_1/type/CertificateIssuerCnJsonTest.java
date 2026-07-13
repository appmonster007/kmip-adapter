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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerCn;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerCn Json Serialization Tests")
class CertificateIssuerCnJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerCn> {

    @Override
    public Class<CertificateIssuerCn> type() {
        return CertificateIssuerCn.class;
    }

    @Override
    public CertificateIssuerCn createDefault() {
        return CertificateIssuerCn.of("default-string");
    }

    @Override
    public CertificateIssuerCn createVariant() {
        return CertificateIssuerCn.of("variant-string");
    }
}