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
import org.purpleBean.kmip.model.v2_1.type.AlwaysSensitive;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AlwaysSensitive Xml Serialization Tests")
class AlwaysSensitiveXmlTest extends AbstractXmlSerializationTestSuite<AlwaysSensitive> {

    @Override
    public Class<AlwaysSensitive> type() {
        return AlwaysSensitive.class;
    }

    @Override
    public AlwaysSensitive createDefault() {
        return AlwaysSensitive.of(true);
    }

    @Override
    public AlwaysSensitive createVariant() {
        return AlwaysSensitive.of(false);
    }
}