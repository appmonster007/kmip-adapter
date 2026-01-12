package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmXmlSerializer extends AbstractKmipXmlSerializer<CryptographicAlgorithm, String> {

    public CryptographicAlgorithmXmlSerializer() {
        super(CryptographicAlgorithm::getDescription);
    }
}