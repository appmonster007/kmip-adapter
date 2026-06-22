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
import org.purpleBean.kmip.model.v3_0.structure.link.DerivationObjectLink;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DerivationObjectLink Json Serialization Tests")
class DerivationObjectLinkJsonTest extends AbstractJsonSerializationTestSuite<DerivationObjectLink> {

    @Override
    public Class<DerivationObjectLink> type() {
        return DerivationObjectLink.class;
    }

    @Override
    public DerivationObjectLink createDefault() {
        return DerivationObjectLink.of(UniqueIdentifier.of("test-id"));
    }

    @Override
    public DerivationObjectLink createVariant() {
        return DerivationObjectLink.of(UniqueIdentifier.of("test-id"));
    }
}