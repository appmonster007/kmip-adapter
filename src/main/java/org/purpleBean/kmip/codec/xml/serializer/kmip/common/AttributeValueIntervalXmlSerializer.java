package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueInterval;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueIntervalXmlSerializer extends KmipDataTypeXmlSerializer<AttributeValueInterval> {

    @Override
    public void serialize(AttributeValueInterval attributeValueInterval, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueInterval.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", attributeValueInterval.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = attributeValueInterval.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(attributeValueInterval);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", attributeValueInterval.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", attributeValueInterval.getValue());
        xmlGen.writeEndObject();
    }
}
