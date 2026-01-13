package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.Fresh;

public class FreshXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Fresh, Boolean> {

    public FreshXmlSerializer() {
        super(Fresh::getValue);
    }
}