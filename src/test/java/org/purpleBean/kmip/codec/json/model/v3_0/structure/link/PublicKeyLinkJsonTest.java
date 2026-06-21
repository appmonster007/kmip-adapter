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
import org.purpleBean.kmip.model.v3_0.structure.link.PublicKeyLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PublicKeyLink Json Serialization Tests")
class PublicKeyLinkJsonTest extends AbstractJsonSerializationTestSuite<PublicKeyLink> {

    @Override
    public Class<PublicKeyLink> type() {
        return PublicKeyLink.class;
    }

    @Override
    public PublicKeyLink createDefault() {
        return PublicKeyLink.builder().build();
    }

    @Override
    public PublicKeyLink createVariant() {
        return PublicKeyLink.builder().build();
    }
}