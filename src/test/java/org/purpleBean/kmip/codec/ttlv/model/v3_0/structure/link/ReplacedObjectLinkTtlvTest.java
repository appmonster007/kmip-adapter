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
import org.purpleBean.kmip.model.v3_0.structure.link.ReplacedObjectLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReplacedObjectLink Ttlv Serialization Tests")
class ReplacedObjectLinkTtlvTest extends AbstractTtlvSerializationTestSuite<ReplacedObjectLink> {

    @Override
    public Class<ReplacedObjectLink> type() {
        return ReplacedObjectLink.class;
    }

    @Override
    public ReplacedObjectLink createDefault() {
        return ReplacedObjectLink.builder().build();
    }

    @Override
    public ReplacedObjectLink createVariant() {
        return ReplacedObjectLink.builder().build();
    }
}