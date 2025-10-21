package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.List;

public class CryptographicUsageMaskXmlSerializer extends KmipDataTypeXmlSerializer<CryptographicUsageMask> {

    @Override
    public void serialize(CryptographicUsageMask cryptographicUsageMask, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!cryptographicUsageMask.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", cryptographicUsageMask.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = cryptographicUsageMask.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(cryptographicUsageMask);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", cryptographicUsageMask.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", cryptographicUsageMask.getValue());
        xmlGen.writeEndObject();
    }
}
