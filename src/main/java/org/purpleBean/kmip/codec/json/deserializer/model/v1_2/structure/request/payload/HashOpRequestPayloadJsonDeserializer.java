package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.HashOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

import java.io.IOException;

public class HashOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<HashOpRequestPayload, HashOpRequestPayload.HashOpRequestPayloadBuilder> {

    public HashOpRequestPayloadJsonDeserializer() {
        super(HashOpRequestPayload.kmipTag, HashOpRequestPayload.encodingType);
    }

    @Override
    protected HashOpRequestPayload.HashOpRequestPayloadBuilder createBuilder() {
        return HashOpRequestPayload.builder();
    }

    @Override
    protected void setValue(HashOpRequestPayload.HashOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
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
    protected HashOpRequestPayload build(HashOpRequestPayload.HashOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
