package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MacData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MacDataJsonSerializer extends KmipDataTypeJsonSerializer<MacData> {

    @Override
    public void serialize(MacData macData, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (macData == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!macData.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", macData.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(macData.getKmipTag());
        gen.writeStringField("type", macData.getEncodingType().getDescription());
        gen.writeObjectField("value", macData.getValue());
        gen.writeEndObject();
    }
}
