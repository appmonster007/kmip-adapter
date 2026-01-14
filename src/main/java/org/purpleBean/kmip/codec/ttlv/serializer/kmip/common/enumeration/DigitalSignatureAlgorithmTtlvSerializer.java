package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DigitalSignatureAlgorithm, Integer> {

    public DigitalSignatureAlgorithmTtlvSerializer() {
        super(DigitalSignatureAlgorithm::getValue);
    }
}