package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMajor;

import java.io.IOException;

public class ProfileVersionMajorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProfileVersionMajor, ProfileVersionMajor.ProfileVersionMajorBuilder> {

    public ProfileVersionMajorXmlDeserializer() {
        super(ProfileVersionMajor.kmipTag, ProfileVersionMajor.encodingType);
    }

    @Override
    protected ProfileVersionMajor.ProfileVersionMajorBuilder createBuilder() {
        return ProfileVersionMajor.builder();
    }

    @Override
    protected void setValue(ProfileVersionMajor.ProfileVersionMajorBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected ProfileVersionMajor build(ProfileVersionMajor.ProfileVersionMajorBuilder builder) {
        return builder.build();
    }
}