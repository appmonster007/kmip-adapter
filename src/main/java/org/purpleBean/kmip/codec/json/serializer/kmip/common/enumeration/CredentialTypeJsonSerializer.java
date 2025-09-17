package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CredentialTypeJsonSerializer extends KmipDataTypeJsonSerializer<CredentialType> {
    @Override
    public void serialize(CredentialType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) return;
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                String.format("%s '%s' is not supported for KMIP spec %s",
                    value.getKmipTag().getDescription(), value.getDescription(), spec));
        }
        gen.writeStartObject();
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeStringField("value", value.getDescription());
        gen.writeEndObject();
    }
}
