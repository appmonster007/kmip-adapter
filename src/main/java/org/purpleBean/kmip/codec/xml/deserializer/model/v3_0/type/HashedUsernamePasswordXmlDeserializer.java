package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3_0.type.HashedUsernamePassword;

import java.io.IOException;
import java.nio.ByteBuffer;

public class HashedUsernamePasswordXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<HashedUsernamePassword, HashedUsernamePassword.HashedUsernamePasswordBuilder> {

    public HashedUsernamePasswordXmlDeserializer() {
        super(HashedUsernamePassword.kmipTag, HashedUsernamePassword.encodingType);
    }

    @Override
    protected HashedUsernamePassword.HashedUsernamePasswordBuilder createBuilder() {
        return HashedUsernamePassword.builder();
    }

    @Override
    protected void setValue(HashedUsernamePassword.HashedUsernamePasswordBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected HashedUsernamePassword build(HashedUsernamePassword.HashedUsernamePasswordBuilder builder) {
        return builder.build();
    }
}
