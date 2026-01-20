package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CryptographicAlgorithm, String> {

    public CryptographicAlgorithmXmlDeserializer() {
        super(CryptographicAlgorithm.kmipTag, CryptographicAlgorithm.encodingType, String.class, value -> CryptographicAlgorithm.fromName(value).inst());
    }
}