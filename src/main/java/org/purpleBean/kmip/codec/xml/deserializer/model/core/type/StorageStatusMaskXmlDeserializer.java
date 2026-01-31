package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;

import java.io.IOException;

public class StorageStatusMaskXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<StorageStatusMask, StorageStatusMask.StorageStatusMaskBuilder> {

    public StorageStatusMaskXmlDeserializer() {
        super(StorageStatusMask.kmipTag, StorageStatusMask.encodingType);
    }

    @Override
    protected StorageStatusMask.StorageStatusMaskBuilder createBuilder() {
        return StorageStatusMask.builder();
    }

    @Override
    protected void setValue(StorageStatusMask.StorageStatusMaskBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        String maskString = ctxt.readValue(p, String.class);
        StorageStatusMask mask = StorageStatusMask.fromMaskString(maskString);
        builder.value(mask.getValue());
    }

    @Override
    protected StorageStatusMask build(StorageStatusMask.StorageStatusMaskBuilder builder) {
        return builder.build();
    }
}