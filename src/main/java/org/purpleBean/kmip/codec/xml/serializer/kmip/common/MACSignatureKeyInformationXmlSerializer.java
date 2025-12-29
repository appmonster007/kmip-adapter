package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MACSignatureKeyInformationXmlSerializer extends KmipDataTypeXmlSerializer<MACSignatureKeyInformation> {

    @Override
    public void serialize(MACSignatureKeyInformation mACSignatureKeyInformation, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!mACSignatureKeyInformation.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", mACSignatureKeyInformation.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = mACSignatureKeyInformation.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(mACSignatureKeyInformation);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", mACSignatureKeyInformation.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", mACSignatureKeyInformation.getValue());
        xmlGen.writeEndObject();
    }
}