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
import org.purpleBean.kmip.model.v3_0.structure.link.ChildLink;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ChildLink Xml Serialization Tests")
class ChildLinkXmlTest extends AbstractXmlSerializationTestSuite<ChildLink> {

    @Override
    public Class<ChildLink> type() {
        return ChildLink.class;
    }

    @Override
    public ChildLink createDefault() {
        return ChildLink.builder().build();
    }

    @Override
    public ChildLink createVariant() {
        return ChildLink.builder().build();
    }
}