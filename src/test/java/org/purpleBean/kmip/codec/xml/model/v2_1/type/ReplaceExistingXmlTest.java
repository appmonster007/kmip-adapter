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
import org.purpleBean.kmip.model.v2_1.type.ReplaceExisting;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReplaceExisting Xml Serialization Tests")
class ReplaceExistingXmlTest extends AbstractXmlSerializationTestSuite<ReplaceExisting> {

    @Override
    public Class<ReplaceExisting> type() {
        return ReplaceExisting.class;
    }

    @Override
    public ReplaceExisting createDefault() {
        return ReplaceExisting.of(true);
    }

    @Override
    public ReplaceExisting createVariant() {
        return ReplaceExisting.of(false);
    }
}