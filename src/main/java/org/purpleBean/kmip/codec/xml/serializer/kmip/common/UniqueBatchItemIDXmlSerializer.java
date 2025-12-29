package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UniqueBatchItemIDXmlSerializer extends KmipDataTypeXmlSerializer<UniqueBatchItemID> {

    @Override
    public void serialize(UniqueBatchItemID uniqueBatchItemID, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!uniqueBatchItemID.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", uniqueBatchItemID.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = uniqueBatchItemID.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(uniqueBatchItemID);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", uniqueBatchItemID.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", uniqueBatchItemID.getValue());
        xmlGen.writeEndObject();
    }
}