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
import org.purpleBean.kmip.model.v2_1.type.ValidationCertificateUri;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationCertificateUri Ttlv Serialization Tests")
class ValidationCertificateUriTtlvTest extends AbstractTtlvSerializationTestSuite<ValidationCertificateUri> {

    @Override
    public Class<ValidationCertificateUri> type() {
        return ValidationCertificateUri.class;
    }

    @Override
    public ValidationCertificateUri createDefault() {
        return ValidationCertificateUri.of("default-string");
    }

    @Override
    public ValidationCertificateUri createVariant() {
        return ValidationCertificateUri.of("variant-string");
    }
}