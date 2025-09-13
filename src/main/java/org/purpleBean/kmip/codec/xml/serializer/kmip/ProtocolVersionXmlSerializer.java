package org.purpleBean.kmip.codec.xml.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.KmipCodecContext;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ProtocolVersionXmlSerializer extends JsonSerializer<ProtocolVersion> {

    @Override
    public void serialize(ProtocolVersion protocolVersion, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!protocolVersion.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = protocolVersion.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(protocolVersion);

        ProtocolVersion.ProtocolVersionMajor protocolVersionMajor = protocolVersion.getProtocolVersionMajor();
        String protocolVersionMajor_ = protocolVersion.getProtocolVersionMajor().getKmipTag().getDescription();
        ProtocolVersion.ProtocolVersionMinor protocolVersionMinor = protocolVersion.getProtocolVersionMinor();
        String protocolVersionMinor_ = protocolVersion.getProtocolVersionMinor().getKmipTag().getDescription();

        provider.defaultSerializeField(protocolVersionMajor_, protocolVersionMajor, xmlGen);
        provider.defaultSerializeField(protocolVersionMinor_, protocolVersionMinor, xmlGen);
        xmlGen.writeEndObject();
    }
}


