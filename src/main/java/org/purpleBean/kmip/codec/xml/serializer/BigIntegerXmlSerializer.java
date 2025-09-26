package org.purpleBean.kmip.codec.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HexFormat;

public class BigIntegerXmlSerializer extends JsonSerializer<BigInteger> {
    @Override
    public void serialize(BigInteger value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        HexFormat hexFormat = HexFormat.of();
        // Convert byte[] to hex string or any other string format instead of base64
        byte[] valueBytes = value.toByteArray();
        int paddedLength = TtlvConstants.calculatePaddedLength(valueBytes.length);
        ByteBuffer buffer = ByteBuffer.allocate(paddedLength);
        int paddingNeeded = paddedLength - valueBytes.length;
        if (paddingNeeded > 0) {
            byte[] padding = new byte[paddingNeeded];
            Arrays.fill(padding, TtlvConstants.PADDING_BYTE);
            buffer.put(padding);
        }
        buffer.put(valueBytes);

        gen.writeString(hexFormat.formatHex(buffer.array()));
    }
}