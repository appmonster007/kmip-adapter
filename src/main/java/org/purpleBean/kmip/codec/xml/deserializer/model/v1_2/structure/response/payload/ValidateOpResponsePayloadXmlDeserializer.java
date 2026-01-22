package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ValidateOpResponsePayload;

import java.io.IOException;

public class ValidateOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<ValidateOpResponsePayload, ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder> {

    public ValidateOpResponsePayloadXmlDeserializer() {
        super(ValidateOpResponsePayload.kmipTag);
    }

    @Override
    protected ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder createBuilder() {
        return ValidateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.VALIDITY_INDICATOR)) {
            builder.validityIndicator(ctxt.readValue(p, ValidityIndicator.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ValidateOpResponsePayload build(ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
