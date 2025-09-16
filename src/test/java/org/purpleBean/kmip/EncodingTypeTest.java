package org.purpleBean.kmip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("EncodingType basic invariants")
class EncodingTypeTest {

    @Test
    @DisplayName("fromTypeValue finds known types and isValidTypeValue matches")
    void fromTypeValue_and_isValid() {
        for (EncodingType t : EncodingType.values()) {
            Optional<EncodingType> found = EncodingType.fromTypeValue(t.getTypeValue());
            assertThat(found).contains(t);
            assertThat(EncodingType.isValidTypeValue(t.getTypeValue())).isTrue();
        }
        assertThat(EncodingType.fromTypeValue((byte) 0x7F)).isEmpty();
        assertThat(EncodingType.isValidTypeValue((byte) 0x7F)).isFalse();
    }

    @Test
    @DisplayName("fromName works with enum name and description; toString returns description")
    void fromName_and_toString() {
        assertThat(EncodingType.fromName("STRUCTURE")).contains(EncodingType.STRUCTURE);
        assertThat(EncodingType.fromName("Structure")).contains(EncodingType.STRUCTURE);
        assertThat(EncodingType.STRUCTURE.toString()).isEqualTo("Structure");
    }

    @Test
    @DisplayName("isFixedLength matches rawByteSize semantics")
    void isFixedLength_semantics() {
        assertThat(EncodingType.INTEGER.isFixedLength()).isTrue();
        assertThat(EncodingType.DATE_TIME.isFixedLength()).isTrue();
        assertThat(EncodingType.STRUCTURE.isFixedLength()).isFalse();
        assertThat(EncodingType.TEXT_STRING.isFixedLength()).isFalse();
    }
}
