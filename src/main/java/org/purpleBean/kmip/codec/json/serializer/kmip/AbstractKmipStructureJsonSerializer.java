package org.purpleBean.kmip.codec.json.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class AbstractKmipStructureJsonSerializer<T extends KmipStructure> extends KmipDataTypeJsonSerializer<T> {

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            return;
        }

        KmipSpec spec = KmipContext.getSpec();
        List<KmipDataType> fields = value.getValues();
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupported()) {
                throw new UnsupportedEncodingException(String.format("%s in %s is not supported for KMIP spec %s",
                        field.getKmipTag().getDescription(), value.getKmipTag().getDescription(), spec));
            }
        }

        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s", value.getKmipTag().getDescription(), spec));
        }

        gen.writeStartObject();
//        gen.writeObjectField("tag", value.getKmipTag().getDescription());
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());

        List<KmipDataType> values = value.getValues();
        if (values != null) {
            gen.writeFieldName("value");
            gen.writeStartArray();
            for (KmipDataType kmipDataType : values) {
                if (kmipDataType != null) {
                    serializers.defaultSerializeValue(kmipDataType, gen);
                }
            }
            gen.writeEndArray();
        }

        gen.writeEndObject();
    }
}