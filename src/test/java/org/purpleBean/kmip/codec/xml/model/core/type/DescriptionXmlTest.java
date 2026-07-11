package org.purpleBean.kmip.codec.xml.model.core.type;

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
import org.purpleBean.kmip.model.core.type.Description;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Description Xml Serialization Tests")
class DescriptionXmlTest extends AbstractXmlSerializationTestSuite<Description> {

    @Override
    public Class<Description> type() {
        return Description.class;
    }

    @Override
    public Description createDefault() {
        return Description.of("default-string");
    }

    @Override
    public Description createVariant() {
        return Description.of("variant-string");
    }
}