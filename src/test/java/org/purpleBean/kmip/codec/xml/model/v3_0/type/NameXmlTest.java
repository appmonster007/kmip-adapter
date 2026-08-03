package org.purpleBean.kmip.codec.xml.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.Name;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Name Xml Serialization Tests")
class NameXmlTest extends AbstractXmlSerializationTestSuite<Name> {

    @Override
    public Class<Name> type() {
        return Name.class;
    }

    @Override
    public Name createDefault() {
        return Name.of("default-string");
    }

    @Override
    public Name createVariant() {
        return Name.of("variant-string");
    }
}