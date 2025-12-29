package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LastChangeDateJsonSerializer extends KmipDataTypeJsonSerializer<LastChangeDate> {

    @Override
    public void serialize(LastChangeDate lastChangeDate, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (lastChangeDate == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!lastChangeDate.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", lastChangeDate.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(lastChangeDate.getKmipTag());
        gen.writeStringField("type", lastChangeDate.getEncodingType().getDescription());
        gen.writeObjectField("value", lastChangeDate.getValue());
        gen.writeEndObject();
    }
}
