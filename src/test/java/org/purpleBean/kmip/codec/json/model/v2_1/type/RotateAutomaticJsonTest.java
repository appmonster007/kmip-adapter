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
import org.purpleBean.kmip.model.v2_1.type.RotateAutomatic;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateAutomatic Json Serialization Tests")
class RotateAutomaticJsonTest extends AbstractJsonSerializationTestSuite<RotateAutomatic> {

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