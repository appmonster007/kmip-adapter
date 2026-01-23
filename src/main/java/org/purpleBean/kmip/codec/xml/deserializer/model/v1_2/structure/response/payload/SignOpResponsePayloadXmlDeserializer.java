package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.SignOpResponsePayload;

import java.io.IOException;

public class SignOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SignOpResponsePayload, SignOpResponsePayload.SignOpResponsePayloadBuilder> {

    public SignOpResponsePayloadXmlDeserializer() {
        super(SignOpResponsePayload.kmipTag);
    }

    @Override
    protected SignOpResponsePayload.SignOpResponsePayloadBuilder createBuilder() {
        return SignOpResponsePayload.builder();
    }

    @Override
    protected void setValue(SignOpResponsePayload.SignOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.SIGNATURE_DATA -> builder.signatureData(ctxt.readValue(p, SignatureData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SignOpResponsePayload build(SignOpResponsePayload.SignOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
