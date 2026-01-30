package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;

import java.io.IOException;

public class ProfileNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProfileName, ProfileName.ProfileNameBuilder> {

    public ProfileNameJsonDeserializer() {
        super(ProfileName.kmipTag, ProfileName.encodingType);
    }

    @Override
    protected ProfileName.ProfileNameBuilder createBuilder() {
        return ProfileName.builder();
    }

    @Override
    protected void setValue(ProfileName.ProfileNameBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ProfileName.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ProfileName build(ProfileName.ProfileNameBuilder builder) {
        return builder.build();
    }
}
