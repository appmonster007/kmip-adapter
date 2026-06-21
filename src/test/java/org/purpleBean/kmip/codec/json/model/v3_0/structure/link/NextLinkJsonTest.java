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
import org.purpleBean.kmip.model.v3_0.structure.link.NextLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("NextLink Json Serialization Tests")
class NextLinkJsonTest extends AbstractJsonSerializationTestSuite<NextLink> {

    @Override
    public Class<NextLink> type() {
        return NextLink.class;
    }

    @Override
    public NextLink createDefault() {
        return NextLink.builder().build();
    }

    @Override
    public NextLink createVariant() {
        return NextLink.builder().build();
    }
}