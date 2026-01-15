package org.purpleBean.kmip.codec.xml.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipStructure;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.List;

public class AbstractKmipStructureXmlSerializer<T extends KmipStructure> extends KmipDataTypeXmlSerializer<T> {

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        String elementName = value.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(value);

        List<KmipDataType> values = value.getValues();
        if (values != null) {
            for (KmipDataType kmipDataType : values) {
                if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                    serializers.defaultSerializeField(kmipDataType.getKmipTag().getDescription(), kmipDataType, gen);
                }
            }
        }

        xmlGen.writeEndObject();
    }
}