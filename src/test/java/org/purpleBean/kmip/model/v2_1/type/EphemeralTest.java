package org.purpleBean.kmip.model.v2_1.type;

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
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("Ephemeral Domain Tests")
class EphemeralTest extends AbstractKmipDataTypeTestSuite<Ephemeral> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<Ephemeral> type() {
        return Ephemeral.class;
    }

    @Override
    protected Ephemeral createDefault() {
        return Ephemeral.of(true);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BOOLEAN;
    }
}