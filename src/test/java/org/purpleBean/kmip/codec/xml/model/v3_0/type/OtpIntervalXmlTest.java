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
import org.purpleBean.kmip.model.v3_0.type.OtpInterval;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OtpInterval Xml Serialization Tests")
class OtpIntervalXmlTest extends AbstractXmlSerializationTestSuite<OtpInterval> {

    @Override
    public Class<OtpInterval> type() {
        return OtpInterval.class;
    }

    @Override
    public OtpInterval createDefault() {
        return OtpInterval.of(123);
    }

    @Override
    public OtpInterval createVariant() {
        return OtpInterval.of(456);
    }
}