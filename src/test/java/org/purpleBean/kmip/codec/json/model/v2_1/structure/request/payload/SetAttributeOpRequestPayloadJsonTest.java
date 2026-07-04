package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetAttributeOpRequestPayload Json Serialization Tests")
class SetAttributeOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<SetAttributeOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<SetAttributeOpRequestPayload> type() {
        return SetAttributeOpRequestPayload.class;
    }

    @Override
    public SetAttributeOpRequestPayload createDefault() {
        return SetAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("set-attr-uid-1").build())
                .newAttribute(NewAttribute.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
                .build();
    }

    @Override
    public SetAttributeOpRequestPayload createVariant() {
        return SetAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("set-attr-uid-2").build())
                .newAttribute(NewAttribute.builder().attribute(CryptographicAlgorithm.Standard.RSA.inst()).build())
                .build();
    }
}
