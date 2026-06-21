package org.purpleBean.kmip.codec.json.model.v3_0.structure.link;

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
import org.purpleBean.kmip.model.v3_0.structure.link.CertificateLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateLink Json Serialization Tests")
class CertificateLinkJsonTest extends AbstractJsonSerializationTestSuite<CertificateLink> {

    @Override
    public Class<CertificateLink> type() {
        return CertificateLink.class;
    }

    @Override
    public CertificateLink createDefault() {
        return CertificateLink.builder().build();
    }

    @Override
    public CertificateLink createVariant() {
        return CertificateLink.builder().build();
    }
}