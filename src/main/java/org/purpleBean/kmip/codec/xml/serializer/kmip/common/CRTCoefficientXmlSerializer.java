package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CRTCoefficientXmlSerializer extends KmipDataTypeXmlSerializer<CRTCoefficient> {

    @Override
    public void serialize(CRTCoefficient cRTCoefficient, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!cRTCoefficient.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", cRTCoefficient.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = cRTCoefficient.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(cRTCoefficient);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", cRTCoefficient.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", cRTCoefficient.getValue());
        xmlGen.writeEndObject();
    }
}