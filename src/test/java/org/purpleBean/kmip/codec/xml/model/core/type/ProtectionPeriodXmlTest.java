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
import org.purpleBean.kmip.model.core.type.ProtectionPeriod;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProtectionPeriod Xml Serialization Tests")
class ProtectionPeriodXmlTest extends AbstractXmlSerializationTestSuite<ProtectionPeriod> {

    @Override
    public Class<ProtectionPeriod> type() {
        return ProtectionPeriod.class;
    }

    @Override
    public ProtectionPeriod createDefault() {
        return ProtectionPeriod.of(12345L);
    }

    @Override
    public ProtectionPeriod createVariant() {
        return ProtectionPeriod.of(54321L);
    }
}