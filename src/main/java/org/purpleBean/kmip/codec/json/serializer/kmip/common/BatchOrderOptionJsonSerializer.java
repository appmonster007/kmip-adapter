package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.BatchOrderOption;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class BatchOrderOptionJsonSerializer extends KmipDataTypeJsonSerializer<BatchOrderOption> {

    @Override
    public void serialize(BatchOrderOption batchOrderOption, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (batchOrderOption == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!batchOrderOption.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", batchOrderOption.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(batchOrderOption.getKmipTag());
        gen.writeStringField("type", batchOrderOption.getEncodingType().getDescription());
        gen.writeObjectField("value", batchOrderOption.getValue());
        gen.writeEndObject();
    }
}