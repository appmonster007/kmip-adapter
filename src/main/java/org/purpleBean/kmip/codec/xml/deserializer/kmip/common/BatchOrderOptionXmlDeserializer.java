package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionXmlDeserializer() {
        super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType, Boolean.class, value -> BatchOrderOption.builder().value(value).build());
    }
}