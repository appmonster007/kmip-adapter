package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

public class CryptographicUsageMaskXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskXmlSerializer() {
        super(CryptographicUsageMask::getValue);
    }
}