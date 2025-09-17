package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;

import javax.xml.namespace.QName;
import java.io.IOException;

public class SimpleRequestHeaderXmlSerializer extends KmipDataTypeXmlSerializer<SimpleRequestHeader> {

    @Override
    public void serialize(SimpleRequestHeader header,
                          JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = header.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(header);

        // Serialize nested ProtocolVersion using its registered serializer
        serializers.defaultSerializeField(
                header.getProtocolVersion().getClass().getSimpleName(),
                header.getProtocolVersion(),
                gen
        );

        // End element
        xmlGen.writeEndObject();
    }
}
