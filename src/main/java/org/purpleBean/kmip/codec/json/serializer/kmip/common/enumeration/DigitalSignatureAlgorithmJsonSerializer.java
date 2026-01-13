package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmJsonSerializer() {
        super(DigitalSignatureAlgorithm::getDescription);
    }
}