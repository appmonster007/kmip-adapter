package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.CustomAttribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * JSON serializer for Custom.
 */
public class CustomAttributeJsonSerializer extends KmipDataTypeJsonSerializer<CustomAttribute> {

    @Override
    public void serialize(CustomAttribute attribute, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (attribute == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attribute.getKmipTag().getDescription(), spec)
            );
        }
        List<KmipDataType> fields = attribute.getValues();
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(String.format("%s in %s is not supported for KMIP spec %s",
                        field.getKmipTag().getDescription(), attribute.getKmipTag().getDescription(), spec));
            }
        }

        gen.writeStartObject();
        gen.writeObject(attribute.getKmipTag());
        gen.writeStringField("type", attribute.getEncodingType().getDescription());
        gen.writeFieldName("value");
        gen.writeStartArray();
        for (KmipDataType fieldValue : fields) {
            if (fieldValue != null) {
                gen.writeObject(fieldValue);
            }
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
