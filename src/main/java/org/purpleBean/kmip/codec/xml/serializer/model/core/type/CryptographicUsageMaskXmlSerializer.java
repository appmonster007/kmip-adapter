package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;

public class CryptographicUsageMaskXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskXmlSerializer() {
        super(CryptographicUsageMask::getValue);
    }
}