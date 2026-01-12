package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.Fresh;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class FreshXmlSerializer extends AbstractKmipXmlSerializer<Fresh, Boolean> {

    public FreshXmlSerializer() {
        super(Fresh::getValue);
    }
}