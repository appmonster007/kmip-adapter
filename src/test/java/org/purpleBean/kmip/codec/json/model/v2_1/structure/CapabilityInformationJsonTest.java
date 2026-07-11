package org.purpleBean.kmip.codec.json.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.CapabilityInformation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CapabilityInformation Json Serialization Tests")
class CapabilityInformationJsonTest extends AbstractJsonSerializationTestSuite<CapabilityInformation> {

    @Override
    public Class<CapabilityInformation> type() {
        return CapabilityInformation.class;
    }

    @Override
    public CapabilityInformation createDefault() {
        return CapabilityInformation.builder().build();
    }

    @Override
    public CapabilityInformation createVariant() {
        return CapabilityInformation.builder().build();
    }
}