package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;

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
    }

    @Override
    protected SimpleRequestBatchItem build(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder) {
        return builder.build();
    }
}