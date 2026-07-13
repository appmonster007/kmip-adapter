package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SignatureVerifyOpResponsePayload;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SignatureVerifyOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SignatureVerifyOpResponsePayload, SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder> {

    public SignatureVerifyOpResponsePayloadTtlvDeserializer() {
        super(SignatureVerifyOpResponsePayload.kmipTag, SignatureVerifyOpResponsePayload.encodingType);
    }

    @Override
    protected SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder createBuilder() {
        return SignatureVerifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.VALIDITY_INDICATOR -> builder.validityIndicator(mapper.readValue(p, ValidityIndicator.class));
            case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
            case KmipTag.Standard.CORRELATION_VALUE -> builder.correlationValue(mapper.readValue(p, CorrelationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SignatureVerifyOpResponsePayload build(SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
