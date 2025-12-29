package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ApplicationData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ApplicationDataJsonSerializer extends KmipDataTypeJsonSerializer<ApplicationData> {

    @Override
    public void serialize(ApplicationData applicationData, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (applicationData == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!applicationData.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", applicationData.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(applicationData.getKmipTag());
        gen.writeStringField("type", applicationData.getEncodingType().getDescription());
        gen.writeObjectField("value", applicationData.getValue());
        gen.writeEndObject();
    }
}