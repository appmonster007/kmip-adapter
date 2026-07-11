package org.purpleBean.kmip.codec.json.model.core.type;

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
import org.purpleBean.kmip.model.core.type.ProtectionStorageMask;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProtectionStorageMask Json Serialization Tests")
class ProtectionStorageMaskJsonTest extends AbstractJsonSerializationTestSuite<ProtectionStorageMask> {

    @Override
    public Class<ProtectionStorageMask> type() {
        return ProtectionStorageMask.class;
    }

    @Override
    public ProtectionStorageMask createDefault() {
        return ProtectionStorageMask.of(123);
    }

    @Override
    public ProtectionStorageMask createVariant() {
        return ProtectionStorageMask.of(456);
    }
}