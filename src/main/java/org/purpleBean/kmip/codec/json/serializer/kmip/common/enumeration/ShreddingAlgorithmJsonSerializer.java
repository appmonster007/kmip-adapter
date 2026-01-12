package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmJsonSerializer extends AbstractKmipJsonSerializer<ShreddingAlgorithm, String> {

    public ShreddingAlgorithmJsonSerializer() {
        super(ShreddingAlgorithm::getDescription);
    }
}