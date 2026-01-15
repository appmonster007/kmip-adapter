package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Fresh;

public class FreshXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Fresh, Boolean> {

    public FreshXmlSerializer() {
        super(Fresh::getValue);
    }
}