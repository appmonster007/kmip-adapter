package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionXmlSerializer() {
        super(BatchOrderOption::getValue);
    }
}