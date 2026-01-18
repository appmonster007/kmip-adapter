package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;

import java.io.IOException;

public class SimpleRequestBatchItemXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SimpleRequestBatchItem, SimpleRequestBatchItem.SimpleRequestBatchItemBuilder> {

    public SimpleRequestBatchItemXmlDeserializer() {
        super(SimpleRequestBatchItem.kmipTag);
    }

    @Override
    protected SimpleRequestBatchItem.SimpleRequestBatchItemBuilder createBuilder() {
        return SimpleRequestBatchItem.builder();
    }

    @Override
    protected void setValue(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        // This structure is a wrapper, the logic is in the parent deserializer
        builder.requestPayloadStructure(ctxt.readValue(p, RequestPayloadStructure.class));
    }

    @Override
    protected SimpleRequestBatchItem build(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder) {
        return builder.build();
    }
}