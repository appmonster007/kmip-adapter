package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;

public class BatchOrderOptionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionXmlDeserializer() {
        super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType, Boolean.class, value -> BatchOrderOption.builder().value(value).build());
    }
}