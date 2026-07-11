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
import org.purpleBean.kmip.model.core.type.RotateAutomatic;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RotateAutomatic Xml Serialization Tests")
class RotateAutomaticXmlTest extends AbstractXmlSerializationTestSuite<RotateAutomatic> {

    @Override
    public Class<RotateAutomatic> type() {
        return RotateAutomatic.class;
    }

    @Override
    public RotateAutomatic createDefault() {
        return RotateAutomatic.of(true);
    }

    @Override
    public RotateAutomatic createVariant() {
        return RotateAutomatic.of(false);
    }
}