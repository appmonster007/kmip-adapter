package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.common.ActivationDateAttribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ActivationDateAttributeXmlSerializer extends JsonSerializer<ActivationDateAttribute> {

    @Override
    public void serialize(ActivationDateAttribute activationDateAttribute, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!activationDateAttribute.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        xmlGen.writeStartObject(activationDateAttribute.getKmipTag().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", activationDateAttribute.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("value", activationDateAttribute.getDateTime().toString());
        xmlGen.writeEndObject();
    }
}


