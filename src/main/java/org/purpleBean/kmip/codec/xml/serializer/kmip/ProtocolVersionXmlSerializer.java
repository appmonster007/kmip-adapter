package org.purpleBean.kmip.codec.xml.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ProtocolVersionXmlSerializer extends KmipDataTypeXmlSerializer<ProtocolVersion> {

    @Override
    public void serialize(ProtocolVersion protocolVersion, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!protocolVersion.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", protocolVersion.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = protocolVersion.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(protocolVersion);

        // Serialize all fields
        List<KmipDataType> values = protocolVersion.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                serializers.defaultSerializeField(kmipDataType.getKmipTag().getDescription(), kmipDataType, gen);
            }
        }

        xmlGen.writeEndObject();
    }
}
