package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

public class CryptographicUsageMaskXmlSerializer extends AbstractKmipXmlSerializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskXmlSerializer() {
        super(CryptographicUsageMask::getValue);
    }
}