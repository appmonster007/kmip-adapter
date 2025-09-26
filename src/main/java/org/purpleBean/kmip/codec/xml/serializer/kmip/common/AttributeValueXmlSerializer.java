package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.structure.Attribute;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * XML serializer for AttributeValue.
 */
public class AttributeValueXmlSerializer extends KmipDataTypeXmlSerializer<Attribute.AttributeValue> {

    @Override
    public void serialize(Attribute.AttributeValue value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = value.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(value);

        ObjectMapper mapper = (ObjectMapper) gen.getCodec();
        String attrValue_ = mapper.writeValueAsString(value.getValue());
        JsonNode inner = mapper.readTree(attrValue_);

        JsonNode extractedType = inner.has("type") ? inner.get("type") : inner;

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeFieldName("type");
        mapper.writeTree(xmlGen, extractedType);

        JsonNode extractedValue = inner.has("value") ? inner.get("value") : inner;

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeFieldName("value");
        mapper.writeTree(xmlGen, extractedValue);

        xmlGen.writeEndObject();
    }
}
