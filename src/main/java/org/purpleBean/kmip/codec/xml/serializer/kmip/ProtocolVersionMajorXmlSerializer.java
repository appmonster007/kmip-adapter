package org.purpleBean.kmip.codec.xml.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.KmipContext;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ProtocolVersionMajorXmlSerializer extends JsonSerializer<ProtocolVersion.ProtocolVersionMajor> {

    @Override
    public void serialize(ProtocolVersion.ProtocolVersionMajor protocolVersionMajor, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!protocolVersionMajor.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = protocolVersionMajor.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(protocolVersionMajor);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", protocolVersionMajor.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("value", String.valueOf(protocolVersionMajor.getValue()));
        xmlGen.writeEndObject();
    }
}


