package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;

import java.io.IOException;

public class SimpleRequestBatchItemXmlSerializer extends JsonSerializer<SimpleRequestBatchItem> {

    @Override
    public void serialize(SimpleRequestBatchItem item,
                          JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = item.getKmipTag().getDescription();
        xmlGen.writeStartObject(elementName);

        // If there were nested fields, they could be serialized here using:
        // serializers.defaultSerializeField(fieldName, fieldValue, gen);

        // Close element
        xmlGen.writeEndObject();
    }
}
