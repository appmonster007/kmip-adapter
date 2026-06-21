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
import org.purpleBean.kmip.model.v3_0.structure.link.DerivedObjectLink;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DerivedObjectLink Xml Serialization Tests")
class DerivedObjectLinkXmlTest extends AbstractXmlSerializationTestSuite<DerivedObjectLink> {

    @Override
    public Class<DerivedObjectLink> type() {
        return DerivedObjectLink.class;
    }

    @Override
    public DerivedObjectLink createDefault() {
        return DerivedObjectLink.builder().build();
    }

    @Override
    public DerivedObjectLink createVariant() {
        return DerivedObjectLink.builder().build();
    }
}