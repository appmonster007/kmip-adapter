package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.StorageStatusMask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class StorageStatusMaskJsonSerializer extends KmipDataTypeJsonSerializer<StorageStatusMask> {

    @Override
    public void serialize(StorageStatusMask storageStatusMask, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (storageStatusMask == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!storageStatusMask.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", storageStatusMask.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(storageStatusMask.getKmipTag());
        gen.writeStringField("type", storageStatusMask.getEncodingType().getDescription());
        gen.writeObjectField("value", storageStatusMask.getValue());
        gen.writeEndObject();
    }
}