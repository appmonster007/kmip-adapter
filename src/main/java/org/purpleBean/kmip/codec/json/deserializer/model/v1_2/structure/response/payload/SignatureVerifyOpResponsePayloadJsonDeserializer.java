package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.SignatureVerifyOpResponsePayload;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;

import java.io.IOException;

public class SignatureVerifyOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SignatureVerifyOpResponsePayload, SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder> {

    public SignatureVerifyOpResponsePayloadJsonDeserializer() {
        super(SignatureVerifyOpResponsePayload.kmipTag, SignatureVerifyOpResponsePayload.encodingType);
    }

    @Override
    protected SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder createBuilder() {
        return SignatureVerifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.VALIDITY_INDICATOR ->
                    builder.validityIndicator(ctxt.readValue(p, ValidityIndicator.class));
            case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
            case KmipTag.Standard.CORRELATION_VALUE -> builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SignatureVerifyOpResponsePayload build(SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
