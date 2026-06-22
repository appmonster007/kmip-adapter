package org.purpleBean.kmip.codec.xml.model.v3_0.structure.link;

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
import org.purpleBean.kmip.model.v3_0.structure.link.ParentLink;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ParentLink Xml Serialization Tests")
class ParentLinkXmlTest extends AbstractXmlSerializationTestSuite<ParentLink> {

    @Override
    public Class<ParentLink> type() {
        return ParentLink.class;
    }

    @Override
    public ParentLink createDefault() {
        return ParentLink.of(UniqueIdentifier.of("test-id"));
    }

    @Override
    public ParentLink createVariant() {
        return ParentLink.of(UniqueIdentifier.of("test-id"));
    }
}