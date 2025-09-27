package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DestroyDateJsonSerializer extends KmipDataTypeJsonSerializer<DestroyDate> {

    @Override
    public void serialize(DestroyDate destroyDate, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (destroyDate == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!destroyDate.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s", destroyDate.getKmipTag().getDescription(), spec));
        }

        gen.writeStartObject();
        gen.writeObject(destroyDate.getKmipTag());
        gen.writeStringField("type", destroyDate.getEncodingType().getDescription());
        gen.writeObjectField("value", destroyDate.getValue());
        gen.writeEndObject();
    }
}
