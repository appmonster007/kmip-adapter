package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueInteger;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueIntegerXmlSerializer extends KmipDataTypeXmlSerializer<AttributeValueInteger> {

    @Override
    public void serialize(AttributeValueInteger attributeValueInteger, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueInteger.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", attributeValueInteger.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = attributeValueInteger.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(attributeValueInteger);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", attributeValueInteger.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", attributeValueInteger.getValue());
        xmlGen.writeEndObject();
    }
}
