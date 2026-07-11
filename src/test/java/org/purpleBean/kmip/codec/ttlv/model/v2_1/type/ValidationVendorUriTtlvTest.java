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
import org.purpleBean.kmip.model.v2_1.type.ValidationVendorUri;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationVendorUri Ttlv Serialization Tests")
class ValidationVendorUriTtlvTest extends AbstractTtlvSerializationTestSuite<ValidationVendorUri> {

    @Override
    public Class<ValidationVendorUri> type() {
        return ValidationVendorUri.class;
    }

    @Override
    public ValidationVendorUri createDefault() {
        return ValidationVendorUri.of("default-string");
    }

    @Override
    public ValidationVendorUri createVariant() {
        return ValidationVendorUri.of("variant-string");
    }
}