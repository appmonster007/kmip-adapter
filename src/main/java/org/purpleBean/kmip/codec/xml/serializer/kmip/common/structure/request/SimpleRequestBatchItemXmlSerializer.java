package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;

import javax.xml.namespace.QName;
import java.io.IOException;

public class SimpleRequestBatchItemXmlSerializer extends KmipDataTypeXmlSerializer<SimpleRequestBatchItem> {

    @Override
    public void serialize(SimpleRequestBatchItem batchItem,
                          JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = batchItem.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(batchItem);

        // If there were nested fields, they could be serialized here using:
        // serializers.defaultSerializeField(fieldName, fieldValue, gen);

        // Close element
        xmlGen.writeEndObject();
    }
}
