package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DigitalSignatureAlgorithm, Integer> {

    public DigitalSignatureAlgorithmTtlvSerializer() {
        super(DigitalSignatureAlgorithm::getValue);
    }
}