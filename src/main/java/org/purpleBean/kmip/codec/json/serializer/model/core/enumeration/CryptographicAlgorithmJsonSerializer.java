package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CryptographicAlgorithm, String> {

    public CryptographicAlgorithmJsonSerializer() {
        super(CryptographicAlgorithm::getDescription);
    }
}