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
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectO;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectO Ttlv Serialization Tests")
class CertificateSubjectOTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateSubjectO> {

    @Override
    public Class<CertificateSubjectO> type() {
        return CertificateSubjectO.class;
    }

    @Override
    public CertificateSubjectO createDefault() {
        return CertificateSubjectO.of("default-string");
    }

    @Override
    public CertificateSubjectO createVariant() {
        return CertificateSubjectO.of("variant-string");
    }
}