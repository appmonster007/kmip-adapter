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
import org.purpleBean.kmip.model.v3_0.structure.link.PreviousLink;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PreviousLink Xml Serialization Tests")
class PreviousLinkXmlTest extends AbstractXmlSerializationTestSuite<PreviousLink> {

    @Override
    public Class<PreviousLink> type() {
        return PreviousLink.class;
    }

    @Override
    public PreviousLink createDefault() {
        return PreviousLink.builder().build();
    }

    @Override
    public PreviousLink createVariant() {
        return PreviousLink.builder().build();
    }
}