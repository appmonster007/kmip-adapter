package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ExtensionTag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ExtensionTagJsonSerializer extends KmipDataTypeJsonSerializer<ExtensionTag> {

    @Override
    public void serialize(ExtensionTag extensionTag, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (extensionTag == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!extensionTag.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", extensionTag.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(extensionTag.getKmipTag());
        gen.writeStringField("type", extensionTag.getEncodingType().getDescription());
        gen.writeObjectField("value", extensionTag.getValue());
        gen.writeEndObject();
    }
}