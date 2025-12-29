package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ExtensionName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ExtensionNameJsonSerializer extends KmipDataTypeJsonSerializer<ExtensionName> {

    @Override
    public void serialize(ExtensionName extensionName, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (extensionName == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!extensionName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", extensionName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(extensionName.getKmipTag());
        gen.writeStringField("type", extensionName.getEncodingType().getDescription());
        gen.writeObjectField("value", extensionName.getValue());
        gen.writeEndObject();
    }
}