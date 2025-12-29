package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class OpaqueDataValueJsonSerializer extends KmipDataTypeJsonSerializer<OpaqueDataValue> {

    @Override
    public void serialize(OpaqueDataValue opaqueDataValue, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (opaqueDataValue == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!opaqueDataValue.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", opaqueDataValue.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(opaqueDataValue.getKmipTag());
        gen.writeStringField("type", opaqueDataValue.getEncodingType().getDescription());
        gen.writeObjectField("value", opaqueDataValue.getValue());
        gen.writeEndObject();
    }
}