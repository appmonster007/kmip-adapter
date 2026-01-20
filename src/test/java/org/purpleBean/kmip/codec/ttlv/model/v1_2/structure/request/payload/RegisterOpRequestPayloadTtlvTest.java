package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PublicKey;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RegisterOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RegisterOpRequestPayload Ttlv Serialization Tests")
class RegisterOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<RegisterOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<RegisterOpRequestPayload> type() {
        return RegisterOpRequestPayload.class;
    }

    @Override
    protected RegisterOpRequestPayload createDefault() {
        return RegisterOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .templateAttribute(TemplateAttribute.builder().build())
                .object(SymmetricKey.builder()
                        .keyBlock(KeyBlock.builder()
                                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                                .build())
                        .build())
                .build();
    }

    @Override
    protected RegisterOpRequestPayload createVariant() {
        return RegisterOpRequestPayload.builder()
                .objectType(ObjectType.Standard.PUBLIC_KEY.inst())
                .templateAttribute(TemplateAttribute.builder().build())
                .object(PublicKey.builder()
                        .keyBlock(KeyBlock.builder()
                                .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
                                .build())
                        .build())
                .build();
    }
}