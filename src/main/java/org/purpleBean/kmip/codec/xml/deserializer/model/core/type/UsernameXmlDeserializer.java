package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Username;

import java.io.IOException;

public class UsernameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Username, Username.UsernameBuilder> {

    public UsernameXmlDeserializer() {
        super(Username.kmipTag, Username.encodingType);
    }

    @Override
    protected Username.UsernameBuilder createBuilder() {
        return Username.builder();
    }

    @Override
    protected void setValue(Username.UsernameBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected Username build(Username.UsernameBuilder builder) {
        return builder.build();
    }
}