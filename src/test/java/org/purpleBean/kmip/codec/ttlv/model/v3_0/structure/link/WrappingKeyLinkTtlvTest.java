package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.link;

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
import org.purpleBean.kmip.model.v3_0.structure.link.WrappingKeyLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("WrappingKeyLink Ttlv Serialization Tests")
class WrappingKeyLinkTtlvTest extends AbstractTtlvSerializationTestSuite<WrappingKeyLink> {

    @Override
    public Class<WrappingKeyLink> type() {
        return WrappingKeyLink.class;
    }

    @Override
    public WrappingKeyLink createDefault() {
        return WrappingKeyLink.builder().build();
    }

    @Override
    public WrappingKeyLink createVariant() {
        return WrappingKeyLink.builder().build();
    }
}