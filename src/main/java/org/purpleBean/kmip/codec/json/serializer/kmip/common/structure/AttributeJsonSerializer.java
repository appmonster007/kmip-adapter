package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class AttributeJsonSerializer extends KmipDataTypeJsonSerializer<Attribute> {

    @Override
    public void serialize(Attribute attribute, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (attribute == null) {
            return;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s", attribute.getKmipTag().getDescription(), spec));
        }

        List<KmipDataType> fields = attribute.getValues();
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(String.format("%s in %s is not supported for KMIP spec %s",
                        field.getKmipTag().getDescription(), attribute.getKmipTag().getDescription(), spec));
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(attribute.getKmipTag());
        jsonGenerator.writeStringField("type", attribute.getEncodingType().getDescription());
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeStartArray();
        for (KmipDataType fieldValue : fields) {
            if (fieldValue != null) {
                jsonGenerator.writeObject(fieldValue);
            }
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
