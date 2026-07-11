package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RngParameters Ttlv Serialization Tests")
class RngParametersTtlvTest extends AbstractTtlvSerializationTestSuite<RngParameters> {

    @Override
    public Class<RngParameters> type() {
        return RngParameters.class;
    }

    @Override
    public RngParameters createDefault() {
        return RngParameters.builder().build();
    }

    @Override
    public RngParameters createVariant() {
        return RngParameters.builder().build();
    }
}