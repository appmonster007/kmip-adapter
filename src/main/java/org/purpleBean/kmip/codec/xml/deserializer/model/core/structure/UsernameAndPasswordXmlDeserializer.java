package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.UsernameAndPassword;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;

import java.io.IOException;

public class UsernameAndPasswordXmlDeserializer extends AbstractKmipStructureXmlDeserializer<UsernameAndPassword, UsernameAndPassword.UsernameAndPasswordBuilder> {

    public UsernameAndPasswordXmlDeserializer() {
        super(UsernameAndPassword.kmipTag);
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