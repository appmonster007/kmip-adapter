package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.structure.Attribute;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class AttributeXmlSerializer extends KmipDataTypeXmlSerializer<Attribute> {

    @Override
    public void serialize(Attribute attribute, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = attribute.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(attribute);

        // Serialize all fields
        List<KmipDataType> values = attribute.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                String fieldName = kmipDataType.getKmipTag().getDescription();
                serializers.defaultSerializeField(fieldName, kmipDataType, gen);
            }
        }

        xmlGen.writeEndObject();
    }
}
