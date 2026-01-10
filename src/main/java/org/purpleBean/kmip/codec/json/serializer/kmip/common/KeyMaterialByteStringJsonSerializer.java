package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class KeyMaterialByteStringJsonSerializer extends KmipDataTypeJsonSerializer<KeyMaterialByteString> {

    @Override
    public void serialize(KeyMaterialByteString keyMaterialByteString, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (keyMaterialByteString == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!keyMaterialByteString.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", keyMaterialByteString.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(keyMaterialByteString.getKmipTag());
        gen.writeStringField("type", keyMaterialByteString.getEncodingType().getDescription());
        gen.writeObjectField("value", keyMaterialByteString.getValue());
        gen.writeEndObject();
    }
}