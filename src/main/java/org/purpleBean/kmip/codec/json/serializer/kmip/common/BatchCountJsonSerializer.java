package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.BatchCount;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class BatchCountJsonSerializer extends KmipDataTypeJsonSerializer<BatchCount> {

    @Override
    public void serialize(BatchCount batchCount, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (batchCount == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!batchCount.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", batchCount.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(batchCount.getKmipTag());
        gen.writeStringField("type", batchCount.getEncodingType().getDescription());
        gen.writeObjectField("value", batchCount.getValue());
        gen.writeEndObject();
    }
}