package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ApplicationNamespaceJsonSerializer extends KmipDataTypeJsonSerializer<ApplicationNamespace> {

    @Override
    public void serialize(ApplicationNamespace applicationNamespace, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (applicationNamespace == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!applicationNamespace.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", applicationNamespace.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(applicationNamespace.getKmipTag());
        gen.writeStringField("type", applicationNamespace.getEncodingType().getDescription());
        gen.writeObjectField("value", applicationNamespace.getValue());
        gen.writeEndObject();
    }
}