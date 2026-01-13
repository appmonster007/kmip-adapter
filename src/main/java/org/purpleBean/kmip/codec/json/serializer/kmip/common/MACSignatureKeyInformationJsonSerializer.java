package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationJsonSerializer() {
        super(MACSignatureKeyInformation::getValue);
    }
}