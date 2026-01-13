package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.Key;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransparentSymmetricKey Domain Tests")
class TransparentSymmetricKeyTest extends AbstractKmipStructureTestSuite<TransparentSymmetricKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentSymmetricKey> type() {
        return TransparentSymmetricKey.class;
    }

    @Override
    protected TransparentSymmetricKey createDefault() {
        Key key = Key.of(new byte[]{0x01, 0x02, 0x03});
        return TransparentSymmetricKey.of(key);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 1;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values.get(0).getEncodingType()).isEqualTo(EncodingType.BYTE_STRING);
    }
}