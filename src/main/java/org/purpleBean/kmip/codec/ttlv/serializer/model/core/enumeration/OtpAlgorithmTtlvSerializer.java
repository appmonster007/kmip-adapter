package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.OtpAlgorithm;

public class OtpAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OtpAlgorithm, Integer> {

    public OtpAlgorithmTtlvSerializer() {
        super(OtpAlgorithm::getValue);
    }
}