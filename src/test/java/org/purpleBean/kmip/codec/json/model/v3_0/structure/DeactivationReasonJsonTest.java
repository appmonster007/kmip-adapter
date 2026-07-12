package org.purpleBean.kmip.codec.json.model.v3_0.structure;

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
import org.purpleBean.kmip.model.v3_0.structure.DeactivationReason;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;
import org.purpleBean.kmip.model.v3_0.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.model.v3_0.type.DeactivationMessage;

@DisplayName("DeactivationReason Json Serialization Tests")
class DeactivationReasonJsonTest extends AbstractJsonSerializationTestSuite<DeactivationReason> {

    @Override
    public Class<DeactivationReason> type() {
        return DeactivationReason.class;
    }

    @Override
    public DeactivationReason createDefault() {
        return DeactivationReason.builder()
                .deactivationReasonCode(DeactivationReasonCode.of(DeactivationReasonCode.Standard.UNSPECIFIED))
                .build();
    }

    @Override
    public DeactivationReason createVariant() {
        return DeactivationReason.builder()
                .deactivationReasonCode(DeactivationReasonCode.of(DeactivationReasonCode.Standard.DEACTIVATION_DATE))
                .deactivationMessage(DeactivationMessage.of("expired"))
                .build();
    }
}