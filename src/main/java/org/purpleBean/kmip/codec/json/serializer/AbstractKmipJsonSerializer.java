package org.purpleBean.kmip.codec.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.function.Function;

public abstract class AbstractKmipJsonSerializer<T extends KmipDataType, V> extends KmipDataTypeJsonSerializer<T> {

    private final Function<T, V> valueExtractor;

    protected AbstractKmipJsonSerializer(Function<T, V> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: Null check
        if (value == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", value.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
//        gen.writeObjectField("tag", value.getKmipTag().getDescription());
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());

        V rawValue = valueExtractor.apply(value);
        gen.writeFieldName("value");
        serializers.defaultSerializeValue(rawValue, gen);

        gen.writeEndObject();
    }
}