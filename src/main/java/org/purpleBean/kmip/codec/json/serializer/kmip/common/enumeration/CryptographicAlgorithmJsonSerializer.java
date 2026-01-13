package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CryptographicAlgorithm, String> {

    public CryptographicAlgorithmJsonSerializer() {
        super(CryptographicAlgorithm::getDescription);
    }
}