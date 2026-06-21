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
import org.purpleBean.kmip.model.v3_0.structure.link.ReplacedObjectLink;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReplacedObjectLink Xml Serialization Tests")
class ReplacedObjectLinkXmlTest extends AbstractXmlSerializationTestSuite<ReplacedObjectLink> {

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