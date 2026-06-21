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
import org.purpleBean.kmip.model.v3_0.structure.link.DerivationObjectLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DerivationObjectLink Ttlv Serialization Tests")
class DerivationObjectLinkTtlvTest extends AbstractTtlvSerializationTestSuite<DerivationObjectLink> {

    @Override
    public Class<DerivationObjectLink> type() {
        return DerivationObjectLink.class;
    }

    @Override
    public DerivationObjectLink createDefault() {
        return DerivationObjectLink.builder().build();
    }

    @Override
    public DerivationObjectLink createVariant() {
        return DerivationObjectLink.builder().build();
    }
}