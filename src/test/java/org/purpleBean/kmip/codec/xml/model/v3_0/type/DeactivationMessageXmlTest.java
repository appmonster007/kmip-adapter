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
import org.purpleBean.kmip.model.v3_0.type.DeactivationMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeactivationMessage Xml Serialization Tests")
class DeactivationMessageXmlTest extends AbstractXmlSerializationTestSuite<DeactivationMessage> {

    @Override
    public Class<DeactivationMessage> type() {
        return DeactivationMessage.class;
    }

    @Override
    public DeactivationMessage createDefault() {
        return DeactivationMessage.of("default-string");
    }

    @Override
    public DeactivationMessage createVariant() {
        return DeactivationMessage.of("variant-string");
    }
}