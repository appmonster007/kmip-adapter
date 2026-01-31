package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.RevocationMessage;

import java.io.IOException;

public class RevocationMessageXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RevocationMessage, RevocationMessage.RevocationMessageBuilder> {

    public RevocationMessageXmlDeserializer() {
        super(RevocationMessage.kmipTag, RevocationMessage.encodingType);
    }

    @Override
    protected RevocationMessage.RevocationMessageBuilder createBuilder() {
        return RevocationMessage.builder();
    }

    @Override
    protected void setValue(RevocationMessage.RevocationMessageBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected RevocationMessage build(RevocationMessage.RevocationMessageBuilder builder) {
        return builder.build();
    }
}