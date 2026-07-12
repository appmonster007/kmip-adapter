package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3_0.type.DeactivationMessage;

import java.io.IOException;

public class DeactivationMessageXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DeactivationMessage, DeactivationMessage.DeactivationMessageBuilder> {

    public DeactivationMessageXmlDeserializer() {
        super(DeactivationMessage.kmipTag, DeactivationMessage.encodingType);
    }

    @Override
    protected DeactivationMessage.DeactivationMessageBuilder createBuilder() {
        return DeactivationMessage.builder();
    }

    @Override
    protected void setValue(DeactivationMessage.DeactivationMessageBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected DeactivationMessage build(DeactivationMessage.DeactivationMessageBuilder builder) {
        return builder.build();
    }
}
