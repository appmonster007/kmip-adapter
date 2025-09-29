package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValue;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * XML serializer for AttributeValue.
 */
public class AttributeValueXmlSerializer extends KmipDataTypeXmlSerializer<AttributeValue> {

    @Override
    public void serialize(AttributeValue attributeValue, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValue.isSupported()) {
            throw new UnsupportedEncodingException();
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = attributeValue.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(attributeValue);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", attributeValue.getEncodingType().getDescription());

        if (attributeValue.getEncodingType() == EncodingType.STRUCTURE) {
            xmlGen.writeFieldName("value");
            List<?> fields = attributeValue.getValues();
            xmlGen.writeStartArray();
            for (Object fieldValue : fields) {
                if (fieldValue != null) {
                    xmlGen.writeObject(fieldValue);
                }
            }
            xmlGen.writeEndArray();
        } else {
            xmlGen.setNextIsAttribute(true);
            xmlGen.writeFieldName("value");
            xmlGen.writeObject(attributeValue.getValue());
        }
        xmlGen.writeEndObject();
    }
}
