package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.Fresh;

public class FreshXmlSerializer extends AbstractKmipXmlSerializer<Fresh, Boolean> {

    public FreshXmlSerializer() {
        super(Fresh::getValue);
    }
}