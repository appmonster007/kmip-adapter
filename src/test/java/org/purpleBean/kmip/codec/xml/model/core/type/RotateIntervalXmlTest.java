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
import org.purpleBean.kmip.model.core.type.RotateInterval;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RotateInterval Xml Serialization Tests")
class RotateIntervalXmlTest extends AbstractXmlSerializationTestSuite<RotateInterval> {

    @Override
    public Class<RotateInterval> type() {
        return RotateInterval.class;
    }

    @Override
    public RotateInterval createDefault() {
        return RotateInterval.of(12345L);
    }

    @Override
    public RotateInterval createVariant() {
        return RotateInterval.of(54321L);
    }
}