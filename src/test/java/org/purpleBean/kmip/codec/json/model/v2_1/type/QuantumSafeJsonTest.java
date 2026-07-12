package org.purpleBean.kmip.codec.json.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.QuantumSafe;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("QuantumSafe Json Serialization Tests")
class QuantumSafeJsonTest extends AbstractJsonSerializationTestSuite<QuantumSafe> {

    @Override
    public Class<QuantumSafe> type() {
        return QuantumSafe.class;
    }

    @Override
    public QuantumSafe createDefault() {
        return QuantumSafe.of(true);
    }

    @Override
    public QuantumSafe createVariant() {
        return QuantumSafe.of(false);
    }
}