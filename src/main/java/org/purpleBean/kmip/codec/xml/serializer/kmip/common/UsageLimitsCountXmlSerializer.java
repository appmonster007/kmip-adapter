package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.UsageLimitsCount;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UsageLimitsCountXmlSerializer extends KmipDataTypeXmlSerializer<UsageLimitsCount> {

    @Override
    public void serialize(UsageLimitsCount usageLimitsCount, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!usageLimitsCount.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", usageLimitsCount.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = usageLimitsCount.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(usageLimitsCount);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", usageLimitsCount.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", usageLimitsCount.getValue());
        xmlGen.writeEndObject();
    }
}