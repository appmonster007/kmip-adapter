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
import org.purpleBean.kmip.model.v3_0.structure.link.PrivateKeyLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PrivateKeyLink Json Serialization Tests")
class PrivateKeyLinkJsonTest extends AbstractJsonSerializationTestSuite<PrivateKeyLink> {

    @Override
    public Class<PrivateKeyLink> type() {
        return PrivateKeyLink.class;
    }

    @Override
    public PrivateKeyLink createDefault() {
        return PrivateKeyLink.builder().build();
    }

    @Override
    public PrivateKeyLink createVariant() {
        return PrivateKeyLink.builder().build();
    }
}