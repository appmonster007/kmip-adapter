package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CryptographicAlgorithm, String> {

    public CryptographicAlgorithmJsonDeserializer() {
        super(CryptographicAlgorithm.kmipTag, CryptographicAlgorithm.encodingType, String.class, value -> new CryptographicAlgorithm(CryptographicAlgorithm.fromName(value)));
    }
}