package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CryptographicAlgorithm, String> {

    public CryptographicAlgorithmXmlSerializer() {
        super(CryptographicAlgorithm::getDescription);
    }
}