package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
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
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", attribute.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        String elementName = attribute.getKmipTag().getDescription();
        ((ToXmlGenerator) gen).setNextName(QName.valueOf(elementName));
        gen.writeStartObject(attribute);

        List<KmipDataType> values = attribute.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                serializers.defaultSerializeField(kmipDataType.getKmipTag().getDescription(), kmipDataType, gen);
            }
        }

        gen.writeEndObject();
    }
}
