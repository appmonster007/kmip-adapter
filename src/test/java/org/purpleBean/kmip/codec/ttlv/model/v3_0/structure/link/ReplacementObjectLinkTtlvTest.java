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
import org.purpleBean.kmip.model.v3_0.structure.link.ReplacementObjectLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReplacementObjectLink Ttlv Serialization Tests")
class ReplacementObjectLinkTtlvTest extends AbstractTtlvSerializationTestSuite<ReplacementObjectLink> {

    @Override
    public Class<ReplacementObjectLink> type() {
        return ReplacementObjectLink.class;
    }

    @Override
    public ReplacementObjectLink createDefault() {
        return ReplacementObjectLink.builder().build();
    }

    @Override
    public ReplacementObjectLink createVariant() {
        return ReplacementObjectLink.builder().build();
    }
}