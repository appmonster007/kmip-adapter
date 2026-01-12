package org.purpleBean.kmip.codec.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.function.Function;

public abstract class AbstractKmipXmlSerializer<T extends KmipDataType, V> extends KmipDataTypeXmlSerializer<T> {

    private final Function<T, V> valueExtractor;

    protected AbstractKmipXmlSerializer(Function<T, V> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", value.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        xmlGen.setNextName(new QName(value.getKmipTag().getDescription()));
        xmlGen.writeStartObject();

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", value.getEncodingType().getDescription());

        V rawValue = valueExtractor.apply(value);
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeFieldName("value");
        serializers.defaultSerializeValue(rawValue, gen);

        xmlGen.writeEndObject();
    }
}