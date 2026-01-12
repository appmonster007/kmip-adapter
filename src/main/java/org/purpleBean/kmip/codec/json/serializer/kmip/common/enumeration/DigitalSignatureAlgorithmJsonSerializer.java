package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmJsonSerializer extends AbstractKmipJsonSerializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmJsonSerializer() {
        super(DigitalSignatureAlgorithm::getDescription);
    }
}