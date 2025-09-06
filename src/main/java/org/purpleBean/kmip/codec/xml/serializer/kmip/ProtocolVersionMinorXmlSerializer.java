package org.purpleBean.kmip.codec.xml.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.KmipCodecContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ProtocolVersionMinorXmlSerializer extends JsonSerializer<ProtocolVersion.ProtocolVersionMinor> {

    @Override
    public void serialize(ProtocolVersion.ProtocolVersionMinor protocolVersionMinor, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!protocolVersionMinor.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        String elementName = protocolVersionMinor.getKmipTag().getDescription();

        xmlGen.writeStartObject(elementName);
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", protocolVersionMinor.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("value", String.valueOf(protocolVersionMinor.getValue()));
        xmlGen.writeEndObject();
    }
}


