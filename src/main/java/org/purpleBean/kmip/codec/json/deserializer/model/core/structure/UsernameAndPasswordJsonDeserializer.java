package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.UsernameAndPassword;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;

import java.io.IOException;

public class UsernameAndPasswordJsonDeserializer extends AbstractKmipStructureJsonDeserializer<UsernameAndPassword, UsernameAndPassword.UsernameAndPasswordBuilder> {

    public UsernameAndPasswordJsonDeserializer() {
        super(UsernameAndPassword.kmipTag, UsernameAndPassword.encodingType);
    }

    @Override
    protected UsernameAndPassword.UsernameAndPasswordBuilder createBuilder() {
        return UsernameAndPassword.builder();
    }

    @Override
    protected void setValue(UsernameAndPassword.UsernameAndPasswordBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.USERNAME -> builder.username(ctxt.readValue(p, Username.class));
            case KmipTag.Standard.PASSWORD -> builder.password(ctxt.readValue(p, Password.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected UsernameAndPassword build(UsernameAndPassword.UsernameAndPasswordBuilder builder) {
        return builder.build();
    }
}