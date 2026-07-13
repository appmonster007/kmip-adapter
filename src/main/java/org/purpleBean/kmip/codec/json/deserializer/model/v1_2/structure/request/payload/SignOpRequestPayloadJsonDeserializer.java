package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.SignOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

import java.io.IOException;

public class SignOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SignOpRequestPayload, SignOpRequestPayload.SignOpRequestPayloadBuilder> {

    public SignOpRequestPayloadJsonDeserializer() {
        super(SignOpRequestPayload.kmipTag, SignOpRequestPayload.encodingType);
    }

    @Override
    protected SignOpRequestPayload.SignOpRequestPayloadBuilder createBuilder() {
        return SignOpRequestPayload.builder();
    }

    @Override
    protected void setValue(SignOpRequestPayload.SignOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
            case KmipTag.Standard.INIT_INDICATOR -> builder.initIndicator(ctxt.readValue(p, InitIndicator.class));
            case KmipTag.Standard.FINAL_INDICATOR -> builder.finalIndicator(ctxt.readValue(p, FinalIndicator.class));
            case KmipTag.Standard.CORRELATION_VALUE -> builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SignOpRequestPayload build(SignOpRequestPayload.SignOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
