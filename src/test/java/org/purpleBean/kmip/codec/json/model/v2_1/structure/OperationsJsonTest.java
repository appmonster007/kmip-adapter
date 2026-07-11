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
import org.purpleBean.kmip.model.v2_1.structure.Operations;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Operations Json Serialization Tests")
class OperationsJsonTest extends AbstractJsonSerializationTestSuite<Operations> {

    @Override
    public Class<Operations> type() {
        return Operations.class;
    }

    @Override
    public Operations createDefault() {
        return Operations.builder().build();
    }

    @Override
    public Operations createVariant() {
        return Operations.builder().build();
    }
}