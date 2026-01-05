package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.KeyValueLocation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class KeyValueLocationJsonSerializer extends KmipDataTypeJsonSerializer<KeyValueLocation> {

    @Override
    public void serialize(KeyValueLocation value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (value == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s",
                    value.getKmipTag().getDescription(), spec));
        }

        List<KmipDataType> fields = value.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupported()) {
                throw new UnsupportedEncodingException(String.format("%s in %s is not supported for KMIP spec %s",
                        field.getKmipTag().getDescription(), value.getKmipTag().getDescription(), spec));
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(value.getKmipTag());
        jsonGenerator.writeStringField("type", value.getEncodingType().getDescription());
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeStartArray();
        for (KmipDataType field : fields) {
            if (field != null) {
                jsonGenerator.writeObject(field);
            }
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}