package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionXmlSerializer extends AbstractKmipXmlSerializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionXmlSerializer() {
        super(BatchOrderOption::getValue);
    }
}