package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.BatchOrderOption;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for BatchOrderOption.
 */
public class BatchOrderOptionJsonSerializer extends KmipDataTypeJsonSerializer<BatchOrderOption> {

    @Override
    public void serialize(BatchOrderOption value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (value == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("BatchOrderOption is not supported for KMIP spec %s", spec)
            );
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(value.getKmipTag());
        jsonGenerator.writeStringField("type", value.getEncodingType().getDescription());
        jsonGenerator.writeObjectField("value", value.getValue().toString());
        jsonGenerator.writeEndObject();
    }
}
