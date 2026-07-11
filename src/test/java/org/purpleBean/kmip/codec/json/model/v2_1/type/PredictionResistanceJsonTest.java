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
import org.purpleBean.kmip.model.v2_1.type.PredictionResistance;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PredictionResistance Json Serialization Tests")
class PredictionResistanceJsonTest extends AbstractJsonSerializationTestSuite<PredictionResistance> {

    @Override
    public Class<PredictionResistance> type() {
        return PredictionResistance.class;
    }

    @Override
    public PredictionResistance createDefault() {
        return PredictionResistance.of(true);
    }

    @Override
    public PredictionResistance createVariant() {
        return PredictionResistance.of(false);
    }
}