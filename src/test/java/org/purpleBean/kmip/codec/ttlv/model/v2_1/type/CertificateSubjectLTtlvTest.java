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
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectL;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectL Ttlv Serialization Tests")
class CertificateSubjectLTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateSubjectL> {

    @Override
    public Class<CertificateSubjectL> type() {
        return CertificateSubjectL.class;
    }

    @Override
    public CertificateSubjectL createDefault() {
        return CertificateSubjectL.of("default-string");
    }

    @Override
    public CertificateSubjectL createVariant() {
        return CertificateSubjectL.of("variant-string");
    }
}