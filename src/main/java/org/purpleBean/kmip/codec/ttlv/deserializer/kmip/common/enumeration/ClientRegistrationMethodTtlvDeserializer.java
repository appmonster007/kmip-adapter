package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * TTLV deserializer for ClientRegistrationMethod.
 */
public class ClientRegistrationMethodTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ClientRegistrationMethod> {
    private final KmipTag kmipTag = ClientRegistrationMethod.kmipTag;
    private final EncodingType encodingType = ClientRegistrationMethod.encodingType;

    @Override
    public ClientRegistrationMethod deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for ClientRegistrationMethod", encodingType.getTypeValue()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int value = bb.getInt();

        KmipSpec spec = KmipContext.getSpec();
        ClientRegistrationMethod clientregistrationmethod = new ClientRegistrationMethod(ClientRegistrationMethod.fromValue(value));

        if (!clientregistrationmethod.isSupported()) {
            throw new NoSuchElementException(
                String.format("ClientRegistrationMethod '%d' not supported for spec %s", value, spec));
        }
        return clientregistrationmethod;
    }
}
