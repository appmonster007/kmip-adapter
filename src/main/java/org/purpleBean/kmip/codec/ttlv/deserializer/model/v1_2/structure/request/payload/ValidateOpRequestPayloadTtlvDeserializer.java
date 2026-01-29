package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.ValidityDate;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ValidateOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ValidateOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidateOpRequestPayload, ValidateOpRequestPayload.ValidateOpRequestPayloadBuilder> {

    public ValidateOpRequestPayloadTtlvDeserializer() {
        super(ValidateOpRequestPayload.kmipTag, ValidateOpRequestPayload.encodingType);
    }

    @Override
    protected ValidateOpRequestPayload.ValidateOpRequestPayloadBuilder createBuilder() {
        return ValidateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ValidateOpRequestPayload.ValidateOpRequestPayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE -> builder.certificate(mapper.readValue(p, Certificate.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.VALIDITY_DATE -> builder.validityDate(mapper.readValue(p, ValidityDate.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ValidateOpRequestPayload build(ValidateOpRequestPayload.ValidateOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
