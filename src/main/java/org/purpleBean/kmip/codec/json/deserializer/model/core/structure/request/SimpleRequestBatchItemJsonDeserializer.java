package org.purpleBean.kmip.codec.json.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;

import java.io.IOException;

public class SimpleRequestBatchItemJsonDeserializer extends AbstractKmipStructureJsonDeserializer<SimpleRequestBatchItem, SimpleRequestBatchItem.SimpleRequestBatchItemBuilder> {

    public SimpleRequestBatchItemJsonDeserializer() {
        super(SimpleRequestBatchItem.kmipTag, SimpleRequestBatchItem.encodingType);
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