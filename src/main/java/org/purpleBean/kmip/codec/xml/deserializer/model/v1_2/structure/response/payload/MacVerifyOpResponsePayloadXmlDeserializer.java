package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.MacVerifyOpResponsePayload;

import java.io.IOException;

public class MacVerifyOpResponsePayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MacVerifyOpResponsePayload, MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder> {

    public MacVerifyOpResponsePayloadXmlDeserializer() {
        super(MacVerifyOpResponsePayload.kmipTag, MacVerifyOpResponsePayload.encodingType);
    }

    @Override
    protected MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder createBuilder() {
        return MacVerifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.VALIDITY_INDICATOR ->
                    builder.validityIndicator(ctxt.readValue(p, ValidityIndicator.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected MacVerifyOpResponsePayload build(MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
