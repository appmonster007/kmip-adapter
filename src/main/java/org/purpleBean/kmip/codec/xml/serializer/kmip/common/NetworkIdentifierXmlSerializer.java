package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class NetworkIdentifierXmlSerializer extends KmipDataTypeXmlSerializer<NetworkIdentifier> {

    @Override
    public void serialize(NetworkIdentifier networkIdentifier, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!networkIdentifier.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", networkIdentifier.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = networkIdentifier.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(networkIdentifier);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", networkIdentifier.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", networkIdentifier.getValue());
        xmlGen.writeEndObject();
    }
}