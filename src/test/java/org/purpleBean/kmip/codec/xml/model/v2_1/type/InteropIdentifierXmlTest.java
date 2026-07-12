package org.purpleBean.kmip.codec.xml.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.InteropIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InteropIdentifier Xml Serialization Tests")
class InteropIdentifierXmlTest extends AbstractXmlSerializationTestSuite<InteropIdentifier> {

    @Override
    public Class<InteropIdentifier> type() {
        return InteropIdentifier.class;
    }

    @Override
    public InteropIdentifier createDefault() {
        return InteropIdentifier.of("default-string");
    }

    @Override
    public InteropIdentifier createVariant() {
        return InteropIdentifier.of("variant-string");
    }
}