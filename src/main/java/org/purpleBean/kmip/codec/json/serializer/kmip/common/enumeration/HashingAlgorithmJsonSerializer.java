package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

public class HashingAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<HashingAlgorithm, String> {

    public HashingAlgorithmJsonSerializer() {
        super(HashingAlgorithm::getDescription);
    }
}