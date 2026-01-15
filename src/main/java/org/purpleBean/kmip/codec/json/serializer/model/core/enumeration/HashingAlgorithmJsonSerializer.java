package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;

public class HashingAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<HashingAlgorithm, String> {

    public HashingAlgorithmJsonSerializer() {
        super(HashingAlgorithm::getDescription);
    }
}