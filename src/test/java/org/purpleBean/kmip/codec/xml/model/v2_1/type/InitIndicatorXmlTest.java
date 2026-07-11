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
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InitIndicator Xml Serialization Tests")
class InitIndicatorXmlTest extends AbstractXmlSerializationTestSuite<InitIndicator> {

    @Override
    public Class<InitIndicator> type() {
        return InitIndicator.class;
    }

    @Override
    public InitIndicator createDefault() {
        return InitIndicator.of(true);
    }

    @Override
    public InitIndicator createVariant() {
        return InitIndicator.of(false);
    }
}