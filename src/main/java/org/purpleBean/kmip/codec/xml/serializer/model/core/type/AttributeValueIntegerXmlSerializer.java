package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueIntegerXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerXmlSerializer() {
        super(AttributeValueInteger::getValue);
    }

    @Override
    public void serialize(AttributeValueInteger value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
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

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeFieldName("value");
        serializers.defaultSerializeValue(
                value.getMaskStringValue() == null ? value.getValue() : value.getMaskStringValue(),
                gen
        );

        xmlGen.writeEndObject();
    }
}